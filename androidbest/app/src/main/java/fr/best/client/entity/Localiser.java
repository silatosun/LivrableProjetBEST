package fr.best.client.entity;

/**
 * Created by barou on 17/03/18.
 */
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;

import java.lang.Math.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Localiser
{
    private  double distancePoint(Point p1,Point p2)
    {
        double distance=0.0;

        double x=p2.getX()-p1.getX();
        double y=p2.getY()-p1.getY();

        x= Math.pow(x,2);
        y= Math.pow(y,2);
        distance=Math.sqrt(x+y);

        return distance;
    }

    private  double distanceRssi(int currentrssi,int storerssi)
    {
        double distance=0.0;
        distance=Math.abs(currentrssi-storerssi);
        distance=Math.sqrt(distance);

        return distance;
    }

    public  TreeMap<Double,Point> getPositin(List<Signal> currentsignal,List<Zone> fingerP)
    {
        TreeMap<Double,Point> listPosition= new TreeMap<Double, Point>();

        for (Zone z:fingerP) {
            for (Point p:z.getListPoint()) {
                for(Signal s1:p.getListSignal()){
                    for(Signal s2:currentsignal){
                        if(s1.getSsid().equals(s2.getSsid())){
                            listPosition.put(Double.valueOf(distanceRssi(s2.getRssi(),s1.getRssi())),p);
                        }
                    }
                }

            }
        }
        System.out.println("SEARCH");
        return listPosition;
    }

    public  TreeMap<Double,Point> getDistanceSortie(Point point,List<Sortie> listeS)
    {

        TreeMap<Double,Point> listDistanceSortie= new TreeMap<>();

        for (Sortie s : listeS) {
            double d = distancePoint(s.getCoordonne(),point);
            listDistanceSortie.put(Double.valueOf(d),s.getCoordonne());
            System.out.println("11 = "+listDistanceSortie);
        }
        System.out.println("OUT="+listDistanceSortie);
        return listDistanceSortie;

    }



}
