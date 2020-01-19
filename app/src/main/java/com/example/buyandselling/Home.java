package com.example.buyandselling;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment {
    private RecyclerView recyclerView;
    private List<Student> mUploads;
    private ImageAdapter mAdapter;
    private DatabaseReference databaseReference;




    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, container, false);
        recyclerView=view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mUploads=new ArrayList<>();
        databaseReference= FirebaseDatabase.getInstance().getReference("image");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Student upload = postSnapshot.getValue(Student.class);
                    mUploads.add(upload);
                }

                mAdapter=new ImageAdapter(getContext(),mUploads);
                  recyclerView.setAdapter(mAdapter);




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });






        return view;
    }
}