package th.go.dol.vong.rachain.mymapgps;

import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.textservice.TextInfo;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

     //Explicit
    private TextView showLatTextView,showLongTextView;
    private LocationManager objLacationManager;
    private Criteria objCriteria;
    private boolean GPSABoolean,networkABoolean;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Open Service Get Location

        // Bind Widget

        bindWidget();


        openServiceGetLocation();






    }  // Main method

    private void bindWidget() {

        showLatTextView= (TextView) findViewById(R.id.txtShowLat);
        showLongTextView= (TextView) findViewById(R.id.txtShowLong);


    }

    // Create Class for Find Location

    public final LocationListener objLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            showLatTextView.setText(String.format("%.7f",location.getLatitude()));
            showLongTextView.setText(String.format("%.7f",location.getLongitude()));


        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };  // End of Class


    private void openServiceGetLocation() {

        objLacationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        objCriteria = new Criteria();
        objCriteria.setAccuracy(Criteria.ACCURACY_FINE);
        objCriteria.setAltitudeRequired(false);
        objCriteria.setBearingRequired(false);




    }  // OpenServiceGetLocation


    public void clickMyMap(View view){

        Intent objIntent = new Intent(MainActivity.this,MapsActivity.class);
               startActivity(objIntent);



    }


}  // Main Class
