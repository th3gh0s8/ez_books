package com.gh0s8.e_zbooks.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gh0s8.e_zbooks.MainActivity;
import com.gh0s8.e_zbooks.R;
import com.gh0s8.e_zbooks.adapter.CategoryAdapter;
import com.gh0s8.e_zbooks.adapter.SliderAdapter;
import com.gh0s8.e_zbooks.model.Catagory;
import com.gh0s8.e_zbooks.model.SliderItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private FirebaseFirestore firebaseFirestore;
    private RecyclerView recyclerView;
    private  List<Catagory> catagoryList;



    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainActivity activity =(MainActivity) getActivity();
        activity.showBottomNavigationView(true);


        firebaseFirestore = FirebaseFirestore.getInstance();

        catagoryList = new ArrayList<>();








        //insertData();


    }

//    @Override
//    public void onStart() {
//        super.onStart();
//
//        MainActivity activity=(MainActivity) getActivity();
//        activity.showBotoomNavigationViwe(true);
//
//
//
//    }

    private void insertData() {


        CollectionReference reference = firebaseFirestore.collection("categories");


//        List<Catagory> catagories = new ArrayList<>();
//        catagories.add(new Catagory("1","Fantacy",""));
//        catagories.add(new Catagory("2","Novel",""));
//        catagories.add(new Catagory("3","Education",""));
//        catagories.add(new Catagory("4","History",""));
//        catagories.add(new Catagory("5","religious",""));

        List<Catagory> catagories = new ArrayList<>();
        catagories.add(new Catagory("1","Fantacy",""));
        catagories.add(new Catagory("2","Novel",""));
        catagories.add(new Catagory("3","Education",""));
        catagories.add(new Catagory("4","History",""));
        catagories.add(new Catagory("5","religious",""));

        //reference.add(catagories);



        for (Catagory c : catagories){


            reference.add(c);



        }





    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


//        MainActivity activity=(MainActivity) getActivity();
//        activity.showBottomNavigationView(true);


        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




        SliderView sliderView = view.findViewById(R.id.imageSlider);
        SliderAdapter sliderAdapter = new SliderAdapter(view.getContext());
        sliderAdapter.addItem(new SliderItem("", "https://icms-image.slatic.net/images/ims-web/5264c754-632f-47c8-8fdd-59eec28d61ad.jpg_1200x1200.jpg"));
        sliderAdapter.addItem(new SliderItem("", "https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/14d5f677630559.5c8d3005a7c9c.png"));
        sliderAdapter.addItem(new SliderItem("", "https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/da6c8077630559.5c8d3005a810f.png"));

        sliderView.setSliderAdapter(sliderAdapter);

        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setScrollTimeInSec(5);
        sliderView.startAutoCycle();






        recyclerView = view.findViewById(R.id.order_res_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
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