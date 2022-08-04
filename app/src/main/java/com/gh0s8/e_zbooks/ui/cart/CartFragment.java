package com.gh0s8.e_zbooks.ui.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gh0s8.e_zbooks.adapter.CartAdapter;
import com.gh0s8.e_zbooks.model.CartItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.gh0s8.e_zbooks.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;


public class CartFragment extends Fragment {
    private static final String TAG = "eshop";
    private FirebaseFirestore firebaseFirestore;
    private FirebaseStorage storage;
    private FirebaseAuth firebaseAuth;
    private RecyclerView cartRecyclerView;
    private List<CartItem> cartItems;
    private NumberFormat formatter = new DecimalFormat("LKR #,###.##");

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseFirestore = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        cartItems = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        cartRecyclerView = view.findViewById(R.id.cart_item_list);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        CartAdapter cartAdapter = new CartAdapter(cartItems, view.getContext(), this);
        cartRecyclerView.setAdapter(cartAdapter);

        firebaseFirestore.collection("users").whereEqualTo("id", firebaseAuth.getCurrentUser().getUid())
                .limit(1)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot snapshot : task.getResult()) {

                                snapshot.getReference().collection("cart")
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    cartItems.clear();

                                                    for (QueryDocumentSnapshot snapshot : task.getResult()) {
                                                        CartItem cartItem = snapshot.toObject(CartItem.class);
                                                        cartItems.add(cartItem);
                                                    }
                                                    calculateTotal();
                                                    cartAdapter.notifyDataSetChanged();

                                                }
                                            }
                                        });

                            }
                        }

                    }
                });
    }


    public void calculateTotal() {

        double total = 0.00;

        for (CartItem cartItem : cartItems) {
            total += cartItem.getProduct().getPrice() * cartItem.getQty();
        }

        TextView totalView = getView().findViewById(R.id.cart_total);
        totalView.setText(formatter.format(total));



    }

}