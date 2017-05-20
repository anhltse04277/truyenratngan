package zeus.minhquan.truyenratngan.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import zeus.minhquan.truyenratngan.StoryApplication;
import zeus.minhquan.truyenratngan.databases.StoryDatabase;
import zeus.minhquan.truyenratngan.fragments.ChapterFragment;
import zeus.minhquan.truyenratngan.databases.model.Story;

/**
 * Created by AnhLT on 5/14/2017.
 */

public class ChapterAdapter extends FragmentStatePagerAdapter {
    private Story story;
    private StoryDatabase storyDatabase;
    private List<Integer> chapterIds;

    public ChapterAdapter(FragmentManager fm) {
        super(fm);
        storyDatabase = StoryApplication.getInstance().getStoryDatabase();
    }

    public ChapterAdapter setStory(Story story) {
        this.story = story;
        this.chapterIds = storyDatabase.loadChapterIds(story);
        return this;
    }

    @Override
    public Fragment getItem(int position) {
        int chapterId = chapterIds.get(position);
        ChapterFragment chapterFragment = new ChapterFragment();
        chapterFragment.setChapterId(chapterId);
        return chapterFragment;
    }

    @Override
    public int getCount() {
        return storyDatabase.getChapterCount(story);
    }
}
