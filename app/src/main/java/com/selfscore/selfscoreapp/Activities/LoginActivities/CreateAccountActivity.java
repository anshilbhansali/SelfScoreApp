package com.selfscore.selfscoreapp.Activities.LoginActivities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.selfscore.selfscoreapp.Activities.DashboardActivities.MainActivity;
import com.selfscore.selfscoreapp.Model.Model;
import com.selfscore.selfscoreapp.R;
import com.selfscore.selfscoreapp.SelfScoreApplication;

public class CreateAccountActivity extends AppCompatActivity {

    private EditText create_username, create_password, create_conf_password, sec_answer;
    private Spinner spinner;
    private Button continue_button;
    private View mProgressView;
    private View mLoginFormView;


    //flag
    boolean cancel;

    //model
    Model model;

    //credentials
    String username, password, conf_password, question, answer;

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserCreateTask mAuthTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        mLoginFormView = findViewById(R.id.login_form);
        mLoginFormView.requestFocus();
        mProgressView = findViewById(R.id.login_progress);

        create_username = (EditText) findViewById(R.id.username_create);
        create_password = (EditText) findViewById(R.id.password_create);
        create_conf_password = (EditText) findViewById(R.id.confirm_password_create);
        spinner = (Spinner) findViewById(R.id.spinner_create);
        sec_answer = (EditText) findViewById(R.id.sec_question_answer);
        final View sec_answer_view = findViewById(R.id.sec_question_answer_view);
        continue_button = (Button) findViewById(R.id.continue_button);

        continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptCreate();
            }
        });
        sec_answer_view.setVisibility(View.GONE);

        model = ((SelfScoreApplication) this.getApplication()).getModel();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, model.getSecQuestions());
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view;
                question = textView.getText().toString();

                if(position != 0)
                {
                    sec_answer_view.setVisibility(View.VISIBLE);
                    sec_answer_view.requestFocus();
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void attemptCreate()
    {
        //reset errors
        create_username.setError(null);
        create_password.setError(null);
        create_conf_password.setError(null);

        //get text
        username = create_username.getText().toString();
        password = create_password.getText().toString();
        conf_password = create_conf_password.getText().toString();
        answer = sec_answer.getText().toString();

        cancel = false;
        View focusView = null;


        //check if password is created
        if (TextUtils.isEmpty(password)) {
            create_password.setError(getString(R.string.error_field_required));
            focusView = create_password;
            cancel = true;
        }

        // Check for a valid username.
        if (TextUtils.isEmpty(username)) {
            create_username.setError(getString(R.string.error_field_required));
            focusView = create_username;
            cancel = true;
        }
        else if (!isUsernameValid(username)) {
            create_username.setError("Username must be 8 characters minimum");
            focusView = create_username;
            cancel = true;
        }


        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            create_password.setError(getString(R.string.error_invalid_password));
            focusView = create_password;
            cancel = true;
        }

        //check confirm password field
        if(!checkConfPassword(password, conf_password))
        {
            create_conf_password.setError(getString(R.string.conf_password_error));
            focusView = create_conf_password;
            cancel = true;
        }

        //CHECK FOR SEC QUESTION
        if(question.equals("Select a security question..."))
        {
            Toast.makeText(CreateAccountActivity.this, "Please select a security question", Toast.LENGTH_SHORT).show();
            //Log.v("FOCUS VIEW REQUESTED",focusView.toString());
            if(!cancel)
            {
                //change focus view only when username password info is validated,
                //otherwise leave focusview as is
                focusView = mLoginFormView;
                cancel = true;
            }

        }

        if(TextUtils.isEmpty(answer))
        {
            sec_answer.setError("Field is required");
            if(!cancel)
            {
                focusView = sec_answer;
                cancel = true;
            }

        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            Log.v("FOCUS VIEW REQUESTED",focusView.toString());
            focusView.requestFocus();
        }
        else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new UserCreateTask(username, password, question, answer);
            mAuthTask.execute((Void) null);
        }

    }

    private boolean isUsernameValid(String username) {

        //check username criteria
        return username.length() >= 5;
    }

    private boolean isPasswordValid(String password) {

        //check password criteria
        return password.length() > 4;
    }

    private boolean checkConfPassword(String password, String conf_password)
    {
        return password.equals(conf_password);
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserCreateTask extends AsyncTask<Void, Void, Boolean> {

        private final String mUserName;
        private final String mPassword;
        private final String mQuestion;
        private final String mAnswer;

        UserCreateTask(String username, String password, String question, String answer) {
            mUserName = username;
            mPassword = password;
            mQuestion = question;
            mAnswer = answer;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                return false;
            }

            //Register new user in database

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success)
            {
                goToDashboard();
            }
            else
            {

            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }



    }

    public void goToDashboard()
    {
        Intent intent = new Intent(this, MainActivity.class);
        model.setUsername(username);
        model.setPassword(password);
        model.setSecQuestion(question);
        model.setAnswer(answer);

        intent.putExtra("ACTIVITY_NAME", "Login");
        startActivity(intent);

    }


}
