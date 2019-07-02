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
import android.widget.ListAdapter;
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
import com.naver.maps.map.overlay.Align;
import com.naver.maps.map.overlay.ArrowheadPathOverlay;
import com.naver.maps.map.overlay.CircleOverlay;
import com.naver.maps.map.overlay.InfoWindow;
import com.naver.maps.map.overlay.LocationOverlay;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.MultipartPathOverlay;
import com.naver.maps.map.overlay.Overlay;
import com.naver.maps.map.overlay.OverlayImage;
import com.naver.maps.map.overlay.PathOverlay;
import com.naver.maps.map.overlay.PolygonOverlay;
import com.naver.maps.map.overlay.PolylineOverlay;
import com.naver.maps.map.util.FusedLocationSource;
import com.naver.maps.map.util.MarkerIcons;

import java.nio.channels.AsynchronousFileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
        Marker marker1 = new Marker();
        Marker marker2 = new Marker();
        InfoWindow infoWindow = new InfoWindow();
        PolylineOverlay polyline = new PolylineOverlay();
        CircleOverlay circle = new CircleOverlay();
        PathOverlay path = new PathOverlay();
        MultipartPathOverlay multipartPath = new MultipartPathOverlay();
        ArrowheadPathOverlay arrowheadPath = new ArrowheadPathOverlay();
        Marker marker3 = new Marker();
        Marker marker4 = new Marker();

        CameraPosition cameraPosition =
                new CameraPosition(new LatLng(35.969856,126.936638), 9,45,0);
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


//        naverMap.setLocationSource(locationSource); //해당 위치
//
//        naverMap.setLocationTrackingMode(LocationTrackingMode.None); //해당 위치 아이콘 설정
//
//
//        naverMap.addOnLocationChangeListener(location -> Toast.makeText(this,
//                location.getLatitude() + ", " + location.getLatitude(),
//                Toast.LENGTH_SHORT).show()); //위치 위도 경도 뜸


        LocationOverlay locationOverlay = naverMap.getLocationOverlay();
//
//        marker.setPosition(coord);
//        marker.setIcon(MarkerIcons.BLACK); //해당 색으로 지정
//        marker.setIcon(Color.RED); //해당 색으로 바꿈
//        marker.setIcon(OverlayImage.fromResource(R.drawable.marker_icon)); //내가 다운받은 아이콘으로 설정
//        marker.setWidth(Marker.SIZE_AUTO); //가로 사이즈 조정
//        marker.setHeight(Marker.SIZE_AUTO); //세로 사이즈 조정
        //marker.setAngle(90); // 각도 조정
        //marker.setFlat(true); // 눕힘
//        marker.setIconPerspectiveEnabled(true); //원근 효과

        //marker.setCaptionText("주 캡션"); //캡션 택스트 설정
        //marker.setCaptionRequestedWidth(200); // 텍스트 줄바꿈
        //marker.setCaptionAlign(Align.Top); //캡션 위치 설정 (위에 배치
        //marker.setCaptionOffset(30);//아이콘과 켑션 사이 30픽셀의 간격 지정
        //marker.setCaptionColor(Color.BLUE); //캡션을 파란색으로 지정
        //marker.setCaptionHaloColor(Color.rgb(200,255,200)); //캡션 외곽선을 연한 녹색으로 지정
        //marker.setCaptionTextSize(16); //캡션 사이즈 16dp로 지정

//        marker.setSubCaptionText("보조 캡션\n(sub caption)");
//        marker.setSubCaptionColor(Color.BLUE);
//        marker.setSubCaptionHaloColor(Color.rgb(200,255,200));
//        marker.setSubCaptionTextSize(10);

//        marker.setAlpha(0.5f); //marker 불투명도

//        marker.setMap(naverMap);


//        marker1.setPosition(new LatLng(35.9448724,126.6818884));
//        marker1.setIconPerspectiveEnabled(true);
//        marker1.setMap(naverMap); //원근 효과시 두번째 마커

//        infoWindow.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
//            @NonNull
//            @Override
//            public CharSequence getText(@NonNull InfoWindow infoWindow) {
//                return "정보 창 내용";
//            }
//        });
//
//        infoWindow.open(marker);

        //정보 창을 군산대학교 좌표에 열음
//        infoWindow.setPosition(coord);
//        infoWindow.open(naverMap);

        //infoWindow.close(); // 정보 창을 닫음
//
//        infoWindow.setMap(naverMap); //map 속성을 이용해 정보창을 열음
//        infoWindow.setMap(null);//map 속성을 이용해 정보창을 닫음

//        //지도를 클릭하면 정보 창을 닫음
//        naverMap.setOnMapClickListener((lanLng, point)->{
//            infoWindow.close();
//        });

//        //마커를 클릭하면
//        Overlay.OnClickListener listener = overlay -> {
//
//            if(marker.getInfoWindow() == null)
//            {
//                //현재 마커에 정보 창이 열려있지 않을 경우 엶
//                infoWindow.open(marker);
//            }
//            else
//            {
//                //이미 현재 마커에 정보 창이 열려있을 경우 닫음
//                infoWindow.close();
//            }
//            return true;
//        };
//
//        marker.setOnClickListener(listener);

//        marker.setTag("마커1");
//        marker.setOnClickListener(overlay -> {
//            //마커를 클릭할 때 정보창을 엶
//            infoWindow.open(marker);
//            return true;
//        });
//
//        marker1.setTag("마커2");
//        marker1.setOnClickListener(overlay -> {
//            //마커를 클릭할 때 정보창을 엶
//            infoWindow.open(marker1);
//            return true;
//        });
//
//        infoWindow.setAdapter(new InfoWindow.DefaultTextAdapter( this) {
//            @NonNull
//            @Override
//            public CharSequence getText(@NonNull InfoWindow infoWindow) {
//                //정보 창이 열린 마커의 tag를 텍스트로 노출하도록 반환
//                return (CharSequence)infoWindow.getMarker().getTag();
//            }
//        });

//        polyline.setCoords(Arrays.asList(
//                new LatLng(35.9448724,126.6818884),
//                new LatLng(35.943657,126.681690),
//                coord
//        )); // 폴리라인 오버레이 객체 생성

//        polyline.setMap(naverMap); //생성한 폴리라인 오버레이 지도에 추가
//        polyline.setMap(null); //오버레이를 지도에서 제거


//        List<LatLng> coords = new ArrayList<>();
//        Collections.addAll(coords,
//                new LatLng(35.9448724,126.6818884),
//                new LatLng(35.943657,126.681690),
//                coord
//        );
//        polyline.setCoords(coords);
//
//        coords.set(0,new LatLng(35.944723,126.679920)); //아직 반영x
//        polyline.setCoords(coords);//반영됨
//        polyline.setMap(naverMap);
//
//        polyline.setWidth(10); //두께를 10픽셀로 지정
//
//        polyline.setColor(Color.GREEN); // 녹색으로 지정
//
//        polyline.setPattern(10,5); // 점선 패턴 (실선 10픽셀, 공백 5픽셀)
//
//        polyline.setCapType(PolylineOverlay.LineCap.Round); //오버레이 끝 지점 모양 지정
//
//        polyline.setJoinType(PolylineOverlay.LineJoin.Round); // 오버레이 연결점 모양 지정


//        //폴리곤 오버레이 객체 생성 / 지도에 추가
//        PolygonOverlay polygon = new PolygonOverlay();
//        polygon.setCoords(Arrays.asList(
//                new LatLng(37.5640984, 126.9712268),
//                new LatLng(37.5651279, 126.9767904),
//                new LatLng(37.5625365, 126.9832241),
//                new LatLng(37.5585305, 126.9809297),
//                new LatLng(37.5590777, 126.974617)
//        ));
//        polygon.setMap(naverMap);
//
//        //polygon.setMap(null); //폴리곤 오버레이를 지도에서 제거
//
//        List<LatLng> coords = new ArrayList<>();
//        Collections.addAll(coords,
//                new LatLng(37.5640984, 126.9712268),
//                new LatLng(37.5651279, 126.9767904),
//                new LatLng(37.5625365, 126.9832241),
//                new LatLng(37.5585305, 126.9809297),
//                new LatLng(37.5590777, 126.974617)
//        );
//        polygon.setCoords(coords);
//
//        coords.set(0, new LatLng(37.5734571, 126.975335));// 아직 반영되지 않음
//        polygon.setCoords(coords);// 반영됨

//        polygon.setHoles(Collections.singletonList(Arrays.asList(
//                new LatLng(37.5612243, 126.9768938),
//                new LatLng(37.5627692, 126.9795502),
//                new LatLng(37.5628377, 126.976066)
//        )));

        //polygon.setColor(Color.BLUE); //폴리곤 오버레이의 면색상 지정

//        polygon.setOutlineWidth(5); //폴리곤 오버레이 테두리 두께 5픽셀 지정
//        polygon.setOutlineColor(Color.GREEN); // 폴리곤 오버레이 테두리 색상 지정


//        //서클 오버레이 객체를 지도에 추가
//        circle.setCenter(coord); //서클 오버레이의 중심점
//        circle.setRadius(500); //서클 오버레이 반경
//        circle.setColor(Color.RED); //서클 오버레이 색상
//        circle.setOutlineWidth(10); //서클 오버레이의 테두리 두께
//        circle.setOutlineColor(Color.YELLOW); // 서클 오버레이의 테두리 색상
//        circle.setMap(naverMap);
//
//        circle.setMap(null); //서클 오버레이 지도에서 제거


//        //경로선 오버레이 생성 및 지도에 추가
//        path.setCoords(Arrays.asList(
//                new LatLng(37.57152, 126.97714),
//                new LatLng(37.56607, 126.98268),
//                new LatLng(37.56445, 126.97707),
//                new LatLng(37.55855, 126.97822)
//        ));
//        path.setMap(naverMap);

        //path.setMap(null); // 경로선 오버레이 지도에서 제거

//        //경로선 오버레이의 좌표열 중 일부 변경
//        List<LatLng> coords = new ArrayList<>();
//        Collections.addAll(coords,
//                new LatLng(37.57152, 126.97714),
//                new LatLng(37.56607, 126.98268),
//                new LatLng(37.56445, 126.97707),
//                new LatLng(37.55855, 126.97822)
//        );
//        path.setCoords(coords);
//
//        coords.set(0, new LatLng(37.5734571, 126.975335));// 아직 반영되지 않음
//        path.setCoords(coords);// 반영됨

//        path.setWidth(30);//경로 오버레이 두께 지정
//        path.setOutlineWidth(5); // 경로 오버레이 테두리 두께 지정


//        //멀티파트 경로선 오버레이 생성 및 지도에 추가/제거
//        multipartPath.setCoordParts(Arrays.asList(
//                Arrays.asList(
//                        new LatLng(37.5744287, 126.982625),
//                        new LatLng(37.57152, 126.97714),
//                        new LatLng(37.56607, 126.98268)
//                ),
//                Arrays.asList(
//                        new LatLng(37.56607, 126.98268),
//                        new LatLng(37.56445, 126.97707),
//                        new LatLng(37.55855, 126.97822)
//                )
//        ));
//
//        multipartPath.setColorParts(Arrays.asList(
//                new MultipartPathOverlay.ColorPart(
//                        Color.RED, Color.WHITE, Color.GRAY, Color.LTGRAY),
//                new MultipartPathOverlay.ColorPart(
//                        Color.GREEN, Color.WHITE, Color.DKGRAY, Color.LTGRAY)
//        ));
//
//        multipartPath.setMap(naverMap);

        //multipartPath.setMap(null);//경로선 오버레이 지도에서 제거

//        //경로선 오버레이의 좌표열 중 일부 변경
//        List<LatLng> coordPart1 = new ArrayList<>();
//        Collections.addAll(coordPart1,
//                new LatLng(37.5744287, 126.982625),
//                new LatLng(37.57152, 126.97714),
//                new LatLng(37.56607, 126.98268)
//        );
//
//        List<LatLng> coordPart2 = new ArrayList<>();
//        Collections.addAll(coordPart2,
//                new LatLng(37.56607, 126.98268),
//                new LatLng(37.56445, 126.97707),
//                new LatLng(37.55855, 126.97822)
//        );
//
//        List<List<LatLng>> coordParts = Arrays.asList(coordPart1, coordPart2);
//        multipartPath.setCoordParts(coordParts);
//
//        coordParts.get(0).set(0, new LatLng(37.5734571, 126.975335));// 아직 반영되지 않음
//        multipartPath.setCoordParts(coordParts);// 반영됨

//        //색상 파트
//        MultipartPathOverlay.ColorPart colorPart = new MultipartPathOverlay.ColorPart(
//                Color.RED,   // 지나갈 경로선의 선 색상을 빨간색으로 지정
//                Color.WHITE, // 지나갈 경로선의 테두리 색상을 흰색으로 지정
//                Color.GRAY,  // 지나온 경로선의 선 색상을 회색으로 지정
//                Color.LTGRAY // 지나온 경로선의 테두리 색상을 밝은 회색으로 지정
//        );
//
//        multipartPath.setColorParts(Arrays.asList(
//                new MultipartPathOverlay.ColorPart(
//                        Color.RED, Color.WHITE, Color.GRAY, Color.LTGRAY),
//                new MultipartPathOverlay.ColorPart(
//                        Color.GREEN, Color.WHITE, Color.DKGRAY, Color.LTGRAY)
//        ));

//        //화살표 오버레이 생성 및 지도에 추가/제거
//        arrowheadPath.setCoords(Arrays.asList(
//                new LatLng(37.568003, 126.9772503),
//                new LatLng(37.5701573, 126.9772503),
//                new LatLng(37.5701573, 126.9793745)
//        ));
//        arrowheadPath.setMap(naverMap);

        //arrowheadPath.setMap(null); //화살표 오버레이 지도에서 제거

//        //화살표 오버레이 좌표열 중 일부 변경
//        List<LatLng> coords = new ArrayList<>();
//        Collections.addAll(coords,
//                new LatLng(37.568003, 126.9772503),
//                new LatLng(37.5701573, 126.9772503),
//                new LatLng(37.5701573, 126.9793745)
//        );
//        arrowheadPath.setCoords(coords);
//
//        coords.set(0, new LatLng(37.5734571, 126.975335));// 아직 반영되지 않음
//        arrowheadPath.setCoords(coords);// 반영됨

//        arrowheadPath.setWidth(20); // 화살표 오버레이의 두께 지정
//        arrowheadPath.setHeadSizeRatio(4); //화살표 오버레이의 머리크기를 두께의 4배로 지정
//        arrowheadPath.setColor(Color.GREEN); // 화살표 오버레이의 색상 지정
//        arrowheadPath.setOutlineColor(Color.BLUE); // 화살표 오버레이의 테두리 색상 지정


        marker1.setPosition(coord); //군산대
        marker1.setIconPerspectiveEnabled(true);
        marker1.setIcon(MarkerIcons.BLACK); //해당 색으로 지정
        marker1.setWidth(50);
        marker1.setHeight(80);
        marker1.setMap(naverMap);

        marker2.setPosition(new LatLng(35.9673799,126.7366249)); //군산시청
        marker2.setIconPerspectiveEnabled(true);
        marker2.setIcon(MarkerIcons.BLACK); //해당 색으로 지정
        marker2.setWidth(50);
        marker2.setHeight(80);
        marker2.setMap(naverMap);

        marker3.setPosition(new LatLng(35.969439,126.957327)); //원광대
        marker3.setIconPerspectiveEnabled(true);
        marker3.setIcon(MarkerIcons.BLACK); //해당 색으로 지정
        marker3.setWidth(50);
        marker3.setHeight(80);
        marker3.setMap(naverMap);

        marker4.setPosition(new LatLng(35.8441799,127.1289129)); //전북대
        marker4.setIconPerspectiveEnabled(true);
        marker4.setIcon(MarkerIcons.BLACK); //해당 색으로 지정
        marker4.setWidth(50);
        marker4.setHeight(80);
        marker4.setMap(naverMap);


    }
}
