package com.example.chathuranga.chaindrawabledemo;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

public class ChainFriendlyImageView extends AppCompatImageView {

    public ChainFriendlyImageView(Context context) {
        super(context);
    }

    public ChainFriendlyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChainFriendlyImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected boolean verifyDrawable(@NonNull Drawable dr) {
        super.verifyDrawable(dr);
        return true;
    }
}
