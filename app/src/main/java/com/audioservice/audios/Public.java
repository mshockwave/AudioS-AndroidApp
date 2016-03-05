package com.audioservice.audios;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.audioservice.jeffchien.audios.rest.IAudioS;

import java.io.File;

public class Public {

    public static File ExternalDir = null;

    public static IAudioS AudioS = null;

    public static class Constants {
        public static final String AUDIO_FILES_DIR = "AudioFiles";
    }

    public static class Routines {
        public static boolean isConnected(Context context){
            ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            return info != null && info.isConnectedOrConnecting();
        }
    }
}
