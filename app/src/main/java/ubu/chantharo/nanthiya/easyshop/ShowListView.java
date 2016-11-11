package ubu.chantharo.nanthiya.easyshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ShowListView extends AppCompatActivity {

    //Explicit
    private int anInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list_view);

        //Get Value From Intent
        anInt = getIntent().getIntExtra("index",0);
        Log.d("11novV1", "anInt ==>" +anInt);

    }// Main Method

}//Main Class
