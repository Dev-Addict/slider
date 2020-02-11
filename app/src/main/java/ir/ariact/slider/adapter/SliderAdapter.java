package ir.ariact.slider.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import ir.ariact.slider.R;

public class SliderAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater inflater;
    private  List<View> slides;

    public SliderAdapter(Context context, List<View> slides) {
        this.slides = slides;
        this.context = context;
    }

    @Override
    public int getCount() {
        return slides.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewPager viewPager = (ViewPager) container;
        assert inflater != null;
        @SuppressLint("InflateParams") View view = inflater.inflate(
                R.layout.single_slide_layout,
                null);
        FrameLayout frameLayout = view.findViewById(R.id.single_slide_layout_frame_layout);
        if (frameLayout.getParent() == null) {
            frameLayout.addView(slides.get(position));
            viewPager.addView(frameLayout, 0);
        }
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }
}
