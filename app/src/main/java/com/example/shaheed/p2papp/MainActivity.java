package com.example.shaheed.p2papp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<QuotesClass> mList;
    ArrayList<QuotesClass> arrayList;
    RecyclerView recyclerView;
    ListAdapter listAdapter;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        arrayList = new ArrayList<>();
        listAdapter = new ListAdapter(mList);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);

        FirebaseApp.initializeApp(this);

        db.collection("Quotes").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {

                Log.d("LOG", "onEvent: " + queryDocumentSnapshots.toString());
                
                for (DocumentChange doc: queryDocumentSnapshots.getDocumentChanges()){

                    if (doc.getType() == DocumentChange.Type.ADDED){

                        QuotesClass QC = new QuotesClass(doc.getDocument().getString("Quote"),
                                doc.getDocument().getString("Author"));
                        mList.add(QC);

                        listAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main2, menu);

        MenuItem resource = menu.findItem(R.id.action_call);
        MenuItem articles = menu.findItem(R.id.action_articles);
        MenuItem quotes = menu.findItem(R.id.action_quotes);

        articles.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent i = new Intent(MainActivity.this, ArticlesActivity.class);
                startActivity(i);
                return false;
            }
        });

        quotes.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                startActivity(i);
                return false;
            }
        });

        resource.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent m = new Intent(MainActivity.this, Resources.class);
                startActivity(m);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}