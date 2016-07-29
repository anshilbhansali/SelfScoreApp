package com.selfscore.selfscoreapp.Model;

import java.util.ArrayList;

/**
 * Created by anshilbhansali on 7/29/16.
 */
public class UserInfo {
    private String name, phone_pt1, phone_pt2, phone_pt3, email;
    private String street_addr_1, street_addr_2, city;
    private String grad_school, grad_fos, undergrad_school, undergrad_fos;


    public UserInfo()
    {
        name = "Aditya Bhandari";
        phone_pt1 = "555";
        phone_pt2 = "123";
        phone_pt3 = "4567";
        email = "guysmiley@email.com";
        street_addr_1 = "123 Happytown Road";
        street_addr_2 = "Apartment 777";
        city = "Happytown, ME 12345";
        grad_school = "Stanford University";
        grad_fos = "PhD Computer Science";
        undergrad_school = "UC Berkely";
        undergrad_fos = "BS Computer Science";
    }

    public void savePhone(String phone1, String phone2, String phone3)
    {
        this.phone_pt1 = phone1;
        this.phone_pt2 = phone2;
        this.phone_pt3 = phone3;
    }

    public void saveEmail(String email)
    {
        this.email = email;
    }

    public void saveAddress(String sa1, String sa2, String city)
    {
        this.street_addr_1 = sa1;
        this.street_addr_2 = sa2;
        this.city = city;
    }

    public void saveGraduate(String gradschool, String gradfos)
    {
        this.grad_school = gradschool;
        this.grad_fos = gradfos;
    }

    public void saveUnderGrad(String ugradSchool, String ugradfos)
    {
        this.undergrad_fos = ugradfos;
        this.undergrad_school = ugradSchool;
    }

    public String getName(){return this.name;}

    public String getEmail(){return this.email;}

    public ArrayList<String> getPhone()
    {
        ArrayList<String> phone = new ArrayList<>();
        phone.add(this.phone_pt1);
        phone.add(this.phone_pt2);
        phone.add(this.phone_pt3);
        return phone;
    }

    public String getSA1(){return this.street_addr_1;}
    public String getSA2(){return this.street_addr_2;}
    public String getCity(){return this.city;}

    public ArrayList<String> getGrad()
    {
        ArrayList<String> grad = new ArrayList<>();
        grad.add(this.grad_school);
        grad.add(this.grad_fos);

        return grad;
    }

    public ArrayList<String> getUGrad()
    {
        ArrayList<String> ugrad = new ArrayList<>();
        ugrad.add(this.undergrad_school);
        ugrad.add(this.undergrad_fos);

        return ugrad;
    }







}
