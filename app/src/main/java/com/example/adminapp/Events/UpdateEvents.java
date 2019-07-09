package com.example.adminapp.Events;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.adminapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.HashMap;

public class UpdateEvents extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    EditText event_description,event_title;
    private ImageView imageView;
    private String randon;
    private static final int galleryPick=1;

    private Uri resultImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_events);
        /*getting id*/
        event_description=(EditText)findViewById(R.id.event_des);
        event_title=(EditText)findViewById(R.id.event_title);
        imageView=(ImageView)findViewById(R.id.image_event);
        /*getting reference*/
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Events");
        storageReference= FirebaseStorage.getInstance().getReference().child("EventImages");
        /*taking image from galery*/
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery=new Intent();
                gallery.setAction(Intent.ACTION_GET_CONTENT);
                gallery.setType("image/*");
                startActivityForResult(gallery,galleryPick);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        Toast.makeText(getApplicationContext()," error22222"+resultCode,Toast.LENGTH_SHORT).show();
        if(requestCode==galleryPick && resultCode==RESULT_OK && data!=null){
            Uri imageUri=data.getData();
            CropImage.activity(imageUri)
                    .setAspectRatio(1,1)
                    .start(this);
            Toast.makeText(getApplicationContext()," 1",Toast.LENGTH_SHORT).show();
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if(resultCode==RESULT_OK){
                resultImage=result.getUri();
                imageView.setImageURI(resultImage);

            }
            else if(resultCode==CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                Exception error=result.getError();
                Toast.makeText(getApplicationContext()," error"+error,Toast.LENGTH_SHORT).show();
            }
        }

    }
//    public static String random() {
//        Random generator = new Random();
//        StringBuilder randomStringBuilder = new StringBuilder();
//        int randomLength = generator.nextInt(10);
//        char tempChar;
//        for (int i = 0; i < randomLength; i++){
//            tempChar = (char) (generator.nextInt(96) + 32);
//            randomStringBuilder.append(tempChar);
//        }
//        return randomStringBuilder.toString();
//    }

    public void NewEvent(View view){
        final String title=event_title.getText().toString();
        final String des=event_description.getText().toString();
        event_title.setText("");
        event_description.setText("");
        imageView.setImageURI(null);
        if(title.isEmpty() || des.isEmpty()){
            Toast.makeText(this,"Please Enter all Fields",Toast.LENGTH_SHORT).show();
        }
        else{
//            randon=random();
            StorageReference filePath=storageReference.child("profile_images").child(title+".jpg");
            filePath.putFile(resultImage).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if(task.isSuccessful()){
                        task.getResult().getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                HashMap<String,Object> data=new HashMap<>();
                                data.put("EventTitle",title);
                                data.put("EventDescription",des);
//                                data.put("Image",uri);
                                String uri1=uri.toString();
                                data.put("Image",uri1);
                                databaseReference.child(title).updateChildren(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(getApplicationContext(),"Successfuly store",Toast.LENGTH_SHORT).show();
                                        }
                                        else{
                                            Toast.makeText(getApplicationContext(),"Not Store",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                Toast.makeText(getApplicationContext(),"uri is "+title,Toast.LENGTH_SHORT).show();
                                Toast.makeText(getApplicationContext(),"uri is "+uri,Toast.LENGTH_SHORT).show();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(),"uri is "+e,Toast.LENGTH_SHORT).show();
                            }
                        });
//                        HashMap<String,Object> data=new HashMap<>();
//                        data.put("EventTitle",title);
//                        data.put("EventDescription",des);
//                        data.put("Image",download_url);
//                        databaseReference.child(randon).updateChildren(data).addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                if(task.isSuccessful()){
//                                    Toast.makeText(getApplicationContext(),"Successfuly store",Toast.LENGTH_SHORT).show();
//                                }
//                                else{
//                                    Toast.makeText(getApplicationContext(),"Not Store",Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });

                    }else{
                        Toast.makeText(getApplicationContext()," not done",Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }
}
