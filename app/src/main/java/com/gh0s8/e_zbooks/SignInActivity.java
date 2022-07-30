package com.gh0s8.e_zbooks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity {



    private static final String TAG ="E_ZBooks";
    private FirebaseAuth firebaseAuth;
    private EditText editEmail,editPassword;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_EZbooks_FullScreen);
        setContentView(R.layout.activity_sign_in);

        firebaseAuth = FirebaseAuth.getInstance();

        editEmail =findViewById(R.id.login_email);
        editPassword =findViewById(R.id.login_password);



        findViewById(R.id.login_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String email = editEmail.getText().toString();
                String password = editPassword.getText().toString();

                firebaseAuth
                        .signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            Log.i(TAG, "signInWithEmail:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            updateUI(user);

                        }else {
                            Log.w(TAG,"signInWithEmail:failure",task.getException());
                            Toast.makeText(SignInActivity.this,"Authentication failed",Toast.LENGTH_LONG).show();
                        }


                    }
                });

            }

        });
        findViewById(R.id.login_btn_singup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();


            }
        });




    }


    private void updateUI(FirebaseUser user) {

        Intent intent = new Intent(SignInActivity.this,MainActivity.class);
        startActivity(intent);
        finish();

    }
}