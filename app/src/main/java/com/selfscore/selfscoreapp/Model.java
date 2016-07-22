package com.selfscore.selfscoreapp;

import android.app.Application;
import android.database.Observable;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anshilbhansali on 7/22/16.
 */
public class Model extends Observable{

    //app
    private Application app;

    //credentials
    private String username, password;

    //drawers
    private String[] left_drawer = new String[]{"Pay Bill", "Credit Availability",
    "Earn Cash", "My Purchases"};
    private String[] right_drawer = new String[]{"My Profile", "Settings", "Log out"};
    private List<Integer> images_left = new ArrayList<>();
    private List<Integer> images_right = new ArrayList<>();
    private List<RowItem> rowItems_left = new ArrayList<>();
    private List<RowItem> rowItems_right = new ArrayList<>();

    //Cardviews
    private String[] card_headers = new String[]{"Pay Bill", "Credit Availability",
            "Earn Cash", "My Purchases"};
    private String[] card_subheaders = new String[]{"payment due in","of credit available in",
            "of credit available in",""};
    private String[] card_subheader_nums = new String[]{"$15","20%","$20",""};
    private String[] button_txt = new String[]{"PAY NOW","INCREASE CREDIT","INVITE FRIENDS","NONE"};


    //METHODS
    public Model(Application app)
    {
        this.app = app;

        addDatatoDrawers();

    }

    private void addDatatoDrawers()
    {
        images_left.add(R.mipmap.paybill_icon);
        images_left.add(R.mipmap.creditavail_icon);
        images_left.add(R.mipmap.earncash_icon);
        images_left.add(R.mipmap.mypurchases_icon);

        for (int i = 0; i < left_drawer.length; i++) {

            RowItem item = new RowItem(images_left.get(i), left_drawer[i]);
            rowItems_left.add(item);
        }

        images_right.add(R.mipmap.myprofile_icon);
        images_right.add(R.mipmap.settings_icon);
        images_right.add(R.mipmap.logout_icon);


        for (int i = 0; i < right_drawer.length; i++) {

            RowItem item = new RowItem(images_right.get(i), right_drawer[i]);
            rowItems_right.add(item);
        }
    }

    public void setUsername(String username) {this.username = username;}
    public void setPassword(String password) {this.password = password;}
    public String getUsername() {return username;}


    public String[] getLeftDrawer(){return this.left_drawer;}
    public String[] getRightDrawer(){return this.right_drawer;}
    public List<RowItem> getLeftRowItems(){return this.rowItems_left;}
    public List<RowItem> getRightRowItems(){return this.rowItems_right;}

    public String[] getCard_headers(){return this.card_headers;}
    public String[] getCard_subheaders(){return  this.card_subheaders;}
    public String[] getCard_subheader_nums(){return this.card_subheader_nums;}
    public String[] getCard_buttons(){return this.button_txt;}


}
