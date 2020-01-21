package com.example.buyandselling;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class Home2 extends Fragment {
    ArrayList<Student>myArrayList=new ArrayList<>();
    ListView myListView;
    Firebase myFirebase;
    Student upload;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  =inflater.inflate(R.layout.newfile,container,false);

        Firebase.setAndroidContext(getContext());
        myFirebase=new Firebase("https://buy-and-selling-7137d.firebaseio.com/");

        final ArrayAdapter<Student>myArrayAdapter=new ArrayAdapter<Student>(getContext(),android.R.layout.simple_list_item_1,myArrayList);

       myListView=(ListView) view.findViewById(R.id.listview);

        myListView.setAdapter(myArrayAdapter);

       /* myFirebase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String myChildValues=dataSnapshot.getValue(String.class);

                myArrayList.add(myChildValues);
                myArrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                myArrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });*/
        myFirebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    upload = postSnapshot.getValue(Student.class);
                    myArrayList.add(upload);
                    myArrayAdapter.notifyDataSetChanged();


                }



            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        return  view;


    }
}