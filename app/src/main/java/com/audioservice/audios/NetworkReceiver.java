package com.audioservice.audios;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NetworkReceiver extends BroadcastReceiver {
    public NetworkReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(Public.ActiveActivityCount.get() > 0){
            if(!Public.Routines.isConnected(context)){
                Public.Routines.bailOffline(context);
            }
        }
    }
}
