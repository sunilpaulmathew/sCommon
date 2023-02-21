package in.sunilpaulmathew.sCommon.PermissionUtils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

import java.util.Locale;

/*
 * Created by sunilpaulmathew <sunil.kde@gmail.com> on November 15, 2021
 */
public class sPermissionUtils {

    public static boolean isPermissionDenied(String permission, Context context) {
        return (context.checkCallingOrSelfPermission(permission) != PackageManager.PERMISSION_GRANTED);
    }

    public static String getDescription(String permission, Context context) {
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
            case "READ_PHONE_STATE":
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
            case "BODY_SENSORS_BACKGROUND":
                return context.getString(R.string.permission_body_sensors);
            case "READ_EXTERNAL_STORAGE":
                return context.getString(R.string.permission_read_storage);
            case "WRITE_EXTERNAL_STORAGE":
                return context.getString(R.string.permission_write_storage);
            case "GET_ACCOUNTS":
            case "GET_ACCOUNTS_PRIVILEGED":
                return context.getString(R.string.permission_get_accounts);
            case "RUN_IN_BACKGROUND":
                return context.getString(R.string.permission_run_background);
            case "BIND_ACCESSIBILITY_SERVICE":
                return context.getString(R.string.permission_accessibility_service);
            case "START_FOREGROUND":
                return context.getString(R.string.permission_start_foreground);
            case "BOOT_COMPLETED":
            case "RECEIVE_BOOT_COMPLETED":
                return context.getString(R.string.permission_boot_completed);
            case "ACCEPT_HANDOVER":
                return "Allows a calling app to continue a call which was started in another app.";
            case "ACCESS_BACKGROUND_LOCATION":
                return "Allows an app to access location in the background.";
            case "ACCESS_BLOBS_ACROSS_USERS":
                return "Allows an application to access data blobs across users.";
            case "ACCESS_CHECKIN_PROPERTIES":
                return "Allows read/write access to the \"properties\" table in the checkin database, to change values that get uploaded.";
            case "ACCESS_LOCATION_EXTRA_COMMANDS":
                return "Allows an application to access extra location provider commands.";
            case "ACCESS_MEDIA_LOCATION":
                return "Allows an application to access any geographic locations persisted in the user's shared collection.";
            case "ACCESS_NETWORK_STATE":
                return "Allows applications to access information about networks.";
            case "ACCESS_NOTIFICATION_POLICY":
                return "Marker permission for applications that wish to access notification policy.";
            case "ACCESS_WIFI_STATE":
                return "Allows applications to access information about Wi-Fi networks.";
            case "ACCOUNT_MANAGER":
                return "Allows applications to call into AccountAuthenticators.";
            case "ACTIVITY_RECOGNITION":
                return "Allows an application to recognize physical activity.";
            case "ADD_VOICEMAIL":
                return "Allows an application to add voicemails into the system.";
            case "ANSWER_PHONE_CALLS":
                return "Allows the app to answer an incoming phone call.";
            case "BATTERY_STATS":
                return "Allows an application to collect battery statistics";
            case "BIND_APPWIDGET":
                return "Allows an application to tell the AppWidget service which application can access AppWidget's data.";
            case "BIND_AUTOFILL_SERVICE":
                return "Must be required by a AutofillService, to ensure that only the system can bind to it.";
            case "BIND_CALL_REDIRECTION_SERVICE":
                return "Must be required by a CallRedirectionService, to ensure that only the system can bind to it.";
            case "BIND_CARRIER_MESSAGING_CLIENT_SERVICE":
                return "A subclass of CarrierMessagingClientService must be protected with this permission.";
            case "BIND_CARRIER_MESSAGING_SERVICE":
                return "This constant was deprecated in API level 23. Use BIND_CARRIER_SERVICES instead";
            case "BIND_CARRIER_SERVICES":
                return "The system process that is allowed to bind to services in carrier apps will have this permission.";
            case "BIND_CHOOSER_TARGET_SERVICE":
                return "This constant was deprecated in API level 30. For publishing direct share targets, please follow the instructions in https://developer.android.com/training/sharing/receive.html#providing-direct-share-targets instead.";
            case "BIND_COMPANION_DEVICE_SERVICE":
                return "Must be required by any CompanionDeviceServices to ensure that only the system can bind to it.";
            case "BIND_CONDITION_PROVIDER_SERVICE":
                return "Must be required by a ConditionProviderService, to ensure that only the system can bind to it.";
            case "BIND_CONTROLS":
                return "Allows SystemUI to request third party controls.";
            case "BIND_DEVICE_ADMIN":
                return "Must be required by device administration receiver, to ensure that only the system can interact with it.";
            case "BIND_DREAM_SERVICE":
                return "Must be required by an DreamService, to ensure that only the system can bind to it.";
            case "BIND_INCALL_SERVICE":
                return "Must be required by a InCallService, to ensure that only the system can bind to it.";
            case "BIND_INPUT_METHOD":
                return "Must be required by an InputMethodService, to ensure that only the system can bind to it.";
            case "BIND_MIDI_DEVICE_SERVICE":
                return "Must be required by an MidiDeviceService, to ensure that only the system can bind to it.";
            case "BIND_NFC_SERVICE":
                return "Must be required by a HostApduService or OffHostApduService to ensure that only the system can bind to it.";
            case "BIND_NOTIFICATION_LISTENER_SERVICE":
                return "Must be required by an NotificationListenerService, to ensure that only the system can bind to it.";
            case "BIND_PRINT_SERVICE":
                return "Must be required by a PrintService, to ensure that only the system can bind to it.";
            case "BIND_QUICK_ACCESS_WALLET_SERVICE":
                return "Must be required by a QuickAccessWalletService to ensure that only the system can bind to it.";
            case "BIND_QUICK_SETTINGS_TILE":
                return "Allows an application to bind to third party quick settings tiles.";
            case "BIND_REMOTEVIEWS":
                return "Must be required by a RemoteViewsService, to ensure that only the system can bind to it.";
            case "BIND_SCREENING_SERVICE":
                return "Must be required by a CallScreeningService, to ensure that only the system can bind to it.";
            case "BIND_TELECOM_CONNECTION_SERVICE":
                return "Must be required by a ConnectionService, to ensure that only the system can bind to it.";
            case "BIND_TEXT_SERVICE":
                return "Must be required by a TextService (e.g. SpellCheckerService) to ensure that only the system can bind to it.";
            case "BIND_TV_INPUT":
                return "Must be required by a TvInputService to ensure that only the system can bind to it.";
            case "BIND_TV_INTERACTIVE_APP":
                return "Must be required by a TvInteractiveAppService to ensure that only the system can bind to it.";
            case "BIND_VISUAL_VOICEMAIL_SERVICE":
                return "Must be required by a link VisualVoicemailService to ensure that only the system can bind to it.";
            case "BIND_VOICE_INTERACTION":
                return "Must be required by a VoiceInteractionService, to ensure that only the system can bind to it.";
            case "BIND_VPN_SERVICE":
                return "Must be required by a VpnService, to ensure that only the system can bind to it.";
            case "BIND_VR_LISTENER_SERVICE":
                return "Must be required by an VrListenerService, to ensure that only the system can bind to it.";
            case "BIND_WALLPAPER":
                return "Must be required by a WallpaperService, to ensure that only the system can bind to it.";
            case "BLUETOOTH":
                return "Allows applications to connect to paired bluetooth devices.";
            case "BLUETOOTH_ADMIN":
                return "Allows applications to discover and pair bluetooth devices.";
            case "BLUETOOTH_ADVERTISE":
                return "Required to be able to advertise to nearby Bluetooth devices.";
            case "BLUETOOTH_CONNECT":
                return "Required to be able to connect to paired Bluetooth devices.";
            case "BLUETOOTH_PRIVILEGED":
                return "Allows applications to pair bluetooth devices without user interaction, and to allow or disallow phonebook access or message access.";
            case "BLUETOOTH_SCAN":
                return "Required to be able to discover and pair nearby Bluetooth devices.";
            case "BROADCAST_PACKAGE_REMOVED":
                return "Allows an application to broadcast a notification that an application package has been removed.";
            case "BROADCAST_SMS":
                return "Allows an application to broadcast an SMS receipt notification.";
            case "BROADCAST_STICKY":
                return "Allows an application to broadcast sticky intents.";
            case "BROADCAST_WAP_PUSH":
                return "Allows an application to broadcast a WAP PUSH receipt notification.";
            case "CALL_COMPANION_APP":
                return "Allows an app which implements the InCallService API to be eligible to be enabled as a calling companion app.";
            case "CALL_PRIVILEGED":
                return "Allows an application to call any phone number, including emergency numbers, without going through the Dialer user interface for the user to confirm the call being placed.";
            case "CAPTURE_AUDIO_OUTPUT":
                return "Allows an application to capture audio output.";
            case "CHANGE_COMPONENT_ENABLED_STATE":
                return "Allows an application to change whether an application component (other than its own) is enabled or not.";
            case "CHANGE_CONFIGURATION":
                return "Allows an application to modify the current configuration, such as locale.";
            case "CHANGE_NETWORK_STATE":
                return "Allows applications to change network connectivity state.";
            case "CHANGE_WIFI_MULTICAST_STATE":
                return "Allows applications to enter Wi-Fi Multicast mode.";
            case "CLEAR_APP_CACHE":
                return "Allows an application to clear the caches of all installed applications on the device.";
            case "CONTROL_LOCATION_UPDATES":
                return "Allows enabling/disabling location update notifications from the radio.";
            case "DELETE_CACHE_FILES":
                return "Old permission for deleting an app's cache files, no longer used, but signals for us to quietly ignore calls instead of throwing an exception.";
            case "DELETE_PACKAGES":
                return "Allows an application to delete packages.";
            case "DELIVER_COMPANION_MESSAGES":
                return "Allows an application to deliver companion messages to system";
            case "DIAGNOSTIC":
                return "Allows applications to RW to diagnostic resources.";
            case "DISABLE_KEYGUARD":
                return "Allows applications to disable the keyguard if it is not secure.";
            case "DUMP":
                return "Allows an application to retrieve state dump information from system services.";
            case "EXPAND_STATUS_BAR":
                return "Allows an application to expand or collapse the status bar.";
            case "FACTORY_TEST":
                return "Run as a manufacturer test application, running as the root user.";
            case "FOREGROUND_SERVICE":
                return "Allows a regular application to use Service.startForeground.";
            case "GET_PACKAGE_SIZE":
                return "Allows an application to find out the space used by any package.";
            case "GET_TASKS":
                return "This constant was deprecated in API level 21. No longer enforced.";
            case "GLOBAL_SEARCH":
                return "This permission can be used on content providers to allow the global search system to access their data.";
            case "HIDE_OVERLAY_WINDOWS":
                return "Allows an app to prevent non-system-overlay windows from being drawn on top of it";
            case "HIGH_SAMPLING_RATE_SENSORS":
                return "Allows an app to access sensor data with a sampling rate greater than 200 Hz.";
            case "INSTALL_LOCATION_PROVIDER":
                return "Allows an application to install a location provider into the Location Manager.";
            case "INSTALL_PACKAGES":
                return "Allows an application to install packages.";
            case "INSTALL_SHORTCUT":
                return "Allows an application to install a shortcut in Launcher.";
            case "INSTANT_APP_FOREGROUND_SERVICE":
                return "Allows an instant app to create foreground services.";
            case "INTERACT_ACROSS_PROFILES":
                return "Allows interaction across profiles in the same profile group.";
            case "INTERNET":
                return "Allows applications to open network sockets.";
            case "KILL_BACKGROUND_PROCESSES":
                return "Allows an application to call ActivityManager.killBackgroundProcesses(String).";
            case "LAUNCH_MULTI_PANE_SETTINGS_DEEP_LINK":
                return "An application needs this permission for Settings.ACTION_SETTINGS_EMBED_DEEP_LINK_ACTIVITY to show its Activity embedded in Settings app.";
            case "LOADER_USAGE_STATS":
                return "Allows a data loader to read a package's access logs.";
            case "LOCATION_HARDWARE":
                return "Allows an application to use location features in hardware, such as the geofencing api.";
            case "MANAGE_DOCUMENTS":
                return "Allows an application to manage access to documents, usually as part of a document picker.";
            case "MANAGE_EXTERNAL_STORAGE":
                return "Allows an application a broad access to external storage in scoped storage.";
            case "MANAGE_MEDIA":
                return "Allows an application to modify and delete media files on this device or any connected storage device without user confirmation.";
            case "MANAGE_ONGOING_CALLS":
                return "Allows to query ongoing call details and manage ongoing calls";
            case "MANAGE_OWN_CALLS":
                return "Allows a calling application which manages its own calls through the self-managed ConnectionService APIs.";
            case "MANAGE_WIFI_INTERFACES":
                return "Allows applications to get notified when a Wi-Fi interface request cannot be satisfied without tearing down one or more other interfaces, and provide a decision whether to approve the request or reject it.";
            case "MANAGE_WIFI_NETWORK_SELECTION":
                return "This permission is used to let OEMs grant their trusted app access to a subset of privileged wifi APIs to improve wifi performance.";
            case "MASTER_CLEAR":
                return "Not for use by third-party applications.";
            case "MEDIA_CONTENT_CONTROL":
                return "Allows an application to know what content is playing and control its playback.";
            case "MODIFY_AUDIO_SETTINGS":
                return "Allows an application to modify global audio settings.";
            case "MODIFY_PHONE_STATE":
                return "Allows modification of the telephony state - power on, mmi, etc.";
            case "MOUNT_FORMAT_FILESYSTEMS":
                return "Allows formatting file systems for removable storage.";
            case "MOUNT_UNMOUNT_FILESYSTEMS":
                return "Allows mounting and unmounting file systems for removable storage.";
            case "NEARBY_WIFI_DEVICES":
                return "Required to be able to advertise and connect to nearby devices via Wi-Fi.";
            case "NFC":
                return "Allows applications to perform I/O operations over NFC.";
            case "NFC_PREFERRED_PAYMENT_INFO":
                return "Allows applications to receive NFC preferred payment service information.";
            case "NFC_TRANSACTION_EVENT":
                return "Allows applications to receive NFC transaction events.";
            case "OVERRIDE_WIFI_CONFIG":
                return "Allows an application to modify any wifi configuration, even if created by another application.";
            case "PERSISTENT_ACTIVITY":
                return "This constant was deprecated in API level 15. This functionality will be removed in the future; please do not use. Allow an application to make its activities persistent.";
            case "QUERY_ALL_PACKAGES":
                return "Allows query of any normal app on the device, regardless of manifest declarations.";
            case "READ_ASSISTANT_APP_SEARCH_DATA":
                return "Allows an application to query over global data in AppSearch that's visible to the ASSISTANT role.";
            case "READ_BASIC_PHONE_STATE":
                return "Allows read only access to phone state with a non dangerous permission, including the information like cellular network type, software version.";
            case "READ_HOME_APP_SEARCH_DATA":
                return "Allows an application to query over global data in AppSearch that's visible to the HOME role.";
            case "READ_INPUT_STATE":
                return "This constant was deprecated in API level 16. The API that used this permission has been removed.";
            case "READ_LOGS":
                return "Allows an application to read the low-level system log files.";
            case "READ_MEDIA_AUDIO":
                return "Allows an application to read audio files from external storage.";
            case "READ_MEDIA_IMAGES":
                return "Allows an application to read image files from external storage.";
            case "READ_MEDIA_VIDEO":
                return "Allows an application to read video files from external storage.";
            case "READ_NEARBY_STREAMING_POLICY":
                return "Allows an application to read nearby streaming policy.";
            case "READ_PRECISE_PHONE_STATE":
                return "Allows read only access to precise phone state.";
            case "READ_SYNC_SETTINGS":
                return "Allows applications to read the sync settings.";
            case "READ_SYNC_STATS":
                return "Allows applications to read the sync stats.";
            case "READ_VOICEMAIL":
                return "Allows an application to read voicemails in the system.";
            case "REBOOT":
                return "Required to be able to reboot the device.";
            case "REORDER_TASKS":
                return "Allows an application to change the Z-order of tasks.";
            case "REQUEST_COMPANION_PROFILE_APP_STREAMING":
                return "Allows application to request to be associated with a virtual display capable of streaming Android applications (AssociationRequest.DEVICE_PROFILE_APP_STREAMING) by CompanionDeviceManager.";
            case "REQUEST_COMPANION_PROFILE_AUTOMOTIVE_PROJECTION":
                return "Allows application to request to be associated with a vehicle head unit capable of automotive projection (AssociationRequest.DEVICE_PROFILE_AUTOMOTIVE_PROJECTION) by CompanionDeviceManager.";
            case "REQUEST_COMPANION_PROFILE_COMPUTER":
                return "Allows application to request to be associated with a computer to share functionality and/or data with other devices, such as notifications, photos and media (AssociationRequest.DEVICE_PROFILE_COMPUTER) by CompanionDeviceManager.";
            case "REQUEST_COMPANION_PROFILE_WATCH":
                return "Allows app to request to be associated with a device via CompanionDeviceManager as a \"watch\"";
            case "REQUEST_COMPANION_SELF_MANAGED":
                return "Allows an application to create a \"self-managed\" association.";
            case "REQUEST_COMPANION_START_FOREGROUND_SERVICES_FROM_BACKGROUND":
                return "Allows a companion app to start a foreground service from the background.";
            case "REQUEST_COMPANION_USE_DATA_IN_BACKGROUND":
                return "Allows a companion app to use data in the background.";
            case "REQUEST_IGNORE_BATTERY_OPTIMIZATIONS":
                return "Permission an application must hold in order to use Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS.";
            case "REQUEST_OBSERVE_COMPANION_DEVICE_PRESENCE":
                return "Allows an application to subscribe to notifications about the presence status change of their associated companion device";
            case "REQUEST_PASSWORD_COMPLEXITY":
                return "Allows an application to request the screen lock complexity and prompt users to update the screen lock to a certain complexity level.";
            case "RESTART_PACKAGES":
                return "This constant was deprecated in API level 15. The ActivityManager.restartPackage(String) API is no longer supported.";
            case "SCHEDULE_EXACT_ALARM":
                return "Allows applications to use exact alarm APIs.";
            case "SEND_RESPOND_VIA_MESSAGE":
                return "Allows an application (Phone) to send a request to other applications to handle the respond-via-message action during incoming calls.";
            case "SET_ALARM":
                return "Allows an application to broadcast an Intent to set an alarm for the user.";
            case "SET_ALWAYS_FINISH":
                return "Allows an application to control whether activities are immediately finished when put in the background.";
            case "SET_ANIMATION_SCALE":
                return "Modify the global animation scaling factor.";
            case "SET_DEBUG_APP":
                return "Configure an application for debugging.";
            case "SET_PREFERRED_APPLICATIONS":
                return "This constant was deprecated in API level 15. No longer useful, see PackageManager.addPackageToPreferred(String) for details.";
            case "SET_PROCESS_LIMIT":
                return "Allows an application to set the maximum number of (not needed) application processes that can be running.";
            case "SET_TIME":
                return "Allows applications to set the system time directly.";
            case "SET_TIME_ZONE":
                return "Allows applications to set the system time zone directly.";
            case "SET_WALLPAPER":
                return "Allows applications to set the wallpaper.";
            case "SET_WALLPAPER_HINTS":
                return "Allows applications to set the wallpaper hints.";
            case "SIGNAL_PERSISTENT_PROCESSES":
                return "Allow an application to request that a signal be sent to all persistent processes.";
            case "SMS_FINANCIAL_TRANSACTIONS":
                return "This constant was deprecated in API level 31. The API that used this permission is no longer functional.";
            case "START_FOREGROUND_SERVICES_FROM_BACKGROUND":
                return "Allows an application to start foreground services from the background at any time.";
            case "START_VIEW_APP_FEATURES":
                return "Allows the holder to start the screen with a list of app features.";
            case "START_VIEW_PERMISSION_USAGE":
                return "Allows the holder to start the permission usage screen for an app.";
            case "STATUS_BAR":
                return "Allows an application to open, close, or disable the status bar and its icons.";
            case "SUBSCRIBE_TO_KEYGUARD_LOCKED_STATE":
                return "Allows an application to subscribe to keyguard locked (i.e., showing) state.";
            case "TRANSMIT_IR":
                return "Allows using the device's IR transmitter, if available.";
            case "UNINSTALL_SHORTCUT":
                return "Don't use this permission in your app.";
            case "UPDATE_DEVICE_STATS":
                return "Allows an application to update device statistics.";
            case "UPDATE_PACKAGES_WITHOUT_USER_ACTION":
                return "Allows an application to indicate via PackageInstaller.SessionParams.setRequireUserAction(int) that user action should not be required for an app update.";
            case "USE_EXACT_ALARM":
                return "Allows apps to use exact alarms just like with SCHEDULE_EXACT_ALARM but without needing to request this permission from the user.";
            case "USE_FULL_SCREEN_INTENT":
                return "Required for apps targeting Build.VERSION_CODES.Q that want to use notification full screen intents.";
            case "USE_ICC_AUTH_WITH_DEVICE_IDENTIFIER":
                return "Allows to read device identifiers and use ICC based authentication like EAP-AKA.";
            case "USE_SIP":
                return "Allows an application to use SIP service.";
            case "UWB_RANGING":
                return "Required to be able to range to devices using ultra-wideband.";
            case "WRITE_APN_SETTINGS":
                return "Allows applications to write the apn settings and read sensitive fields of an existing apn settings like user and password.";
            case "WRITE_GSERVICES":
                return "Allows an application to modify the Google service map.";
            case "WRITE_SECURE_SETTINGS":
                return "Allows an application to read or write the secure system settings.";
            case "WRITE_SYNC_SETTINGS":
                return "Allows applications to write the sync settings.";
            case "WRITE_VOICEMAIL":
                return "Allows an application to modify and remove existing voicemails in the system.";
            default:
                return context.getString(R.string.permission_unavailable, permission.toUpperCase(Locale.getDefault()));
        }
    }

    public static void requestPermission(String[] permissions, Activity activity) {
        ActivityCompat.requestPermissions(activity, permissions, 0);
    }

}