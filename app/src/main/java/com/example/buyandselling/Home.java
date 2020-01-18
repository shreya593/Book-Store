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
    private DatabaseReference databaseReference;
    private ImageAdapter mAdapter;



    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sell, container, false);
        recyclerView=view.findViewById(R.id.recycler_view);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setLayoutManager(layoutManager);
       // recyclerView.setHasFixedSize(true);
        mUploads=new ArrayList<>();
        databaseReference= FirebaseDatabase.getInstance().getReference("image");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren() )
                {
                    Student student=postSnapshot.getValue(Student.class);
                    mUploads.add(student);
                  mAdapter=new ImageAdapter(getContext(),mUploads);
                  recyclerView.setAdapter(mAdapter);



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });






        return view;
    }
}