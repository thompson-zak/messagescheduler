package com.example.messagescheduler.ui.main;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.messagescheduler.R;
import com.example.messagescheduler.db.main.ScheduledMessage;

import java.util.List;

public class MessagesAdapter extends ArrayAdapter<ScheduledMessage> {

    public MessagesAdapter(Context context, List<ScheduledMessage> messages) {
        super(context, 0, messages);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ScheduledMessage message = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_message_list_item, parent, false);
        }
        // Lookup view for data population
        TextView recipientName = convertView.findViewById(R.id.recipient_name);
        TextView recipientNumber = convertView.findViewById(R.id.recipient_number);
        TextView messageScheduledTime = convertView.findViewById(R.id.message_scheduled_time);
        TextView messageText = convertView.findViewById(R.id.message_text);
        // Populate the data into the template view using the data object
        recipientName.setText(fetchRecipientName(message.getPhoneNumber()));
        recipientNumber.setText(message.getPhoneNumber());
        messageScheduledTime.setText(generateScheduledTime(message));
        messageText.setText(message.getMessage());
        // Return the completed view to render on screen
        return convertView;
    }

    /**
     * Since our application does not ask for name during message ingestion, we need to perform a contact lookup
     * @param phoneNumber - recipient number associated with scheduled message
     * @return name string
     */
    private String fetchRecipientName(String phoneNumber) {
        // TODO - remove this and prompt user for name
        /*Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNumber));
        String name = "?";

        ContentResolver contentResolver = getContext().getContentResolver();
        Cursor contactLookup = contentResolver.query(uri, new String[] {BaseColumns._ID,
                ContactsContract.PhoneLookup.DISPLAY_NAME }, null, null, null);

        try {
            if (contactLookup != null && contactLookup.getCount() > 0) {
                contactLookup.moveToNext();
                name = contactLookup.getString(contactLookup.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
            }
        } finally {
            if (contactLookup != null) {
                contactLookup.close();
            }
        }

        return name;*/
        return "Placeholder";
    }

    /**
     * Provides formatted time string which includes frequency, time, and timezone for users in list view
     * @param message - message object containing all date info
     * @return formatted string
     */
    private String generateScheduledTime(ScheduledMessage message) {
        return message.getFrequency().name() + " @ " + message.getScheduledTime() + " " + message.getTimeZone().name();
    }
}
