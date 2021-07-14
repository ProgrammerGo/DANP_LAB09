package com.labplatform.laboratorio09;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class CamTwoActivity extends AppCompatActivity {

    private static final int RC_CAPTURE = 0;
    private static final int RC_ACCESS = 1;
    private Uri fileURI;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cam_two);

        img = (ImageView) findViewById(R.id.imagen);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_CAPTURE){
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, new String [] {Manifest.permission.READ_EXTERNAL_STORAGE}, RC_ACCESS);
            }else{
                img.setImageBitmap(BitmapFactory.decodeFile(fileURI.getPath()));
            }
        }

    }
    public void takephoto ( View view){
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        Intent tomarFotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(tomarFotoIntent.resolveActivity(getPackageManager()) != null){
            fileURI = generarURI();
            tomarFotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileURI);
            startActivityForResult(tomarFotoIntent, RC_CAPTURE);
        }
    }

    private Uri generarURI() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(System.currentTimeMillis());
        String fileName = "IMG_"+timeStamp+".png";
        return Uri.fromFile(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), fileName));
    }

}