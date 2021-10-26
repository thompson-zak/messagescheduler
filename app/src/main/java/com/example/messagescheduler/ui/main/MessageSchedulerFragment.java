package com.example.messagescheduler.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.messagescheduler.R;

/**
 * Fragment containing a form used to schedule new messages
 */
public class MessageSchedulerFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_scheduler, viewGroup, false);
    }
}