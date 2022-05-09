package com.example.campus.pdf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.campus.R;
import com.example.campus.ui.gallery.GalleryAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PdfActivity extends AppCompatActivity {
    private RecyclerView rv_pdf;
    private DatabaseReference reference;
    private ArrayList<PdfData>list;
    private PdfAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        rv_pdf=findViewById(R.id.rv_pdf);
        reference= FirebaseDatabase.getInstance().getReference().child("Files");
        getPdfs();
    }

    private void getPdfs() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list=new ArrayList<>();
                for(DataSnapshot snapshot1:snapshot.getChildren())
                {
                    PdfData data=snapshot1.getValue(PdfData.class);
                    list.add(data);
                }
                rv_pdf.setHasFixedSize(true);
                rv_pdf.setLayoutManager(new LinearLayoutManager(PdfActivity.this));
                adapter=new PdfAdapter(list,PdfActivity.this);
                rv_pdf.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(PdfActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}