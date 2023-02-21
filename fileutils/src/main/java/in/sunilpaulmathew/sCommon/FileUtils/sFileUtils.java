package in.sunilpaulmathew.sCommon.FileUtils;

import android.content.Context;
import android.net.Uri;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Objects;

/*
 * Created by sunilpaulmathew <sunil.kde@gmail.com> on November 10, 2021
 */
public class sFileUtils {

    public static boolean delete(File file) {
        if (file.isDirectory()) {
            for (File files : Objects.requireNonNull(file.listFiles()))
                delete(files);
        }
        return file.delete();
    }

    public static boolean exist(File file) {
        return file.exists();
    }

    public static boolean mkdir(File folderPath) {
        return folderPath.mkdirs();
    }

    public static String read(File file) {
        BufferedReader buf = null;
        try {
            buf = new BufferedReader(new FileReader(file));

            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = buf.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }

            return stringBuilder.toString().trim();
        } catch (IOException ignored) {
        } finally {
            try {
                if (buf != null) buf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String read(Uri uri, Context context) {
        try {
            InputStream inputStream = context.getContentResolver().openInputStream(uri);
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            for (int result = bis.read(); result != -1; result = bis.read()) {
                buf.write((byte) result);
            }
            return buf.toString("UTF-8");
        } catch (IOException ignored) {
        }
        return null;
    }

    public static String readAssetFile(String assetName, Context context) {
        InputStream input = null;
        BufferedReader buf = null;
        try {
            StringBuilder s = new StringBuilder();
            input = context.getAssets().open(assetName);
            buf = new BufferedReader(new InputStreamReader(input));

            String str;
            while ((str = buf.readLine()) != null) {
                s.append(str).append("\n");
            }
            return s.toString().trim();
        } catch (IOException ignored) {
        } finally {
            try {
                if (input != null) input.close();
                if (buf != null) buf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void create(String text, File path) {
        try {
            FileWriter writer = new FileWriter(path);
            writer.write(text);
            writer.close();
        } catch (IOException ignored) {
        }
    }

    /*
     * Requires permission to write into the destination (dest) directory
     */
    public static void copy(File source, File dest) {
        try {
            FileInputStream inputStream = new FileInputStream(source);
            FileOutputStream outputStream = new FileOutputStream(dest);

            copyStream(inputStream, outputStream);

            inputStream.close();
            outputStream.close();
        } catch (IOException ignored) {}
    }

    /*
     * Requires permission to write into the destination (dest) directory
     */
    public static void copy(Uri uri, File dest, Context context) {
        try (FileOutputStream outputStream = new FileOutputStream(dest, false)) {
            InputStream inputStream = context.getContentResolver().openInputStream(uri);
            
            copyStream(inputStream, outputStream);
            
            inputStream.close();
        } catch (IOException ignored) {}
    }

    /*
     * Requires permission to write into the destination (dest) directory
     */
    public static void copyAssetFile(String assetName, File dest, Context context) {
        try {
            InputStream inputStream = context.getAssets().open(assetName);
            FileOutputStream outputStream = new FileOutputStream(dest);

            copyStream(inputStream, outputStream);

            inputStream.close();
            outputStream.close();
        } catch (IOException ignored) {}
    }

    /*
     * Requires permission to write into the destination (destDir) directory
     */
    public static void copyDir(File sourceDir, File destDir) {
        if (!exist(destDir)) {
            mkdir(destDir);
        }
        for (File mFile : Objects.requireNonNull(sourceDir.listFiles())) {
            if (mFile.isDirectory()) {
                copyDir(mFile, new File(destDir, mFile.getName()));
            } else {
                copy(mFile, new File(destDir, mFile.getName()));
            }
        }
    }

    public static void copyStream(InputStream from, OutputStream to) throws IOException {
        byte[] buf = new byte[1024 * 1024];
        int len;
        while ((len = from.read(buf)) > 0) {
            to.write(buf, 0, len);
        }
    }

}