package com.wardell.fightquotes.fragments;

/**
 * Created by wardell on 1/10/16.
 */
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class QuoteDeleteFragment extends DialogFragment {
    String quote;
    String webHashKey;
    Firebase quoteRef;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        final String quote = bundle.getString("quote");
        final String webHashKey = bundle.getString("webHashKey");

        return new AlertDialog.Builder(getActivity())
                // set dialog icon
                .setIcon(android.R.drawable.stat_notify_error)
                        // set Dialog Title
                .setTitle("Are you sure you want to delete this Quote?")
                        // Set Dialog Message
                .setMessage(quote)

                        // positive button
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        quoteRef = new Firebase("https://glaring-fire-7430.firebaseio.com/quotes/"+webHashKey);
                        quoteRef.removeValue();
                        dialog.dismiss();
                    }
                })
                        // negative button
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).create();
    }
}