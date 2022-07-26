package com.example.photodraw;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import com.dsphotoeditor.sdk.activity.DsPhotoEditorActivity;
import com.dsphotoeditor.sdk.utils.DsPhotoEditorConstants;
import java.io.File;

public class EditPhotoActivity extends AppCompatActivity {

    ImageView imgeit;
    String path = "";
    Uri inputImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_photo);
        imgeit = (ImageView) findViewById(R.id.imgeit);

        path = getIntent().getExtras().getString("path");
        File imgFile = new File(path);
        if (imgFile.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            imgeit.setImageBitmap(myBitmap);
        }

        inputImageUri = Uri.fromFile(new File(path));
        edit_trial();

    }

    public void edit_trial () {
        Intent dsPhotoEditorIntent = new Intent(this, DsPhotoEditorActivity.class );
        dsPhotoEditorIntent.setData(inputImageUri);

        // set edited image to directory
        dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_OUTPUT_DIRECTORY,"Photo Directory");
        int[] toolsToHide = {DsPhotoEditorActivity.TOOL_ORIENTATION,DsPhotoEditorActivity.TOOL_CROP };

        dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_TOOLS_TO_HIDE,toolsToHide);
        //noinspection
        startActivityForResult(dsPhotoEditorIntent,200);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_OK) {
            switch (requestCode) {
                case 200 : Uri outputUri = data.getData();
                // set Edited Image
                imgeit.setImageURI(outputUri);
                break;
            }
        }
    }
}