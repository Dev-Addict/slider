package ir.ariact.slider.transform;

import android.view.View;

import androidx.viewpager.widget.ViewPager;

public class CubeOutRotationTransformation implements ViewPager.PageTransformer {
    @Override
    public void transformPage(View page, float position) {
        if (position < -1){
            page.setAlpha(0);
        } else if (position <= 0) {
            page.setAlpha(1);
            page.setPivotX(page.getWidth());
            page.setRotationY(-90 * Math.abs(position));
        } else if (position <= 1){
            page.setAlpha(1);
            page.setPivotX(0);
            page.setRotationY(90 * Math.abs(position));
        } else {
            page.setAlpha(0);
        }
    }
}