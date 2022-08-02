package com.gh0s8.e_zbooks.ui.profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gh0s8.e_zbooks.R;

import com.gh0s8.e_zbooks.model.Catagory;
import com.gh0s8.e_zbooks.ui.settings.ChangePasswordFragment;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;


public class ProfileFragment extends Fragment {


    //private FirebaseFirestore firebaseFirestore;


    public ProfileFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //firebaseFirestore =FirebaseFirestore.getInstance();







       // insertData();

    }

//    private void insertData() {
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
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);

//        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.profile_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new ChangePasswordFragment());
            }
        });
    }


    public void loadFragment(Fragment fragment) {

        FragmentManager supFragMan = getParentFragmentManager();
        FragmentTransaction fragoTran = supFragMan.beginTransaction();
        fragoTran.add(R.id.container, fragment);
        fragoTran.addToBackStack("account");

        fragoTran.commit();
    }


}