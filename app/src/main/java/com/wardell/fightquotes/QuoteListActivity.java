package com.wardell.fightquotes;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.wardell.fightquotes.fragments.QuoteFragment;

public class QuoteListActivity extends AppCompatActivity implements QuoteFragment.QuoteListener {
    private Firebase quoteRef;
    private ListView listView;
    private ListAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_list);
        //Firebase.setAndroidContext(this);
        quoteRef = new Firebase("https://glaring-fire-7430.firebaseio.com/quotes");


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

            QuoteFragment editNameDialog = new QuoteFragment();
            editNameDialog.show(manager, "fragment_add_quote");
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFinishUserDialog(String quote_Input) {
        quoteRef.push().setValue(quote_Input);
        Toast.makeText(this,  quote_Input + " was added to the list of quotes", Toast.LENGTH_SHORT).show();
    }
}
