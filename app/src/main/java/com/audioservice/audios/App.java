package com.audioservice.audios;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
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

        registerActivityLifecycleCallbacks(mLifecycleCallbacks);
    }

    private final ActivityLifecycleCallbacks mLifecycleCallbacks = new ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {
            //Increase active activity count
            Public.ActiveActivityCount.incrementAndGet();

            if(!Public.Routines.isConnected(activity)){
                if(!activity.getIntent().getBooleanExtra(Public.Constants.EXTRA_BOOL_HAD_HANDLE_OFFLINE, false)){
                    Public.Routines.bailOffline(activity);
                    activity.finish();
                }
            }
        }

        @Override
        public void onActivityPaused(Activity activity) {
            //Decrease active activity count
            Public.ActiveActivityCount.decrementAndGet();
        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {

        }
    };
}
