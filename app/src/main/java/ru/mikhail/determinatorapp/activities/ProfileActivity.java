package ru.mikhail.determinatorapp.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import ru.mikhail.determinatorapp.R;
import ru.mikhail.determinatorapp.common.UserDTO;

public class ProfileActivity extends AppCompatActivity {
    TextView fio;
    TextView universityName;
    TextView email;
    TextView group;

    TextView fioTitle;
    TextView universityNameTitle;
    TextView emailTitle;
    TextView groupTitle;
    TextView errorMessage;

    String globalUsername = "admin";
    String globalPassword = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        fio = findViewById(R.id.profile_fio);
        universityName = findViewById(R.id.profile_university_name);
        email = findViewById(R.id.profile_email);
        group = findViewById(R.id.profile_group);

        fioTitle = findViewById(R.id.profile_fio_title);
        universityNameTitle = findViewById(R.id.profile_university_title);
        emailTitle = findViewById(R.id.profile_email_title);
        groupTitle = findViewById(R.id.profile_group_title);
        errorMessage = findViewById(R.id.profile_error_message);

        fioTitle.setVisibility(View.VISIBLE);
        universityNameTitle.setVisibility(View.VISIBLE);
        emailTitle.setVisibility(View.VISIBLE);
        groupTitle.setVisibility(View.VISIBLE);
        errorMessage.setVisibility(View.INVISIBLE);



        try {
            fillUser();
        } catch (JSONException e) {
            Log.e("DET", "onCreate: ", e);
        }
    }

    public void fillUser() throws JSONException {
        RequestQueue queue = Volley.newRequestQueue(this);

        JSONObject auth = new JSONObject();
        auth.put("username", globalUsername);
        auth.put("password", globalPassword);

        JSONObject arguments = new JSONObject();
        arguments.put("authentication", auth);

        String url = "http://192.168.1.34:3000/user/info";
        String emulatorUrl = "http://10.0.2.2:3000/user/info";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                url,
                arguments,
                response -> {
                    Log.i("DET", "onResponse: " + response.toString());
                    try {
                        fio.setText(response.getString("fio"));
                        email.setText(response.getString("email"));
                        universityName.setText(response.getString("universityName"));
                        group.setText(response.getString("groupName"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    fioTitle.setVisibility(View.INVISIBLE);
                    universityNameTitle.setVisibility(View.INVISIBLE);
                    emailTitle.setVisibility(View.INVISIBLE);
                    groupTitle.setVisibility(View.INVISIBLE);
                    errorMessage.setVisibility(View.VISIBLE);
                    Log.e("DET", "onResponse: " + error.getMessage());
                });
        queue.add(request);
    }
}