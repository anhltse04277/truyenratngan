package com.techkid.anh82.truyenratngan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.techkid.anh82.truyenratngan.databases.StoryDatabase;
import com.techkid.anh82.truyenratngan.databases.models.Lich;
import com.techkid.anh82.truyenratngan.databases.models.Story;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class StoryListActivity extends AppCompatActivity {

    ListView lv;
    MyArrayAdapter adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       lv = (ListView) findViewById(R.id.lv1);

        StoryDatabase storyDatabase = StoryApplication.getInstance().getStoryDatabase();
        ArrayList<Lich> stories = (ArrayList<Lich>) storyDatabase.loadAllStories();
         String arr[] = new String[ stories.size() ];
        if(stories != null){
            for(int i = 0; i < stories.size(); i++){
               arr[i] = stories.get(i).toString();
                Log.d("hello" ,arr[0] + "");
            }
        }

//        ArrayAdapter<String>adapter=new ArrayAdapter<String>
//                (this, android.R.layout.simple_list_item_1, arr);
//        //4. Đưa Data source vào ListView
//        //lv.setAdapter(adapter);
//        //final TextView txt=(TextView) findViewById(R.id.lv1);
        adapter=new MyArrayAdapter(this, R.layout.my_item, stories);
        lv.setAdapter(adapter);



//        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, arr);
//        lv.setAdapter(adapter1);


        //2. Lấy đối tượng Listview dựa vào id

        //3. Gán Data source vào ArrayAdapter
//       ArrayAdapter<String> adapter=new ArrayAdapter<String>
//                (this, android.R.layout.simple_list_item_1, arr);
//        //4. Đưa Data source vào ListView
//        lv.setAdapter(adapter);

        //5. Thiết lập sự kiện cho Listview, khi chọn phần tử nào thì hiển thị lên TextView




    }
}
