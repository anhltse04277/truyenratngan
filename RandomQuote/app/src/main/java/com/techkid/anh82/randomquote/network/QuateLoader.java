package com.techkid.anh82.randomquote.network;

import android.graphics.Color;
import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import com.techkid.anh82.randomquote.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by anh82 on 5/16/2017.
 */

public class QuateLoader extends AsyncTask<Void, Void, String> {
    private final String QUOTE_URL ="http://quotesondesign.com/wp-json/posts?filter[orderby]=rand";
    private TextView textView;

    public QuateLoader(TextView textView) {
        this.textView =textView;
    }

    @Override
    protected String doInBackground(Void... params) {
        //1: Open Connection
        try {
            URL url = new URL(QUOTE_URL);
            //2:Open stream
            InputStream inputStream = url.openStream();
            //3: Get data
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream)) ;
            StringBuilder stringBuilder = new StringBuilder();
            String line = "";
            while((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line);
            }
            String content = stringBuilder.toString();
            Log.d("bLABLA" , content);
            //4:Decode data

            JSONArray jsonArray = new JSONArray(content);
            JSONObject jsonObject =jsonArray.getJSONObject(0);

            Log.d("do in back ground" , jsonObject.toString());

            String contentNew = jsonObject.getString("content");


            Log.d("content  " , contentNew);


            return  contentNew;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        textView.setText(Html.fromHtml(s));
        textView.setTextColor(Color.GRAY);

    }
}
