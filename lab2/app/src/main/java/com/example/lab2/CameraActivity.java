package com.example.lab2;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraActivity extends AppCompatActivity implements  SurfaceHolder.Callback{

    SurfaceView surfaceView;
    static Camera camera = null;
    SurfaceHolder surfaceHolder;
    Button button;
    Camera.PictureCallback pictureCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        surfaceView = findViewById(R.id.cameraV);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        button = findViewById(R.id.takePhoto);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                camera.takePicture(null, null, pictureCallback);
            }
        });

        pictureCallback = new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {

                Bitmap bmp = BitmapFactory.decodeByteArray(data, 0,data.length);
                Bitmap bmpNou = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(),null, true);
                String pathFileName = DataForm();
                savePhoto(bmpNou, pathFileName);
                Toast.makeText(getApplicationContext(), "Salvat!", Toast.LENGTH_LONG).show();
                CameraActivity.this.camera.startPreview();

            }
        };
    }


    private String DataForm() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HH_MM_ss");
        String currentTime = dateFormat.format(new Date());
        return currentTime;

    }

    private void savePhoto(Bitmap bmpNou, String pathFileName) {
        File outputFile = new File(Environment.getExternalStorageDirectory(), "/sdcard/" + pathFileName + ".jpg");
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
            bmpNou.compress(Bitmap.CompressFormat.JPEG, 200, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        camera = Camera.open();
        Camera.Parameters parameters;
        parameters = camera.getParameters();
        parameters.setPreviewFrameRate(25);
        parameters.setPreviewSize(300,250);
        camera.setParameters(parameters);
        camera.setDisplayOrientation(90);
        try{
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        camera.stopPreview();
        camera.release();

    }



}