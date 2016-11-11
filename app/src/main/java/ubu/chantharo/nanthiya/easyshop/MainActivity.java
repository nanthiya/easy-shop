package ubu.chantharo.nanthiya.easyshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Explicit
    private ImageView cat1ImageView, cat2ImageView, cat3ImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        cat1ImageView = (ImageView) findViewById(R.id.imageView);
        cat2ImageView = (ImageView) findViewById(R.id.imageView2);
        cat3ImageView = (ImageView) findViewById(R.id.imageView3);

        //Image Controller
        cat1ImageView.setOnClickListener(this);
        cat2ImageView.setOnClickListener(this);
        cat3ImageView.setOnClickListener(this);


    }//Main Method

    @Override
    public void onClick(View view) {

        int index = 0;

        switch (view.getId()) {
            case R.id.imageView:
                index = 0;
                break;
            case R.id.imageView2:
                index = 1;
                break;
            case R.id.imageView3:
                index = 2;
                break;
        } //switch

        //intent to ShowlistView
        Intent intent = new Intent(MainActivity.this, ShowListView.class);
        intent.putExtra("index", index);
        startActivity(intent);

    }//onClick

}//Main Class
