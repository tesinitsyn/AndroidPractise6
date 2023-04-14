package com.example.androidpractise5;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(getBaseContext(), "31321", Toast.LENGTH_SHORT).show();
        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        final WindowManager.LayoutParams params = new
                WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        View rootView = LayoutInflater.from(this).inflate(R.layout.foreground_layout, null);
        ((TextView) rootView.findViewById(R.id.infoDisplay)).setText("hello");
        windowManager.addView(rootView, params);
        params.gravity = Gravity.BOTTOM | Gravity.CENTER;
        windowManager.updateViewLayout(rootView, params);

        rootView.findViewById(R.id.returnToFragmnetButton).setOnClickListener(v -> {
            windowManager.removeView(rootView);
        });
        return super.onStartCommand(intent, flags, startId);
    }
}
