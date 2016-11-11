package ubu.chantharo.nanthiya.easyshop;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ShowListView extends AppCompatActivity {

    //Explicit
    private int anInt;
    private TextView textView;
    private ListView listView;
    private Button button;
    private String urlJSON = "http://swiftcodingthai.com/bee/get_shop_where_bee.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list_view);

        //Bind Widget
        textView = (TextView) findViewById(R.id.textView4);
        listView = (ListView) findViewById(R.id.livsShop);
        button = (Button) findViewById(R.id.button);

        //Get Value From Intent
        anInt = getIntent().getIntExtra("index", 0);
        Log.d("11novV1", "anInt ==>" + anInt);

        //Button Controller
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Show Text
        String strResult = null;
        switch (anInt) {
            case 0:
                strResult = getResources().getString(R.string.cat1);
                break;
            case 1:
                strResult = getResources().getString(R.string.cat2);
                break;
            case 2:
                strResult = getResources().getString(R.string.cat3);
                break;
        }
        textView.setText(strResult);

        //Create ListView
        SynShop synShop = new SynShop(ShowListView.this);
        synShop.execute(urlJSON);



    }// Main Method

    //Inner Class
    private class SynShop extends AsyncTask<String, Void, String> {

        //Explicit
        private Context context;

        public SynShop(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }//doInBack

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }//onPost

    }//SynShop Class



}//Main Class
