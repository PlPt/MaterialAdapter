# MaterialAdapter
Simple classes that allow you implementing Material components on Android easily.

###German Description
Der MaterialAdapter ist eine einfache Möglichkeit, Apps im MaterialDesign zu programmieren.
Der Vorteil ist, man entwickelt die Layouts ganz normal, mit einfachen Layouts etc.
Das heißt du kannst komplett auf ein DrawerLayout, einen ViewPager mit Tabs oder CoordinatorLayout mit Toolbar etc. verzichten.
Der Adapter generiert dir das passende Layout.

Dabei ist die Nutzung recht einfach:
Um die Features nutzen zu können, muss man die eigene Activity von der AppCompatActivity(Support v7) erben.
Dann erstellt man sein XML Layout ohne den ganzen Toolbar und Layout kram fürs MaterialDesign zu beachten.
In der Activity selber setzt man dann mit setContentView(R.layout....) sein Layout.
Damit die Activity jetzt eine Toolbar etc. bekommt muss man im einfachen Fall nur eine Instanz der materialView erstellen.
MaterialView myMaterialView = new MaterialView(this); 
Damit ist es dann schon erledigt. 
Um dein Layout herum wird das passende Coordinator und AppBar Layout mit Toolbar generiert.

Die Funktionsweise:
Nachdem das "normale" Layout per setContentView() Inflated wurde, wird von der Lib die RootView ausgelesen und so modifiziert, dass der Inhalt sich im passenden Layout mit Toolbar wiederfindet.
Damit spart man sich ein ewiges und neriges herumhantieren mit dem XML Layout gedöns ;).

Codebeispiele: 

Simple to use in AppCompatActivity:

1. Material Navigation  Drawer with View Pager

        MaterialViewPager viewPager = new MaterialViewPager(this,true,mSectionsPagerAdapter); // instatiate Pager
        MaterialDrawer materialDrawer = new MaterialDrawer(this,viewPager); // Set Up drawer
        materialDrawer.setNavigationItemSelectedListener(myNavigationListener); // Set Listener
        viewPager.setCurrentItem(1);//set start Item
        materialDrawer.setMenu(R.menu.main_dawer_menu);// set Drawer Menu
        //Maybe modify Header information for Accounts


2. Only Navigation Drawer
  
        MaterialDrawer materialDrawer = new MaterialDrawer(this); // Instanciate Drawer
        materialDrawer.setMenu(R.menu.main_dawer_menu); // set menu
        materialDrawer.setNavigationItemSelectedListener(myNavigationListener);// set global   listener
        materialDrawer.setToolbarTitle("Test", "App");// Set Title and Subtitle
        Tools.setDrawerHeaderData(materialDrawer); // set Header data like Icon and color

3. Only View Pager

     MaterialViewPager viewPager = new MaterialViewPager(this,true, mSectionsPagerAdapter);//Instanciate View Pager
                    viewPager.init(); // Init
                    
4. Only Material Toolbar

        MaterialView myMaterialView = new MaterialView(this);


###English description coming soon :P
