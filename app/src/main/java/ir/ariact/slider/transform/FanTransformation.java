package ir.ariact.slider.transform;

import android.view.View;

import androidx.viewpager.widget.ViewPager;

public class FanTransformation implements ViewPager.PageTransformer{
    @Override
    public void transformPage(View page, float position) {
        page.setTranslationX(-position*page.getWidth());
        page.setPivotX(0);
        page.setPivotY(page.getHeight()/2);
        page.setCameraDistance(20000);
        if (position < -1){
            page.setAlpha(0);
        } else if (position <= 0){
            page.setAlpha(1);
            page.setRotationY(-120*Math.abs(position));
        } else if (position <= 1){
            page.setAlpha(1);
            page.setRotationY(120*Math.abs(position));
        } else {
            page.setAlpha(0);
        }
    }
}