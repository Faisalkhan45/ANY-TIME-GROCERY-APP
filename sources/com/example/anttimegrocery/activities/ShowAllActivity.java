package com.example.anttimegrocery.activities;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.anttimegrocery.R;
import com.example.anttimegrocery.adapters.ShowAllAdapter;
import com.example.anttimegrocery.models.ShowAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;

public class ShowAllActivity extends AppCompatActivity {
    FirebaseFirestore firestore;
    RecyclerView recyclerView;
    List<ShowAllModel> showAllModelList;
    ShowAllAdapter showallAdapter;
    Toolbar toolbar;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_show_all);
        Toolbar toolbar2 = (Toolbar) findViewById(R.id.show_all_toolbar);
        setSupportActionBar(toolbar2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar2.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ShowAllActivity.this.finish();
            }
        });
        String type = getIntent().getStringExtra("type");
        this.firestore = FirebaseFirestore.getInstance();
        RecyclerView recyclerView2 = (RecyclerView) findViewById(R.id.show_all_rec);
        this.recyclerView = recyclerView2;
        recyclerView2.setLayoutManager(new GridLayoutManager(this, 2));
        this.showAllModelList = new ArrayList();
        ShowAllAdapter showAllAdapter = new ShowAllAdapter(this, this.showAllModelList);
        this.showallAdapter = showAllAdapter;
        this.recyclerView.setAdapter(showAllAdapter);
        this.firestore.collection("ShowAll").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            public void onComplete(Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                        ShowAllActivity.this.showAllModelList.add((ShowAllModel) doc.toObject(ShowAllModel.class));
                        ShowAllActivity.this.showallAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
        if (type == null || type.isEmpty()) {
            this.firestore.collection("ShowAll").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                public void onComplete(Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                            ShowAllActivity.this.showAllModelList.add((ShowAllModel) doc.toObject(ShowAllModel.class));
                            ShowAllActivity.this.showallAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }
        if (type != null && type.equalsIgnoreCase("shoes")) {
            this.firestore.collection("ShowAll").whereEqualTo("type", (Object) "shoes").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                public void onComplete(Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                            ShowAllActivity.this.showAllModelList.add((ShowAllModel) doc.toObject(ShowAllModel.class));
                            ShowAllActivity.this.showallAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }
        if (type != null && type.equalsIgnoreCase("camera")) {
            this.firestore.collection("ShowAll").whereEqualTo("type", (Object) "camera").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                public void onComplete(Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                            ShowAllActivity.this.showAllModelList.add((ShowAllModel) doc.toObject(ShowAllModel.class));
                            ShowAllActivity.this.showallAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }
        if (type != null && type.equalsIgnoreCase("watch")) {
            this.firestore.collection("ShowAll").whereEqualTo("type", (Object) "watch").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                public void onComplete(Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                            ShowAllActivity.this.showAllModelList.add((ShowAllModel) doc.toObject(ShowAllModel.class));
                            ShowAllActivity.this.showallAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }
        if (type != null && type.equalsIgnoreCase("men")) {
            this.firestore.collection("ShowAll").whereEqualTo("type", (Object) "men").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                public void onComplete(Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                            ShowAllActivity.this.showAllModelList.add((ShowAllModel) doc.toObject(ShowAllModel.class));
                            ShowAllActivity.this.showallAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }
        if (type != null && type.equalsIgnoreCase("kids")) {
            this.firestore.collection("ShowAll").whereEqualTo("type", (Object) "kids").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                public void onComplete(Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                            ShowAllActivity.this.showAllModelList.add((ShowAllModel) doc.toObject(ShowAllModel.class));
                            ShowAllActivity.this.showallAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }
        if (type != null && type.equalsIgnoreCase("woman")) {
            this.firestore.collection("ShowAll").whereEqualTo("type", (Object) "woman").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                public void onComplete(Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                            ShowAllActivity.this.showAllModelList.add((ShowAllModel) doc.toObject(ShowAllModel.class));
                            ShowAllActivity.this.showallAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }
    }
}
