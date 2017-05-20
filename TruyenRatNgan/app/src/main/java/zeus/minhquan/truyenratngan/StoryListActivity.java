package zeus.minhquan.truyenratngan;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import zeus.minhquan.truyenratngan.fragments.StoryListFragment;

public class StoryListActivity extends AppCompatActivity {

    private final String TAG = "StoryListActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayStartScreeen();
    }

    private void displayStartScreeen() {
        // 1: tao fragment
        StoryListFragment storyListFragment = new StoryListFragment();
        // 2: create a transaction
        changeScreen(storyListFragment, false);


    }

    public void changeScreen(Fragment fragment , boolean addToBackStack){
        FragmentManager fragmentManager =  getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fl_main, fragment);
        if(addToBackStack){
            transaction.addToBackStack(null);
        }

        //3: Commit
        transaction.commit();
    }


}
