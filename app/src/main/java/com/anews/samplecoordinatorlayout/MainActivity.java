package com.anews.samplecoordinatorlayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v){
        Intent intent = null;
        switch (v.getId()){
            case R.id._1:{
                intent = new Intent(this, SimpleActivity.class);
                break;
            }
            case R.id._2:{
                intent = new Intent(this, SimpleEnterAlwaysActivity.class);
                break;
            }
            case R.id._3:{
                intent = new Intent(this, SimpleEnterAlwaysCollapsedActivity.class);
                break;
            }
            case R.id._4:{
                intent = new Intent(this, CollapsingEnterAlwaysActivity.class);
                break;
            }
            case R.id._5:{
                intent = new Intent(this, CollapsingEnterAlwaysCollapsedActivity.class);
                break;
            }
            case R.id._6:{
                intent = new Intent(this, CollapsingExitUntilActivity.class);
                break;
            }
            case R.id._7:{
                intent = new Intent(this, CollapsingEnterAlwaysCollapsedEnterAlwaysActivity.class);
                break;
            }
            case R.id._8:{
                intent = new Intent(this, CollapsingEnterAlwaysCollapsedExitActivity.class);
                break;
            }
            case R.id._9:{
                intent = new Intent(this, CollapsingEnterAlwaysExitActivity.class);
                break;
            }
            case R.id._10:{
                intent = new Intent(this, Simple3XActivity.class);
                break;
            }
            case R.id._11:{
                intent = new Intent(this, FABActivity.class);
                break;
            }
            case R.id._12:{
                intent = new Intent(this, TabFABActivity.class);
                break;
            }
        }
        startActivity(intent);
    }
}
