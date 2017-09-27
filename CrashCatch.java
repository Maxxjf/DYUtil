package com.example.dy.dgtlelike.ui.exception;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by DY on 2017/9/26.
 */

public class CrashCatch implements Thread.UncaughtExceptionHandler {
    private static CrashCatch e ;
    private Context mContext;


    private CrashCatch() {
    }

    public static CrashCatch getInstence(){
        if(e==null){
            e = new CrashCatch();
        }
        return e;
    }



    public void init(Context context){
        //初始化，获得上下文，设置该异常捕捉为默认异常捕捉
        this.mContext = context;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
//打印报错信息
        Writer result = new StringWriter();
        PrintWriter printWriter = new PrintWriter(result);
        e.printStackTrace(printWriter);
        Log.d("CrashCatch",result.toString());

//开启新UI线程显示提示框
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();

                new AlertDialog.Builder(mContext)
                        .setTitle("提示")
                        .setCancelable(false)
                        .setMessage("程序崩溃了...")
                        .setNeutralButton("我知道了", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                System.exit(0);
                            }
                        }).create().show();

                Looper.loop();
            }
        }.start();

    }

}
