package com.selfscore.selfscoreapp.Activities.DashboardActivities;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.selfscore.selfscoreapp.Model.Model;
import com.selfscore.selfscoreapp.R;
import com.selfscore.selfscoreapp.SelfScoreApplication;

public class UpdateInfoActivity extends AppCompatActivity {

    private Model model;

    private EditText phone1, phone2, phone3, email_view, sa1, sa2, city;
    private EditText grad_school, grad_fos, ugrad_school, ugrad_fos;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);

        model = ((SelfScoreApplication) this.getApplication()).getModel();

        Toolbar myToolbar = (Toolbar) findViewById(R.id._toolbar);
        setSupportActionBar(myToolbar);

        ImageView back_button = (ImageView) findViewById(R.id.back_button);
        ImageView home_button = (ImageView) findViewById(R.id.home_button);
        TextView header_view = (TextView) findViewById(R.id.header_card);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)header_view.getLayoutParams();
        params.setMarginStart(150);
        header_view.setLayoutParams(params);
        header_view.setText("Update Info");

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        home_button.setVisibility(View.GONE);

        //USER INFO
        phone1 = (EditText) findViewById(R.id.phone_pt1);
        phone2 = (EditText) findViewById(R.id.phone_pt2);
        phone3 = (EditText) findViewById(R.id.phone_pt3);
        email_view = (EditText) findViewById(R.id.email_view);
        sa1 = (EditText) findViewById(R.id.sa1);
        sa2 = (EditText) findViewById(R.id.sa2);
        city = (EditText) findViewById(R.id.city_view);
        grad_school = (EditText) findViewById(R.id.grad_schoo);
        grad_fos = (EditText) findViewById(R.id.grad_fos_v);
        ugrad_school = (EditText) findViewById(R.id.ugrad_schoo);
        ugrad_fos = (EditText) findViewById(R.id.ugrad_fos_v);

        //set info
        phone1.setText(model.getUser().getPhone().get(0));
        phone2.setText(model.getUser().getPhone().get(1));
        phone3.setText(model.getUser().getPhone().get(2));
        email_view.setText(model.getUser().getEmail());
        sa1.setText(model.getUser().getSA1());
        sa2.setText(model.getUser().getSA2());
        city.setText(model.getUser().getCity());
        grad_school.setText(model.getUser().getGrad().get(0));
        grad_fos.setText(model.getUser().getGrad().get(1));
        ugrad_school.setText(model.getUser().getUGrad().get(0));
        ugrad_fos.setText(model.getUser().getUGrad().get(1));


        Button save_changes_b = (Button) findViewById(R.id.sav_changes_button);
        Button cancel_b = (Button) findViewById(R.id.cancel_button);

        save_changes_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveInfo();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                Toast.makeText(UpdateInfoActivity.this, "Saved changes!", Toast.LENGTH_SHORT).show();
            }
        });

        cancel_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



    }

    private void saveInfo()
    {
        String p1, p2, p3;
        p1 = phone1.getText().toString();
        p2 = phone2.getText().toString();
        p3 = phone3.getText().toString();

        //save phone number
        model.getUser().savePhone(p1, p2, p3);

        String email, street_addr1, street_addr2, c;
        email = email_view.getText().toString();
        street_addr1 = sa1.getText().toString();
        street_addr2 = sa2.getText().toString();
        c = city.getText().toString();

        //save email and address details
        model.getUser().saveEmail(email);
        model.getUser().saveAddress(street_addr1, street_addr2, c);

        String graduate_school, graduate_fos;
        graduate_school = grad_school.getText().toString();
        graduate_fos = grad_fos.getText().toString();

        //save graduate school
        model.getUser().saveGraduate(graduate_school, graduate_fos);

        String ugraduate_school, ugraduate_fos;
        ugraduate_school = ugrad_school.getText().toString();
        ugraduate_fos = ugrad_fos.getText().toString();

        //save undergraduate schol
        model.getUser().saveUnderGrad(ugraduate_school, ugraduate_fos);



    }



}
