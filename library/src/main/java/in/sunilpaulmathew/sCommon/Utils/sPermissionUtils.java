package in.sunilpaulmathew.sCommon.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

import java.util.Locale;

import in.sunilpaulmathew.sCommon.R;

/*
 * Created by sunilpaulmathew <sunil.kde@gmail.com> on November 15, 2021
 */
public class sPermissionUtils {

    public static boolean isPermissionDenied(String permission, Context context) {
        return (context.checkCallingOrSelfPermission(permission) != PackageManager.PERMISSION_GRANTED);
    }

    private static String getDescription(String permission, Context context) {
        switch (permission) {
            case "COARSE_LOCATION":
                return context.getString(R.string.permission_coarse_location);
            case "FINE_LOCATION":
            case "ACCESS_FINE_LOCATION":
                return context.getString(R.string.permission_fine_location);
            case "GPS":
                return context.getString(R.string.permission_gps);
            case "REQUEST_INSTALL_PACKAGES":
                return context.getString(R.string.permission_install_packages);
            case "REQUEST_DELETE_PACKAGES":
                return context.getString(R.string.permission_delete_packages);
            case "VIBRATE":
                return context.getString(R.string.permission_vibrate);
            case "READ_CONTACTS":
                return context.getString(R.string.permission_read_contacts);
            case "WRITE_CONTACTS":
                return context.getString(R.string.permission_write_contacts);
            case "READ_CALL_LOG":
                return context.getString(R.string.permission_read_call_log);
            case "WRITE_CALL_LOG":
                return context.getString(R.string.permission_write_call_log);
            case "CALL_PHONE":
                return context.getString(R.string.permission_call_phone);
            case "READ_PHONE_NUMBERS":
                return context.getString(R.string.permission_read_phone_numbers);
            case "OP_READ_PHONE_STATE":
                return context.getString(R.string.permission_read_phone_state);
            case "PROCESS_OUTGOING_CALLS":
                return context.getString(R.string.permission_process_outgoing_calls);
            case "READ_CALENDAR":
                return context.getString(R.string.permission_read_calender);
            case "WRITE_CALENDAR":
                return context.getString(R.string.permission_write_calender);
            case "WIFI_SCAN":
                return context.getString(R.string.permission_wifi_scan);
            case "CHANGE_WIFI_STATE":
                return context.getString(R.string.permission_change_wifi);
            case "ACCESS_NOTIFICATIONS":
                return context.getString(R.string.permission_access_notification);
            case "AUDIO_NOTIFICATION_VOLUME":
                return context.getString(R.string.permission_audio_notification);
            case "POST_NOTIFICATION":
                return context.getString(R.string.permission_post_notification);
            case "NEIGHBORING_CELLS":
                return context.getString(R.string.permission_neighbouring_cells);
            case "READ_SMS":
                return context.getString(R.string.permission_read_sms);
            case "WRITE_SMS":
            case "SEND_SMS":
                return context.getString(R.string.permission_write_sms);
            case "RECEIVE_SMS":
                return context.getString(R.string.permission_receive_sms);
            case "RECEIVE_EMERGENCY_SMS":
                return context.getString(R.string.permission_receive_emergency_sms);
            case "RECEIVE_MMS":
                return context.getString(R.string.permission_receive_mms);
            case "RECEIVE_WAP_PUSH":
                return context.getString(R.string.permission_receive_wap_push);
            case "WRITE_SETTINGS":
                return context.getString(R.string.permission_write_settings);
            case "SYSTEM_ALERT_WINDOW":
                return context.getString(R.string.permission_system_alert_window);
            case "CAMERA":
                return context.getString(R.string.permission_camera);
            case "RECORD_AUDIO":
                return context.getString(R.string.permission_record_audio);
            case "TAKE_AUDIO_FOCUS":
                return context.getString(R.string.permission_take_audio_focus);
            case "AUDIO_RING_VOLUME":
                return context.getString(R.string.permission_ring_volume);
            case "READ_CLIPBOARD":
                return context.getString(R.string.permission_read_clipboard);
            case "WRITE_CLIPBOARD":
                return context.getString(R.string.permission_write_clipboard);
            case "WAKE_LOCK":
                return context.getString(R.string.permission_wake_lock);
            case "GET_USAGE_STATS":
            case "PACKAGE_USAGE_STATS":
                return context.getString(R.string.permission_usage_status);
            case "MUTE_MICROPHONE":
                return context.getString(R.string.permission_mute_microphone);
            case "TOAST_WINDOW":
                return context.getString(R.string.permission_toast_window);
            case "WRITE_WALLPAPER":
                return context.getString(R.string.permission_write_wallpaper);
            case "USE_BIOMETRIC":
                return context.getString(R.string.permission_use_biometric);
            case "USE_FINGERPRINT":
                return context.getString(R.string.permission_use_fingerprint);
            case "BODY_SENSORS":
                return context.getString(R.string.permission_body_sensors);
            case "READ_EXTERNAL_STORAGE":
                return context.getString(R.string.permission_read_storage);
            case "WRITE_EXTERNAL_STORAGE":
                return context.getString(R.string.permission_write_storage);
            case "GET_ACCOUNTS":
                return context.getString(R.string.permission_get_accounts);
            case "RUN_IN_BACKGROUND":
                return context.getString(R.string.permission_run_background);
            case "BIND_ACCESSIBILITY_SERVICE":
                return context.getString(R.string.permission_accessibility_service);
            case "START_FOREGROUND":
                return context.getString(R.string.permission_start_foreground);
            case "BOOT_COMPLETED":
                return context.getString(R.string.permission_boot_completed);
            default:
                return context.getString(R.string.permission_unavailable, permission.toUpperCase(Locale.getDefault()));
        }
    }

    public static void requestPermission(String[] permissions, Activity activity) {
        ActivityCompat.requestPermissions(activity, permissions, 0);
    }

}