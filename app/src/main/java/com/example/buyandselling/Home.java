package com.example.buyandselling;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Home extends Fragment {
    private RecyclerView recyclerView;
    private List<Student> mUploads;
    private ImageAdapter mAdapter;
    private DatabaseReference databaseReference;
    Student student;



    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, container, false);
        recyclerView=view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
       recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));//
        mUploads=new ArrayList<>();
        databaseReference= FirebaseDatabase.getInstance().getReference("image");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                 student    = postSnapshot.getValue(Student.class);

                    mUploads.add(student);
                }

                mAdapter=new ImageAdapter(getActivity(),mUploads);
                recyclerView.setAdapter(mAdapter);
}

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
  return view;


    }

   /* @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mUploads = new ArrayList<>();

        mAdapter=new ImageAdapter(getActivity(),mUploads);
        recyclerView.setAdapter(mAdapter);

    }
    public void onResponse(String response) {

        // Instead of setting adapter here...
        // TipAdapter tipAdapter = new TipAdapter(getActivity(), tipList);
        // recyclerView.setAdapter(tipAdapter);

        // Just notify the adapter that tipList has changed.
        recyclerView.getAdapter().notifyDataSetChanged();

        // ...
    }*/
}