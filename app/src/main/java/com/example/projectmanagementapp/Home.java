package com.example.projectmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Home extends AppCompatActivity {
    TextView mTextUsername;
    String intentusername;
    TextView mTextUserEmail;
    String result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        intentusername = getIntent().getStringExtra("EXTRA_USERNAME");
        mTextUsername= (TextView) findViewById(R.id.mTextUsername);
        mTextUserEmail= (TextView) findViewById(R.id.mTextUserEmail);

        new RetrieveNameTask().execute();
    }

    // Retrieve Name of User
    private class RetrieveNameTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {

            // Define the URL of the server script that will retrieve the user's name
            String url = "https://renthousesrilanka.lk/Project%20Management/selectUser.php?email=" + intentusername;

            try {
                // Create an HTTP connection to the server
                HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();

                // Set the request method to GET
                conn.setRequestMethod("GET");

                // Send the request to the server
                conn.connect();

                // Read the response from the server
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }
                reader.close();
                conn.disconnect();

                // Parse the JSON response to extract the user's name
                JSONObject jsonObject = new JSONObject(stringBuilder.toString());
                String name = jsonObject.getString("name");

                // Return the user's name
                return name;

            } catch (IOException | JSONException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String name) {
            // Display the user's name in the TextView
            mTextUsername.setText(name);
            mTextUserEmail.setText(intentusername);
        }
    }

    public void task(View view) {

        Intent maintainIntent = new Intent(Home.this, ViewTask.class);
        maintainIntent.putExtra("EXTRA_USERNAME", intentusername);
        startActivity(maintainIntent);
    }


    public void profile(View view) {

        Intent maintainIntent = new Intent(Home.this, EditProfile.class);
        maintainIntent.putExtra("EXTRA_USERNAME", intentusername);
        startActivity(maintainIntent);
    }


    public void logout(View view){
        Intent maintainIntent = new Intent(Home.this, MainActivity.class);
        startActivity(maintainIntent);
    }



    //read data from online database
    public void SelectUserDetails(){

        class SendPostReqAsyncTask extends AsyncTask<String,Void,String> {

            String id, name, username,email,phoneNumber;

            @Override
            protected String doInBackground(String... strings) {

                try {
                    URL url = new URL("https://renthousesrilanka.lk/Project%20Management/selectUser.php?email="+ intentusername);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));

                    String line = null;
                    StringBuilder sb = new StringBuilder();

                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                    Log.e("pass 2", "connection success ");
                } catch (Exception e) {
                    Log.e("Fail 2", e.toString());
                }

                try {
                    JSONObject json_data = new JSONObject(result);
                    id = (json_data.getString("id"));
                    name = (json_data.getString("name"));
                    username = (json_data.getString("username"));
                    email = (json_data.getString("email"));
                    phoneNumber = (json_data.getString("phoneNumber"));

                } catch (Exception e) {
                    Log.e("Fail 3", e.toString());

                }
                return "Data Select Successfully";
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if(s!=null){
                    // Do you work here on success
                    mTextUsername.setText(username);

                }else{
                    // null response or Exception occur
                }
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute();
        return;
    }
}