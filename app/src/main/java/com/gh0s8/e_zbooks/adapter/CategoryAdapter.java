package com.gh0s8.e_zbooks.adapter;

import android.content.Context;
import android.icu.util.ULocale;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gh0s8.e_zbooks.R;
import com.gh0s8.e_zbooks.model.Catagory;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CaegoryViewHolder> {



    private List<Catagory> categories ;

    private Context context;


    public CategoryAdapter(List<Catagory> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }






    @NonNull
    @Override
    public CaegoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.order_lisr_item,parent,false);
        return new CaegoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CaegoryViewHolder holder, int position) {



        Catagory catagory = categories.get(position);
        holder.name.setText(catagory.getName());


        Glide.with(context)
                .load(catagory.getImagePath().isEmpty() ? R.drawable.book : catagory.getImagePath())
                .override(100,100)
                .into(holder.image);



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, holder.name.getText(), Toast.LENGTH_SHORT).show();
            }
        });





    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class CaegoryViewHolder extends RecyclerView.ViewHolder{


        View itemView;
        ImageView image;
        TextView name;



        public CaegoryViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
