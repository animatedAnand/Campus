package com.example.campus.ui.notice;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.campus.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NoticeFragment extends Fragment {
    private RecyclerView rv_notice;
    private ProgressBar pb;
    private ArrayList<NoticeData> list;
    private NoticeAdapter adapter;
    private DatabaseReference ref;
@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_notice, container, false);

    rv_notice=view.findViewById(R.id.rv_notice);
    pb=view.findViewById(R.id.pb_notice);
    rv_notice.setLayoutManager(new LinearLayoutManager(getContext()));
    rv_notice.setHasFixedSize(true);
    ref= FirebaseDatabase.getInstance().getReference().child("Notice");
    getNotice();



        return view;
    }
    private void getNotice() {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list =new ArrayList<NoticeData>();
                for(DataSnapshot cur_ss:snapshot.getChildren())
                {
                    NoticeData data=cur_ss.getValue(NoticeData.class);
                    list.add(data);
                }
                pb.setVisibility(View.GONE);
                adapter=new NoticeAdapter(getContext(),list);
                adapter.notifyDataSetChanged();
                rv_notice.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                pb.setVisibility(View.GONE);
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}