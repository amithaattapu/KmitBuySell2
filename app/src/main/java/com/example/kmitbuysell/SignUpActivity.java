package com.example.kmitbuysell;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText email,pass,sname,roll;
    private Button b1;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.pass);
        sname = (EditText) findViewById(R.id.editTextName);
        roll = (EditText) findViewById(R.id.editTextRoll);
        String u = roll.getText().toString();
        b1=(Button)findViewById(R.id.signup);

    }

    private void validate(String name,String u,String email,String pass) {
        if (((u.length() == 10) && (u.charAt(2) == 'b' || u.charAt(2) == 'B') && (u.charAt(3) == 'd' || u.charAt(3) == 'D') &&
                (u.charAt(4) == '1' || u.charAt(4) == '5') && (u.charAt(5) == 'A' || u.charAt(5) == 'a') &&
                ((u.charAt(6) == '0' && u.charAt(7) == '5') ||
                        (u.charAt(6) == '1' && u.charAt(7) == '2') ||
                        (u.charAt(6) == '0' && u.charAt(7) == '4') ||
                        (u.charAt(6) == '1' && u.charAt(7) == '0')))&&(!u.isEmpty())&&(!name.isEmpty())&&(!email.isEmpty())&&(!pass.isEmpty()))
        {
            createAccount(email,pass,u,name);

        }
        else{
            Toast.makeText(SignUpActivity.this, "Enter correct Roll No!",
                    Toast.LENGTH_SHORT).show();
            if(u.isEmpty())
                Toast.makeText(SignUpActivity.this, "Enter your Roll No!",
                        Toast.LENGTH_SHORT).show();

            if(name.isEmpty())
                Toast.makeText(SignUpActivity.this, "Enter your Name!",
                        Toast.LENGTH_SHORT).show();

            if(email.isEmpty())
                Toast.makeText(SignUpActivity.this, "Enter your EmailId!",
                        Toast.LENGTH_SHORT).show();

            if(pass.isEmpty())
                Toast.makeText(SignUpActivity.this, "Enter your Password!",
                        Toast.LENGTH_SHORT).show();
        }
    }
    private void createAccount(final String email, final String password, final String u, final String name) {

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information





                            FirebaseUser user = mAuth.getCurrentUser();
                            sendEmailVerification();
                            Toast.makeText(SignUpActivity.this, "Registered Successfully.Verify your email",
                                    Toast.LENGTH_SHORT).show();
                            store(name,u,email,password);

                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }


                    }
                });
        // [END create_user_with_email]
    }
    private void sendEmailVerification() {
        // Disable button


        // Send verification email
        // [START send_email_verification]
        final FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // [START_EXCLUDE]
                        // Re-enable button

                        Log.v( "SSSS", String.valueOf(user.isEmailVerified()));

                        // [END_EXCLUDE]
                    }
                });
        // [END send_email_verification]
    }

    ///////////////////////
    private void store(final String sname,final String roll,final String email,String password) {



                        //    if (task.isSuccessful()) {

                        User use = new User(
                                sname,
                                roll,
                                email,
                                FirebaseAuth.getInstance().getCurrentUser().getUid()
                        );

                        FirebaseDatabase.getInstance().getReference("Users")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(use).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                //progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(SignUpActivity.this, "Registered Successfully.",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(SignUpActivity.this,LoginActivity.class);
                                    Intent inten=getIntent();
                                    int flag=inten.getIntExtra("flag",1);
                                    intent.putExtra("flag",flag);
                                    intent.putExtra("userid",inten.getExtras().get("userid").toString());
                                    startActivity(intent);
                                } else {
                                    //display a failure message
                                }
                            }
                        });

                 /*   } else {
                        Toast.makeText(Main2Activity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }*/




    }



    ////////////////////////
    public void ClickforSignup(View view) {
        validate(sname.getText().toString(),roll.getText().toString(),email.getText().toString(),pass.getText().toString());

    }


}
