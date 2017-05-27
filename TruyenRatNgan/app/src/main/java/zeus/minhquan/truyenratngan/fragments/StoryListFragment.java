package zeus.minhquan.truyenratngan.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import zeus.minhquan.truyenratngan.R;
import zeus.minhquan.truyenratngan.StoryApplication;
import zeus.minhquan.truyenratngan.MainActivity;
import zeus.minhquan.truyenratngan.adapter.StoryAdapter;
import zeus.minhquan.truyenratngan.databases.StoryDatabase;
import zeus.minhquan.truyenratngan.databases.model.Story;


public class StoryListFragment extends Fragment {

    private List<Story> storyList;
    private ListView lvStory;
    private StoryAdapter storyAdapter;

    public StoryListFragment() {

    }


    // TODO: Rename and change types and number of parameters
    public static StoryListFragment newInstance(String param1, String param2) {
        StoryListFragment fragment = new StoryListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_story_list, container, false);
        lvStory = (ListView) view.findViewById(R.id.lv_story_list);
        loadData();
        setupUI();
        return view ;
    }


    private void setupUI() {
        storyAdapter = new StoryAdapter(storyList);
        //connect adapter to listview and model
        lvStory.setAdapter(storyAdapter);
        //add event
        lvStory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO :CHANGE SCREEN
                ((MainActivity)   getActivity()).changeScreen(new StoryDetailFragment().setStory(storyList.get(position)),true);
            }
        });
    }

    private void loadData() {
        StoryDatabase storyDatabase = StoryApplication.getInstance().getStoryDatabase();
        storyList = storyDatabase.loadAllStories();
    }
}
