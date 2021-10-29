package com.app.keepdog;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import androidx.appcompat.app.AppCompatActivity;

public class DeatilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deatil);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ImageView iv_details = findViewById(R.id.iv_details);
        TextView tvNameDeatils = findViewById(R.id.tvNameDeatils);
        TextView tvContentDeatils = findViewById(R.id.tvContentDeatils);

        tvContentDeatils.setMovementMethod(ScrollingMovementMethod.getInstance());
        Intent intent = getIntent();
        DogItem item = (DogItem) intent.getSerializableExtra("item");
        tvNameDeatils.setText(item.name);
        tvContentDeatils.setText(item.content);
        Glide.with(this).load(item.icon).into(iv_details);
        setTitle(item.name + "详情");



        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.conmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }else if(item.getItemId() == R.id.action_neve)
        {
            ProgressDialog progressDialog = new ProgressDialog(DeatilActivity.this);
            progressDialog.setMessage("领养中。。。");
            progressDialog.show();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SystemClock.sleep(2000);
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.dismiss();
                            Toast.makeText(DeatilActivity.this,"领养成功",Toast.LENGTH_LONG).show();
                            finish();
                        }
                    });
                }
            }).start();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static void openWebviewActivity(Context context,DogItem dogItem){
        Intent intent = new Intent(context, DeatilActivity.class);
        intent.putExtra("item",dogItem);
        context.startActivity(intent);
    }

}