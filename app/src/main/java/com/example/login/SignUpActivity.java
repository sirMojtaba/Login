package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
    public static final String EXTRA_USERNAME = "username";
    public static final String EXTRA_PASSWORD = "password";
    private EditText mEditTextUsername;
    private EditText mEditTextPassword;
    private String mUsername;
    private String mPassword;
    private Button mButtonSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        findViews();
        setClickListeners();
        Intent intent = getIntent();
        mUsername = intent.getStringExtra(LoginActivity.EXTRA_USER_NAME);
        mPassword = intent.getStringExtra(LoginActivity.EXTRA_PASSWORD);
        mEditTextUsername.setText(mUsername);
        mEditTextPassword.setText(mPassword);
    }

    private void findViews() {
        mEditTextUsername = findViewById(R.id.edit_text_username);
        mEditTextPassword = findViewById(R.id.edit_text_password);
        mButtonSignup = findViewById(R.id.button_signup);
    }

    private void setClickListeners() {
        mButtonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEditTextUsername.getText().length() == 0 || mEditTextPassword.getText().length() == 0)
                    Toast.makeText(SignUpActivity.this, "The Username or Password is empty!", Toast.LENGTH_SHORT).show();
                else {
                    Intent intent = new Intent();
                    intent.putExtra(EXTRA_USERNAME, mEditTextUsername.getText().toString());
                    intent.putExtra(EXTRA_PASSWORD, mEditTextPassword.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
}