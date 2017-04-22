package com.techkid.anh82.truyenratngan.databases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import com.techkid.anh82.truyenratngan.databases.models.Lich;
import com.techkid.anh82.truyenratngan.databases.models.Story;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anh82 on 4/18/2017.
 */

public class StoryDatabase extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "LichBongDa.db";
    private static final int DATABASE_VERSION = 1;

    private  static final String STORY_IMAGE= "image";
    private  static final String STORY_TITLE = "title";
    private  static final String STORY_DESCRIPTION = "description";
    private  static final String STORY_IS_FAVORITE= "is_favorite";

    private static final String NAME = "name";
    private static final String TIME = "time";
    private static final String IMAGE = "image";



    private static final String[] STORY_ALL_COLUMNS = new String[]{
            NAME,
            TIME,
            IMAGE
    };

    public StoryDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    public List<Lich> loadAllStories(){
        // get readable database , lay quyen dc doc
        List<Lich> stories = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        //query ==> cursor
        Cursor cursor = db.query("lich_tbl",STORY_ALL_COLUMNS, null, null, null,null, null);

        // Go through rows

        while(cursor.moveToNext()){
            String image = cursor.getString(cursor.getColumnIndex(NAME));
            String title = cursor.getString(cursor.getColumnIndex(TIME));
            String image2 = cursor.getString(cursor.getColumnIndex(IMAGE));

            stories.add(new Lich(image,title,image2));
//            Log.d("image", image);
//            Log.d("title", title);
//            Log.d("description", description);
//            Log.d("is_favorite", is_favorite + "");
        }
        cursor.close();
        db.close();
        return stories;
    }
}
