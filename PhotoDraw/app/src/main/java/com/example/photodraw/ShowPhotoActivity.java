package com.example.photodraw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

public class ShowPhotoActivity extends AppCompatActivity {

    ImageView imageView;
    Button btn_edit;
    String path = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_photo);

        imageView = (ImageView)findViewById(R.id.imageview);
        btn_edit = (Button) findViewById(R.id.btn_edit);

        path = getIntent().getExtras().getString("path");
        File imgFile = new File(path);

        if (imgFile.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            imageView.setImageBitmap(RotateBitmap(myBitmap,90));
        }

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // open file to other activity
                Intent intent = new Intent(ShowPhotoActivity.this,EditPhotoActivity.class);
                intent.putExtra("path",path);
                startActivity(intent);
            }
        });
    }

    public static Bitmap RotateBitmap(Bitmap source,float angel) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angel);
        return Bitmap.createBitmap(source,0,0,source.getWidth(),source.getHeight(),matrix,true);

    }
}