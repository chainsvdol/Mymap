package th.go.dol.vong.rachain.mymapgps;

import android.renderscript.Double3;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String centerLatString,centerLngString;
    private LatLng centerLatLng;
     private Double [] myLatDoubles={13.66927731,13.66604555,13.66531579,13.66856841};
    private Double [] myLngDoubles={100.62328577,100.61918736,100.6239295,100.6274271};
    private LatLng[] myLatLngs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_maps);

        // Create LatLng
        createLatLng();


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment);
          mapFragment.getMapAsync(this);

    } // Main

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



    }   // OnMapReady
}  // Main class
