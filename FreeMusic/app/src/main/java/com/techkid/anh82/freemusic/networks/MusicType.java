package com.techkid.anh82.freemusic.networks;

import com.google.gson.annotations.SerializedName;

/**
 * Created by anh82 on 5/23/2017.
 */

public class MusicType {
    private String id;
   @SerializedName("translation_key")
    private String key;

    public String getId() {
        return id;
    }

    public String getKey() {
        return key;
    }
}
