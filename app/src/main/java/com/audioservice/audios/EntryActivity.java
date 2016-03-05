package com.audioservice.audios;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.audioservice.jeffchien.audios.map.IAudioSProxy;
import com.audioservice.jeffchien.audios.rest.AudioSFactory;
import com.audioservice.jeffchien.audios.rest.layouts.User;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class EntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * Initialize most of the application specific stuffs
         **/

        //Check network
        if(!Public.Routines.isConnected(this)){
            //Offline, only show local file fragment
            Intent intent = new Intent(EntryActivity.this, ViewerActivity.class);
            intent.putExtra(Public.Constants.EXTRA_BOOL_SHOW_LOCAL_ONLY, true);
            intent.putExtra(Public.Constants.EXTRA_STRING_SHOW_TOAST_MESSAGE, getString(R.string.offline_mode));
            startActivity(intent);
            return;
        }

        //Initialize AudioS API stuffs
        Public.AudioS = new AudioSFactory(this).create();
        IAudioSProxy.AudioSInterface = Public.AudioS;

        //Check login status
        Public.AudioS.getUserProfile().enqueue(mProfileCallback);
    }

    private void bailNotLogin(){
        Intent intent = new Intent(EntryActivity.this, MainActivity.class);
        intent.putExtra(Public.Constants.EXTRA_BOOL_NOT_LOGIN, true);
        startActivity(intent);
    }

    private final Callback<User> mProfileCallback = new Callback<User>() {
        @Override
        public void onResponse(Response<User> response, Retrofit retrofit) {
            if(response != null && response.body() != null){
                //Goto MainActivity
                startActivity(new Intent(EntryActivity.this, MainActivity.class));
            }else{
                //Treated as not login, bail
                bailNotLogin();
            }
        }

        @Override
        public void onFailure(Throwable t) {
            bailNotLogin();
        }
    };
}
