package com.example.androidpractise5;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ChooseRole extends Fragment {

    private static final int NOTIFY_ID = 0;
    private final String CHANNEL_ID = "channel_id";
    View view;

    @SuppressLint("MissingPermission")
    public void showNotification(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Your first notification realisation")
                .setContentText("It's still your first notification realisation")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getContext());
        notificationManagerCompat.notify(NOTIFY_ID, builder.build());
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Channel name",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Channel description");
            NotificationManager notificationManager = getActivity().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public ChooseRole() {
        super(R.layout.fragment_choose_role);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_choose_role, container, false);
        view.findViewById(R.id.sportsman_button).setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("buttonPressing", "1");
            Navigation.findNavController(view).navigate(R.id.action_chooseRole_to_registration, bundle);
        });
        view.findViewById(R.id.notificationButton).setOnClickListener(v ->{
            showNotification();
        });
        return view;
    }
}