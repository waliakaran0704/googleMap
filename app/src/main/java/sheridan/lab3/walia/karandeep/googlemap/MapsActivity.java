package sheridan.lab3.walia.karandeep.googlemap;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.GoogleMap.OnCameraIdleListener;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,OnMapClickListener, OnMapLongClickListener, OnCameraIdleListener {

    private GoogleMap mMap;
    private TextView mTextView;
    private TextView mTextView1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        mTextView = (TextView) findViewById(R.id.info);
        mTextView1 = (TextView) findViewById(R.id.camera);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapClickListener(this);
        mMap.setOnMapLongClickListener(this);
        mMap.setOnCameraIdleListener(this);

        int height = 150;
        int width = 150;

        int height1 = 150;
        int width1 = 100;

        BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.marker);
        Bitmap b=bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

        BitmapDrawable bitmapdraw1=(BitmapDrawable)getResources().getDrawable(R.drawable.oakville);
        Bitmap b1=bitmapdraw1.getBitmap();
        Bitmap smallMarker1 = Bitmap.createScaledBitmap(b1, width1, height1, false);

        // Add a marker in Sydney and move the camera
        LatLng Davis = new LatLng(43.656280, -79.739066);
        LatLng HMC = new LatLng(43.5911, -79.6468);
        LatLng Oakville = new LatLng(43.4692164565, -79.6909839027);

        mMap.addMarker(new MarkerOptions().position(Davis).title("Davis Location").snippet("7899 McLaughlin Rd, Brampton, ON L6Y 5H9").icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));;
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Davis));

        mMap.addMarker(new MarkerOptions().position(HMC).title("HMC Location").snippet("4180 Duke of York Blvd, Mississauga, ON L5B 0G5"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(HMC));

        mMap.addMarker(new MarkerOptions().position(Oakville).title("Oakville Location").snippet("1430 Trafalgar Rd, Oakville, ON L6H 2L1").icon(BitmapDescriptorFactory.fromBitmap(smallMarker1)));;
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Oakville));



        PolylineOptions rectOptions = new PolylineOptions()
                .add(new LatLng(43.656280, -79.739066))
                .add(new LatLng(43.5911, -79.6468))  // North of the previous point, but at the same longitude
                .add(new LatLng(43.4692164565, -79.6909839027));  // Same latitude, and 30km to the west


// Get back the mutable Polyline
        Polyline polyline = mMap.addPolyline(rectOptions);

        Circle circle = mMap.addCircle(new CircleOptions()
                .center(new LatLng(43.656280, -79.6909839027))
                .radius(20000)
                .strokeColor(Color.RED)
                .fillColor(Color.GRAY));

        GoogleMapOptions options = new GoogleMapOptions().liteMode(true);

    }

    public void onMapClick(LatLng point) {
        mTextView.setText("There are 24000 students enrolled in campuses");
    }


    public void onMapLongClick(LatLng point) {
        mTextView.setText("There are 24000 students enrolled in campuses");
    }


 public void onCameraIdle() {
        mTextView1.setText(mMap.getCameraPosition().toString());

    }
}



