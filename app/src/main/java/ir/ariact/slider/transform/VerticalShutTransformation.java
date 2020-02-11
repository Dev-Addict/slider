package ir.ariact.slider.transform;

import android.view.View;

import androidx.viewpager.widget.ViewPager;

public class VerticalShutTransformation implements ViewPager.PageTransformer{
    @Override
    public void transformPage(View page, float position) {
        page.setTranslationX(-position*page.getWidth());
        page.setCameraDistance(999999999);
        if (position < 0.5 && position > -0.5){
            page.setVisibility(View.VISIBLE);
        } else {
            page.setVisibility(View.INVISIBLE);
        }
        if (position < -1){
            page.setAlpha(0);
        } else if (position <= 0 ){
            page.setAlpha(1);
            page.setRotationX(180*(1-Math.abs(position)+1));
        } else if (position <= 1){
            page.setAlpha(1);
            page.setRotationX(-180*(1-Math.abs(position)+1));
        } else {
            page.setAlpha(0);
        }
    }
}