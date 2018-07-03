package com.woody.fakegps;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;

public class MainActivity extends AppCompatActivity {

	MapView mapView;
	AMap aMap;
	Marker marker;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mapView = findViewById(R.id.map);
		mapView.onCreate(savedInstanceState);
		if(aMap == null){
			aMap = mapView.getMap();
			MyLocationStyle myLocationStyle;
			myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
			myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);
			myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
			myLocationStyle.showMyLocation(true);
			aMap.moveCamera(CameraUpdateFactory.zoomTo(14));
			aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
			aMap.getUiSettings().setMyLocationButtonEnabled(true);
			aMap.setMyLocationEnabled(true);
			aMap.setOnMapLongClickListener(new AMap.OnMapLongClickListener() {
				@Override
				public void onMapLongClick(LatLng latLng) {
					if(marker != null){
						marker.remove();
					}
					marker = aMap.addMarker(new MarkerOptions().position(latLng).title("经纬度").snippet(latLng.toString()));
					CameraUpdate mCameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(latLng,14,0,0));
					aMap.animateCamera(mCameraUpdate);
					//save latlng
					SharedPreferences sharedPreferences = getSharedPreferences("fakegps", Activity.MODE_WORLD_READABLE);
					sharedPreferences.edit().putString("latitude",latLng.latitude+"").putString("longitude",latLng.longitude+"").apply();
				}
			});

			aMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
				@Override
				public boolean onMarkerClick(Marker marker) {
					if(marker.isInfoWindowShown()){
						marker.showInfoWindow();
					}else {
						marker.hideInfoWindow();
					}
					return false;
				}
			});
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mapView.onDestroy();
	}

	@Override
	protected void onResume() {
		super.onResume();
		mapView.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		mapView.onPause();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mapView.onSaveInstanceState(outState);
	}
}
