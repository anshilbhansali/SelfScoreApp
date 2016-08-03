package com.selfscore.selfscoreapp.Activities.DashboardActivities;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.selfscore.selfscoreapp.Model.Model;
import com.selfscore.selfscoreapp.R;
import com.selfscore.selfscoreapp.SelfScoreApplication;

public class UpdateSecQuestion extends AppCompatActivity {

    EditText password_view;
    Model model;
    TextView questionView;
    String question, answer;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_sec_question);

        model = ((SelfScoreApplication) this.getApplication()).getModel();

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


        final View check_p_view = findViewById(R.id.check_password_view);
        final View update_sq_view = findViewById(R.id.update_secq_view);

        check_p_view.setVisibility(View.VISIBLE);
        update_sq_view.setVisibility(View.GONE);

        Button continue_b = (Button) findViewById(R.id.continue_button);
        password_view = (EditText) findViewById(R.id.current_p);

        continue_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = password_view.getText().toString();
                Boolean cancel = false;

                //reset error
                password_view.setError(null);

                if(!checkPassword(password))
                {
                    password_view.setError("Password is wrong");
                    cancel = true;
                }

                if(cancel)
                {
                    password_view.requestFocus();
                }
                else
                {
                    check_p_view.setVisibility(View.GONE);
                    update_sq_view.setVisibility(View.VISIBLE);
                }

            }
        });

        //AFTER PASSWORD VERIFIED...UPDATE SEC QUESTION
        final View arrow_down = findViewById(R.id.arrow_down);
        Spinner spinner = (Spinner) findViewById(R.id.spinner_create);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, model.getSecQuestions());
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                questionView = (TextView) view;
                question = questionView.getText().toString();

                if(!question.equals("Select a security question..."))
                {
                    //if a question is selected, hide arrow
                    arrow_down.setVisibility(View.GONE);
                }
                else
                {
                    arrow_down.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final EditText answer_view = (EditText) findViewById(R.id.answer_view);
        Button update_sq_b = (Button) findViewById(R.id.update_sq_button);
        update_sq_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean cancel = false;
                answer = answer_view.getText().toString();
                answer_view.setError(null);

                if(!checkQuestion(question))
                {
                    Toast.makeText(UpdateSecQuestion.this, "Please select a security question", Toast.LENGTH_SHORT).show();
                    cancel = true;
                }

                if(!checkAnswer(answer))
                {
                    answer_view.setError("Field is required");
                    cancel = true;
                }

                if(cancel)
                {
                    answer_view.requestFocus();
                }
                else
                {
                    model.setSecQuestion(question);
                    model.setAnswer(answer);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(UpdateSecQuestion.this, "Security question updated!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    public boolean checkPassword(String password)
    {
        return password.length()>0;
    }

    public boolean checkQuestion(String question)
    {
        if(question.equals("Select a security question..."))
            return false;

        return true;
    }

    public boolean checkAnswer(String answer)
    {
        return answer.length()>0;
    }
}

