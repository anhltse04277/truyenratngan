package com.techkid.anh82.truyenratngan;

import android.app.Application;
import android.util.Log;

import com.techkid.anh82.truyenratngan.databases.StoryDatabase;

/**
 * Created by anh82 on 4/18/2017.
 */

public class StoryApplication extends Application {
    private StoryDatabase storyDatabase;
    private static  StoryApplication instance;

    @Override
    public void onCreate() {
        instance = this;
        storyDatabase = new StoryDatabase(this);
        super.onCreate();
        Log.d("Hello" , "oncreate");
    }

    public StoryDatabase getStoryDatabase(){
        return storyDatabase;
    }

    public static StoryApplication getInstance() {
        return instance;
    }
}
