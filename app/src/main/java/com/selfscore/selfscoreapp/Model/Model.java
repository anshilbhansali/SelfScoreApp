package com.selfscore.selfscoreapp.Model;

import android.app.Application;
import android.database.Observable;

import com.selfscore.selfscoreapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anshilbhansali on 7/22/16.
 */
public class Model extends Observable{

    //<------------------------PRIVATE VARIABLES---------------------------------------->

    //app
    private Application app;

    //credentials
    private String username, password;
    private String sec_question="What is the name of the person you admire the most?", answer;
    public UserInfo user;

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
    private String[] card_subheaders = new String[]{"payment due in","of credit available",
            "cash earned",""};
    private String[] card_subheader_nums = new String[]{"$15","20%","$20",""};
    private String[] button_txt = new String[]{"PAY NOW","INCREASE CREDIT","INVITE FRIENDS","NONE"};

    //Create account
    private String[] sec_questions = new String[]{"Select a security question...",
            "What is the name of your favourite pet?",
            "What is the name of your favourite teacher?",
            "What is the colour of your first car?",
            "What is the first name of your best childhood friend?",
            "What was your favourite place to visit as a child?",
            "What was the make of your first car?",
            "Who is your favourite actor or actress?",
            "What is the name of the person you admire the most?",
            "What is your favourite food?",
            "What is your favourite colour?"
    };

    //<------------------------METHODS---------------------------------------->
    public Model(Application app)
    {
        this.app = app;

        //should get data from servers

        addDatatoDrawers();

        user = new UserInfo();
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
    public void setSecQuestion(String question){this.sec_question = question;}
    public String getSecQuestion(){return sec_question;}
    public void setAnswer(String answer){this.answer = answer;}


    public String[] getLeftDrawer(){return this.left_drawer;}
    public String[] getRightDrawer(){return this.right_drawer;}
    public List<RowItem> getLeftRowItems(){return this.rowItems_left;}
    public List<RowItem> getRightRowItems(){return this.rowItems_right;}

    public String[] getCard_headers(){return this.card_headers;}
    public String[] getCard_subheaders(){return  this.card_subheaders;}
    public String[] getCard_subheader_nums(){return this.card_subheader_nums;}
    public String[] getCard_buttons(){return this.button_txt;}

    public String[] getSecQuestions(){ return this.sec_questions;}

    public UserInfo getUser(){return user;}


}
