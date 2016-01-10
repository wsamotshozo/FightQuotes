package com.wardell.fightquotes.fragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.wardell.fightquotes.R;

/**
 * Created by wardell on 1/9/16.
 */
public class QuoteFragment extends DialogFragment implements TextView.OnEditorActionListener {
    private EditText quote;
    private static final String TAG = "Quote Fragment";


    public interface QuoteListener {
        void onFinishUserDialog(String quote_Input);
    }

    // Empty constructor required for DialogFragment
    public QuoteFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quote, container);
        quote = (EditText) view.findViewById(R.id.quote);
        quote.setOnEditorActionListener(this);
        quote.requestFocus();

        return view;
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        // Return input text to activity
        QuoteListener activity = (QuoteListener) getActivity();
        activity.onFinishUserDialog(quote.getText().toString());
        Log.v(TAG, "quote is :" + quote.getText().toString());
        this.dismiss();
        return true;
    }
}
