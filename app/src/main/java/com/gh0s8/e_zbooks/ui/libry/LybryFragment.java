package com.gh0s8.e_zbooks.ui.libry;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gh0s8.e_zbooks.R;
import com.gh0s8.e_zbooks.adapter.CategoryAdapter;
import com.gh0s8.e_zbooks.model.Catagory;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class LybryFragment extends Fragment {


    private FirebaseFirestore firebaseFirestore;
    private RecyclerView recyclerView;
    private  List<Catagory> catagoryList;



    public LybryFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        firebaseFirestore = FirebaseFirestore.getInstance();

        catagoryList = new ArrayList<>();








        //insertData();


    }

    private void insertData() {


        CollectionReference reference = firebaseFirestore.collection("categories");


        List<Catagory> catagories = new ArrayList<>();
        catagories.add(new Catagory("1","Fantacy",""));
        catagories.add(new Catagory("2","Novel",""));
        catagories.add(new Catagory("3","Education",""));
        catagories.add(new Catagory("4","History",""));
        catagories.add(new Catagory("5","religious",""));

//        List<Catagory> catagories = new ArrayList<>();
//        catagories.add(new Catagory(1,"Fantacy",""));
//        catagories.add(new Catagory(2,"Novel",""));
//        catagories.add(new Catagory(3,"Education",""));
//        catagories.add(new Catagory(4,"History",""));
//        catagories.add(new Catagory(5,"religious",""));

        //reference.add(catagories);



        for (Catagory c : catagories){


            reference.add(c);



        }





    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lybry, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.order_res_view);
        recyclerView.setLayoutManager(new LinearLayoutManager (view.getContext()));
        CategoryAdapter adapter =new CategoryAdapter(catagoryList, view.getContext() );
        recyclerView.setAdapter(adapter);

        firebaseFirestore.collection("categories").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()){


                    catagoryList.clear();

                    for (QueryDocumentSnapshot snapshot : task.getResult()){



                        Catagory catagory = snapshot.toObject(Catagory.class);
                        catagoryList.add(catagory);



                    }


                    adapter.notifyDataSetChanged();



                }


            }
        });





    }
}

