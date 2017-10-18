package com.gzdefine.dydemo.View;

import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.gzdefine.dydemo.Linsteren.PermissionListener;
import com.gzdefine.dydemo.R;
import com.gzdefine.dydemo.Util.NetUtils;
import com.gzdefine.dydemo.Util.SPUtils;
import com.gzdefine.dydemo.Util.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 所有Activity都要继承的BaseActivity
 * TODO：
 * Created by Max on 2017/9/5.
 */

public class BaseActivity extends AppCompatActivity {

    TextView title;
    private static PermissionListener mListener;
    private static final int PERMISSION_REQUESTCODE = 100;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.windowBackground);//通知栏所需颜色
        }
    }

    public void requestRunPermisssion(String[] permissions, PermissionListener listener){
        mListener = listener;
        List<String> permissionLists = new ArrayList<>();
        for(String permission : permissions){
            if(ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED){
                permissionLists.add(permission);
            }
        }

        if(!permissionLists.isEmpty()){
            ActivityCompat.requestPermissions(this, permissionLists.toArray(new String[permissionLists.size()]), PERMISSION_REQUESTCODE);
        }else{
            //表示全都授权了
            mListener.onGranted();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_REQUESTCODE:
                if(grantResults.length > 0){
                    //存放没授权的权限
                    List<String> deniedPermissions = new ArrayList<>();
                    for(int i = 0; i < grantResults.length; i++){
                        int grantResult = grantResults[i];
                        String permission = permissions[i];
                        if(grantResult != PackageManager.PERMISSION_GRANTED){
                            deniedPermissions.add(permission);
                        }
                    }
                    if(deniedPermissions.isEmpty()){
                        //说明都授权了
                        mListener.onGranted();
                    }else{
                        mListener.onDenied(deniedPermissions);
                    }
                }
                break;
            default:
                break;
        }
    }



    public void getinfo() {
        String localClassName = this.getLocalClassName();
        Log.d("max", "localClassName:" + localClassName);
        ArrayList<UserHabitCollection> users= (ArrayList<UserHabitCollection>) SPUtils.get(this,"UserHabitCollection",new ArrayList<>());
        Log.d("max","users:"+users);
        UserHabitCollection userHabitCollection=new UserHabitCollection();
        userHabitCollection.setActName(localClassName);
        if (users==null){
            users=new ArrayList<>();
        }
        users.add(userHabitCollection);
        if (NetUtils.isWifi(this)){
            //TODO WIFI情况下发送用户习惯
//            users.clear();//清空
        }
        Log.d("max","list长度："+users.size());
        SPUtils.put(this,"UserHabitCollection",users);
        ArrayList<UserHabitCollection> users2= (ArrayList<UserHabitCollection>) SPUtils.get(this,"UserHabitCollection",new ArrayList<>());
        Log.d("max","users:"+users2);
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void setFinishOnTouchOutside(boolean finish) {
        super.setFinishOnTouchOutside(finish);
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent event) {
//        Log.d("max", "maax:");
//        Log.d("max", "getActionIndex:" + event.getActionIndex());
//        Log.d("max", "getButtonState:" + event.getButtonState());
//        Log.d("max", "getDeviceId:" + event.getDeviceId());
//        Log.d("max", "getDeviceId:" + event.getDeviceId());
//        return false;
//    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
