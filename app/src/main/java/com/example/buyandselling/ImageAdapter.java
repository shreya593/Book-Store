package com.example.buyandselling;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> implements Filterable {
    public Context mContext;
    public List<Student> mUploads;
    public List<Student> mUploadscopy;
    public ImageAdapter(Context context, List<Student> uploads) {
        mContext = context;
        mUploads = uploads;
       mUploadscopy = new ArrayList<>(mUploads);
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
        return new ImageViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        Student uploadCurrent = mUploads.get(position);
        holder.bookname.setText(uploadCurrent.getBookname());
        holder.price.setText(uploadCurrent.getPrice());
        Picasso.get()
                .load(uploadCurrent.getmImageUrl())
                .placeholder(R.drawable.preview)
                .fit()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

        public class ImageViewHolder extends RecyclerView.ViewHolder {
        public TextView bookname;
        public TextView price;
        public ImageView imageView;
        public ImageViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent =new Intent(v.getContext(),Bookdetails.class);
                    intent.putExtra("book_name", mUploads.get(getAdapterPosition()).getBookname());
                    intent.putExtra("book_image", mUploads.get(getAdapterPosition()).getmImageUrl());
                    intent.putExtra("seller_name", mUploads.get(getAdapterPosition()).getmName());
                    intent.putExtra("phone", mUploads.get(getAdapterPosition()).getmSellernumber());
                    intent.putExtra("email", mUploads.get(getAdapterPosition()).getmSelleremail());
                    intent.putExtra("address", mUploads.get(getAdapterPosition()).getmSelleraddress());
                    intent.putExtra("bookdescp", mUploads.get(getAdapterPosition()).getBookdescpt());
                    v.getContext().startActivity(intent);
                }
            });
            bookname = itemView.findViewById(R.id.text_view_name);
            price = itemView.findViewById(R.id.price);
            imageView = itemView.findViewById(R.id.image_view_upload);
        }
    }

   @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private  Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Student> filteredList = new ArrayList<>();
            if(constraint ==null || constraint.length()== 0){
                filteredList.addAll(mUploadscopy);
            } else{
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Student item : mUploadscopy){
                    if(item.getBookname().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return  results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
        mUploads.clear();
         mUploads.addAll((List)results.values);
         notifyDataSetChanged();
        }
    };
}