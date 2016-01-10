package com.wardell.fightquotes.fragments;

import android.app.ListFragment;
import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.wardell.fightquotes.R;
import com.wardell.fightquotes.adapters.QuoteListAdapter;

/**
 * Created by wardell on 1/9/16.
 */
public class QuoteListFragment extends ListFragment implements AdapterView.OnItemClickListener {
    private Firebase quoteRef;
    private static final String TAG = "Quote List Fragment";
    private Context context;
    private QuoteListAdapter quoteListAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quote_list, container, false);
        quoteRef = new Firebase("https://glaring-fire-7430.firebaseio.com/quotes");
        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
        // Setup our view and list adapter. Ensure it scrolls to the bottom as data changes
        final ListView listView = getListView();
        quoteListAdapter = new QuoteListAdapter(quoteRef,getActivity(),R.layout.listview_quote_item);

        listView.setAdapter(quoteListAdapter);
        quoteListAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                listView.setSelection(quoteListAdapter.getCount() - 1);
            }
        });
        getListView().setOnItemClickListener(this);


    }
    @Override
    public void onStop(){
        super.onStop();
        quoteListAdapter.cleanup();
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

        Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT)
                .show();

    }
}