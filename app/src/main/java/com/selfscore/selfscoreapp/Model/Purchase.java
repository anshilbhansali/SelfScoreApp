package com.selfscore.selfscoreapp.Model;

/**
 * Created by anshilbhansali on 8/4/16.
 */
public class Purchase {

    private String date, month, name, amount;

    public Purchase(String date, String month, String name, String amount)
    {
        this.date = date;
        this.month = month;
        this.name = name;
        this.amount = amount;
    }

    public String getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getMonth() {
        return month;
    }

    public String getPurchaseName() {
        return name;
    }

}
