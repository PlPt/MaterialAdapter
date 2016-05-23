package de.plpt.MaterialAdapter;

import android.app.Activity;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by Pascal on 07.12.2015.
 * Simple class represents a basic NavigationDrawer in MaterialDesign
 */
public class MaterialDrawer extends MaterialView {


    //region VarDef
    DrawerLayout _drawerLayout;
    NavigationView _navView;
    ActionBarDrawerToggle _toggle;
    //endregion


    //region Constructors

    /**
     * Default Constructor with default Layout, Drawer wil inclue it into a drawer Layout
     *
     * @param activity Activity member
     */
    public MaterialDrawer(AppCompatActivity activity) {

        this._act = activity;
        View view = LayoutInflater.from(activity).inflate(R.layout.default_material_drawer, null);
        _drawerLayout = (DrawerLayout) view.findViewById(R.id.drawer_layout);
        _navView = (NavigationView) _drawerLayout.findViewById(R.id.nav_view);
        _toolbar = (android.support.v7.widget.Toolbar) _drawerLayout.findViewById(R.id.toolbar);
        _toolbar.setPopupTheme(PopupOverlayTheme);
        (_act).setSupportActionBar(_toolbar);


        initDrawerToggle();

        applyLayout(_drawerLayout, _act, true);


    }

    /**
     * Defalut constructor to use with a MaterialViewPager
     *
     * @param activity Activityx to apply Layout
     * @param pager    View pager to include into Layout
     */
    public MaterialDrawer(AppCompatActivity activity, MaterialViewPager pager) {
        pager.setMaterialDrawer(this);


        this._act = activity;

        View view = activity.getLayoutInflater().inflate(R.layout.view_pager_material_drawer, null);

        _drawerLayout = (DrawerLayout) view.findViewById(R.id.drawer_layout);
        if (pager.allowsTabLayout()) {
            TabLayout tabLayout = (TabLayout) _drawerLayout.findViewById(R.id.tabs);
            tabLayout.setVisibility(View.VISIBLE);

        }
        _navView = (NavigationView) _drawerLayout.findViewById(R.id.nav_view);
        _toolbar = (android.support.v7.widget.Toolbar) _drawerLayout.findViewById(R.id.toolbar);
        _toolbar.setPopupTheme(PopupOverlayTheme);


        (_act).setSupportActionBar(_toolbar);


        initDrawerToggle();


        applyLayout(_drawerLayout, _act, false);
        pager.init();
        _toggle.syncState();

        initDrawerToggle();


    }

    /**
     * Default Constuructor to use MaterialDrawer with "external" Layouts
     *
     * @param activity     current Activity
     * @param drawerLayout DrawerLayout
     * @param toolbar      Toolbar
     * @param _navView     NavigationView
     */
    public MaterialDrawer(AppCompatActivity activity, DrawerLayout drawerLayout, android.support.v7.widget.Toolbar toolbar, NavigationView _navView) {
        this._act = activity;
        _drawerLayout = drawerLayout;
        this._navView = _navView;
        _toolbar = toolbar;
        _toolbar.setPopupTheme(PopupOverlayTheme);

        (_act).setSupportActionBar(_toolbar);


        initDrawerToggle();


        applyLayout(_drawerLayout, _act, false);

    }

    //endregion

    //region Private Methods

    /**
     * Initializes the DrawerToggle
     */
    private void initDrawerToggle() {
        _toggle = new ActionBarDrawerToggle(
                _act, _drawerLayout, _toolbar, R.string.drawer_open, R.string.drawer_close);
        _drawerLayout.setDrawerListener(_toggle);
        _toggle.syncState();
    }


    /**
     * Removes all Header Views of the Drawer/NavigationView
     */
    private void removeAllHeaders() {
        int idx = _navView.getHeaderCount();

        for (int i = 0; i < idx; i++) {
            _navView.removeHeaderView(_navView.getHeaderView(i));
        }
    }


    /**
     * Applies the inflated Layout to the activity.
     * Activity Layout will be replaced through the DrawerLayout. Content of Activity Layout will be added into the DrawerLayout
     *
     * @param _drawerLayout      inflated DrawerLayout
     * @param _act               Activity to get/set Views
     * @param replaceContentView true if the method should replace the contentView of the activity
     */
    private void applyLayout(DrawerLayout _drawerLayout, Activity _act, boolean replaceContentView) {
        _toolbar.setPopupTheme(PopupOverlayTheme);
        if (replaceContentView) {
            FrameLayout content = (FrameLayout) _drawerLayout.findViewById(R.id.content);
            View rootView = _act.findViewById(android.R.id.content).getRootView();

            ViewGroup viewGroup = (ViewGroup) ((ViewGroup) _act
                    .findViewById(android.R.id.content)).getChildAt(0);
            ((ViewGroup) viewGroup.getParent()).removeViewInLayout(viewGroup);
            content.addView(viewGroup);
        }

        _act.setContentView(_drawerLayout);
    }
    //endregion

    //region publicMethods

    /**
     * Closes the Drawer when it's open.
     * Can be used in an if, for checking if its open
     *
     * @return wasDrawerOpen?
     */
    public boolean closeDrawerIfOpen() {
        if (_drawerLayout.isDrawerOpen(GravityCompat.START)) {
            _drawerLayout.closeDrawer(GravityCompat.START);
            return false;
        }
        return true;
    }
//endregion


    //region Properties


    /**
     * Returns the current DraqwerToggle Object
     *
     * @return current used DrawerToggle Object
     */
    public ActionBarDrawerToggle getDrawerToggle() {
        return _toggle;
    }

    /**
     * Returns the Activity used with this Drawer
     *
     * @return Activity related to Drawer
     */
    public Activity getActivity() {
        return _act;
    }


    public NavigationView getNavigationView()
    {
        return _navView;
    }

    /**
     * Return the current DrawerLayout
     *
     * @return current DrawerLayout
     */
    public DrawerLayout getDrawerLayout() {
        return _drawerLayout;
    }

    /**
     * Sets the Drawer Header View
     *
     * @param header DrawerHeader View
     */
    public void setDrawerHeader(View header) {
        removeAllHeaders();
        _navView.addHeaderView(header);
    }

    /**
     * Returns the current DrawerHeaderView at specific position
     *
     * @param idx Position of the view in the HeaderList
     * @return View at position
     */
    public View getHeaderView(int idx) {
        return _navView.getHeaderView(idx);
    }

    /**
     * Return the default HeaderView (pos=0)
     *
     * @return DefaultHeaderView
     */
    public View getDefaultHeaderView() {
        return getHeaderView(0);
    }

    /**
     * Sets the DefaultHeaderView to an XML Resource
     *
     * @param resId Layout Resource ID
     */
    public void setDrawerHeader(int resId) {
        removeAllHeaders();
        _navView.inflateHeaderView(resId);
    }

    /**
     * Adds a View to DrawerHeaderList
     *
     * @param view View to add to Header
     */
    public void addDrawerHeader(View view) {
        _navView.addHeaderView(view);
    }

    /**
     * Sets the Array of Menu Items as nav Items into the Drawer
     *
     * @param menuItems Navigation Items of the Drawer
     */
    public void setMenu(MenuItem... menuItems) {
        _navView.getMenu().clear();
        for (MenuItem item : menuItems) {

            MenuItem _activeMenu = _navView.getMenu().add(item.getTitle());

        }
    }

    /**
     * Sets the menu from Resource ID
     *
     * @param resID Resource ID of the menu you want to add
     */
    public void setMenu(int resID) {
        _navView.getMenu().clear();
        _navView.inflateMenu(resID);
    }

    /**
     * Add a menu resource to the existing menu
     *
     * @param resId Resource ID of the menu to add
     */
    public void addMenu(int resId) {
        _navView.inflateMenu(resId);
    }

    /**
     * Sets the NavigationItemListener of the Drawer
     *
     * @param _listener NavItemListener
     */
    public void setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener _listener) {
        _navView.setNavigationItemSelectedListener(_listener);
    }

    /**
     * Returns the current used Toolbar object
     *
     * @return current toolbar object
     */
    public android.support.v7.widget.Toolbar getToolbar() {
        return _toolbar;
    }


// endregion


}
