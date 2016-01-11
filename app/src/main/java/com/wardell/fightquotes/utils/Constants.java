package com.wardell.fightquotes.utils;

import com.firebase.client.Firebase;
import com.wardell.fightquotes.adapters.QuoteListAdapter;

/**
 * Created by wardell on 1/2/16.
 */
public class Constants {
    public static String firebaseURL = "https://glaring-fire-7430.firebaseio.com/";
    public static Firebase firebaseRef = new Firebase(firebaseURL);
    public static String previous= "Previous";
    public static String next = "Next";
    public static String namespace = "com.wardell.fightquotes.";
    public static QuoteListAdapter quoteListAdapter;
    public static int quoteTracker = 0;
    public static String getQuote()
    {
        return quoteListAdapter.getItem(quoteTracker).toString();
    }
    public static String getNextQuote()
    {
        quoteTracker ++;
        if(quoteTracker == quoteListAdapter.getCount())
        {
            quoteTracker = 0;
        }
        return quoteListAdapter.getItem(quoteTracker).toString();
    }
    public static String getPreviousQuote()
    {
        quoteTracker --;
        if(quoteTracker < 0)
        {
            quoteTracker = quoteListAdapter.getCount()-1;
        }
        return quoteListAdapter.getItem(quoteTracker).toString();
    }

}
