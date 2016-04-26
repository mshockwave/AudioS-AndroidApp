package com.audioservice.audios;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.util.ArrayList;

public class AudioFragment extends Fragment {

    ArrayList<String> list = new ArrayList<>();

    public AudioFragment() {
        // Required empty public constructor
    }

    public static AudioFragment newInstance(String param1, String param2) {
        return new AudioFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_audio, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // Add the file list to list array
        File file = new File(Public.ExternalDir.getPath() + "/" + Public.Constants.AUDIO_FILES_DIR);
        File fileArr[] = file.listFiles();
        for (File f : fileArr)
            list.add(f.getName());
        // Set the adapter
        AudioFragmentAdapter audioFragmentAdapter = new AudioFragmentAdapter(getContext(), list);
        recyclerView.setAdapter(audioFragmentAdapter);
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
