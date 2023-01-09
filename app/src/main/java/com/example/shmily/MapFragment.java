package com.example.shmily;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;

import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;

import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;

public class MapFragment extends AppCompatActivity {
    MapView mMapView=null;
    AMap aMap;//AMap 类是地图的控制器类，用来操作地图。
    AMapLocationClient mLocationClient=null;
    AMapLocationClientOption mLocationOption=null;
    Marker mMarker;


    public AMapLocationListener mLocationListener=new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if(aMapLocation!=null){
                if(aMapLocation.getErrorCode()==0){
                    //可解析amapLocation获取相应内容

                    //获取经纬度
                    LatLng latLng=new LatLng(aMapLocation.getLatitude(),
                            aMapLocation.getLongitude());
                    //显示定位maker
                    //改
                    if(mMarker==null){
                        mMarker=aMap.addMarker(new MarkerOptions()
                                .position(latLng).draggable(true).setFlat(true));
                        //主动显示infowindow
                        //在getInfoWindow中进行infowindow的自定义
                        mMarker.showInfoWindow();
                        //固定标签在屏幕中央
                        mMarker.setPositionByPixels(mMapView.getWidth() / 2,mMapView.getHeight() / 2);

                    }else{
                        //已经显示过就修改
                        mMarker.setPosition(latLng);

                    }
                    //然后可以移动到定位点,使用animateCamera就有动画效果
                    //参数提示:1.经纬度 2.缩放级别
                    aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));

                }else{
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，
                    // errInfo是错误信息，详见错误码表。
                    Log.e("AmapError","location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                }
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐私权限
        //改
        AMapLocationClient.updatePrivacyShow(this,true,true);
        AMapLocationClient.updatePrivacyAgree(this,true);

        setContentView(R.layout.fragment_map);
        mMapView =findViewById(R.id.map);//找到地图控件
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)
        mMapView.onCreate(savedInstanceState);
        if(aMap==null){
            aMap = mMapView.getMap();//得到一个map对象
        }

        //初始化定位
        try {
            mLocationClient = new AMapLocationClient(getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //初始化AMapLocationClientOption对象
        mLocationOption=new AMapLocationClientOption();
        //设置定位模式为高精度模式
        mLocationOption.setLocationMode
                (AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位回调
        mLocationClient.setLocationListener(mLocationListener);
        //获取一次定位结果
        mLocationOption.setOnceLocation(true);
        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，
        // 启动定位时SDK会返回最近3s内精度最高的一次定位结果。
        // 如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，
        // 反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);
        mLocationOption.setOnceLocationLatest(true);
        //设置是否返回地址信息
        mLocationOption.setNeedAddress(true);
        //定位客户端设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();













    }




        //设置地图的生命周期
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }
}