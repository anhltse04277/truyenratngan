package com.techkid.anh82.freemusic.networks;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by anh82 on 5/23/2017.
 */

public interface MusicTypeService {
    @GET("data/media-types.json")
    Call<List<MediaType>> getMusictype();
}
