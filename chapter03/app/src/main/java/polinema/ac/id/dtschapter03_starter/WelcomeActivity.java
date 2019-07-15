package polinema.ac.id.dtschapter03_starter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private MyViewPager mMyViewPager;
    private LinearLayout mLayout;
    private TextView[] dots;
    private Button mSkipButton;
    private Button mNextButton;
    private PrefManager mPrefManager;

    private int[] layouts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // initialize variable mPrefManager with param this (Context)
        mPrefManager = new PrefManager(this);

        if (! mPrefManager.isFirstTimeLaunch()) launchWelcomeBack();
        if (Build.VERSION.SDK_INT >= 21) {
            // Get Window Android
            Window window = getWindow();

            // Set Fullscreen
            // change status bar color to transparent
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }

        // Set Content View for this activity
        setContentView(R.layout.activity_welcome);

        // initialize variables view
        mViewPager  = (ViewPager) findViewById(R.id.view_pager);
        mLayout     = (LinearLayout) findViewById(R.id.layoutDots);
        mSkipButton = (Button) findViewById(R.id.btn_skip);
        mNextButton = (Button) findViewById(R.id.btn_next);

        // instance class MyViewPager
        mMyViewPager = new MyViewPager();

        // initialize layouts in array
        layouts = new int[]{
                R.layout.slide_welcome_calendar,
                R.layout.slide_welcome_super_hero,
                R.layout.slide_welcome_assign
        };

        // set first state dots in bottom view
        addBottomDots(0);

        // set Adapter
        mViewPager.setAdapter(mMyViewPager);

        // add listener
        mViewPager.addOnPageChangeListener(viewPagerListerner);
        mSkipButton.setOnClickListener(skipButtonListener);
        mNextButton.setOnClickListener(nextButtonListener);
    }

    private void addBottomDots(int current) {
        dots = new TextView[layouts.length];

        // Get array int file res/colors.xml
        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        // remove all views
        mLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[current]);
            mLayout.addView(dots[i]);
        }

        if (dots.length > 0) dots[current].setTextColor(colorsActive[current]);
    }

    private int getItem(int i) {
        return mViewPager.getCurrentItem() + 1;
    }

    /**
     * @void launchWelcomeBack
     * this method use to launch WelcomeBack
     */
    private void launchWelcomeBack() {
        mPrefManager.setFirstTimeLauch(false);
        startActivity(new Intent(this, WelcomeBackActivity.class));
        finish();
    }

    /**
     * @var
     * Callback for viewPager onPageChange Listener
     */
    private ViewPager.OnPageChangeListener viewPagerListerner =
            new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int i, float v, int i1) { }

                @Override
                public void onPageSelected(int i) {
                    // Set dot
                    addBottomDots(i);

                    if (i == layouts.length - 1) {
                        mNextButton.setText(getString(R.string.start));
                        mSkipButton.setVisibility(View.GONE);
                    } else {
                        mNextButton.setText(getString(R.string.next));
                        mSkipButton.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onPageScrollStateChanged(int i) { }
            };

    /**
     * @callback
     * Action for skipButton onClick Listener
     */
    private View.OnClickListener skipButtonListener =
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    launchWelcomeBack();
                }
            };

    /**
     * @callback
     * Action for nextButton onClick Listener
     */
    private View.OnClickListener nextButtonListener =
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int current = getItem(+1);
                    if (current < layouts.length) {
                        mViewPager.setCurrentItem(current);
                    } else {
                        launchWelcomeBack();
                    }
                }
            };

    /**
     * Method untuk Menghandle Click pada text 'Contact Us'
     *
     * @param view
     */
    public void contactUsOnClick(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);

        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"dts@polinema.ac.id", "polinema@gmail.com"});
        intent.putExtra(Intent.EXTRA_CC, new String[]{"jti@polinema.ac.id"});
        intent.putExtra(Intent.EXTRA_BCC, new String[]{"jti@polinema.ac.id"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Test Email");
        intent.putExtra(Intent.EXTRA_TEXT, "Welcome to DTS 2019");

        startActivity(Intent.createChooser(intent, "Pilih Email Client"));
    }

    /**
     * @class MyViewPager merupakan child dari PagerAdapter
     * Class ini digunakan untuk menghandle swipe left or right pada layar
     *
     */
    public class MyViewPager extends PagerAdapter {
        private LayoutInflater mLayoutInflater;

        public MyViewPager() {}

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            mLayoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            // Mengambil Current View Layout
            View view = mLayoutInflater.inflate(layouts[position], container, false);

            // Set current view layout ke dalam container
            container.addView(view);

            return view;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}
