package com.example.buyandselling;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class Bookdetails extends AppCompatActivity {
TextView book_name,seller_name,seller_email,seller_phone,address,bookdescpt;
ImageView book_image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookdetails);
        book_name=findViewById(R.id.bookname);
        seller_name=findViewById(R.id.seller_name);
        seller_email=findViewById(R.id.seller_email);
        seller_phone=findViewById(R.id.seller_phone);
        book_image=findViewById(R.id.book_image);
        address=findViewById(R.id.seller_address);
        bookdescpt=findViewById(R.id.book_descpt);
        Intent intent =getIntent();
        Picasso.get().load(intent.getStringExtra("book_image")).fit().into(book_image);
        seller_name.setText(intent.getStringExtra("seller_name"));
        seller_email.setText(intent.getStringExtra("email"));
        seller_phone.setText(intent.getStringExtra("phone"));
        address.setText(intent.getStringExtra("address"));
        bookdescpt.setText(intent.getStringExtra("bookdescpt"));
        book_name.setText(intent.getStringExtra("book_name"));

    }
}
