package com.example.get_latitude_longitude;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Initialize the variables.
    EditText etPlace;
    Button btSubmit;
    TextView tvAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPlace = findViewById(R.id.et_place);
        btSubmit = findViewById(R.id.bt_submit);
        tvAddress = findViewById(R.id.tv_address);

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address = etPlace.getText().toString();
                GeoLocation geoLocation = new GeoLocation();
                geoLocation.getAddress(address, getApplicationContext(), new GeoHandler());
            }
        });
    }

    private class GeoHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            String address;
            switch (msg.what) {
                case 1:
                    Bundle bundle = msg.getData();
                    address = bundle.getString("address");
                    break;
                default:
                    address = null;
            }
            tvAddress.setText(address);
        }
    }
}