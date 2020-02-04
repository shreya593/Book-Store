package com.example.buyandselling;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import static android.app.Activity.RESULT_OK;

public class sell extends Fragment {
    public StorageReference Folder;
    public DatabaseReference imagestore;
private static final int ImageBack=1;

Button upload;
ImageButton imageView;
EditText name,email,phone,address,bookdescpt,bookname,price;
TextView presshere;
public Uri ImageData;
ProgressBar mProgressBar;


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ImageBack){
           if(resultCode == RESULT_OK){

                ImageData = data.getData();
             //  Picasso.get().load(ImageData).into(imageView);
               Picasso.get()
                       .load(ImageData)
                       .fit()
                       .into(imageView);
               presshere.setText("");


           }

        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  =inflater.inflate(R.layout.sell,container,false);

        presshere=view.findViewById(R.id.presshere);
        upload = (Button) view.findViewById(R.id.upload);
        imageView=(ImageButton) view.findViewById(R.id.imageview);
        name=(EditText)view.findViewById(R.id.name);
        email=(EditText)view.findViewById(R.id.email);
        phone=(EditText)view.findViewById(R.id.phone);
        address=(EditText)view.findViewById(R.id.address);
        bookdescpt=(EditText)view.findViewById(R.id.bookdescpt);
        bookname = (EditText)view.findViewById(R.id.bookname);
        price = (EditText)view.findViewById(R.id.price);
        mProgressBar=view.findViewById(R.id.progressBar);
        Animation animation= AnimationUtils.loadAnimation(getActivity(),R.anim.anim1);
        presshere.startAnimation(animation);
        Folder = FirebaseStorage.getInstance().getReference().child("ImageFolder");
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,ImageBack);


            }
        });
upload.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mProgressBar.setVisibility(View.VISIBLE);
                mProgressBar.setProgress(0);
            }
        },0);
        final StorageReference Imagename = Folder.child("image"+ImageData.getLastPathSegment());
        Imagename.putFile(ImageData).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                //     Toast.makeText(,"upload succesful",Toast.LENGTH_SHORT).show();
                taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        imagestore   = FirebaseDatabase.getInstance().getReference("image");

                        Student student=new Student(name.getText().toString().trim(),uri.toString(),phone.getText().toString(),email.getText().toString(),address.getText().toString(),bookdescpt.getText().toString(),bookname.getText().toString(),price.getText().toString());
                      // hashMap.put("url",String.valueOf(uri));
                        //hashMap.put("name",name.getText().toString());
                    //    hashMap.put("email",email.getText().toString());
                      //  hashMap.put("phone", phone.getText().toString());
                       // hashMap.put("address",address.getText().toString());
                        //hashMap.put("bookdescpt", bookdescpt.getText().toString());


                        String uploadId = imagestore.push().getKey();
                        imagestore.child(uploadId).setValue(student).addOnSuccessListener(
                                new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                mProgressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(getContext(),"Uploaded",Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                });


            }
        });
    }
});
      return  view;
    }

}
