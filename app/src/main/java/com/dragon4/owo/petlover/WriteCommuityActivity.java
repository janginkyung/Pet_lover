package com.dragon4.owo.petlover;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dragon4.owo.petlover.model.Community;
import com.google.firebase.database.FirebaseDatabase;

public class WriteCommuityActivity extends AppCompatActivity {

    private Button button;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_commuity);

        editText = (EditText) findViewById(R.id.comunityEditText);
        button = (Button) findViewById(R.id.writeCommunityButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadCommunityServer();
            }
        });
    }

    private void uploadCommunityServer() {
        Community community = new Community();
        community.setContent(editText.getText().toString());
        community.setReviewNum(0);
        community.setCountNum(0);
        community.setCommunityTitle(editText.getText().toString().substring(0,5));
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.getReference().child("community").child(community.getCommunityTitle()).setValue(community);
        finish();
    }



}
