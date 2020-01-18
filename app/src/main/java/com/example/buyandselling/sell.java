package com.example.buyandselling;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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

import java.util.HashMap;

import static android.app.Activity.RESULT_OK;

public class sell extends Fragment {
    public StorageReference Folder;
    public DatabaseReference imagestore;
private static final int ImageBack=1;

Button upload;
ImageButton imageView;
EditText name,email,phone,address,bookdescpt;
public Uri ImageData;



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ImageBack){
           if(resultCode == RESULT_OK){

                ImageData = data.getData();
               Picasso.get().load(ImageData).into(imageView);


           }

        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  =inflater.inflate(R.layout.sell,container,false);

        upload = (Button) view.findViewById(R.id.upload);
        imageView=(ImageButton) view.findViewById(R.id.imageview);
        name=(EditText)view.findViewById(R.id.name);
        email=(EditText)view.findViewById(R.id.email);
        phone=(EditText)view.findViewById(R.id.phone);
        address=(EditText)view.findViewById(R.id.address);
        bookdescpt=(EditText)view.findViewById(R.id.bookdescpt);
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
        final StorageReference Imagename = Folder.child("image"+ImageData.getLastPathSegment());
        Imagename.putFile(ImageData).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                //     Toast.makeText(,"upload succesful",Toast.LENGTH_SHORT).show();
                taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        imagestore   = FirebaseDatabase.getInstance().getReference("image");
                        HashMap<String,String> hashMap=new HashMap<>();
                        hashMap.put("imageurl",String.valueOf(uri));
                        hashMap.put("name",name.getText().toString());
                        hashMap.put("email",email.getText().toString());
                        hashMap.put("phone", phone.getText().toString());
                        hashMap.put("address",address.getText().toString());
                        hashMap.put("bookdescpt", bookdescpt.getText().toString());
                        String uploadId = imagestore.push().getKey();
                        imagestore.child(uploadId).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

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
