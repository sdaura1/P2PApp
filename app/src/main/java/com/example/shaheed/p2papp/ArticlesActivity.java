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

import java.util.ArrayList;
import java.util.List;

public class ArticlesActivity extends AppCompatActivity {

    List<ArticleClass> mList;
    ArrayList<ArticleClass> arrayList;
    RecyclerView recyclerView;
    ArticleAdapter articleAdapter;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);

        mList = new ArrayList<>();
        recyclerView = findViewById(R.id.articleRecyclerView);
        arrayList = new ArrayList<>();
        articleAdapter = new ArticleAdapter(mList);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(articleAdapter);

        FirebaseApp.initializeApp(this);

        db.collection("Articles").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {

                Log.d("LOG", "onEvent: " + queryDocumentSnapshots.toString());

                for (DocumentChange doc: queryDocumentSnapshots.getDocumentChanges()){

                    if (doc.getType() == DocumentChange.Type.ADDED){

                        ArticleClass QC = new ArticleClass(doc.getDocument().getString("Article"),
                                doc.getDocument().getString("Author"));
                        mList.add(QC);

                        articleAdapter.notifyDataSetChanged();
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
                Intent i = new Intent(ArticlesActivity.this, ArticlesActivity.class);
                startActivity(i);
                return false;
            }
        });

        quotes.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent i = new Intent(ArticlesActivity.this, MainActivity.class);
                startActivity(i);
                return false;
            }
        });

        resource.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent m = new Intent(ArticlesActivity.this, Resources.class);
                startActivity(m);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
