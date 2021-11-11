package in.sunilpaulmathew.sCommon.Utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * Created by sunilpaulmathew <sunil.kde@gmail.com> on November 10, 2021
 * Ref: https://stackoverflow.com/questions/58767733/android-asynctask-api-deprecating-in-android-11-what-are-the-alternatives
 */
public abstract class sExecutor {

    private final ExecutorService executors;

    public sExecutor() {
        this.executors = Executors.newSingleThreadExecutor();
    }

    private void startBackground() {
        onPreExecute();
        executors.execute(() -> {
            doInBackground();
            new Handler(Looper.getMainLooper()).post(() -> {
                onPostExecute();
                if (!executors.isShutdown()) executors.shutdown();
            });
        });
    }

    public void execute() {
        startBackground();
    }

    public abstract void onPreExecute();

    public abstract void doInBackground();

    public abstract void onPostExecute();
}