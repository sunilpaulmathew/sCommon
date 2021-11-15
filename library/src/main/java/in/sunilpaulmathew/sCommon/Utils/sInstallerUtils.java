package in.sunilpaulmathew.sCommon.Utils;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import in.sunilpaulmathew.sCommon.R;

/*
 * Created by sunilpaulmathew <sunil.kde@gmail.com> on November 13, 2021
 * Based on the original work of nkalra0123 (Ref: https://github.com/nkalra0123/splitapkinstall)
 * & Aefyr (Ref: https://github.com/Aefyr/SAI)
 */
public class sInstallerUtils {

    public static boolean isPermissionDenied(Context context) {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && !context.getPackageManager().canRequestPackageInstalls();
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
    public static int runInstallCreate(sInstallerParams installParams, Context context) {
        return doCreateSession(installParams.mSessionParams, context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private static PackageInstaller getPackageInstaller(Context context) {
        return context.getPackageManager().getPackageInstaller();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static sInstallerParams makeInstallParams(long totalSize) {
        final PackageInstaller.SessionParams sessionParams = new PackageInstaller.SessionParams(
                PackageInstaller.SessionParams.MODE_FULL_INSTALL);
        final sInstallerParams params = new sInstallerParams();
        params.mSessionParams = sessionParams;
        sessionParams.setSize(totalSize);
        return params;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void doCommitSession(int sessionId, Intent callbackIntent, Context context) {
        PackageInstaller.Session session = null;
        try {
            try {
                session = getPackageInstaller(context).openSession(sessionId);
            } catch (IOException ignored) {
            }
            @SuppressLint("UnspecifiedImmutableFlag")
            PendingIntent pendingIntent = PendingIntent.getService(context, 0, callbackIntent, 0);
            assert session != null;
            session.commit(pendingIntent.getIntentSender());
            session.close();
        } finally {
            assert session != null;
            session.close();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private static void doWriteSession(int sessionId, String path, long sizeBytes, String splitName, Context context) {
        PackageInstaller.Session session = null;
        InputStream in = null;
        OutputStream out = null;
        try {
            session = getPackageInstaller(context).openSession(sessionId);
            if (path != null) {
                in = new FileInputStream(path);
            }
            out = session.openWrite(splitName, 0, sizeBytes);
            byte[] buffer = new byte[65536];
            int c;
            assert in != null;
            while ((c = in.read(buffer)) != -1) {
                out.write(buffer, 0, c);
            }
            session.fsync(out);
        } catch (IOException ignored) {
        } finally {
            try {
                assert out != null;
                out.close();
                assert in != null;
                in.close();
                session.close();
            } catch (IOException ignored) {
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void runInstallWrite(long size, int sessionId, String splitName, String path, Context context) {
        long sizeBytes;
        sizeBytes = size;
        doWriteSession(sessionId, path, sizeBytes, splitName, context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void setStatus(int status, Intent intent, Context context) {
        switch (status) {
            case PackageInstaller.STATUS_PENDING_USER_ACTION:
                sUtils.saveString("installationStatus", "waiting", context);
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
                sUtils.saveString("installationStatus", context.getString(R.string.installation_status_success), context);
                break;
            case PackageInstaller.STATUS_FAILURE_ABORTED:
                sUtils.saveString("installationStatus", context.getString(R.string.installation_status_aborted), context);
                break;
            case PackageInstaller.STATUS_FAILURE_BLOCKED:
                sUtils.saveString("installationStatus", context.getString(R.string.installation_status_blocked), context);
                break;
            case PackageInstaller.STATUS_FAILURE_CONFLICT:
                sUtils.saveString("installationStatus", context.getString(R.string.installation_status_conflict), context);
                break;
            case PackageInstaller.STATUS_FAILURE_INCOMPATIBLE:
                sUtils.saveString("installationStatus", context.getString(R.string.installation_status_incompatible), context);
                break;
            case PackageInstaller.STATUS_FAILURE_INVALID:
                sUtils.saveString("installationStatus", context.getString(R.string.installation_status_bad_apks), context);
                break;
            case PackageInstaller.STATUS_FAILURE_STORAGE:
                sUtils.saveString("installationStatus", context.getString(R.string.installation_status_storage), context);
                break;
            default:
                sUtils.saveString("installationStatus", context.getString(R.string.installation_status_failed), context);
                break;
        }
    }

}