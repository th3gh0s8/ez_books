package com.gh0s8.e_zbooks.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gh0s8.e_zbooks.R;
import com.gh0s8.e_zbooks.model.Catagory;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private FirebaseFirestore firebaseFirestore;



    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




//        firebaseFirestore = FirebaseFirestore.getInstance();
//        insertData();


    }
//
//    private void insertData() {
//
//
//
//
//        CollectionReference reference = firebaseFirestore.collection("categories");
//
//
//        List<Catagory> catagories = new ArrayList<>();
//        catagories.add(new Catagory(1,"",""));
//        catagories.add(new Catagory(1,"",""));
//        catagories.add(new Catagory(1,"",""));
//        catagories.add(new Catagory(1,"",""));
//        catagories.add(new Catagory(1,"",""));
//
//
//        reference.add(catagories);
//
//
//
//
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}