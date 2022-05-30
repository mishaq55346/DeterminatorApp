package ru.mikhail.determinatorapp.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import ru.mikhail.determinatorapp.Determinator;
import ru.mikhail.determinatorapp.R;
import ru.mikhail.determinatorapp.activities.MainActivities.GlobalLibraryActivity;
import ru.mikhail.determinatorapp.util.RequestHandler;

public class LoginActivity extends AppCompatActivity {
    private EditText loginEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginEditText = findViewById(R.id.loginEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        loginEditText.addTextChangedListener(new ChangeColorTextWatcher());
        passwordEditText.addTextChangedListener(new ChangeColorTextWatcher());
    }

    public void login(View view) {
        String login = loginEditText.getText().toString().trim().toLowerCase();
        String password = passwordEditText.getText().toString().trim().toLowerCase();
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", login);
        credentials.put("password", password);
        RequestHandler.sendRequest(RequestHandler.RequestType.USER_INFO,
                this, RequestHandler.getDefaultRequestJson(credentials),
                response -> {
                    Determinator.globalUsername = login;
                    Determinator.globalPassword = password;
                    Intent intent = new Intent(LoginActivity.this, GlobalLibraryActivity.class);
                    LoginActivity.this.startActivity(intent);
                },
                error -> paintInColor(Color.RED));
    }

    private void paintInColor(int color) {
        GradientDrawable loginDrawable = (GradientDrawable) loginEditText.getBackground();
        GradientDrawable passwordDrawable = (GradientDrawable) passwordEditText.getBackground();
        loginDrawable.setStroke((int) getResources().getDimension(R.dimen.strokeSize), color);
        passwordDrawable.setStroke((int) getResources().getDimension(R.dimen.strokeSize), color);
    }

    private class ChangeColorTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            paintInColor((int) getResources().getColor(R.color.custom_background_button));
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    }
}