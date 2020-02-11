package ir.ariact.slider.transform;

import android.view.View;

import androidx.viewpager.widget.ViewPager;

public class ClockSpinTransformation implements ViewPager.PageTransformer {
    @Override
    public void transformPage(View page, float position) {
        page.setTranslationX(-position * page.getWidth());
        if (Math.abs(position) <= 0.5) {
            page.setVisibility(View.VISIBLE);
            page.setScaleX(1 - Math.abs(position));
            page.setScaleY(1 - Math.abs(position));
        } else if (Math.abs(position) > 0.5) {
            page.setVisibility(View.GONE);
        }
        if (position < -1){
            page.setAlpha(0);
        } else if (position <= 0) {
            page.setAlpha(1);
            page.setRotation(360 * Math.abs(position));
        } else if (position <= 1) {
            page.setAlpha(1);
            page.setRotation(-360 * Math.abs(position));
        } else {
            page.setAlpha(0);
        }
    }
}