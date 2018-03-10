package com.appteam.appteamworkshop;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class PostActivity extends AppCompatActivity {

    private EditText titleEditText, messageEditText;
    private Button sbtBtn;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        titleEditText = findViewById(R.id.titleEditText);
        messageEditText = findViewById(R.id.messageEditText);
        sbtBtn = findViewById(R.id.sbtBtn);

        sbtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titleEditText.getText().toString();
                String message = messageEditText.getText().toString();

                if(!title.isEmpty() && !message.isEmpty()){

                }

                else {
                    Toast.makeText(PostActivity.this, "Please Enter All Field", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void sendPost(String title, String message){

    }
}
