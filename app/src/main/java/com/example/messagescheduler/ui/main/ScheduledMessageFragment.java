package com.example.messagescheduler.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.messagescheduler.R;

/**
 * A fragment used to display a list of scheduled messages with relevant information
 */
public class ScheduledMessageFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_scheduled, viewGroup, false);
    }
}