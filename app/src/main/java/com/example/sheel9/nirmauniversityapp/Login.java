package com.example.sheel9.nirmauniversityapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Login extends AppCompatActivity {
    private List<DataFetchLogin> data_list;
    Button btn;
    EditText username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn = (Button) findViewById(R.id.button);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        data_list  = new ArrayList<>();
        load_data_from_server(0);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkLogin(v);
            }
        });

        SliderLayout sliderShow = (SliderLayout) findViewById(R.id.slider);

        DefaultSliderView dsv = new DefaultSliderView(this);
        dsv.image("http://www.nirmauni.ac.in/img/road1.jpg").setScaleType(BaseSliderView.ScaleType.Fit);
        dsv.bundle(new Bundle());
        dsv.getBundle().putString("extra", "1");
        DefaultSliderView dsv1 = new DefaultSliderView(this);
        dsv1.image("https://www.collegesearch.in/upload/institute/images/large/campus31.jpg").setScaleType(BaseSliderView.ScaleType.Fit);
        dsv1.bundle(new Bundle());
        dsv1.getBundle().putString("extra", "2");
        DefaultSliderView dsv2 = new DefaultSliderView(this);
        dsv2.image("http://www.nirmauni.ac.in/img/road2.jpg").setScaleType(BaseSliderView.ScaleType.Fit);
        dsv2.bundle(new Bundle());
        dsv2.getBundle().putString("extra", "3");
        DefaultSliderView dsv3 = new DefaultSliderView(this);
        dsv3.image("https://imnublog.files.wordpress.com/2011/10/01.jpg").setScaleType(BaseSliderView.ScaleType.Fit);
        dsv3.bundle(new Bundle());
        dsv3.getBundle().putString("extra", "4");

        sliderShow.addSlider(dsv);
        sliderShow.addSlider(dsv1);
        sliderShow.addSlider(dsv2);
        sliderShow.addSlider(dsv3);
        sliderShow.startAutoCycle();
    }
    private void load_data_from_server(int id) {

        AsyncTask<Integer,Void,Void> task = new AsyncTask<Integer, Void, Void>() {
            @Override
            protected Void doInBackground(Integer... integers) {

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://dbitstudent.webatu.com/user_auth.php")
                        .build();
                try {
                    Response response = client.newCall(request).execute();

                    JSONArray array = new JSONArray(response.body().string());

                    for (int i=0; i<array.length(); i++){

                        JSONObject object = array.getJSONObject(i);

                        DataFetchLogin data = new DataFetchLogin(object.getString("id"), object.getString("password"));

                        data_list.add(data);

                    }



                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    System.out.println("End of content");
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {

            }
        };

        task.execute(id);
    }
    public void checkLogin(View arg0) {

        final String email = username.getText().toString();
        if (!isValidEmail(email)) {
            //Set error message for email field
            username.setError("Invalid Email");
        }

        final String pass = password.getText().toString();
        if (!isValidPassword(pass)) {
            //Set error message for password field
            password.setError("Password cannot be empty");
        }


        if(isValidEmail(email) && isValidPassword(pass))
        {
            Intent dash = new Intent(Login.this, Dashboard.class);
            startActivity(dash);
            finish();

            for (int i = 0; i < data_list.size(); i++) {

                if (data_list.get(i).getUsername().compareTo(username.getText().toString()) == 0 && data_list.get(i).getPassword().compareTo(password.getText().toString()) == 0) {
                    dash = new Intent(Login.this, Dashboard.class);
                    startActivity(dash);
                    finish();
                    break;
                }

            }
        }

    }

    // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();

    }

    // validating password
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() >= 4) {
            return true;
        }
        return false;

    }
}
