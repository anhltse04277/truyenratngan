package com.techkid.anh82.freemusic.networks;

import java.util.List;

/**
 * Created by anh82 on 5/23/2017.
 */

public class MediaType {
    private List<MusicType> subgenres;

    public List<MusicType> getSubgenres() {
        return subgenres;
    }

    public void setSubgenres(List<MusicType> subgenres) {
        this.subgenres = subgenres;
    }
}
