package com.sae.tukangin.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sae.tukangin.IntroManager;
import com.sae.tukangin.R;

public class IntroActivity extends AppCompatActivity {
    private ViewPager viewPager;
    Button btnNext;
    private int[] layouts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        IntroManager intromanager = new IntroManager(this);
        if (!intromanager.Check()) {
            intromanager.setFirst(false);
            Intent intent = new Intent(IntroActivity.this, GetStartedActivity.class);
            startActivity(intent);
            finish();
        }
        setContentView(R.layout.activity_intro);

        viewPager = findViewById(R.id.view_pager);
        btnNext = findViewById(R.id.btnNext);
        layouts = new int[]{R.layout.onboarding_1, R.layout.onboarding_2, R.layout.onboarding_3};
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter();
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(viewListener);

        btnNext.setOnClickListener(view -> {
            int current = getItem();
            if (current < layouts.length) {
                viewPager.setCurrentItem(current);
            } else {
                Intent intent = new Intent(IntroActivity.this, GetStartedActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private int getItem() {
        return viewPager.getCurrentItem() + 1;
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (position == layouts.length - 1) {
                btnNext.setText(R.string.mulai);
            } else {
                btnNext.setText(R.string.lanjut);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public class ViewPagerAdapter extends PagerAdapter {

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup myContainer, int mPosition) {
            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = layoutInflater.inflate(layouts[mPosition], myContainer, false);
            myContainer.addView(v);
            return v;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View mView, @NonNull Object mObject) {
            return mView == mObject;
        }

        @Override
        public void destroyItem(ViewGroup mContainer, int mPosition, @NonNull Object mObject) {
            View v = (View) mObject;
            mContainer.removeView(v);
        }
    }
}