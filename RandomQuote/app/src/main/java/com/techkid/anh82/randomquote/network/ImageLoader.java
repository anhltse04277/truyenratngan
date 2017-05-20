package com.techkid.anh82.randomquote.network;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;

import com.techkid.anh82.randomquote.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


/**
 * Created by AnhLT on 5/14/2017.
 */

public class ImageLoader extends AsyncTask<String,Void,Bitmap> {
    public ImageLoader() {
    }

    private ImageView view;
    private String urlString;
    private String imageTag;

    private int IMAGE_WIDTH;
    private int IMAGE_HEIGHT;


    public ImageLoader setView(ImageView view , Context context) {
        this.view = view;
        this.imageTag = (view.getTag()==null)? "" : view.getTag().toString();
        if (Build.VERSION.SDK_INT >= 11) {
            Point size = new Point();
            try {
                ((Activity)context).getWindowManager().getDefaultDisplay().getRealSize(size);
                IMAGE_WIDTH = size.x;
                IMAGE_HEIGHT = size.y;
            } catch (NoSuchMethodError e) {
            }
        } else {
            DisplayMetrics metrics = new DisplayMetrics();
            ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
            IMAGE_WIDTH = metrics.widthPixels;
            IMAGE_HEIGHT = metrics.heightPixels;
        }

        return this;
    }
    public void loadImage(String urlString){
        if(!urlString.equals(imageTag)){
            view.setImageResource(R.drawable.animation_rotation_loading);
            execute(urlString);
        }
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        urlString = params[0];
        //1: open connection
        if(urlString.equals(imageTag)){
            return null;
        }
        try{
            urlString = urlString + IMAGE_WIDTH + "x" + IMAGE_HEIGHT;
            Log.d("chieu rong la", IMAGE_WIDTH+"");
            URL url = new URL(urlString);
            //2Get Stream
            InputStream inputStream = url.openStream();

            //3: get bitmap
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        //4: display bitmap
        if(bitmap!=null) {
            view.setImageBitmap(bitmap);
            view.setScaleType(ImageView.ScaleType.FIT_XY);
            view.setTag(urlString);
        }
    }
}
