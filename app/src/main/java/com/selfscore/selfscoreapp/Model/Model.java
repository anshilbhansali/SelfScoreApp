package com.selfscore.selfscoreapp.Model;

import android.app.Application;
import android.database.Observable;
import android.support.annotation.NonNull;
import android.util.Log;

import com.selfscore.selfscoreapp.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static java.util.Collections.sort;

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

    //Purchases
    private List<Purchase> myPurchases = new ArrayList<>();

    //<------------------------METHODS---------------------------------------->
    public Model(Application app)
    {
        this.app = app;

        //should get data from servers

        addDatatoDrawers();
        initPurchases();

        user = new UserInfo();
    }

    private void initPurchases()
    {

        Purchase purchase = new Purchase("31", "OCT", "Target", "$20.48");
        myPurchases.add(purchase);

        purchase = new Purchase("20", "OCT", "Walmart", "$201.24");
        myPurchases.add(purchase);

        purchase = new Purchase("19", "OCT", "Trader Joe's", "$124.48");
        myPurchases.add(purchase);

        purchase = new Purchase("18", "OCT", "Deli Cafe", "$24.98");
        myPurchases.add(purchase);

        purchase = new Purchase("17", "OCT", "Target", "$57.48");
        myPurchases.add(purchase);

        purchase = new Purchase("16", "OCT", "Walmart", "$29.48");
        myPurchases.add(purchase);

        purchase = new Purchase("15", "OCT", "Trader Joe's", "$160.48");
        myPurchases.add(purchase);

        purchase = new Purchase("13", "OCT", "Deli Cafe", "$48.78");
        myPurchases.add(purchase);

        purchase = new Purchase("11", "OCT", "Target", "$73.35");
        myPurchases.add(purchase);

        purchase = new Purchase("8", "OCT", "Walgreens", "$20.48");
        myPurchases.add(purchase);

        purchase = new Purchase("5", "OCT", "Walmart", "$201.58");
        myPurchases.add(purchase);

        purchase = new Purchase("4", "OCT", "Bevmo", "$106.48");
        myPurchases.add(purchase);
    }


    private void addDatatoDrawers()
    {
        images_left.add(R.drawable.paybill);
        images_left.add(R.drawable.creditavail);
        images_left.add(R.drawable.earncash);
        images_left.add(R.drawable.mypurchases);

        for (int i = 0; i < left_drawer.length; i++) {

            RowItem item = new RowItem(images_left.get(i), left_drawer[i]);
            rowItems_left.add(item);
        }

        images_right.add(R.drawable.myprof);
        images_right.add(R.drawable.settings);
        images_right.add(R.drawable.logout);


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

    public List<Purchase> getMyPurchases(){return this.myPurchases;}

    public List<Purchase> getFilteredPurchases(String query)
    {
        List<Purchase> filteredPurchases = new ArrayList<>();

        for(int i=0;i<myPurchases.size();i++)
        {
            Purchase p = myPurchases.get(i);

            if(p.getAmount().contains(query) || p.getPurchaseName().contains(query))
            {
                filteredPurchases.add(p);
            }
        }

        return filteredPurchases;
    }

    public List<Purchase> getSortedByAmount()
    {

        sort(myPurchases, new Comparator<Purchase>() {
            @Override
            public int compare(Purchase p1, Purchase p2) {

                String amt1_str = p1.getAmount().split("\\$")[1];
                int amt1 = Integer.parseInt(amt1_str.split("\\.")[0]);

                String amt2_str = p2.getAmount().split("\\$")[1];
                int amt2 = Integer.parseInt(amt2_str.split("\\.")[0]);

                return (amt1 - amt2);

            }
        });

        return myPurchases;
    }

    public List<Purchase> getSortedByName()
    {
        sort(myPurchases, new Comparator<Purchase>() {
            @Override
            public int compare(Purchase p1, Purchase p2) {
                String name1 = p1.getPurchaseName();
                String name2 = p2.getPurchaseName();

                return name1.compareTo(name2);
            }
        });

        return myPurchases;
    }

    public List<Purchase> getSortedByDate()
    {
        sort(myPurchases, new Comparator<Purchase>() {
            @Override
            public int compare(Purchase p1, Purchase p2) {
                String m1 = p1.getMonth();
                String d1 = p1.getDate();

                String m2 = p2.getMonth();
                String d2 = p2.getDate();

                int date1 = Integer.parseInt(d1);
                int date2 = Integer.parseInt(d2);

                int month1 = getMonthNumber(m1);
                int month2 = getMonthNumber(m2);

                if(month1 == month2)
                    return (date2-date1);

                return (month2-month1);

            }
        });

        return myPurchases;
    }

    private int getMonthNumber(String month)
    {
        if(month.equals("JAN"))
            return 1;
        if(month.equals("FEB"))
            return 2;
        if(month.equals("MAR"))
            return 3;
        if(month.equals("APR"))
            return 4;
        if(month.equals("MAY"))
            return 5;
        if(month.equals("JUN"))
            return 6;
        if(month.equals("JUL"))
            return 7;
        if(month.equals("AUG"))
            return 8;
        if(month.equals("SEP"))
            return 9;
        if(month.equals("OCT"))
            return 10;
        if(month.equals("NOV"))
            return 11;
        if(month.equals("DEC"))
            return 12;


        return 0;
    }

}
