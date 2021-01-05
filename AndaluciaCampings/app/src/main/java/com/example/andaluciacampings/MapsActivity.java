package com.example.andaluciacampings;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private LocationListener locationListener;
    private LocationManager locationManager;

    private final long MIN_TIME = 1000;
    private final long MIN_DIST = 50;
    int PERMISSION_ID = 44;



    private LatLng latLng;
    private Marker marker;
    private LatLng currentPosition, campingCabopino, campingLosEscullos, campingGiralda, campingCañosDelMeca, campingLaRosaleda, campingAlmayateCosta
            , campingPinarSanJosé, campingLasLomas, campingPlayaAguadulce, CampingAlmanat, campingValdevaqueros, campingMarAzulBalerma
            , campingLaAldea, CampingTarifa, CampingLuz;
    private LatLng[] campings;

    private BottomNavigationView bottomNav;

    private DatabaseHelper myDb;

    private final static boolean forceNetwork = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        bottomNav = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        bottomNav.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);
        myDb = new DatabaseHelper(this);
        ActivityCompat.requestPermissions(this, new String [] {Manifest.permission.ACCESS_FINE_LOCATION}, PackageManager.PERMISSION_GRANTED);
        ActivityCompat.requestPermissions(this, new String [] {Manifest.permission.ACCESS_COARSE_LOCATION}, PackageManager.PERMISSION_GRANTED);

        bottomNav.getMenu().getItem(0).setChecked(false);
        bottomNav.getMenu().getItem(1).setChecked(true);


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
        CampingTarifa = new LatLng(36.05472585341114, -5.64981372321939);
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
                if(checkPermissions()){
                    if(isLocationEnabled()) {
                        try {

                            Location target = new Location("target");

                            latLng = new LatLng(location.getLatitude(), location.getLongitude());
                            if (marker != null) {
                                marker.remove();
                            }
                            marker = mMap.addMarker(new MarkerOptions().position(latLng).title("You are here"));
                            float zoomLevel = 7.0f; //This goes up to 21
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel));

                            for (LatLng point : campings) {
                                target.setLatitude(point.latitude);
                                target.setLongitude(point.longitude);
                                if (location.distanceTo(target) < 100) {
                                    if (myDb.updateExperiencia(UsuarioAplicacion.get().getNombre(), 10)) {
                                        Toast.makeText(MapsActivity.this, R.string.Alcanzado_nuevo_camping_string, Toast.LENGTH_LONG).show();
                                        System.out.println("LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
                                    }
                                }
                            }
                        } catch (SecurityException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(MapsActivity.this,"Please turn on your location...",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(intent);
                    }
                } else {
                    requestPermissions();
                }
            }
        };
        //?
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME, MIN_DIST, locationListener);
        } catch (SecurityException e){
            e.printStackTrace();
        }
    }

    private boolean isLocationEnabled(){
        LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    private boolean checkPermissions() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_ID);
    }

    public void Home(){
        Intent intento = new Intent(MapsActivity.this, InicioAplicacion.class);
        startActivity(intento);
    }

    public void Perfil(){
        Intent intento = new Intent(MapsActivity.this, Perfil.class);
        startActivity(intento);
    }

    public void Ubicacion() {
        Intent intento = new Intent(this, MapsActivity.class);
        startActivity(intento);
    }

    public void Ranking(){
        Intent intento = new Intent(MapsActivity.this, RankingPersonas.class);
        startActivity(intento);
    }

    public void Campings(){
        Intent intento = new Intent(MapsActivity.this, ListaCampings.class);
        startActivity(intento);
    }


    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        bottomNav.postDelayed(() -> {
            int itemId = item.getItemId();
            if (itemId == R.id.inicioAplicacion){
                Home();
            } else if (itemId == R.id.map) {
                Ubicacion();
            } else if (itemId == R.id.perfil) {
                Perfil();
            } else if (itemId == R.id.rank) {
                Ranking();
            } else if (itemId == R.id.listaCampings){
                Campings();
            }
            finish();
        }, 300);
        return true;
    }
}