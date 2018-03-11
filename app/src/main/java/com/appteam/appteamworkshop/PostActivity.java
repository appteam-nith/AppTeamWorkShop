package com.appteam.appteamworkshop;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class PostActivity extends AppCompatActivity {

    private EditText titleEditText, messageEditText;
    private Button sbtBtn;
    private ProgressDialog progressDialog;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        AndroidNetworking.initialize(getApplicationContext());
        titleEditText = findViewById(R.id.titleEditText);
        messageEditText = findViewById(R.id.messageEditText);
        sbtBtn = findViewById(R.id.sbtBtn);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        sbtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titleEditText.getText().toString();
                String message = messageEditText.getText().toString();

                if(!title.isEmpty() && !message.isEmpty()){
                    progressBar.setVisibility(View.VISIBLE);
                    sendPost(title,message);
                }

                else {
                    Toast.makeText(PostActivity.this, "Please Enter All Field", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void sendPost(final String title, final String message){
        AndroidNetworking.post("https://appteamworkshop.herokuapp.com/api/confess")
                .addBodyParameter("title", title)
                .addBodyParameter("message", message)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response!=null)
                        {
                            try {
                                if((Boolean) response.get("success")){
                                    progressBar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(PostActivity.this, "Post Uploaded successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(PostActivity.this,MainActivity.class));
                                }
                                else{
                                    progressBar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(PostActivity.this, "Post didn't uploaded successfully", Toast.LENGTH_SHORT).show();
                                    titleEditText.setText("");
                                    messageEditText.setText("");
                                }
                            } catch (JSONException e) {
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(PostActivity.this, "Conncection Error", Toast.LENGTH_SHORT).show();
                                titleEditText.setText("");
                                messageEditText.setText("");

                            }
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(PostActivity.this, "Conncection Error", Toast.LENGTH_SHORT).show();
                        titleEditText.setText("");
                        messageEditText.setText("");
                    }
                });
    }
}
