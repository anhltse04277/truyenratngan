package zeus.minhquan.truyenratngan.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import zeus.minhquan.truyenratngan.R;
import zeus.minhquan.truyenratngan.StoryApplication;
import zeus.minhquan.truyenratngan.databases.model.Story;
import zeus.minhquan.truyenratngan.networks.ImageLoader;

/**
 * Created by AnhLT on 5/7/2017.
 */

public class StoryAdapter extends BaseAdapter {

    private List<Story> storyList;

    public StoryAdapter(List<Story> storyList) {
        this.storyList = storyList;
    }

    @Override
    public int getCount() {
        return storyList.size();
    }

    @Override
    public Object getItem(int position) {
        return storyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //load item data(model)
        final Story story = storyList.get(position);

        //create view if neccessary (view)
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            convertView = layoutInflater.inflate(R.layout.item_story, parent, false);
        }


        //config and return
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
        TextView tvDes = (TextView) convertView.findViewById(R.id.tv_description);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_image_strory);
        final ImageView ivFav = (ImageView) convertView.findViewById(R.id.iv_fav);
        tvTitle.setText(story.getTitle());
        tvDes.setText(story.getDescription());
        if(story.isFavorite()){
            ivFav.setImageResource(R.drawable.ic_favorite_black_24dp);
        } else {
            ivFav.setImageResource(R.drawable.ic_favorite_border_black_24dp);
        }
        (new ImageLoader()).setView(imageView).loadImage(story.getImage());

        ivFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(story.isFavorite()){
                    ivFav.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                    StoryApplication.getInstance().getStoryDatabase().updateFavourite(story,0);
                    story.setFavorite(false);
                } else {
                    ivFav.setImageResource(R.drawable.ic_favorite_black_24dp);
                    StoryApplication.getInstance().getStoryDatabase().updateFavourite(story,1);
                    story.setFavorite(true);
                }
                notifyDataSetChanged();
            }
        });
        notifyDataSetChanged();
        return convertView;
    }
}
