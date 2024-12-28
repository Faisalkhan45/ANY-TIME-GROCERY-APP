package com.example.anttimegrocery.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.example.anttimegrocery.R;
import com.example.anttimegrocery.adapters.SliderAdapter;

public class OnBoardingActivity extends AppCompatActivity {
    Animation animation;
    Button btn;
    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        public void onPageSelected(int position) {
            OnBoardingActivity.this.addDots(position);
            if (position == 0) {
                OnBoardingActivity.this.btn.setVisibility(4);
            } else if (position == 1) {
                OnBoardingActivity.this.btn.setVisibility(4);
            } else {
                OnBoardingActivity onBoardingActivity = OnBoardingActivity.this;
                onBoardingActivity.animation = AnimationUtils.loadAnimation(onBoardingActivity, R.anim.slide_animation);
                OnBoardingActivity.this.btn.setAnimation(OnBoardingActivity.this.animation);
                OnBoardingActivity.this.btn.setVisibility(0);
            }
        }

        public void onPageScrollStateChanged(int state) {
        }
    };
    TextView[] dots;
    LinearLayout dotsLayout;
    SliderAdapter sliderAdapter;
    ViewPager viewPager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(1024, 1024);
        setContentView((int) R.layout.activity_on_boarding);
        getSupportActionBar().hide();
        this.viewPager = (ViewPager) findViewById(R.id.slider);
        this.dotsLayout = (LinearLayout) findViewById(R.id.dots);
        this.btn = (Button) findViewById(R.id.get_started_btn);
        addDots(0);
        this.viewPager.addOnPageChangeListener(this.changeListener);
        SliderAdapter sliderAdapter2 = new SliderAdapter(this);
        this.sliderAdapter = sliderAdapter2;
        this.viewPager.setAdapter(sliderAdapter2);
        this.btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                OnBoardingActivity.this.startActivity(new Intent(OnBoardingActivity.this, RegistrationActivity.class));
                OnBoardingActivity.this.finish();
            }
        });
    }

    /* access modifiers changed from: private */
    public void addDots(int position) {
        TextView[] textViewArr;
        this.dots = new TextView[3];
        this.dotsLayout.removeAllViews();
        int i = 0;
        while (true) {
            textViewArr = this.dots;
            if (i >= textViewArr.length) {
                break;
            }
            textViewArr[i] = new TextView(this);
            this.dots[i].setText(Html.fromHtml("&#8226;"));
            this.dots[i].setTextSize(35.0f);
            this.dotsLayout.addView(this.dots[i]);
            i++;
        }
        if (textViewArr.length > 0) {
            textViewArr[position].setTextColor(getResources().getColor(R.color.pink));
        }
    }
}
