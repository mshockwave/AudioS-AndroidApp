package com.audioservice.audios;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.audioservice.jeffchien.audios.rest.AudioSFactory;

public class EntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * Initialize most of the application specific stuffs
         * **/

        //Check network
        if(!Public.Routines.isConnected(this)){
            //Offline, only show local file fragment
            return;
        }

        //Initialize AudioS API stuffs
        Public.AudioS = new AudioSFactory()
                    .setContext(this)
                    .create();

    }
}
