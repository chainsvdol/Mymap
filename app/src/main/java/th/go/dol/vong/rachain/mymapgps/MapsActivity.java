package th.go.dol.vong.rachain.mymapgps;

import android.graphics.Color;
import android.renderscript.Double3;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    private String centerLatString,centerLngString;
    private LatLng centerLatLng;
     private Double [] myLatDoubles={13.66927731,13.66604555,13.66531579,13.66856841};
    private Double [] myLngDoubles={100.62328577,100.61918736,100.6239295,100.6274271};
    private LatLng[] myLatLngs;
    private Button normalButton,satelliteButton,terrainButton,hybidButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_maps);

        // bind

        bindwidget();

        // Create LatLng
        createLatLng();


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment);
          mapFragment.getMapAsync(this);

    } // Main

    private void bindwidget() {

        normalButton= (Button) findViewById(R.id.button2);
        satelliteButton= (Button) findViewById(R.id.button3);
        terrainButton = (Button) findViewById(R.id.button4);
        hybidButton = (Button) findViewById(R.id.button5);


    }

    private void createLatLng() {
          //Forcenter

          centerLatString=getIntent().getStringExtra("Lat");
          centerLngString=getIntent().getStringExtra("Lng");

        Double latDouble =Double.parseDouble(centerLatString);
        Double lngDouble =Double.parseDouble(centerLngString);

        centerLatLng =new LatLng(latDouble,lngDouble);
          // For Other Marker
        myLatLngs =new LatLng[myLatDoubles.length];

        for (int i=0;i<myLatDoubles.length;i++){

            myLatLngs[i]=new LatLng(myLatDoubles[i],myLngDoubles[i]);

        }  // For


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centerLatLng,16));
        //mMap.addMarker(new MarkerOptions().position(centerLatLng)); // Default Marker
        mMap.addMarker(new MarkerOptions()
                .position(centerLatLng)
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.friend))); //
        mMap.addMarker(new MarkerOptions()
            .position(myLatLngs[0])
            .icon(BitmapDescriptorFactory
                       .defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

        mMap.addMarker(new MarkerOptions()
                .position(myLatLngs[1])
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mMap.addMarker(new MarkerOptions()
                .position(myLatLngs[2])
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
        mMap.addMarker(new MarkerOptions()
                .position(myLatLngs[3])
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_BLUE )));

        changeTypeMap();

        createPolyline();




    }   // OnMapReady

    private void createPolyline() {

        PolylineOptions objPolylineOptions =new PolylineOptions()
                .add(myLatLngs[0])
                .add(myLatLngs[1])
                .add(myLatLngs[2])
                .add(myLatLngs[3])
                .add(myLatLngs[0])
                .width(5)
                .color(Color.BLUE);
        mMap.addPolyline(objPolylineOptions);



    }

    private void changeTypeMap() {

        normalButton.setOnClickListener(this);
        satelliteButton.setOnClickListener(this);
        terrainButton.setOnClickListener(this);
        hybidButton.setOnClickListener(this);





    }//   change type map

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.button2:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
            case R.id.button3:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.button4:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                break;
            case R.id.button5:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
        }
    }
}  // Main class
