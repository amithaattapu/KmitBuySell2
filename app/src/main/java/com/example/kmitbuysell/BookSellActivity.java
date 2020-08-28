package com.example.kmitbuysell;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
//import com.google.firebase.storage.UploadTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class BookSellActivity extends AppCompatActivity {
    public static final int PICK_IMAGE_REQUEST=1;
    private Button mupload;
    private EditText mbookname,mprice,mbdescription;
    private ImageView mimage;
    private RadioGroup radioGroup,radioGroup1,radioGroup2;
    private Uri mimageuri;
    private StorageReference mstorageref;
    private DatabaseReference mdatabaseref;
    private Button post;
    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_sell);
        mupload=findViewById(R.id.upload);
        mbookname=findViewById(R.id.bookname);
        mimage=findViewById(R.id.imageView);
        mbdescription=findViewById(R.id.mbdesc);
        radioGroup=findViewById(R.id.branch);
        radioGroup1=findViewById(R.id.Year1);
        radioGroup2=findViewById(R.id.type);
        mprice=findViewById(R.id.price);
        post=findViewById(R.id.post);
        mstorageref= FirebaseStorage.getInstance().getReference().child("Photos");
        mdatabaseref= FirebaseDatabase.getInstance().getReference("uploads");
        mupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mbookname.getText().toString().isEmpty())
                {
                    Toast.makeText(BookSellActivity.this,"Please enter book name",Toast.LENGTH_LONG).show();

                }
                else if(mprice.getText().toString().isEmpty())
                {
                    Toast.makeText(BookSellActivity.this,"Please enter price",Toast.LENGTH_LONG).show();

                }
                else if(radioGroup.getCheckedRadioButtonId()==-1)
                {
                    Toast.makeText(BookSellActivity.this,"Please select branch",Toast.LENGTH_LONG).show();
                }
                else if(radioGroup1.getCheckedRadioButtonId()==-1)
                {
                    Toast.makeText(BookSellActivity.this,"Please select year",Toast.LENGTH_LONG).show();
                }
                else if(radioGroup2.getCheckedRadioButtonId()==-1)
                {
                    Toast.makeText(BookSellActivity.this,"Please select type",Toast.LENGTH_LONG).show();
                }
                else
                {
                Toast.makeText(BookSellActivity.this,"Your Ad is getting posted, please wait",Toast.LENGTH_LONG).show();
                uploadFile();}
            }
        });
    }
    private void openFileChooser()
    {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE_REQUEST && resultCode==RESULT_OK
                && data!=null && data.getData()!=null)
        {
            mimageuri=data.getData();
            Picasso.with(this).load(mimageuri).into(mimage);
        }
    }
    private  String getFileExtension(Uri uri)
    {
        ContentResolver cR=getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
    private void uploadFile()
    {

        if (mimageuri != null)
        {final StorageReference fileReference=mstorageref.child(System.currentTimeMillis()
                +"."+getFileExtension(mimageuri));
            fileReference.putFile(mimageuri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>()
            {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception
                {
                    if (!task.isSuccessful())
                    {
                        throw task.getException();
                    }
                    return fileReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>()
            {
                @Override
                public void onComplete(@NonNull Task<Uri> task)
                {
                    if (task.isSuccessful())
                    {

                    {Uri downloadUri = task.getResult();

                        //Log.e(TAG, "then: " + downloadUri.toString());
                        int t=radioGroup.getCheckedRadioButtonId();
                        RadioButton radioButton =findViewById(t);
                        int typ=radioGroup2.getCheckedRadioButtonId();
                        RadioButton radioButton2=findViewById(typ);
                        int year=radioGroup1.getCheckedRadioButtonId();
                        RadioButton radioButton1=findViewById(year);
                        String uid=firebaseUser.getUid();
                        Book book = new Book(
                                downloadUri.toString()
                                ,mbookname.getText().toString()
                                ,mprice.getText().toString()
                                ,radioButton.getText().toString()
                                ,mbdescription.toString()
                                ,radioButton2.getText().toString()
                        ,uid
                        ,radioButton1.getText().toString());

                        mdatabaseref.push().setValue(book);
                        Intent intent=new Intent(BookSellActivity.this,AdLiveActivity.class);
                        startActivity(intent);
                       finish();}
                    } else
                    {
                        Toast.makeText(BookSellActivity.this, "upload failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
