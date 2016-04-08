package de.plpt.MaterialAdapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by Pascal on 07.12.2015.
 * Simple class which presents a MaterialView in Material design
 */
public class MaterialView {


    //region VarDef
    AppCompatActivity _act;
    public static int PopupOverlayTheme = R.style.AppTheme_PopupOverlay;
   FrameLayout _contentLayout ;
    android.support.v7.widget.Toolbar _toolbar;
    boolean useTabLayout;
    View _currView;
    //endregion


    //region Constructors
    /**
     * Default Constructor for Object inheritance
     * DON'T use it for common class instance creation
     */
        @Deprecated
         public MaterialView()
         {

         }



    /**
     * Default Constructor with default Layout, Drawer will include it into a drawer Layout
     * @param activity Activity member
     */
    public MaterialView(AppCompatActivity activity)
    {
        this._act = activity;
       View view = _act.getLayoutInflater().inflate(R.layout.default_material_view,null);

          _contentLayout = (FrameLayout)view.findViewById(R.id.content);
       _toolbar = (android.support.v7.widget.Toolbar) view.findViewById(R.id.toolbar);

        _toolbar.setPopupTheme(PopupOverlayTheme);


            _act.setSupportActionBar(_toolbar);
            _act.getSupportActionBar().setHomeButtonEnabled(true);
           _act. getSupportActionBar().setDisplayShowHomeEnabled(true);
           _act.getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        applyLayout(view, _act, true);


    }



    /**
     * Default Constructor in use with a MaterialViewPager
     * @param activity curr Activity
     * @param pager ViewPager
     */
    public MaterialView(AppCompatActivity activity, MaterialViewPager pager)
    {
              this._act = activity;
        View view =  LayoutInflater.from(activity).inflate(R.layout.view_pager_material_drawer,null);
        _contentLayout = (FrameLayout)view.findViewById(R.id.content);
        if(pager.allowsTabLayout())
        {
            TabLayout tabLayout = (TabLayout)_contentLayout.findViewById(R.id.tabs);
            tabLayout.setVisibility(View.VISIBLE);

        }

        _toolbar = (android.support.v7.widget.Toolbar) view.findViewById(R.id.toolbar);

            (_act).setSupportActionBar(_toolbar);
           (_act).getSupportActionBar().setHomeButtonEnabled(true);
            (_act). getSupportActionBar().setDisplayShowHomeEnabled(true);

        applyLayout(view, _act, false);
        pager.init();
    }



    /**
     * Constructor to use MateriaView with custom Layout
     * @param activity Activity
     * @param v currentView
     * @param toolbar Toolbar
     */
    public MaterialView(AppCompatActivity activity, View v, android.support.v7.widget.Toolbar toolbar)
    {
        this._act = activity;
        _toolbar = toolbar;
         (_act).setSupportActionBar(_toolbar);
         applyLayout(v, _act, false);
    }
    //endregion


    //region private Methods
    /**
     * Resizes a Drawable to 100x100px
     * @param image
     * @return
     */
    private Drawable resize(Drawable image) {
        Bitmap b = ((BitmapDrawable)image).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(b, 100, 100, false);
        return new BitmapDrawable(_act.getResources(), bitmapResized);
    }


    //endregion

    //region public Methods
    /**
     * Returns the high of the StatusBar
     * @return high of Status Bar
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
     * Applies or Replaces the LAyout to ContentView of given Activity
     * @param _drawerLayout Layout to Apply
     * @param _act Activity to apply Layout
     * @param replaceContentView should ContentView be replaced
     */
    public   void applyLayout(final View _drawerLayout, Activity _act,boolean replaceContentView) {

        if(replaceContentView)
        {
            final FrameLayout content = (FrameLayout) _drawerLayout.findViewById(R.id.content);
            View rootView = _act.findViewById(android.R.id.content).getRootView();

            ViewGroup viewGroup = (ViewGroup) ((ViewGroup) _act
                    .findViewById(android.R.id.content)).getChildAt(0);
            ((ViewGroup) viewGroup.getParent()).removeViewInLayout(viewGroup);

            //   View viewGrou =  _act.getLayoutInflater().inflate(resId,null);
            content.addView(viewGroup);

        }

        _act.setContentView(_drawerLayout); // Set layout to Activity
    }


    //endregion


    //region Properties


    public Activity getActivity() {
        return _act;
    }

    /**
     * Return the current Toobar
     * @return currentToolbar object
     */
    public android.support.v7.widget.Toolbar getToolbar()
    {
        return _toolbar;
    }

    /**
     * Set ToobarTirle
     * @param title Titel of Toolbar
     * @param subtitle Subtitle of toolbar
     */
    public void setToolbarTitle(String title,String subtitle)
    {

        setToolbarTitle(title);

        setToolbarSubTitle(subtitle);
    }

    /**
     * Sets the title of the Toolbar
     * @param title Title
     */
    public void setToolbarTitle(String title)
    {
        if(_act.getSupportActionBar() !=null)
        (_act).getSupportActionBar().setTitle(title);


    }

    /**
     *  Sets the subtitle of the Toolbar
     * @param subtitle Subtitle
     */
    public void setToolbarSubTitle(String subtitle)
    {

        _toolbar.setSubtitle(subtitle);

        _toolbar.setSubtitleTextAppearance(_act, android.R.style.TextAppearance_Small);
    }
    //endregion





}
