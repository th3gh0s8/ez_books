package com.gh0s8.e_zbooks.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gh0s8.e_zbooks.R;
import com.gh0s8.e_zbooks.model.Catagory;
import com.gh0s8.e_zbooks.ui.Product.ProductFragment;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {



    private List<Catagory> categories ;

    private Context context;


    public CategoryAdapter(List<Catagory> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }






    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.item_viwer,parent,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {



        Catagory category = categories.get(position);
        holder.catagory = category;
        holder.name.setText(category.getName());


        Glide.with(context)
                .load(category.getImagePath().isEmpty() ? R.drawable.book : category.getImagePath())
                .override(100,100)
                .into(holder.image);



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, holder.name.getText(), Toast.LENGTH_SHORT).show();

                FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.container, new ProductFragment(holder.catagory));
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });





    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder{


        View itemView;
        ImageView image;
        TextView name;
        Catagory catagory;


        public CategoryViewHolder(@NonNull View itemView) {


            super(itemView);


            image = itemView.findViewById(R.id.item_imge);
            name = itemView.findViewById(R.id.item_name);
            this.itemView = itemView;



        }
    }
}
