package com.audioservice.audios;

import android.app.Application;
import android.util.Log;

import java.io.File;

public class App extends Application{
    public static final String TAG = App.class.getName();

    @Override
    public void onCreate() {
        super.onCreate();

        Public.ExternalDir = getExternalFilesDir(null);
        //Create necessary folders
        File audioDir = new File(Public.ExternalDir, Public.Constants.AUDIO_FILES_DIR);
        if(!(audioDir.exists() && audioDir.isDirectory())){
            if(!audioDir.mkdir()) Log.e(TAG, "Error creating audio files folder");
        }
    }
}
