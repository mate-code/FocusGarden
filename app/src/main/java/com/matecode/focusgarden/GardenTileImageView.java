package com.matecode.focusgarden;

import android.content.Context;
import android.util.AttributeSet;

public class GardenTileImageView extends androidx.appcompat.widget.AppCompatImageView {

    public GardenTileImageView(Context context) {
        super(context);
    }

    public GardenTileImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GardenTileImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);    // Override onMeasure method to create ideal square by passing width 2 times
    }
}
