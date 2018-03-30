package fr.best.client.entity;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by barou on 19/03/18.
 */

public class WifiScanner
{
    static final int REQUEST_ACCESS_FINE_LOCATION = 1;

    private static List<Signal> lsignal=new ArrayList<>();

    public static List<Signal> scanWifi(Context context)
    {   //précaution
        if(!lsignal.isEmpty())
            lsignal.clear();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions((Activity) context,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_ACCESS_FINE_LOCATION);

                return null;
            }
        }

        List<String> listwifi=new ArrayList<>();
        listwifi.add("SFR_F340");
        listwifi.add("dlink");


        WifiManager managerWifi=(WifiManager)  context.getSystemService(Context.WIFI_SERVICE);

        List<ScanResult> listScan=managerWifi.getScanResults();

        if(listScan.size()>0){
            for(ScanResult scanresult:listScan){
                if(listwifi.contains(scanresult.SSID))
                    lsignal.add(new Signal(scanresult.level,scanresult.SSID,scanresult.BSSID));
            }
        }

        System.out.println(lsignal);

        return lsignal;
    }
}
