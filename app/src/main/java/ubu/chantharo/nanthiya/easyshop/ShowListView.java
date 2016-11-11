package ubu.chantharo.nanthiya.easyshop;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

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
        private String[] columnStrings = new String[]{
                "id",
                "Name",
                "Detail",
                "Phone",
                "Image",
                "Category",
                "Lat",
                "Lng"};

        private String[] nameStrings, detailStrings,
                phoneStrings, iconStrings,
                categoryStrings, latStrings, lngStrings ;

        public SynShop(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                OkHttpClient okHttpClient = new OkHttpClient();
                RequestBody requestBody = new FormEncodingBuilder()
                        .add("isAdd", "true")
                        .add("Category", Integer.toString(anInt))
                        .build();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(strings[0]).post(requestBody).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();

            } catch (Exception e) {
                Log.d("11novV1", "e doIn ==>" + e.toString());
                return null;
            }

        }//doInBack

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("11novV1", "JSON ===>" + s);

            try{

                JSONArray jsonArray = new JSONArray(s);

                nameStrings = new String[jsonArray.length()];
                detailStrings = new String[jsonArray.length()];
                phoneStrings = new String[jsonArray.length()];
                iconStrings = new String[jsonArray.length()];
                categoryStrings = new String[jsonArray.length()];
                latStrings = new String[jsonArray.length()];
                lngStrings = new String[jsonArray.length()];

                for (int i=0;i<jsonArray.length();i++){

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    nameStrings[i] = jsonObject.getString(columnStrings[1]);
                    detailStrings[i] = jsonObject.getString(columnStrings[2]);
                    phoneStrings[i] = jsonObject.getString(columnStrings[3]);
                    iconStrings[i] = jsonObject.getString(columnStrings[4]);
                    categoryStrings[i] = jsonObject.getString(columnStrings[5]);
                    latStrings[i] = jsonObject.getString(columnStrings[6]);
                    lngStrings[i] = jsonObject.getString(columnStrings[7]);

                    //Show

                }//for

                //Create ListView
                MyAdapter myAdapter = new MyAdapter(context, nameStrings,
                        detailStrings, phoneStrings,iconStrings);
                listView.setAdapter(myAdapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        Intent intent = new Intent(ShowListView.this, DetailActivity.class);
                        intent.putExtra("Name", nameStrings[i]);
                        intent.putExtra("Detail", detailStrings[i]);
                        intent.putExtra("Phone", phoneStrings[i]);
                        intent.putExtra("Image", iconStrings[i]);
                        intent.putExtra("Lat", latStrings[i]);
                        intent.putExtra("Lng", lngStrings[i]);
                        startActivity(intent);

                    }
                });


            }catch (Exception e){
                e.printStackTrace();
            }


        }//onPost

    }//SynShop Class



}//Main Class
