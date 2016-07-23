package com.selfscore.selfscoreapp;

import android.app.Application;
import android.graphics.AvoidXfermode;

/**
 * Created by anshilbhansali on 7/22/16.
 */
public class SelfScoreApplication extends Application {

    private Model model = new Model(this);

    public Model getModel(){return model;}

    public void setModel(Model model) {this.model = model;}

}
