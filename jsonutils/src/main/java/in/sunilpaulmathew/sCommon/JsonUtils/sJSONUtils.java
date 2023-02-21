package in.sunilpaulmathew.sCommon.JsonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/*
 * Created by sunilpaulmathew <sunil.kde@gmail.com> on November 10, 2021
 */
public class sJSONUtils {

    public static boolean getBoolean(JSONObject object, String booleanName) {
        try {
            return object.getBoolean(booleanName);
        } catch (JSONException ignored) {
            return false;
        }
    }

    public static JSONArray getJSONArray(JSONObject object, String arrayName) {
        try {
            return object.getJSONArray(arrayName);
        } catch (JSONException ignored) {
            return null;
        }
    }

    public static JSONObject getJSONObject(String jsonString) {
        try {
            return new JSONObject(jsonString);
        } catch (JSONException ignored) {
            return null;
        }
    }

    public static int getInt(JSONObject object, String intName) {
        try {
            return object.getInt(intName);
        } catch (JSONException ignored) {
            return Integer.MIN_VALUE;
        }
    }

    public static long getLong(JSONObject object, String longName) {
        try {
            return object.getLong(longName);
        } catch (JSONException ignored) {
            return Long.MIN_VALUE;
        }
    }

    public static String getString(JSONObject object, String stringName) {
        try {
            return object.getString(stringName);
        } catch (JSONException ignored) {
            return null;
        }
    }

}