package com.example.navermap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.geometry.LatLngBounds;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.NaverMapOptions;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;
import com.naver.maps.map.overlay.LocationOverlay;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.OverlayImage;
import com.naver.maps.map.util.FusedLocationSource;
import com.naver.maps.map.util.MarkerIcons;

import java.nio.channels.AsynchronousFileChannel;

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
        locationSource = new FusedLocationSource(this,LOCATION_PERMISSION_REQUEST_CODE);
        mapFragment.getMapAsync(this);

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
        LatLng coord = new LatLng(35.9423408, 126.6832079);
        Marker marker = new Marker();

        CameraPosition cameraPosition =
                new CameraPosition(coord, 16,45,0);
        naverMap.setCameraPosition(cameraPosition);
        //naverMap.setMapType(NaverMap.MapType.Hybrid);

        //UiSettings uiSettings = naverMap.getUiSettings();
        //uiSettings.setCompassEnabled(false); //나침반 비활성화
        //uiSettings.setLocationButtonEnabled(true); //현위치 버튼 활성화

        //uiSettings.setTiltGesturesEnabled(false); // 틸트 비활성화
        //uiSettings.setRotateGesturesEnabled(false); // 회전 제스처 비활성화

        //NaverMapOptions options = new NaverMapOptions()
                //.locationButtonEnabled(true)
                //.tiltGesturesEnabled(false); //초깃값 지정시 현위치 버튼 활성화, 틸트 제스처 비활성화

        
//        naverMap.setOnMapClickListener((pointF, latLng) ->
//                Toast.makeText(this,latLng.latitude+", "+ latLng.longitude,
//                        Toast.LENGTH_SHORT).show()); //클릭시 좌표 뜸

//        naverMap.setOnMapLongClickListener((pointF, latLng) ->
//                Toast.makeText(this,latLng.latitude + ", " +latLng.longitude,
//                        Toast.LENGTH_SHORT).show()); //롱클릭시 좌표 뜸

        
//        naverMap.setOnMapClickListener((pointF, latLng) ->
//                Toast.makeText(this,"지도 클릭", Toast.LENGTH_SHORT).show());
//
//        naverMap.setOnSymbolClickListener(symbol -> {
//            if("군산대학교".equals(symbol.getCaption())){
//                Toast.makeText(this,"군산대학교 클릭",Toast.LENGTH_SHORT).show();
//                return true;
//            }
//            return false;
//        });


//        naverMap.setOnMapDoubleTapListener((pointF, latLng) -> {
//            Toast.makeText(this,latLng.latitude + ", " + latLng.longitude,
//                    Toast.LENGTH_SHORT).show();
//            return true;
//        }); //더블탭 시 경도와 위도 표출


//        naverMap.setOnMapTwoFingerTapListener((pointF, latLng) -> {
//            Toast.makeText(this,latLng.latitude + ", " + latLng.longitude,
//                    Toast.LENGTH_SHORT).show();
//            return true;
//        }); //두손가락 탭 시 경도와 위도 표출


//        naverMap.setLocationSource(locationSource);
//
//        naverMap.setLocationTrackingMode(LocationTrackingMode.None);
//
//
//        naverMap.addOnLocationChangeListener(location -> Toast.makeText(this,
//                location.getLatitude() + ", " + location.getLatitude(),
//                Toast.LENGTH_SHORT).show());


        LocationOverlay locationOverlay = naverMap.getLocationOverlay();

        marker.setPosition(coord);
//        marker.setIcon(MarkerIcons.BLACK);
//        marker.setIcon(OverlayImage.fromResource(R.drawable.marker_icon));
        marker.setWidth(60);
        marker.setHeight(80);
        marker.setAngle(90);
        marker.setMap(naverMap);

    }
}
