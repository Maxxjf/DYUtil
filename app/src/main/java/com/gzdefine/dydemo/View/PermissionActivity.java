package com.gzdefine.dydemo.View;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.gzdefine.dydemo.Linsteren.PermissionListener;
import com.gzdefine.dydemo.R;

import java.util.List;

import butterknife.ButterKnife;

public class PermissionActivity extends BaseActivity {
String[] permission=new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.CAMERA};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        ButterKnife.bind(this);
        permission();
    }
    private void permission(){
        requestRunPermisssion(permission, new PermissionListener() {
            @Override
            public void onGranted() {
                //表示所有权限都授权了
                Toast.makeText(PermissionActivity.this, "所有权限都授权了", Toast.LENGTH_SHORT).show();
                //我们可以执行打电话的逻辑
                call();
            }

            @Override
            public void onDenied(List<String> deniedPermission) {
                for(String permission : deniedPermission){
                    Toast.makeText(PermissionActivity.this, "权限被拒绝：" + permission+";请到应用管理设置", Toast.LENGTH_SHORT).show();
                    //当拒绝了授权后，为提升用户体验，可以以弹窗的方式引导用户到设置中去进行设置
                    new AlertDialog.Builder(PermissionActivity.this)
                            .setMessage("需要开启权限才能使用此功能")
                            .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //引导用户到设置中去进行设置
                                    Intent intent = new Intent();
                                    intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                                    intent.setData(Uri.fromParts("package", getPackageName(), null));
                                    startActivity(intent);

                                }
                            })
                            .setNegativeButton("取消", null)
                            .create()
                            .show();
                }
            }
        });
    }

    public void call(){
        try {
            Intent intent = new Intent(Intent.ACTION_CALL);
            Uri uri = Uri.parse("tel:" + "10086");
            intent.setData(uri);
            startActivity(intent);
        }catch (SecurityException e){
            e.printStackTrace();
        }
    }
}
