package com.wardell.fightquotes.receivers;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;

import com.wardell.fightquotes.R;
import com.wardell.fightquotes.utils.Constants;

/**
 * Created by wardell on 1/10/16.
 */
public class QuotePlayerReceiver extends BroadcastReceiver {
    private static final String TAG = "Quote Player Receiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(action.equals(Constants.namespace+"previous"))
        {
            createNotification(context,Constants.getPreviousQuote());
        }
        else if(action.equals(Constants.namespace+"next"))
        {
            createNotification(context,Constants.getNextQuote());
        }
        else{
            createNotification(context,Constants.getQuote());
        }
    }

    public void createNotification(Context context, String quote) {
        Intent prevReceive = new Intent();
        prevReceive.setAction(Constants.namespace +"previous");
        PendingIntent pendingIntentPrev = PendingIntent.getBroadcast(context, 0, prevReceive, PendingIntent.FLAG_UPDATE_CURRENT);


        Intent nextReceive = new Intent();
        nextReceive.setAction(Constants.namespace + "next");
        PendingIntent pendingIntentNext = PendingIntent.getBroadcast(context, 0, nextReceive, PendingIntent.FLAG_UPDATE_CURRENT);

        // Build notification
        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentText(quote);
        builder.setSmallIcon(android.R.drawable.btn_radio);
        builder.addAction(android.R.drawable.ic_media_previous, "Previous", pendingIntentPrev);
        builder.addAction(android.R.drawable.ic_media_next, "Next", pendingIntentNext);
        Notification quoteNotice = builder.build();

        // Create Notification Manager
        NotificationManager notificationmanager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        // Build Notification with Notification Manager
        notificationmanager.notify(0, quoteNotice);
    }
}