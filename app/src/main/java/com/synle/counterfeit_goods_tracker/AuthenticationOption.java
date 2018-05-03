package com.synle.counterfeit_goods_tracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AuthenticationOption extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle(getString(R.string.login));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication_option);
    }


    public void onClickLoginAgency(View v){
        Intent intent = new Intent(this, AuthenticationAgency.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
        startActivity(intent);
    }

    public void onClickLoginCustomer(View v){
        Intent intent = new Intent(this, AuthenticationConsumer.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
        startActivity(intent);
    }
}
