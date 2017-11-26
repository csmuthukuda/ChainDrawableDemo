package com.example.chathuranga.chaindrawabledemo;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.widget.ImageView;

import pl.droidsonroids.gif.AnimationListener;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int[] gifArray = {R.drawable.elawalu, R.drawable.kadala,R.drawable.kos,R.drawable.noodles};
    GifDrawable[] drawableArray=new GifDrawable[4];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = (ImageView)findViewById(R.id.mainImageView);
        for( int i=0;i<4;i++){
            drawableArray[i]=GifDrawable.createFromResource(getResources(), gifArray[i]);
        }
        final ChainedGifDrawable drawable = new ChainedGifDrawable(drawableArray);
        imageView.setImageDrawable(drawable);
        		drawable.setAnimationEnabled();
    }

    public static class ChainedGifDrawable extends Drawable implements AnimationListener {
        private final pl.droidsonroids.gif.GifDrawable[] mDrawables;
        private int mCurrentIndex;

        public ChainedGifDrawable(GifDrawable[] drawableArray) {
            mDrawables = drawableArray;

            for (pl.droidsonroids.gif.GifDrawable drawable : drawableArray) {
                drawable.addAnimationListener(this);

            }
        }

        @Override
        public void onAnimationCompleted(int loopNumber) {
            Log.e("Called ", "Checked");
            mCurrentIndex++;
            mCurrentIndex %= mDrawables.length;
            mDrawables[mCurrentIndex].setCallback(getCallback());
            mDrawables[mCurrentIndex].invalidateSelf();
        }

        @Override
        public void invalidateSelf() {
            mDrawables[mCurrentIndex].invalidateSelf();
            Log.e("Called invalidateSelf " + mCurrentIndex, "Checked");
        }

        @Override
        public void scheduleSelf(@NonNull Runnable what, long when) {
            mDrawables[mCurrentIndex].scheduleSelf(what, when);
            Log.e("Called scheduleSelf " + mCurrentIndex, "Checked");
        }

        @Override
        public void unscheduleSelf(@NonNull Runnable what) {
            mDrawables[mCurrentIndex].unscheduleSelf(what);
            Log.e("Called unscheduleSelf " + mCurrentIndex, "Checked");
        }

        @Override
        public int getIntrinsicWidth() {
            Log.e("Called getIntrinsicWidth " + mCurrentIndex, "Checked");
            return mDrawables[mCurrentIndex].getIntrinsicWidth();
        }

        @Override
        public int getIntrinsicHeight() {
            Log.e("Called getIntrinsicHeight " + mCurrentIndex, "Checked");
            return mDrawables[mCurrentIndex].getIntrinsicHeight();
        }

        @Override
        protected void onBoundsChange(Rect bounds) {
            for (pl.droidsonroids.gif.GifDrawable drawable : mDrawables) {
                drawable.setBounds(bounds);
            }
            Log.e("Called onboundsChange " + mCurrentIndex, "Checked");
        }


        @Override
        public void draw(@NonNull Canvas canvas) {
            mDrawables[mCurrentIndex].draw(canvas);
            Log.e("Called draw " + mCurrentIndex, "Checked");
        }

        @Override
        public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {
            for (pl.droidsonroids.gif.GifDrawable drawable : mDrawables) {
                drawable.setAlpha(alpha);
            }
            Log.e("Called setAlpha " + mCurrentIndex, "Checked");
        }

        @Override
        public void setColorFilter(@Nullable ColorFilter colorFilter) {
            for (pl.droidsonroids.gif.GifDrawable drawable : mDrawables) {
                drawable.setColorFilter(colorFilter);
            }
            Log.e("Called setcolorfilter " + mCurrentIndex, "Checked");
        }

        @Override
        public int getOpacity() {
            Log.e("Called getOpacity  " + mCurrentIndex, "Checked");
            return PixelFormat.TRANSLUCENT;
        }

        public void setAnimationEnabled() {

            mDrawables[0].setCallback(getCallback());
        }
    }
}
