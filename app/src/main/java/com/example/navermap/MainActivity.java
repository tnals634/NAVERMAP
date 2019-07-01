package com.example.navermap;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.graphics.PointF;
import android.os.Bundle;
import android.widget.Toast;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.geometry.LatLngBounds;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.NaverMapOptions;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;
import com.naver.maps.map.util.FusedLocationSource;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;
    private FusedLocationSource locationSource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        MapFragment mapFragment = (MapFragment)fm.findFragmentById(R.id.map);

        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fm.beginTransaction().add(R.id.map, mapFragment).commit();
        }

        mapFragment.getMapAsync(this);

        locationSource = new FusedLocationSource(this,LOCATION_PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults){
        if(locationSource.onRequestPermissionsResult(
                requestCode,permissions,grantResults)){
            return;
        }
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }
    @UiThread
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        LatLng coord = new LatLng(35.9437857, 126.681656);

        CameraPosition cameraPosition =
                new CameraPosition(coord, 16,45,0);
        naverMap.setCameraPosition(cameraPosition);
        naverMap.setMapType(NaverMap.MapType.Hybrid);

        UiSettings uiSettings = naverMap.getUiSettings();
        //uiSettings.setCompassEnabled(false); //나침반 비활성화
        //uiSettings.setLocationButtonEnabled(true); //현위치 버튼 활성화

        uiSettings.setTiltGesturesEnabled(false); // 틸트 비활성화
        uiSettings.setRotateGesturesEnabled(false); // 회전 제스처 비활성화

        NaverMapOptions options = new NaverMapOptions()
                .locationButtonEnabled(true)
                .tiltGesturesEnabled(false); //초깃값 지정시 현위치 버튼 활성화, 틸트 제스처 비활성화

        naverMap.setOnMapClickListener((point, coord) ->
                Toast.makeText(this, coord.latitude + ", " + coord.longitude,
                        Toast.LENGTH_SHORT).show());


    }
}
