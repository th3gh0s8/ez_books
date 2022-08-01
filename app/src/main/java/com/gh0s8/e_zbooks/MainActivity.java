package com.gh0s8.e_zbooks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gh0s8.e_zbooks.model.User;
import com.gh0s8.e_zbooks.ui.home.HomeFragment;
import com.gh0s8.e_zbooks.ui.libry.LybryFragment;
import com.gh0s8.e_zbooks.ui.profile.ProfileFragment;
import com.gh0s8.e_zbooks.ui.wishilist.WishListFragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, NavigationBarView.OnItemSelectedListener {

    private static final String TAG ="E_ZBooks";
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private MaterialToolbar toolbar;
    private BottomNavigationView bottomNavigationView;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setTheme(R.style.Theme_EZbooks__FullScreen);
        setContentView(R.layout.activity_main);


        drawerLayout =findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar =findViewById(R.id.toolbar);
        bottomNavigationView =findViewById(R.id.bottom_navigation);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore =FirebaseFirestore.getInstance();




//        if (firebaseAuth.getCurrentUser() != null){
//
//            UpdateUI(firebaseAuth.getCurrentUser());
//            Log.i(TAG,firebaseAuth.getUid());
//
//        }else {
//
//            Log.i(TAG,"No user");
//
//        }



        UpdateUI(firebaseAuth.getCurrentUser());



        setSupportActionBar(toolbar);

        ActionBarDrawerToggle aToggle =new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(aToggle);
        aToggle.syncState();






        navigationView.setNavigationItemSelectedListener(this);
        bottomNavigationView.setOnItemSelectedListener(this);

        loadFragment(new HomeFragment());
    }

    private void UpdateUI(FirebaseUser currentUser) {

        if (currentUser != null){


            Menu menu = navigationView.getMenu();
            menu.findItem(R.id.side_nave_profile).setVisible(true);
            menu.findItem(R.id.side_nave_order).setVisible(true);
            menu.findItem(R.id.side_nave_wishlist).setVisible(true);

            menu.findItem(R.id.side_nave_login).setVisible(false);
            menu.findItem(R.id.side_nave_logout).setVisible(true);



            View headerView = navigationView.getHeaderView(0);
            ImageView imageViewProfile = headerView.findViewById(R.id.profileImg);

            TextView textViewName = headerView.findViewById(R.id.uname);
            TextView textEmail = headerView.findViewById(R.id.usr_email);

            imageViewProfile.setVisibility(View.VISIBLE);
            textViewName.setVisibility(View.VISIBLE);
            textEmail.setVisibility(View.VISIBLE);

            headerView.findViewById(R.id.hedder_title).setVisibility(View.GONE);

            firebaseFirestore.collection("users").whereEqualTo("id",currentUser.getUid()).get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if(task.isSuccessful()){

                                        for(QueryDocumentSnapshot snapshot : task.getResult()){

                                            User user = snapshot.toObject(User.class);

                                            textViewName.setText(user.getName());
                                            textEmail.setText(user.getEmail());


                                            Glide.with(MainActivity.this)
                                                    .load(R.drawable.books)
                                                    .circleCrop()
                                                    .override(100,100)
                                                    .into(imageViewProfile);



                                        }


                                    }
                                }
                            });

//            textViewName.setText("");
////            textEmail.setText(currentUser.getEmail());
////
////            Glide.with(this)
////                    .load(R.drawable.books)
////                    .circleCrop()
////                    .override(100,100)
////                    .into(imageViewProfile);




        }

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){

            drawerLayout.closeDrawer(GravityCompat.START);

        }else {

            int entryCount = getSupportFragmentManager().getBackStackEntryCount();
            Log.i(TAG,entryCount+"");

            if (entryCount > 0 ){

                getSupportFragmentManager().popBackStack();

            }else {

                super.onBackPressed();

            }

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.side_nave_home:
                loadFragment(new HomeFragment());
                break;
            case  R.id.bot_nav_home:
                loadFragment(new HomeFragment());
                break;
            case R.id.side_nave_profile:
                if (firebaseAuth.getCurrentUser() != null){

                    loadFragment(new ProfileFragment());
//                    break;

                }else {

                    startActivity(new Intent(MainActivity.this,SignInActivity.class));
//                    break;

                }
                break;
            case  R.id.bot_nav_prof:
                if (firebaseAuth.getCurrentUser() != null){

                    loadFragment(new ProfileFragment());
//                    break;

                }else {


                    Intent intent = new Intent(MainActivity.this,SignInActivity.class);
                    startActivity(intent);

//                    break;


                }
                break;
            case R.id.side_nave_order:

                break;
            case R.id.side_nave_wishlist:
                loadFragment(new WishListFragment());
                break;
            case R.id.side_nave_setings:
                break;
            case R.id.side_nave_login:

                Intent intent = new Intent(MainActivity.this,SignInActivity.class);
                startActivity(intent);

                break;
            case R.id.side_nave_logout:

                firebaseAuth.signOut();
                finish();
                startActivity(getIntent());
                break;

            case R.id.bot_nav_libry:
                if (firebaseAuth.getCurrentUser() != null){

                    loadFragment(new LybryFragment());
                    break;
                }else {

                    startActivity(new Intent(MainActivity.this,SignInActivity.class));
//                    break;
                }
                break;


        }
        return true;
    }

    public void loadFragment(Fragment fragment){

        FragmentManager supFragMan =getSupportFragmentManager();
        FragmentTransaction fragoTran = supFragMan.beginTransaction();
        fragoTran.replace(R.id.container,fragment);
        fragoTran.commit();

        //sohort version
        // getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}