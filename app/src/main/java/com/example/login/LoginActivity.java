package com.example.login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {
    public static final String EXTRA_USER_NAME = "user name";
    public static final String EXTRA_PASSWORD = "password";
    public static final int REQUEST_CODE_SIGN_UP = 0;
    private EditText mEditTextUsername;
    private EditText mEditTextPassword;
    private Button mButtonLogin;
    private Button mButtonSignUp;
    private String mUsername;
    private String mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViews();
        setClickListeners();
    }

    private void findViews() {
        mEditTextUsername = findViewById(R.id.edit_text_username);
        mEditTextPassword = findViewById(R.id.edit_text_password);
        mButtonLogin = findViewById(R.id.button_login);
        mButtonSignUp = findViewById(R.id.button_signup);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK || data == null)
            return;
        if (requestCode == REQUEST_CODE_SIGN_UP) {
            mEditTextUsername.setText(data.getStringExtra(SignUpActivity.EXTRA_USERNAME));
            mEditTextPassword.setText(data.getStringExtra(SignUpActivity.EXTRA_PASSWORD));
            mUsername = data.getStringExtra(SignUpActivity.EXTRA_USERNAME);
            mPassword = data.getStringExtra(SignUpActivity.EXTRA_PASSWORD);
        }
    }

    private void setClickListeners() {
        mButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                intent.putExtra(EXTRA_USER_NAME, mEditTextUsername.getText().toString());
                intent.putExtra(EXTRA_PASSWORD, mEditTextPassword.getText().toString());
                startActivityForResult(intent, REQUEST_CODE_SIGN_UP);
            }
        });
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEditTextUsername.getText().length() == 0 || mEditTextPassword.getText().length() == 0)
                    Toast.makeText(LoginActivity.this, "Sign up and fill the blanks", Toast.LENGTH_SHORT).show();
                else if (mEditTextUsername.getText().toString().equals(mUsername) && mEditTextPassword.getText().toString().equals(mPassword))
                    Snackbar.make(findViewById(R.id.layout_login), "The Username and Password are correct", Snackbar.LENGTH_LONG).show();
                else
                    Toast.makeText(LoginActivity.this, "The Username or Password is incorrect", Toast.LENGTH_SHORT).show();
            }
        });
    }
}