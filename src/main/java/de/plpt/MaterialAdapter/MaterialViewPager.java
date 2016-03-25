package de.plpt.MaterialAdapter;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

/**
 * Created by Pascal on 07.12.2015.
 */
public class MaterialViewPager extends MaterialView {


    //<editor-fold desc="Var Def">
    private FragmentPagerAdapter mPagerAdapter;
    MaterialDrawer _materialDrawer;
    boolean useTabLayout;

    public ViewPager getViewPager() {
        return mViewPager;
    }

    ViewPager mViewPager;
    //</editor-fold>

    // <editor-fold desc="Constructors">

    /**
     * Default Constructor with default Layout, Drawer wil include it into a drawer Layout
     * @param activity Activity member
     *
     *
     */
    public MaterialViewPager(AppCompatActivity activity,boolean useTabLayout,FragmentPagerAdapter adapter)
    {
        _act = activity;

       this.useTabLayout = useTabLayout;
        mPagerAdapter = adapter;
    }
    //</editor-fold>

    /**
     * Initializes the ViewPager and it's Layout
     */
    public void init()
    {
        if(_materialDrawer!=null) {
            mViewPager = (ViewPager) _materialDrawer._drawerLayout.findViewById(R.id.viewPager);
            mViewPager.setAdapter(mPagerAdapter);
            ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) mViewPager.getLayoutParams();
            lp.topMargin += getStatusBarHeight();
            mViewPager.setLayoutParams(lp);


            mViewPager.getViewTreeObserver().addOnGlobalLayoutListener(
                    new ViewTreeObserver.OnGlobalLayoutListener() {

                        @Override
                        public void onGlobalLayout() {
                            // gets called after layout has been done but before display
                            // so we can get the height then hide the view


                            int height = _materialDrawer._toolbar.getMeasuredHeight();

                            if (useTabLayout) {
                                TabLayout tabLayout = (TabLayout) _materialDrawer._drawerLayout.findViewById(R.id.tabs);
                                tabLayout.setupWithViewPager(mViewPager);
                                tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
                                tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

                                height += tabLayout.getMeasuredHeight();
                            }

                            ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) mViewPager.getLayoutParams();
                            lp.topMargin += height;
                            //mViewPager.setLayoutParams(lp);

                            //  mViewPager.setPadding(0, height, 0, 0);

                            mViewPager.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                        }

                    });

        }
        else
        {
            //throw new UnsupportedOperationException("Method without drawerLayout not implemented");
        final     View view = _act.getLayoutInflater().inflate(R.layout.view_pager_material_drawer,null);
            _toolbar = (android.support.v7.widget.Toolbar) view.findViewById(R.id.toolbar);
            DrawerLayout drawerLayout = (DrawerLayout)view;
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
           view.findViewById(R.id.nav_view).setVisibility(View.GONE);
          //  NavigationView vv = (NavigationView)view.findViewById(R.id.nav_view);


             // No NavView in onlyViewPagermode
                    (_act).setSupportActionBar(_toolbar);
                         applyLayout(view, _act, false);

            mViewPager = (ViewPager) view.findViewById(R.id.viewPager);

            mViewPager.setAdapter(mPagerAdapter);
            ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) mViewPager.getLayoutParams();
            lp.topMargin += getStatusBarHeight();
            mViewPager.setLayoutParams(lp);
        view.findViewById(R.id.nav_view).setVisibility(View.GONE);
            mViewPager.getViewTreeObserver().addOnGlobalLayoutListener(
                    new ViewTreeObserver.OnGlobalLayoutListener() {

                        @Override
                        public void onGlobalLayout() {
                            // gets called after layout has been done but before display
                            // so we can get the height then hide the view


                            int height = _toolbar.getHeight();

                            if (useTabLayout) {
                                TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);
                               tabLayout.setVisibility(View.VISIBLE);
                                tabLayout.setupWithViewPager(mViewPager);
                                tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
                                tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

                                height += tabLayout.getMeasuredHeight();
                            }

                            ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) mViewPager.getLayoutParams();
                            lp.topMargin += height;
                           // mViewPager.setLayoutParams(lp);

                            mViewPager.setPadding(0, height-25, 0, 0);

                            mViewPager.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                        }

                    });



        }



    }


    /**
     * Set the MaterialDrawer for include the ViewPAger in the DrawerLayout
     * @param materialDrawer
     */
    public void setMaterialDrawer(MaterialDrawer materialDrawer)
    {
        this._materialDrawer = materialDrawer;
    }

    public void setPageMargin(int pixels)
    {
        if(mViewPager!=null)mViewPager.setPageMargin(pixels);
    }


    /**
     * Returns true if the ViewPager allows TabLayout
     * @return isTabLayout allows
     */
    public boolean allowsTabLayout()
    {
        return useTabLayout;
    }

    /**
     * Retuns the StatusBar high
     * @return high of status bar
     */
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = _act.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = _act.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * Sets current selected Fragment/Tab
     * @param currentItem Position f the Fragment to select
     */
    public void setCurrentItem(int currentItem) {
        this.mViewPager.setCurrentItem(currentItem);
    }
}
