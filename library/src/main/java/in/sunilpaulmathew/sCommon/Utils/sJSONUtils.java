package in.sunilpaulmathew.sCommon.Utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/*
 * Created by sunilpaulmathew <sunil.kde@gmail.com> on November 10, 2021
 */
public class sJSONUtils {

    public static boolean getBoolean(JsonObject object, String booleanName) {
        return object.get(booleanName).getAsBoolean();
    }

    public static JsonArray getJSONObject(JsonObject object, String arrayName) {
        return object.getAsJsonArray(arrayName);
    }

    public static JsonObject getJSONObject(String jsonString) {
        return JsonParser.parseString(jsonString).getAsJsonObject();
    }

    public static int getInt(JsonObject object, String intName) {
        return object.get(intName).getAsInt();
    }

    public static long getLong(JsonObject object, String longName) {
        return object.get(longName).getAsLong();
    }

    public static String getString(JsonObject object, String stringName) {
        return object.get(stringName).getAsString();
    }

}