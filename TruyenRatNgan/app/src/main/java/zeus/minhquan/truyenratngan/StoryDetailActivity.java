package zeus.minhquan.truyenratngan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import zeus.minhquan.truyenratngan.adapter.ChapterAdapter;
import zeus.minhquan.truyenratngan.databases.model.Story;

public class StoryDetailActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private Story story;
    ChapterAdapter chapterAdapter;
    private static final float MIN_SCALE = 0.85f;
    private static final float MIN_ALPHA = 0.5f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);

        viewPager = (ViewPager) findViewById(R.id.vp_chapter);
        getStory();
        setUpUI();
    }

    private void setUpUI() {
        viewPager.setAdapter(new ChapterAdapter(getSupportFragmentManager()).setStory(this.story));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                StoryApplication.getInstance().getStoryDatabase().updateChapterNo(story.getId(), position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        Story st = StoryApplication.getInstance().getStoryDatabase().getStoryByID(story.getId());
        if (st.getLastChapterNo() != -1) {
            viewPager.setCurrentItem(st.getLastChapterNo());
        }
    }

    public void getStory() {
        Intent intent = getIntent();
        this.story = (Story) intent.getSerializableExtra("Story");
        Log.d("Inten", story.toString());
    }
}
