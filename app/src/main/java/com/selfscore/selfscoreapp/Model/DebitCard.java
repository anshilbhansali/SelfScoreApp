package com.selfscore.selfscoreapp.Model;

/**
 * Created by anshilbhansali on 8/18/16.
 */
public class DebitCard {

    private String cardnum1 = "0", cardnum2 = "0", cardnum3 = "0", cardnum4 = "2224";
    private int expmonth = 0, expyear = 0, sec = 0;
    private String streetaddr = "none", appt = "none", zip = "none";

    public DebitCard(){};

    public DebitCard(String c1, String c2, String c3, String c4, int expm, int expy, int sec, String addr, String appt, String zip)
    {
        cardnum1 = c1;
        cardnum2 = c2;
        cardnum3 = c3;
        cardnum4 = c4;

        expmonth = expm;
        expyear = expy;

        streetaddr = addr;
        this.appt = appt;
        this.zip = zip;
    }

    public String getCardnum4(){return cardnum4;}

}
