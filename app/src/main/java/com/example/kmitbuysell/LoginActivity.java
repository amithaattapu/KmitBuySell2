package com.example.kmitbuysell;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private EditText email,pass;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=(EditText)findViewById(R.id.email);
        pass=(EditText)findViewById(R.id.pass);
        mAuth = FirebaseAuth.getInstance();
    }

    public void Clicktosignup(View view) {
        Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
        Intent intent10=getIntent();
        int flag= (int) intent10.getExtras().get("flag");
        intent.putExtra("flag",flag);
        if(flag==2)
            intent.putExtra("userid",intent10.getExtras().get("userid").toString());
        startActivity(intent);

    }


    private void signIn(String email, String password) {

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            FirebaseUser user = mAuth.getCurrentUser();
                            //if(user.isEmailVerified()){

                                Toast.makeText(LoginActivity.this,"OK LOGIN SUCCESS",Toast.LENGTH_SHORT).show();
                                Intent intent=getIntent();
                               int flag= (int) intent.getExtras().get("flag");
                               //Toast.makeText(LoginActivity.this,"z"+flag,Toast.LENGTH_LONG).show();
                               Intent inten;
                               switch(flag) {
                                   case 1:inten = new Intent(LoginActivity.this, HomeActivity.class);
                                          startActivity(inten);
                                          finish();
                                          break;
                                   case 2:inten = new Intent(LoginActivity.this, MessageActivity.class);
                                       Intent intent1=getIntent();
                                       String userid=intent1.getExtras().get("userid").toString();
                                       inten.putExtra("userid",userid);
                                       startActivity(inten);
                                       finish();
                                       break;
                                   case 3:inten=new Intent(LoginActivity.this,SellActivity.class);
                                       startActivity(inten);
                                       finish();
                                         break;

                               }

                        } else {

                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // [START_EXCLUDE]
                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // [END_EXCLUDE]
                    }
                });
        // [END sign_in_with_email]
    }

    public void Login(View view) {
        if(email.getText().toString().isEmpty())
            Toast.makeText(LoginActivity.this,"Please enter email",Toast.LENGTH_LONG).show();
        else if(pass.getText().toString().isEmpty())
            Toast.makeText(LoginActivity.this,"Please enter password",Toast.LENGTH_LONG).show();
        else
        signIn(email.getText().toString(),pass.getText().toString());
    }

}
