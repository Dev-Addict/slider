package ir.ariact.slider.container.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;


import ir.ariact.slider.adapter.SliderAdapter;

import java.util.ArrayList;
import java.util.List;

import ir.ariact.slider.R;
import ir.ariact.slider.model.Constant;
import ir.ariact.slider.transform.AntiClockSpinTransformation;
import ir.ariact.slider.transform.ClockSpinTransformation;
import ir.ariact.slider.transform.CubeInDepthTransformation;
import ir.ariact.slider.transform.CubeInRotationTransformation;
import ir.ariact.slider.transform.CubeInScalingTransformation;
import ir.ariact.slider.transform.CubeOutDepthTransformation;
import ir.ariact.slider.transform.CubeOutRotationTransformation;
import ir.ariact.slider.transform.CubeOutScalingTransformation;
import ir.ariact.slider.transform.DepthTransformation;
import ir.ariact.slider.transform.FadeOutTransformation;
import ir.ariact.slider.transform.FanTransformation;
import ir.ariact.slider.transform.FidgetSpinTransformation;
import ir.ariact.slider.transform.GateTransformation;
import ir.ariact.slider.transform.HorizontalFlipTransformation;
import ir.ariact.slider.transform.PopTransformation;
import ir.ariact.slider.transform.SimpleTransformation;
import ir.ariact.slider.transform.SpinnerTransformation;
import ir.ariact.slider.transform.TossTransformation;
import ir.ariact.slider.transform.VerticalFlipTransformation;
import ir.ariact.slider.transform.VerticalShutTransformation;
import ir.ariact.slider.transform.ZoomOutTransformation;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ViewPager viewPager;
    PagerAdapter sliderAdapter;
    ImageView nextImageView, prevImageView;
    Button stopAutoPlayButton, startAutoPlayButton;
    EditText miliSecondsEditText;

    List<View> slides;
    Handler autoPlayHandler;
    Runnable runnable;
    int miliSeconds;

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
        startAutoPlay(3000);
        stopAutoPlayButton = findViewById(R.id.activity_main_stop_auto_play_button);
        stopAutoPlayButton.setOnClickListener(this);
        startAutoPlayButton = findViewById(R.id.activity_main_start_auto_play_button);
        miliSecondsEditText = findViewById(R.id.activity_main_mili_seconds_edit_text);
        startAutoPlayButton.setOnClickListener(this);
        setTransform(Constant.FIDGET_SPINNER_TRANSFORMATION);
    }

    private void setTransform(String transformation) {
        SimpleTransformation simpleTransformation = new SimpleTransformation();
        DepthTransformation depthTransformation = new DepthTransformation();
        ZoomOutTransformation zoomOutTransformation = new ZoomOutTransformation();
        ClockSpinTransformation clockSpinTransformation = new ClockSpinTransformation();
        AntiClockSpinTransformation antiClockSpinTransformation = new AntiClockSpinTransformation();
        FidgetSpinTransformation fidgetSpinTransformation = new FidgetSpinTransformation();
        VerticalFlipTransformation verticalFlipTransformation = new VerticalFlipTransformation();
        HorizontalFlipTransformation horizontalFlipTransformation = new HorizontalFlipTransformation();
        PopTransformation popTransformation = new PopTransformation();
        FadeOutTransformation fadeOutTransformation = new FadeOutTransformation();
        CubeOutRotationTransformation cubeOutRotationTransformation = new CubeOutRotationTransformation();
        CubeInRotationTransformation cubeInRotationTransformation = new CubeInRotationTransformation();
        CubeOutScalingTransformation cubeOutScalingTransformation = new CubeOutScalingTransformation();
        CubeInScalingTransformation cubeInScalingTransformation = new CubeInScalingTransformation();
        CubeOutDepthTransformation cubeOutDepthTransformation = new CubeOutDepthTransformation();
        CubeInDepthTransformation cubeInDepthTransformation = new CubeInDepthTransformation();
        HingeTransformation hingeTransformation = new HingeTransformation();
        GateTransformation gateTransformation = new GateTransformation();
        TossTransformation tossTransformation = new TossTransformation();
        FanTransformation fanTransformation = new FanTransformation();
        SpinnerTransformation spinnerTransformation = new SpinnerTransformation();
        VerticalShutTransformation verticalShutTransformation = new VerticalShutTransformation();
        switch (transformation) {
            case Constant.SIMPLE_TRANSFORMATION:
                viewPager.setPageTransformer(true, simpleTransformation);
                break;
            case Constant.DEPTH_TRANSFORMATION:
                viewPager.setPageTransformer(true, depthTransformation);
                break;
            case Constant.ZOOM_OUT_TRANSFORMATION:
                viewPager.setPageTransformer(true, zoomOutTransformation);
                break;
            case Constant.CLOCK_SPIN_TRANSFORMATION:
                viewPager.setPageTransformer(true, clockSpinTransformation);
                break;
            case Constant.ANTICLOCK_SPIN_TRANSFORMATION:
                viewPager.setPageTransformer(true, antiClockSpinTransformation);
                break;
            case Constant.FIDGET_SPINNER_TRANSFORMATION:
                viewPager.setPageTransformer(true, fidgetSpinTransformation);
                break;
            case Constant.VERTICAL_FLIP_TRANSFORMATION:
                viewPager.setPageTransformer(true, verticalFlipTransformation);
                break;
            case Constant.HORIZONTAL_FLIP_TRANSFORMATION:
                viewPager.setPageTransformer(true, horizontalFlipTransformation);
                break;
            case Constant.POP_TRANSFORMATION:
                viewPager.setPageTransformer(true, popTransformation);
                break;
            case Constant.FADE_OUT_TRANSFORMATION:
                viewPager.setPageTransformer(true, fadeOutTransformation);
                break;
            case Constant.CUBE_OUT_TRANSFORMATION:
                viewPager.setPageTransformer(true, cubeOutRotationTransformation);
                break;
            case Constant.CUBE_IN_TRANSFORMATION:
                viewPager.setPageTransformer(true, cubeInRotationTransformation);
                break;
            case Constant.CUBE_OUT_SCALING_TRANSFORMATION:
                viewPager.setPageTransformer(true, cubeOutScalingTransformation);
                break;
            case Constant.CUBE_IN_SCALING_TRANSFORMATION:
                viewPager.setPageTransformer(true, cubeInScalingTransformation);
                break;
            case Constant.CUBE_OUT_DEPTH_TRANSFORMATION:
                viewPager.setPageTransformer(true, cubeOutDepthTransformation);
                break;
            case Constant.CUBE_IN_DEPTH_TRANSFORMATION:
                viewPager.setPageTransformer(true, cubeInDepthTransformation);
                break;
            case Constant.HINGE_TRANSFORMATION:
                viewPager.setPageTransformer(true, hingeTransformation);
                break;
            case Constant.GATE_TRANSFORMATION:
                viewPager.setPageTransformer(true, gateTransformation);
                break;
            case Constant.TOSS_TRANSFORMATION:
                viewPager.setPageTransformer(true, tossTransformation);
                break;
            case Constant.FAN_TRANSFORMATION:
                viewPager.setPageTransformer(true, fanTransformation);
                break;
            case Constant.SPINNER_TRANSFORMATION:
                viewPager.setPageTransformer(true,spinnerTransformation);
                break;
            case Constant.VERTICAL_SHUT_TRANSFORMATION:
                viewPager.setPageTransformer(true,verticalShutTransformation);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_main_next_image_view:
                goToNextPage();
                break;
            case R.id.activity_main_prev_image_view:
                goToPervPage();
                break;
            case R.id.activity_main_stop_auto_play_button:
                stopAutoPlay();
                break;
            case R.id.activity_main_start_auto_play_button:
                stopAutoPlay();
                int miliSeconds;
                try {
                    miliSeconds = Integer.parseInt(String.valueOf(miliSecondsEditText.getText()));
                } catch (Exception e) {
                    miliSeconds = 3000;
                }
                startAutoPlay(miliSeconds);
                break;
        }
    }

    private void goToPervPage() {
        viewPager.setCurrentItem((
                viewPager.getCurrentItem() + sliderAdapter.getCount() - 1) % sliderAdapter.getCount());
        stopAutoPlay();
        startAutoPlay(miliSeconds);
    }

    private void goToNextPage() {
        viewPager.setCurrentItem((
                viewPager.getCurrentItem() + 1) % sliderAdapter.getCount());
        stopAutoPlay();
        startAutoPlay(miliSeconds);
    }

    private void goToNextPageAuto(final int miliSeconds) {
        viewPager.setCurrentItem((
                viewPager.getCurrentItem() + 1) % sliderAdapter.getCount());
        autoPlayHandler.postDelayed(runnable, miliSeconds);
    }

    public void startAutoPlay(final int miliSeconds) {
        this.miliSeconds = miliSeconds;
        autoPlayHandler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                goToNextPageAuto(miliSeconds);
            }
        };
        autoPlayHandler.postDelayed(runnable, miliSeconds);
    }

    public void stopAutoPlay() {
        autoPlayHandler.removeCallbacks(runnable);
    }
}
