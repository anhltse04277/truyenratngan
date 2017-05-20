package com.techkid.anh82.randomquote.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.techkid.anh82.randomquote.R;
import com.techkid.anh82.randomquote.network.ImageLoader;
import com.techkid.anh82.randomquote.network.QuateLoader;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuoteFragment extends Fragment {

    TextView tvQuate;
    ImageView ivQuate;

    public QuoteFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quote, container, false);
        tvQuate = (TextView) view.findViewById(R.id.tv_quote);
        ivQuate = (ImageView) view.findViewById(R.id.imv_background);

        new QuateLoader(tvQuate).execute();
        (new ImageLoader()).setView(ivQuate, this.getContext()).loadImage("https://source.unsplash.com/random/");
        return view;
    }

}
