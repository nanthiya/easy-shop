package ubu.chantharo.nanthiya.easyshop;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import static ubu.chantharo.nanthiya.easyshop.R.id.textView11;

public class DetailActivity extends FragmentActivity implements OnMapReadyCallback {

    //Explicit
    private GoogleMap mMap;
    private TextView nameTextview, detailTextview, phoneTextview;
    private String nameString, detailString, phoneString,
            imageString, latString, lngString;
    private ImageView imageView, callImageView;
    private Button button;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_detail);

        //bind widget
        nameTextview = (TextView) findViewById(R.id.textView9);
        detailTextview = (TextView) findViewById(R.id.textView10);
        phoneTextview = (TextView) findViewById(R.id.textView11);
        imageView = (ImageView) findViewById(R.id.imageView4);
        button = (Button) findViewById(R.id.button2);
        callImageView = (ImageView) findViewById(R.id.imageView5);

        //Get Intent
        nameString = getIntent().getStringExtra("Name");
        detailString = getIntent().getStringExtra("Detail");
        phoneString = getIntent().getStringExtra("Phone");
        imageString = getIntent().getStringExtra("Image");
        latString = getIntent().getStringExtra("Lat");
        lngString = getIntent().getStringExtra("Lng");

        //Show Image
        Picasso.with(DetailActivity.this).load(imageString).into(imageView);


        //Call Controller
        callImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:=" + phoneString));
                if (ActivityCompat.checkSelfPermission(DetailActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);

            }//onClick
        });


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }//Main Method


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng latLng = new LatLng(Double.parseDouble(latString),
                Double.parseDouble(lngString));

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,16));
        mMap.addMarker(new MarkerOptions()
        .position(latLng)
        .title(nameString)
        .snippet("Phone ===>" + phoneString));

    }//onMapReady
}//Main class
