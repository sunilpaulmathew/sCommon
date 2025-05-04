package in.sunilpaulmathew.sCommon.FileUtils;

import android.content.Context;
import android.net.Uri;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
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

    public static boolean delete(String filePath) {
        return delete((new File(filePath)));
    }

    public static boolean exist(File file) {
        return file.exists();
    }

    public static boolean exist(String filePath) {
        return exist(new File(filePath));
    }

    public static boolean mkdir(File folder) {
        return folder.mkdirs();
    }

    public static boolean mkdirs(String folderPath) {
        return mkdir(new File(folderPath));
    }

    public static String read(File file) {
        try (FileInputStream fis = new FileInputStream(file)) {
            return read(fis);
        } catch (IOException ignored) {
            return null;
        }
    }

    public static String read(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            return read(filePath);
        } catch (IOException ignored) {
            return null;
        }
    }

    public static String read(Uri uri, Context context) throws IOException {
        return read(context.getContentResolver().openInputStream(uri));
    }

    public static String readAssetFile(String assetName, Context context) throws IOException {
        return read(context.getAssets().open(assetName));
    }

    public static String read(InputStream inputStream) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        for (int result = bis.read(); result != -1; result = bis.read()) {
            buf.write((byte) result);
        }
        return buf.toString("UTF-8");
    }

    public static void create(String text, File path) {
        try {
            FileWriter writer = new FileWriter(path);
            writer.write(text);
            writer.close();
        } catch (IOException ignored) {
        }
    }

    public static void create(String text, String filePath) {
        create(text, new File(filePath));
    }

    public static void copy(byte[] bytes, File dest) {
        try (FileOutputStream outputStream = new FileOutputStream(dest, false)) {
            outputStream.write(bytes);
        } catch (IOException ignored) {}
    }

    public static void copy(InputStream inputStream, File dest) {
        try (FileOutputStream outputStream = new FileOutputStream(dest, false)) {

            copyStream(Objects.requireNonNull(inputStream), outputStream);

            inputStream.close();
        } catch (IOException ignored) {}
    }

    public static void copy(File source, File dest) {
        try {
            FileInputStream inputStream = new FileInputStream(source);
            FileOutputStream outputStream = new FileOutputStream(dest);

            copyStream(inputStream, outputStream);

            inputStream.close();
            outputStream.close();
        } catch (IOException ignored) {}
    }

    public static void copy(Uri uri, File dest, Context context) {
        try {
            copy(context.getContentResolver().openInputStream(uri), dest);
        } catch (IOException ignored) {}
    }

    public static void copyAssetFile(String assetName, File dest, Context context) {
        try {
            InputStream inputStream = context.getAssets().open(assetName);
            FileOutputStream outputStream = new FileOutputStream(dest);

            copyStream(inputStream, outputStream);

            inputStream.close();
            outputStream.close();
        } catch (IOException ignored) {}
    }

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