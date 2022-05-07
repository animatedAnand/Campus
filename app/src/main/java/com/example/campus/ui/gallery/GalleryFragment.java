package com.example.campus.ui.gallery;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.campus.R;
import com.example.campus.ui.faculty.FacultyAdapter;
import com.example.campus.ui.faculty.FacultyData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {
    private RecyclerView rv_group_photos,rv_other_photos;
    private DatabaseReference reference,cur_ref;
    private ArrayList<String> group_photos_list,other_photos_list;
    private GalleryAdapter adapter;
@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_gallery, container, false);
        rv_group_photos=view.findViewById(R.id.rv_group_photos);
        rv_other_photos=view.findViewById(R.id.rv_other_photos);

    reference= FirebaseDatabase.getInstance().getReference().child("Gallery");

    get_group_photos();
    get_other_photos();


        return view;
    }

    private void get_group_photos() {
        cur_ref=reference.child("Group Photos");
        cur_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                group_photos_list=new ArrayList<>();
                for(DataSnapshot snapshot1:snapshot.getChildren())
                {
                    String data=(String)snapshot1.getValue();
                    group_photos_list.add(data);
                }
                rv_group_photos.setHasFixedSize(true);
                rv_group_photos.setLayoutManager(new GridLayoutManager(getContext(),3));
                adapter=new GalleryAdapter(group_photos_list,getContext());
                rv_group_photos.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void get_other_photos() {
        cur_ref=reference.child("Others");
        cur_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                other_photos_list=new ArrayList<>();
                for(DataSnapshot snapshot1:snapshot.getChildren())
                {
                    String data=(String)snapshot1.getValue();
                    other_photos_list.add(data);
                }
                rv_other_photos.setHasFixedSize(true);
                rv_other_photos.setLayoutManager(new GridLayoutManager(getContext(),3));
                adapter=new GalleryAdapter(other_photos_list,getContext());
                rv_other_photos.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}