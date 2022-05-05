package com.example.campus.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.campus.R;
public class HomeFragment extends Fragment {
    private ImageView iv_map_location;
@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        iv_map_location=view.findViewById(R.id.iv_map_location);
        iv_map_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                locate_college();
            }
        });

        return view;

    }

    private void locate_college() {
        Uri uri=Uri.parse("geo:0,0?q=IIITM Gwalior");
        Intent intent=new Intent(Intent.ACTION_VIEW,uri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }

}