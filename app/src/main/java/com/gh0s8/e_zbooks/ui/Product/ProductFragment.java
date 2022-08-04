package com.gh0s8.e_zbooks.ui.Product;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.gh0s8.e_zbooks.R;
import com.gh0s8.e_zbooks.adapter.ProductAdapter;
import com.gh0s8.e_zbooks.model.Catagory;
import com.gh0s8.e_zbooks.model.Product;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class ProductFragment extends Fragment {

    private  static final String TAG = "E_ZBooks";
    private FirebaseFirestore firebaseFirestore;
    private GridView productView;
    private Catagory catagory;
    private List<Product> products;


    public ProductFragment(Catagory catagory) {

        this.catagory = catagory;
    }

    public ProductFragment(int contentLayoutId) {
        super(contentLayoutId);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        firebaseFirestore =FirebaseFirestore.getInstance();
        products = new ArrayList<>();




        //insertData();


    }

//    @Override
//    public void onStart() {
//        super.onStart();
//
//        MainActivity activity=(MainActivity) getActivity();
//        activity.showBotoomNavigationViwe(false);
//
//
//
//    }



    private void insertData() {




        CollectionReference products = firebaseFirestore.collection("product");


        List<Product> productList = new ArrayList<>();
        productList.add(new Product("p1","Fantacy Novel","","default entry","1",3000));
        productList.add(new Product("p1","Horror Novel","","default entry","2",3500));
        productList.add(new Product("p1","History Novel","","default entry","4",2000));



        for (Product p: productList){

            products.add(p);
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        MainActivity activity =(MainActivity) getActivity();
//
//        activity.showBottomNavigationView(false);



        return inflater.inflate(R.layout.fragment_product, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        productView = view.findViewById(R.id.product_list);

        ProductAdapter adapter = new ProductAdapter(view.getContext(),products);
        productView.setAdapter(adapter);

        if (this.catagory != null){

            firebaseFirestore.collection("product").whereEqualTo("category",this.catagory.getId()).get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    if(task.isSuccessful()){

                        products.clear();



                        for (QueryDocumentSnapshot snapshot : task.getResult()){

                            Product product = snapshot.toObject(Product.class);
                            products.add(product);

                        }

                        adapter.notifyDataSetChanged();

                        if (task.getResult().isEmpty()){


                            productView.setVisibility(View.GONE);
                            view.findViewById(R.id.product_text).setVisibility(View.VISIBLE);


                        }




                    }







                }
            });

            productView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Product product = products.get(i);
                    FragmentManager fragmentManager = getParentFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.add(R.id.container,new ProductDetailsFragment(product));
                    transaction.addToBackStack(null);
                    transaction.commit();

                }
            });

            productView.setOnScrollListener(new AbsListView.OnScrollListener(){

                @Override
                public void onScrollStateChanged(AbsListView absListView, int i) {



                }

                @Override
                public void onScroll(AbsListView absListView, int fristVisibleItem, int visibelItemCount, int totalItemCount) {

                    final int lastItem = fristVisibleItem + visibelItemCount;
                    if(lastItem == totalItemCount){



//                      Long.i(TAG,"Bottom.. ");

                        //load more [roducts like pagination



//                        view.findViewById(R.id.product_message).setVisibility(View.VISIBLE);



//                        products.add(new Product("p1","Fantacy Novel","","default entry","1",3000));
//                        products.add(new Product("p1","Horror Novel","","default entry","2",3500));
//                        products.add(new Product("p1","History Novel","","default entry","4",2000));
//
//
//                        adapter.notifyDataSetChanged();
//
//                        view.findViewById(R.id.product_message).setVisibility(View.INVISIBLE);

                    }

                }




            });
        }










    }






}