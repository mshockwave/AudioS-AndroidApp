package com.audioservice.audios;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.audioservice.jeffchien.audios.rest.IAudioS;

import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

public class Public {

    public static File ExternalDir = null;

    public static IAudioS AudioS = null;

    public final static AtomicInteger ActiveActivityCount = new AtomicInteger(0);

    public static class Constants {
        public static final String AUDIO_FILES_DIR = "AudioFiles";

        public static final String EXTRA_BOOL_SHOW_LOCAL_ONLY = "extra-show-local";
        public static final String EXTRA_BOOL_NOT_LOGIN = "extra-not-login";
        public static final String EXTRA_STRING_SHOW_TOAST_MESSAGE = "extra-toast-message";

        //Prevent recursive activity invoking
        public static final String EXTRA_BOOL_HAD_HANDLE_OFFLINE = "extra-had-handle-offline";
    }

    public static class Routines {
        public static boolean isConnected(Context context){
            ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            return info != null && info.isConnectedOrConnecting();
        }

        public static void bailOffline(Context context){
            Toast.makeText(context, R.string.offline_mode, Toast.LENGTH_LONG).show();

            //Offline, only show local file fragment
            Intent intent = new Intent(context, ViewerActivity.class);
            intent.putExtra(Public.Constants.EXTRA_BOOL_SHOW_LOCAL_ONLY, true);
            intent.putExtra(Constants.EXTRA_BOOL_HAD_HANDLE_OFFLINE, true);
            //intent.putExtra(Public.Constants.EXTRA_STRING_SHOW_TOAST_MESSAGE, context.getString(R.string.offline_mode));
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
