package com.audioservice.audios;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LocalAudioFragment extends Fragment {

    public LocalAudioFragment() {
        // Required empty public constructor
    }

    public static LocalAudioFragment newInstance(String param1, String param2) {
        return new LocalAudioFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_local_audio, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        LocalAudioFragmentAdapter localAudioFragmentAdapter = new LocalAudioFragmentAdapter(new String[] {"test"});
        recyclerView.setAdapter(localAudioFragmentAdapter);
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
