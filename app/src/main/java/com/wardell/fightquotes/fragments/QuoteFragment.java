package com.wardell.fightquotes.fragments;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wardell.fightquotes.R;

/**
 * Created by wardell on 1/9/16.
 */
public class QuoteFragment extends DialogFragment implements View.OnClickListener /*implements TextView.OnEditorActionListener */ {
    private EditText quote;
    //quote_string is null when the QuoteFragment is creating a new quote
    //quote_String has a quote when the QuoteFragment is editing a quote
    private String quote_string;
    private String webHashKey;
    private Button action;
    private static final String TAG = "Quote Fragment";

    public interface QuoteListener {
        void onFinishQuoteDialog(String quote_Input);
        void onFinishEditQuoteDialog(String quote_Input, String id);
    }

    // Empty constructor required for DialogFragment
    public QuoteFragment() {

    }

    public void setQuote(String quote_input, String key){
        quote_string=quote_input;
        webHashKey=key;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quote, container);
        quote = (EditText) view.findViewById(R.id.quote);
        action = (Button) view.findViewById(R.id.button);
        action.setOnClickListener(this);
        if(quote_string != null)
        {
            quote.setText(quote_string);
            action.setText("edit");
        }
        //quote.setOnEditorActionListener(this);
        quote.requestFocus();



        return view;
    }
    @Override
    public void onClick(View view){
        QuoteListener activity = (QuoteListener) getActivity();
        if(quote_string == null) {//case where user is creating a new quote
            activity.onFinishQuoteDialog(quote.getText().toString());
            Log.v(TAG, "quote is :" + quote.getText().toString());
        }
        else// case where user is editing a quote
        {
            activity.onFinishEditQuoteDialog(quote.getText().toString(),webHashKey);
            Log.v(TAG, "quote is :" + quote.getText().toString());
        }
        this.dismiss();
    }

}
