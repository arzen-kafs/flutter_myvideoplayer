package com.example.hikaioffline.walkthrough;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.hikaioffline.R;

import java.util.List;

public class IntroViewPagerAdapter extends PagerAdapter
{
    Context mcontext;
    List<ScreenItem>mListScreen;


    public IntroViewPagerAdapter(Context context, List<ScreenItem> mListScreen) {
        this.mcontext = context;
        this.mListScreen = mListScreen;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater=(LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutScreen= layoutInflater.inflate(R.layout.screen ,null);

        ImageView imgSlide=(ImageView) layoutScreen.findViewById(R.id.slideImage);
        TextView title=(TextView) layoutScreen.findViewById(R.id.slideHeading);
        TextView description=(TextView) layoutScreen.findViewById(R.id.slideDescription);

        title.setText(mListScreen.get(position).getTitle());
        description.setText(mListScreen.get(position).getDescription());
        imgSlide.setImageResource(mListScreen.get(position).getScreenImage());

        container.addView(layoutScreen);
        return layoutScreen;
    }

    @Override
    public int getCount() {
        return mListScreen.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
