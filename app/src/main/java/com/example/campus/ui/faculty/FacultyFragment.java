package com.example.campus.ui.faculty;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.campus.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FacultyFragment extends Fragment {
    private RecyclerView rv_cs_faculty,rv_mba_faculty,rv_phd_faculty;
    private LinearLayout ll_no_cs_data,ll_no_mba_data,ll_no_phd_data;
    private List<FacultyData> cs_faculty_list,mba_faculty_list,phd_faculty_list;
    private DatabaseReference reference,cur_ref;
    private FacultyAdapter adapter;
@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_faculty, container, false);

    rv_cs_faculty=view.findViewById(R.id.rv_cs_faculty);
    rv_mba_faculty=view.findViewById(R.id.rv_mba_faculty);
    rv_phd_faculty=view.findViewById(R.id.rv_phd_faculty);
    ll_no_cs_data=view.findViewById(R.id.cv_no_cs_faculty_data);
    ll_no_mba_data=view.findViewById(R.id.cv_no_mba_data);
    ll_no_phd_data=view.findViewById(R.id.cv_no_phd_faculty);

    reference= FirebaseDatabase.getInstance().getReference().child("Faculty");
    update_cs_rv();
    update_mba_rv();
    update_phd_rv();
    return view;
    }

    private void update_cs_rv() {
        cur_ref=reference.child("Computer Science");
        cur_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cs_faculty_list=new ArrayList<>();
                if(!snapshot.exists())
                {
                    ll_no_cs_data.setVisibility(View.VISIBLE);
                    rv_cs_faculty.setVisibility(View.GONE);

                }
                else
                {
                    ll_no_cs_data.setVisibility(View.GONE);
                    rv_cs_faculty.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot1:snapshot.getChildren())
                    {
                        FacultyData data=snapshot1.getValue(FacultyData.class);
                        cs_faculty_list.add(data);
                    }
                    rv_cs_faculty.setHasFixedSize(true);
                    rv_cs_faculty.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter=new FacultyAdapter(cs_faculty_list,getContext(),"Computer Science");
                    rv_cs_faculty.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void update_mba_rv() {
        cur_ref=reference.child("MBA");
        cur_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mba_faculty_list=new ArrayList<>();
                if(!snapshot.exists())
                {
                    ll_no_mba_data.setVisibility(View.VISIBLE);
                    rv_mba_faculty.setVisibility(View.GONE);
                }
                else
                {
                    ll_no_mba_data.setVisibility(View.GONE);
                    rv_mba_faculty.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot1:snapshot.getChildren())
                    {
                        FacultyData data=snapshot1.getValue(FacultyData.class);
                        mba_faculty_list.add(data);
                    }
                    rv_mba_faculty.setHasFixedSize(true);
                    rv_mba_faculty.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter=new FacultyAdapter(mba_faculty_list,getContext(),"MBA");
                    rv_mba_faculty.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void update_phd_rv() {
        cur_ref=reference.child("PHD");
        cur_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                phd_faculty_list=new ArrayList<>();
                if(!snapshot.exists())
                {
                    ll_no_phd_data.setVisibility(View.VISIBLE);
                    rv_phd_faculty.setVisibility(View.GONE);
                }
                else
                {
                    ll_no_phd_data.setVisibility(View.GONE);
                    rv_phd_faculty.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot1:snapshot.getChildren())
                    {
                        FacultyData data=snapshot1.getValue(FacultyData.class);
                        phd_faculty_list.add(data);
                    }
                    rv_phd_faculty.setHasFixedSize(true);
                    rv_phd_faculty.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter=new FacultyAdapter(phd_faculty_list,getContext(),"PHD");
                    rv_phd_faculty.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}