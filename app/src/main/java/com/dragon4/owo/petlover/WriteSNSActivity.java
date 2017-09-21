package com.dragon4.owo.petlover;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.dragon4.owo.petlover.model.Sns;
import com.dragon4.owo.petlover.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * Created by InKyung on 2017. 6. 16..
 */

public class WriteSNSActivity extends AppCompatActivity {

    private File destination = null;
    private Bitmap currentBitmap = null;
    private ImageView imageView;
    private EditText editText;
    private Button button;

    private final int REQUEST_IMAGE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_sns);
        imageView = (ImageView)findViewById(R.id.writeSNSImageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePictureCase();
            }
        });
        editText = (EditText)findViewById(R.id.writeSNSEditText);
        button = (Button) findViewById(R.id.upload_sns_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadDataServer();
            }
        });

    }

    private void uploadDataServer() {
        Sns sns = new Sns();
        sns.setUserName(User.getInstance().getUserName());
        sns.setUserImageUrl(User.getInstance().getUserPhotoURL());
        sns.setDate("17년 6월 15일");
        sns.setSnsText(editText.getText().toString());
        uploadImageToServer(sns);

        finish();
    }

    private void uploadImageToServer(final Sns sns) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        // Create a storage reference from our app
        StorageReference storageRef = storage.getReferenceFromUrl("gs://petlover-8bc3f.appspot.com");
        String fileRef = "images/" + User.getInstance().getUserID();
        StorageReference snsRef = storageRef.child(fileRef);

        ByteArrayOutputStream jpegOut = new ByteArrayOutputStream();
        currentBitmap.compress(Bitmap.CompressFormat.JPEG, 70, jpegOut);

        UploadTask uploadTask = snsRef.putBytes(jpegOut.toByteArray());

        // Register observers to listen for when the download is done or if it fails
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                sns.setSnsImageUrl(downloadUrl.toString());
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                database.getReference().child("sns").push().setValue(sns);

            }
        });

    }


    private void choosePictureCase() {

        final Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI) ;
        startActivityForResult(intent, REQUEST_IMAGE);
    }

    public int exifOrientationToDegrees(int exifOrientation)
    {
        if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_90)
        {
            return 90;
        }
        else if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_180)
        {
            return 180;
        }
        else if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_270)
        {
            return 270;
        }
        return 0;
    }

    public Bitmap rotate(Bitmap bitmap, int degrees)
    {
        if(degrees != 0 && bitmap != null)
        {
            Matrix m = new Matrix();
            m.setRotate(degrees, (float) bitmap.getWidth() / 2,
                    (float) bitmap.getHeight() / 2);

            try
            {
                Bitmap converted = Bitmap.createBitmap(bitmap, 0, 0,
                        bitmap.getWidth(), bitmap.getHeight(), m, true);
                if(bitmap != converted)
                {
                    bitmap.recycle();
                    bitmap = converted;
                }
            }
            catch(OutOfMemoryError ex)
            {
                // 메모리가 부족하여 회전을 시키지 못할 경우 그냥 원본을 반환합니다.
            }
        }
        return bitmap;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {

                // if python server on
                //uploadImageToPythonServer(Uri.fromFile(destination));
                final Uri selectedUri = data.getData();
                final String[] filepath={MediaStore.Images.Media.DATA} ;
                final Cursor imagecursor=  this.getContentResolver().query(selectedUri,filepath,null,null,null) ;
                imagecursor.moveToFirst() ;
                final int column = imagecursor.getColumnIndex(filepath[0]);
                final String imagepath = imagecursor.getString(column);
                imagecursor.close();
                currentBitmap = BitmapFactory.decodeFile(imagepath);
                imageView.setImageBitmap(currentBitmap);


            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "사진을 취소하셨습니다.", Toast.LENGTH_SHORT).show();
                destination.delete();
            }
        }
    }






}
