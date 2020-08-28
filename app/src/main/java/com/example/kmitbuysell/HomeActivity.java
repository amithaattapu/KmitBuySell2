package com.example.kmitbuysell;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //setContentView(R.layout.activity_main);

        firebaseAuth=FirebaseAuth.getInstance();
        bottomNavigationView=findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.navigation_chats: Intent intent=new Intent(HomeActivity.this,ChatsActivity.class);
                    startActivity(intent);
                    case R.id.navigation_Ads: Intent intent1=new Intent(HomeActivity.this,MyAdsActivity.class);
                        startActivity(intent1);

                }
                return true;
            }
        });
        //FirebaseUser firebaseUser=null;

    }
    @Override
    protected void onResume()
    {
      invalidateOptionsMenu();
      super.onResume();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (firebaseAuth.getCurrentUser()!=null)
        getMenuInflater().inflate(R.menu.activity_main_actions,menu);
        else
            getMenuInflater().inflate(R.menu.activity_login_signup,menu);
        return true;
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu)
    {MenuItem login=findViewById(R.id.loginOrSignup);
     MenuItem logout=findViewById(R.id.Logout);
     MenuItem profile=findViewById(R.id.profile);
     MenuItem broadcast=findViewById(R.id.broadcast);
        /*if (firebaseAuth.getCurrentUser().getUid()!=null)
        {
            login.setEnabled(false).setVisible(false);
            logout.setEnabled(true).setVisible(true);
            profile.setEnabled(true).setVisible(true);
            broadcast.setEnabled(true).setVisible(true);
        }
        else*/
        /*{
            login.setEnabled(true).setVisible(true);
            logout.setEnabled(false).setVisible(false);
            profile.setEnabled(false).setVisible(false);
            broadcast.setEnabled(false).setVisible(false);
        }*/
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem menuItem)
    {
        switch (menuItem.getItemId())
        {
            case R.id.loginOrSignup:
                Intent intent=new Intent(HomeActivity.this,LoginActivity.class);
                intent.putExtra("flag",1);
                startActivity(intent);
                invalidateOptionsMenu();
                return true;
            case R.id.Logout:
                firebaseAuth.signOut();
                invalidateOptionsMenu();
                return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

        public void sell(View v)
    {if(firebaseAuth.getCurrentUser()==null)
    {
        Intent intent=new Intent(HomeActivity.this,LoginActivity.class);
        intent.putExtra("flag",3);
        startActivity(intent);
    }
    else {
        Intent intent = new Intent(HomeActivity.this, SellActivity.class);
        startActivity(intent);
    }
    }
    public void books(View v)
    {
        Intent intent=new Intent(HomeActivity.this,BooksActivity.class);
        intent.putExtra("flag",1);
        startActivity(intent);
    }

}
