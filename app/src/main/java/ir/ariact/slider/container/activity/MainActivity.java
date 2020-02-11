package ir.ariact.slider.container.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;


import ir.ariact.slider.adapter.SliderAdapter;

import java.util.ArrayList;
import java.util.List;

import ir.ariact.slider.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ViewPager viewPager;
    PagerAdapter sliderAdapter;
    ImageView nextImageView, prevImageView;

    List<View> slides;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.activity_main_slider_view_pager);
        slides = new ArrayList<>();
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        @SuppressLint("InflateParams")
        View slideOne = inflater.inflate(R.layout.slide_example, null);
        @SuppressLint("InflateParams")
        View slideTwo = inflater.inflate(R.layout.slide_example, null);
        @SuppressLint("InflateParams")
        View slideThree = inflater.inflate(R.layout.slide_example, null);
        ImageView slideOneImageView = slideOne.findViewById(R.id.slide_example_image_view);
        TextView slideOneDescriptionTextView = slideOne
                .findViewById(R.id.slide_example_description_text_view);
        ImageView slideTwoImageView = slideTwo.findViewById(R.id.slide_example_image_view);
        TextView slideTwoDescriptionTextView = slideTwo
                .findViewById(R.id.slide_example_description_text_view);
        ImageView slideThreeImageView = slideThree.findViewById(R.id.slide_example_image_view);
        TextView slideThreeDescriptionTextView = slideThree
                .findViewById(R.id.slide_example_description_text_view);
        slideOneImageView.setImageResource(R.drawable.slide_newest_products);
        slideOneDescriptionTextView.setText("newest products");
        slideTwoImageView.setImageResource(R.drawable.slide_top_rated);
        slideTwoDescriptionTextView.setText("top rated products");
        slideThreeImageView.setImageResource(R.drawable.slide_top_viewed);
        slideThreeDescriptionTextView.setText("top viewed products");
        slides.add(slideOne);
        slides.add(slideTwo);
        slides.add(slideThree);
        sliderAdapter = new SliderAdapter(this, slides);
        viewPager.setAdapter(sliderAdapter);
        viewPager.setOffscreenPageLimit(slides.size());
        nextImageView = findViewById(R.id.activity_main_next_image_view);
        prevImageView = findViewById(R.id.activity_main_prev_image_view);
        nextImageView.setOnClickListener(this);
        prevImageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_main_next_image_view:
                viewPager.setCurrentItem((
                        viewPager.getCurrentItem() + 1) % sliderAdapter.getCount());
                break;
            case R.id.activity_main_prev_image_view:
                viewPager.setCurrentItem((
                        viewPager.getCurrentItem() + sliderAdapter.getCount() - 1) % sliderAdapter.getCount());
                break;
        }
    }
}
