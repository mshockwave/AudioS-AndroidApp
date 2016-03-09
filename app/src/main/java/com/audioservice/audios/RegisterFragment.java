package com.audioservice.audios;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.audioservice.jeffchien.audios.rest.layouts.User;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class RegisterFragment extends Fragment {
    private final static String TAG = RegisterFragment.class.getName();

    private TextView mTextStatus;

    public RegisterFragment() {
        // Required empty public constructor
    }

    public static RegisterFragment newInstance() {

        Bundle args = new Bundle();

        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_register, container, false);
        setUpViews(fragmentView);

        return fragmentView;
    }
    private void setUpViews(View rootView){
        mTextStatus = (TextView)rootView.findViewById(R.id.text_register_status_msg);

        final TextInputLayout editEmail = (TextInputLayout)rootView.findViewById(R.id.edit_email);
        final TextInputLayout editUsername = (TextInputLayout)rootView.findViewById(R.id.edit_username);
        final TextInputLayout editPassword = (TextInputLayout)rootView.findViewById(R.id.edit_password);
        final TextInputLayout editPasswordConfirm = (TextInputLayout)rootView.findViewById(R.id.edit_confirm_password);

        final Button butRegister = (Button)rootView.findViewById(R.id.but_register);

        editPasswordConfirm.setError(getString(R.string.password_not_match));
        editPasswordConfirm.setErrorEnabled(false);
        if(editPasswordConfirm.getEditText() != null){
            editPasswordConfirm.getEditText().addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                @Override
                public void afterTextChanged(Editable s) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(editPassword.getEditText() != null){
                        String password = editPassword.getEditText().getText().toString();
                        boolean correct = s.toString().equals(password);
                        editPasswordConfirm.setErrorEnabled(!correct);
                        butRegister.setEnabled(correct);
                    }
                }
            });
        }

        if(editEmail.getEditText() != null &&
                editPassword.getEditText() != null &&
                editUsername.getEditText() != null){
            butRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mTextStatus.setTextColor(Color.BLACK);
                    mTextStatus.setText(R.string.processing);

                    String email = editEmail.getEditText().getText().toString();
                    String password = editPassword.getEditText().getText().toString();
                    String username = editUsername.getEditText().getText().toString();

                    Public.AudioS.userRegister(username, email, password).enqueue(mRegisterCallback);
                }
            });
        }
    }
    private final Callback<User> mRegisterCallback = new Callback<User>() {
        @Override
        public void onResponse(Response<User> response, Retrofit retrofit) {
            int code = response.code();
            if(code == 200){
                Intent intent = new Intent(getActivity(), EntryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }else if(code >= 500 && code < 600){
                //Internal error
                Log.e(TAG, "Register receive internal error: " + response.message());
            }else{
                mTextStatus.setTextColor(Color.RED);
                mTextStatus.setText(R.string.register_failed);
            }
        }

        @Override
        public void onFailure(Throwable t) {
            mTextStatus.setTextColor(Color.RED);
            mTextStatus.setText(R.string.register_failed);
            t.printStackTrace();
        }
    };
}
