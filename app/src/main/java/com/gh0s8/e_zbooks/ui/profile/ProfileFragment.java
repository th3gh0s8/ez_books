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

import com.gh0s8.e_zbooks.ui.settings.ChangePasswordFragment;


public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

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
        fragoTran.replace(R.id.container, fragment);
        fragoTran.addToBackStack("account");

        fragoTran.commit();
    }


}