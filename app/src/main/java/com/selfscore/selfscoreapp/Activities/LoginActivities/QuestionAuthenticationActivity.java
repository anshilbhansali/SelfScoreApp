package com.selfscore.selfscoreapp.Activities.LoginActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.selfscore.selfscoreapp.Activities.DashboardActivities.MainActivity;
import com.selfscore.selfscoreapp.Model.Model;
import com.selfscore.selfscoreapp.R;
import com.selfscore.selfscoreapp.SelfScoreApplication;

public class QuestionAuthenticationActivity extends AppCompatActivity {

    private TextView sec_question;
    private EditText sec_answer;
    private Button continue_button;

    String answer_real, answer_user;

    //model
    Model model;

    //Activity
    Activity activity;

    //Views
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_authentication);

        activity = this;

        sec_question = (TextView) findViewById(R.id.sec_question);
        sec_answer = (EditText) findViewById(R.id.sec_answer);
        continue_button = (Button) findViewById(R.id.continue_button);
        mLoginFormView = findViewById(R.id.login_form);
        mLoginFormView.requestFocus();
        mProgressView = findViewById(R.id.login_progress);

        model = ((SelfScoreApplication) this.getApplication()).getModel();

        sec_question.setText(model.getSecQuestion());

        //get answer_real from database, and check if it equals sec_answer
        answer_user = sec_answer.getText().toString();

        continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer_user = sec_answer.getText().toString();

                if(TextUtils.isEmpty(answer_user))
                {
                    sec_answer.setError("Field is required");
                }
                else
                {
                    showProgress();

                    new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected Void doInBackground(Void... params) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {


                            Intent intent = new Intent(activity, MainActivity.class);
                            intent.putExtra("ACTIVITY_NAME", "Login");
                            startActivity(intent);

                            super.onPostExecute(aVoid);
                        }
                    }.execute();


                }

            }
        });

    }

    private void showProgress()
    {
        mLoginFormView.setVisibility(View.GONE);
        mProgressView.setVisibility(View.VISIBLE);
    }

    private void notShowProgress()
    {
        mLoginFormView.setVisibility(View.VISIBLE);
        mProgressView.setVisibility(View.GONE);
    }
}
