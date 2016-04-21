package com.audioservice.audios;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.audioservice.jeffchien.audios.rest.layouts.SimpleResult;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class LoginFragment extends Fragment {
    private final static String TAG = LoginFragment.class.getName();

    private TextView mTextStatus;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {

        Bundle args = new Bundle();

        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_login, container, false);
        setUpViews(fragmentView);

        return fragmentView;
    }
    private void setUpViews(View rootView){
        mTextStatus = (TextView)rootView.findViewById(R.id.text_login_status_msg);
        final EditText editEmail = (EditText)rootView.findViewById(R.id.edit_email);
        final EditText editPassword = (EditText)rootView.findViewById(R.id.edit_password);

        Button loginButton = (Button)rootView.findViewById(R.id.but_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextStatus.setTextColor(Color.BLACK);
                mTextStatus.setText(R.string.processing);

                String email = editEmail.getText().toString();
                String password = editPassword.getText().toString();

                Public.AudioS.userLogin(email, password).enqueue(mLoginCallback);
            }
        });
    }

    private final Callback<SimpleResult> mLoginCallback = new Callback<SimpleResult>() {
        @Override
        public void onResponse(Response<SimpleResult> response, Retrofit retrofit) {
            int code = response.code();
            if(code == 200){
                Intent intent = new Intent(getActivity(), EntryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }else if(code >= 500 && code < 600){
                //Internal error
                Log.e(TAG, "Login receive internal error: " + response.message());
            }else{
                mTextStatus.setTextColor(Color.RED);
                mTextStatus.setText(R.string.login_failed);
            }
        }

        @Override
        public void onFailure(Throwable t) {
            mTextStatus.setTextColor(Color.RED);
            mTextStatus.setText(R.string.login_failed);
            t.printStackTrace();
        }
    };
}
