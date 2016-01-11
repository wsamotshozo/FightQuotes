package com.wardell.fightquotes;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.wardell.fightquotes.fragments.QuoteFragment;
import com.wardell.fightquotes.fragments.QuoteListFragment;
import com.wardell.fightquotes.receivers.QuotePlayerReceiver;
import com.wardell.fightquotes.utils.Constants;

import java.util.HashMap;
import java.util.Map;

public class QuoteListActivity extends AppCompatActivity implements QuoteFragment.QuoteListener {
    private Firebase quoteRef;
    private QuoteListFragment quoteList;
    //private ListView listView;
    //private ListAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_list);
        //Firebase.setAndroidContext(this);
        quoteRef = new Firebase("https://glaring-fire-7430.firebaseio.com/quotes");
        quoteList = (QuoteListFragment)getFragmentManager().findFragmentById(R.id.quotelist);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if(id == R.id.action_add_quote )
        {
            FragmentManager manager = getFragmentManager();
            Fragment frag = manager.findFragmentByTag("fragment_add_quote");
            if (frag != null) {
                manager.beginTransaction().remove(frag).commit();
            }

            QuoteFragment addQuoteDialog = new QuoteFragment();
            addQuoteDialog.show(manager, "fragment_add_quote");
        }
        else if(id == R.id.action_show_quote)
        {

            //createNotification();
            //createNotification(Constants.getQuote());
            showQuotes();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFinishQuoteDialog(String quote_Input) {
        quoteRef.push().setValue(quote_Input);
        Toast.makeText(this, quote_Input + " was added to the list of quotes", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onFinishEditQuoteDialog(String quote_Input, String id){
        Map<String, Object> quote = new HashMap<String, Object>();
        quote.put(id,quote_Input);
        quoteRef.updateChildren(quote);
        Toast.makeText(this,  quote_Input + " was edited", Toast.LENGTH_SHORT).show();
    }
    public void showQuotes(){
        Intent intent = new Intent();
        intent.setAction("com.wardell.fightquotes.play");
        sendBroadcast(intent);
    }
}
