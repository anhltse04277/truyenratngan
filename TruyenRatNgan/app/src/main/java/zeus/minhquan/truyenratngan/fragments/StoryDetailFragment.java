package zeus.minhquan.truyenratngan.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import zeus.minhquan.truyenratngan.R;
import zeus.minhquan.truyenratngan.StoryApplication;
import zeus.minhquan.truyenratngan.adapter.ChapterAdapter;
import zeus.minhquan.truyenratngan.databases.model.Story;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoryDetailFragment extends Fragment {
    private ViewPager viewPager;
    private Story story;
    ChapterAdapter chapterAdapter;

    public StoryDetailFragment() {
        // Required empty public constructor
    }


    public StoryDetailFragment setStory(Story story) {
        this.story = story;
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_story_detail, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.vp_chapter);

        setUpUI();
        return view ;
    }
    private void setUpUI() {
        viewPager.setAdapter(new ChapterAdapter(this.getFragmentManager()).setStory(this.story));
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



}
