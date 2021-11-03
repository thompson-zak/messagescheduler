package com.example.messagescheduler.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.messagescheduler.R;
import com.example.messagescheduler.db.main.ScheduledMessage;
import com.example.messagescheduler.db.main.SchedulerDatabase;

import java.util.List;

/**
 * A fragment used to display a list of scheduled messages with relevant information
 */
public class ScheduledMessagesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){

        View v = inflater.inflate(R.layout.fragment_scheduled, viewGroup, false);

        //Fetch list of scheduled messages from room DB
        SchedulerDatabase db = SchedulerDatabase.getInstance(getActivity());
        List<ScheduledMessage> messages = db.getScheduledMessageDao().getAll();

        //Create and populate list view using custom messages adapter
        MessagesAdapter adapter = new MessagesAdapter(this.getContext(), messages);
        ListView listView = v.findViewById(R.id.messages_list_view);
        listView.setAdapter(adapter);

        return v;
    }
}