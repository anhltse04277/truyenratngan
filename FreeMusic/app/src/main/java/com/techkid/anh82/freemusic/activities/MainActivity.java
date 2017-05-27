package com.techkid.anh82.freemusic.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.techkid.anh82.freemusic.R;
import com.techkid.anh82.freemusic.networks.MediaType;
import com.techkid.anh82.freemusic.networks.MusicType;
import com.techkid.anh82.freemusic.networks.MusicTypeService;
import com.techkid.anh82.freemusic.networks.RetrofitFactory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MusicTypeService musicTypeService = RetrofitFactory.getInstance().createService(MusicTypeService.class);

        musicTypeService.getMusictype().enqueue(new Callback<List<MediaType>>() {
            @Override
            public void onResponse(Call<List<MediaType>> call, Response<List<MediaType>> response) {
                MediaType mediaType = response.body().get(3);

                List<MusicType> musicTypeList = mediaType.getSubgenres();

                for(MusicType musicType : musicTypeList){
                    Log.d("Musc ID ", "id: " + musicType.getId() );
                }
            }

            @Override
            public void onFailure(Call<List<MediaType>> call, Throwable t) {

            }
        });
    }
}
