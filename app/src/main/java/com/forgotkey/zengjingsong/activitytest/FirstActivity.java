package com.forgotkey.zengjingsong.activitytest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);

        //创建按钮转向至扫描二维码
        Button button1 = (Button) findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(FirstActivity.this, "You clicked Button 1", Toast.LENGTH_SHORT).show();
                //创建IntentIntegrator对象
                IntentIntegrator intentIntegrator = new IntentIntegrator(FirstActivity.this);
                //开始扫描
                intentIntegrator.initiateScan();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 获取解析结果
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "取消扫描", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "扫描内容:" + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
        //return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(FirstActivity.this,SecondActivity.class);
        switch (item.getItemId()){
            case R.id.add_item:
                Toast.makeText(this,"you clicked add", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                break;
            case R.id.remove_item:
                Toast.makeText(this,"you clicked remove",Toast.LENGTH_SHORT).show();
                break;
            case R.id.exit_item:
                Toast.makeText(this,"you clicked exit",Toast.LENGTH_SHORT).show();
                finish();
                break;
            default:
        }
        return true;
    }

}
