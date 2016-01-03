package com.wardell.fightquotes.utils;

import com.firebase.client.Firebase;

/**
 * Created by wardell on 1/2/16.
 */
public class Constants {
    public static String firebaseURL = "https://glaring-fire-7430.firebaseio.com/";
    public static Firebase firebaseRef = new Firebase(firebaseURL);
}
