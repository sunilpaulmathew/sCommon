package in.sunilpaulmathew.sCommon.InstallerUtils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

import in.sunilpaulmathew.sCommon.CommonUtils.sCommonUtils;

/*
 * Created by sunilpaulmathew <sunil.kde@gmail.com> on November 13, 2021
 * Based on the original work of nkalra0123 (Ref: https://github.com/nkalra0123/splitapkinstall)
 * & Aefyr (Ref: https://github.com/Aefyr/SAI)
 */
public class sInstallerUtils {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static boolean isPermissionDenied(Context context) {
        return !context.getPackageManager().canRequestPackageInstalls();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private static int doCreateSession(PackageInstaller.SessionParams params, Context context) {
        int sessionId = 0 ;
        try {
            sessionId = getPackageInstaller(context).createSession(params);
        } catch (IOException ignored) {
        }
        return sessionId;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static int runInstallCreate(long totalSize, Context context) {
        PackageInstaller.SessionParams mSessionParams = new PackageInstaller.SessionParams(
                PackageInstaller.SessionParams.MODE_FULL_INSTALL);
        mSessionParams.setSize(totalSize);
        return doCreateSession(mSessionParams, context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private static PackageInstaller getPackageInstaller(Context context) {
        return context.getPackageManager().getPackageInstaller();
    }

    @SuppressLint("RequestInstallPackagesPolicy")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void doCommitSession(int sessionId, Intent callbackIntent, Context context) {
        PackageInstaller.Session session = null;
        try {
            try {
                session = getPackageInstaller(context).openSession(sessionId);
            } catch (IOException e) {
                return;
            }
            PendingIntent pendingIntent = PendingIntent.getService(
                    context, 0, callbackIntent,
                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.S ? PendingIntent.FLAG_MUTABLE : 0
            );
            try {
                Objects.requireNonNull(session).commit(pendingIntent.getIntentSender());
            } catch (SecurityException e) {
                sCommonUtils.saveString("installationStatus", "Installation is blocked by secure FRP", context);
                return;
            } catch (Exception e) {
                return;
            }
            session.close();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private static void doWriteSession(int sessionId, String path, long sizeBytes, String splitName, Context context) {
        PackageInstaller.Session session = null;
        FileInputStream in = null;
        OutputStream out = null;
        try {
            session = getPackageInstaller(context).openSession(sessionId);
            if (path != null) {
                in = new FileInputStream(path);
            }
            out = session.openWrite(splitName, 0, sizeBytes);
            byte[] buffer = new byte[65536];
            int c;
            while ((c = Objects.requireNonNull(in).read(buffer)) != -1) {
                out.write(buffer, 0, c);
            }
            session.fsync(out);
        } catch (IOException ignored) {
        } finally {
            try {
                Objects.requireNonNull(out).close();
                Objects.requireNonNull(in).close();
                session.close();
            } catch (IOException ignored) {
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void runInstallWrite(long size, int sessionId, String splitName, String path, Context context) {
        doWriteSession(sessionId, path, size, splitName, context);
    }

    public static void setStatus(int status, Intent intent, Context context) {
        switch (status) {
            case PackageInstaller.STATUS_PENDING_USER_ACTION:
                sCommonUtils.saveString("installationStatus", "waiting", context);
                Intent confirmationIntent = intent.getParcelableExtra(Intent.EXTRA_INTENT);
                if (confirmationIntent != null) {
                    confirmationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    try {
                        context.startActivity(confirmationIntent);
                    } catch (Exception ignored) {
                    }
                }
                break;
            case PackageInstaller.STATUS_SUCCESS:
                sCommonUtils.saveString("installationStatus", context.getString(R.string.installation_status_success), context);
                break;
            case PackageInstaller.STATUS_FAILURE_ABORTED:
                sCommonUtils.saveString("installationStatus", context.getString(R.string.installation_status_aborted), context);
                break;
            case PackageInstaller.STATUS_FAILURE_BLOCKED:
                sCommonUtils.saveString("installationStatus", context.getString(R.string.installation_status_blocked), context);
                break;
            case PackageInstaller.STATUS_FAILURE_CONFLICT:
                sCommonUtils.saveString("installationStatus", context.getString(R.string.installation_status_conflict), context);
                break;
            case PackageInstaller.STATUS_FAILURE_INCOMPATIBLE:
                sCommonUtils.saveString("installationStatus", context.getString(R.string.installation_status_incompatible), context);
                break;
            case PackageInstaller.STATUS_FAILURE_INVALID:
                sCommonUtils.saveString("installationStatus", context.getString(R.string.installation_status_bad_apks), context);
                break;
            case PackageInstaller.STATUS_FAILURE_STORAGE:
                sCommonUtils.saveString("installationStatus", context.getString(R.string.installation_status_storage), context);
                break;
            default:
                sCommonUtils.saveString("installationStatus", context.getString(R.string.installation_status_failed), context);
                break;
        }
    }

}