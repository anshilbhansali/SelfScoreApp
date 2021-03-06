package com.selfscore.selfscoreapp.Activities.DashboardActivities;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.selfscore.selfscoreapp.Model.Model;
import com.selfscore.selfscoreapp.R;
import com.selfscore.selfscoreapp.SelfScoreApplication;

public class MyProfileActivity extends AppCompatActivity {

    Model model;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        Toolbar myToolbar = (Toolbar) findViewById(R.id._toolbar);
        setSupportActionBar(myToolbar);

        ImageView back_button = (ImageView) findViewById(R.id.back_button);
        ImageView home_button = (ImageView) findViewById(R.id.home_button);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        View view = findViewById(R.id.update_info_button);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),UpdateInfoActivity.class);
                startActivity(intent);
            }
        });

        model = ((SelfScoreApplication) this.getApplication()).getModel();

        TextView name = (TextView) findViewById(R.id.name_view);
        TextView phone = (TextView) findViewById(R.id.phonenum_view);
        TextView email = (TextView) findViewById(R.id.email_view);
        ImageView profilepic = (ImageView) findViewById(R.id.profile_pic);

        name.setText(model.getUser().getName());
        email.setText(model.getUser().getEmail());
        String phone_num = model.getUser().getPhone().get(0)+"."+model.getUser().getPhone().get(1)+"."+model.getUser().getPhone().get(2);
        phone.setText(phone_num);


        if(model.getUser().getProfilePic() != null)
            profilepic.setImageBitmap(model.getUser().getProfilePic());

    }

}
