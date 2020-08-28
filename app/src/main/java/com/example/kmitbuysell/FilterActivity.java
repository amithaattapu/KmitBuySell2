package com.example.kmitbuysell;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class FilterActivity extends AppCompatActivity {
  Button button;
  RadioGroup radioGroup,radioGroup1,radioGroup2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        button=findViewById(R.id.button);
        radioGroup=findViewById(R.id.rg1);
        radioGroup1=findViewById(R.id.rg2);
        radioGroup2=findViewById(R.id.rg3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                if(radioGroup.getCheckedRadioButtonId()!=-1)
                {
                    int id=radioGroup.getCheckedRadioButtonId();
                    RadioButton radioButton=findViewById(id);
                    String branch=radioButton.getText().toString();
                       bundle.putString("branch",branch);

                }
                else
                {
                    bundle.putString("branch","");
                }
                if(radioGroup1.getCheckedRadioButtonId()!=-1)
                {
                    int id=radioGroup1.getCheckedRadioButtonId();
                    RadioButton radioButton=findViewById(id);
                    String year=radioButton.getText().toString();
                    bundle.putString("year",year);
                }
                else
                {
                    bundle.putString("year","");
                }
                if(radioGroup2.getCheckedRadioButtonId()!=-1)
                {
                    int id=radioGroup2.getCheckedRadioButtonId();
                    RadioButton radioButton=findViewById(id);
                    String type=radioButton.getText().toString();
                    bundle.putString("type",type);
                }
                else
                {
                    bundle.putString("type","");
                }
                Intent intent=new Intent(FilterActivity.this,BooksActivity.class);
                intent.putExtras(bundle);
                intent.putExtra("flag",2);
                startActivity(intent);
                finish();
            }
        });
    }
}
