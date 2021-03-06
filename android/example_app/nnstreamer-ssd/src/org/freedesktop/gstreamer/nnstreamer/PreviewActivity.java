package org.freedesktop.gstreamer.nnstreamer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PreviewActivity extends Activity implements View.OnClickListener {
    private boolean initialized = false;

    private ImageView imageView_preview;

    private Button buttonSave;
    private Button buttonRetry;

    private Bitmap photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        initActivity();
    }

    private void initActivity() {
        if (initialized) {
            return;
        }

        /* set captured photo to ImageView */
        Intent intent = getIntent();

        byte[] arr = intent.getByteArrayExtra("photo");

        photo = BitmapFactory.decodeByteArray(arr,0, arr.length);

        imageView_preview = (ImageView) this.findViewById(R.id.preview_imageview);
        imageView_preview.setImageBitmap(photo);

        buttonSave = (Button) this.findViewById(R.id.preview_button_save);
        buttonSave.setOnClickListener(this);

        buttonRetry = (Button) this.findViewById(R.id.preview_button_retry);
        buttonRetry.setOnClickListener(this);

        initialized = true;
    }

    @Override
    public void onClick(View v) {
        final int viewId = v.getId();

        switch (viewId) {
            case R.id.preview_button_save:
                Date date = new Date(System.currentTimeMillis());
                SimpleDateFormat sdfNow = new SimpleDateFormat("yyyyMMdd_HHmmss");
                String formatDate = sdfNow.format(date);
                saveBitmaptoJpeg(photo, "Autocapture", formatDate);
                finish();
                break;
            case R.id.preview_button_retry:
                finish();
                break;
            default:
                break;
        }
    }

    public void saveBitmaptoJpeg(Bitmap bitmap,String folder, String name){
        String ex_storage = Environment.getExternalStorageDirectory().getAbsolutePath();
        // Get Absolute Path in External Sdcard
        String folder_name = "/DCIM/"+folder+"/";
        String file_name = name+".jpg";
        String string_path = ex_storage+folder_name;


        File file_path;
        try{
            file_path = new File(string_path);
            if(!file_path.isDirectory()){
                file_path.mkdirs();
            }
            FileOutputStream out = new FileOutputStream(string_path+file_name);
            boolean flag = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();

            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            File file = new File(string_path);
            intent.setData(Uri.fromFile(file));
            sendBroadcast(intent);

        }catch(FileNotFoundException exception){
            Log.e("FileNotFoundException", exception.getMessage());
        }catch(IOException exception){
            Log.e("IOException", exception.getMessage());
        }
    }
}
