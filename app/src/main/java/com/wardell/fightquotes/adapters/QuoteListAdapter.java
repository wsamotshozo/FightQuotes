package com.wardell.fightquotes.adapters;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Query;
import com.wardell.fightquotes.R;

import java.util.HashMap;

/**
 * Created by wardell on 1/10/16.
 */
public class QuoteListAdapter extends FirebaseListAdapter<String> {

    public QuoteListAdapter(Query ref, Activity activity, int layout) {
        super(ref, String.class, layout, activity);
    }

    @Override
    protected void populateView(View view, String quote) {
        // Map a Chat object to an entry in our listview
        TextView quoteView = (TextView) view.findViewById(R.id.text);
        quoteView.setText(quote);
    }
    public String getWebHashKey(int position){
        return getmKeys().get(position);
    }
}