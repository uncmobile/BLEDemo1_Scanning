package com.example.nirjon.bledemo1_scanning;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    BluetoothManager myManager;
    BluetoothAdapter myAdapter;
    BluetoothLeScanner myScanner;
    BluetoothDevice myDevice;

    ScanCallback myScanCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            super.onScanResult(callbackType, result);
            if(result.getDevice().getName() != null){
                myDevice = result.getDevice();
                if(myDevice.getName().equals("MIO GLOBAL")){
                    Log.i("Tag", "That's MIO smartwatch!");
                }
            }
        }

        @Override
        public void onBatchScanResults(List<ScanResult> results) {
            super.onBatchScanResults(results);
        }

        @Override
        public void onScanFailed(int errorCode) {
            super.onScanFailed(errorCode);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startScanButton(View v) {
        myManager = (BluetoothManager) getSystemService(BLUETOOTH_SERVICE);
        myAdapter = myManager.getAdapter();
        myScanner = myAdapter.getBluetoothLeScanner();
        myScanner.startScan(myScanCallback);
    }

    public void stopScanButton(View v) {
        myScanner.stopScan(myScanCallback);
    }
}
