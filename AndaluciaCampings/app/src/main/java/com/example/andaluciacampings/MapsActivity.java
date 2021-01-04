package com.example.andaluciacampings;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private LocationListener locationListener;
    private LocationManager locationManager;

    private final long MIN_TIME = 1000;
    private final long MIN_DIST = 5;

    private LatLng latLng;
    private Marker marker;
    private LatLng currentPosition, campingCabopino, campingLosEscullos, campingGiralda, campingCañosDelMeca, campingLaRosaleda, campingAlmayateCosta
            , campingPinarSanJosé, campingLasLomas, campingPlayaAguadulce, CampingAlmanat, campingValdevaqueros, campingMarAzulBalerma
            , campingLaAldea, CampingTarifa, CampingLuz;
    private LatLng[] campings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ActivityCompat.requestPermissions(this, new String [] {Manifest.permission.ACCESS_FINE_LOCATION}, PackageManager.PERMISSION_GRANTED);
        ActivityCompat.requestPermissions(this, new String [] {Manifest.permission.ACCESS_COARSE_LOCATION}, PackageManager.PERMISSION_GRANTED);

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

        campingCabopino = new LatLng(36.490062617714415, -4.743357006152879);
        mMap.addMarker(new MarkerOptions().position(campingCabopino).title("Camping Cabopino")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        campingLosEscullos = new LatLng(36.80353101302459, -2.078768082807563);
        mMap.addMarker(new MarkerOptions().position(campingLosEscullos).title("Camping Los Escullos")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        campingGiralda = new LatLng(37.20009743404518, -7.301330646463092);
        mMap.addMarker(new MarkerOptions().position(campingGiralda).title("Camping Giralda")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        campingCañosDelMeca = new LatLng( 36.201705449863795, -6.035827088816103);
        mMap.addMarker(new MarkerOptions().position(campingCañosDelMeca).title("Camping Caños Del Meca")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        campingLaRosaleda = new LatLng(36.29325383590065, -6.095138894717429);
        mMap.addMarker(new MarkerOptions().position(campingLaRosaleda).title("Camping La Rosaleda")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        campingAlmayateCosta = new LatLng(36.72530616381238, -4.135005567911514);
        mMap.addMarker(new MarkerOptions().position(campingAlmayateCosta).title("Camping Almayate Costa")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        campingPinarSanJosé = new LatLng(36.201124159765804, -6.034519086966395);
        mMap.addMarker(new MarkerOptions().position(campingPinarSanJosé).title("Camping Pinar San José")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        campingLasLomas = new LatLng(37.15961094041243, -3.4547383581073423);
        mMap.addMarker(new MarkerOptions().position(campingLasLomas).title("Camping Las Lomas")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        campingPlayaAguadulce = new LatLng(36.671172297785475, -6.405771323734489);
        mMap.addMarker(new MarkerOptions().position(campingPlayaAguadulce).title("Camping Playa Aguadulce")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        CampingAlmanat = new LatLng(36.726861865016076, -4.113274177160128);
        mMap.addMarker(new MarkerOptions().position(CampingAlmanat).title("Camping Almanat")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        campingValdevaqueros = new LatLng(36.069383890547876, -5.679946416350117);
        mMap.addMarker(new MarkerOptions().position(campingValdevaqueros).title("Camping Valdevaqueros")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        campingMarAzulBalerma = new LatLng(36.72204697996905, -2.8782751716110466);
        mMap.addMarker(new MarkerOptions().position(campingMarAzulBalerma).title("Camping Mar Azul Balerma")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        campingLaAldea = new LatLng(37.14143113234951, -6.490598459957499);
        mMap.addMarker(new MarkerOptions().position(campingLaAldea).title("Camping La Aldea")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        CampingTarifa = new LatLng(36.05488418183475, -5.64954075258505);
        mMap.addMarker(new MarkerOptions().position(CampingTarifa).title("Camping Tarifa")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        CampingLuz = new LatLng(37.20830019007481, -7.252444631120175);
        mMap.addMarker(new MarkerOptions().position(CampingLuz).title("Camping Luz")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        // Add a marker in Malaga and move the camera
        // LatLng currentPosition = new LatLng(36.71784014808168, -4.42045255993546);
        // float zoomLevel = 7.0f; //This goes up to 21
        // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentPosition, zoomLevel));
        mMap.setMapType(mMap.MAP_TYPE_SATELLITE);
        campings = new LatLng[]{campingCabopino, campingLosEscullos, campingGiralda, campingCañosDelMeca, campingLaRosaleda, campingAlmayateCosta
                , campingPinarSanJosé, campingLasLomas, campingPlayaAguadulce, CampingAlmanat, campingValdevaqueros, campingMarAzulBalerma
                , campingLaAldea, CampingTarifa, CampingLuz};

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                try {
                    Location target = new Location("target");

                    latLng = new LatLng(location.getLatitude(), location.getLongitude());
                    if(marker != null){
                        marker.remove();
                    }
                    marker = mMap.addMarker(new MarkerOptions().position(latLng).title("You are here"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

                    for(LatLng point : campings) {
                        target.setLatitude(point.latitude);
                        target.setLongitude(point.longitude);
                        if(location.distanceTo(target) < 100) {
                            System.out.println("LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
                        }
                    }

                } catch(SecurityException e){
                    e.printStackTrace();
                }
            }
        };
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME, MIN_DIST, locationListener);
        } catch (SecurityException e){
            e.printStackTrace();
        }
    }
}