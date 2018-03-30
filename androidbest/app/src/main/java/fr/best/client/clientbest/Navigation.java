package fr.best.client.clientbest;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import fr.best.client.clientbest.VuePlan;
import fr.best.client.entity.Batiment;
import fr.best.client.entity.Localiser;
import fr.best.client.entity.Point;
import fr.best.client.entity.Signal;
import fr.best.client.entity.Sortie;
import fr.best.client.entity.WifiScanner;
import fr.best.client.entity.Zone;
import fr.best.client.singleton.MySingleton;

public class Navigation extends AppCompatActivity {

    private final int marge=300;

    private String urlb= MySingleton.prefixUrl+"/BestServeur/webapi/batimentrest/find/";

    private List<Signal> lsignal=new ArrayList<>();
    private List<Zone> lzone=new ArrayList<>();
    private List<Sortie> lsorties=new ArrayList<>();
    private Batiment batiment;
    static final int REQUEST_ACCESS_FINE_LOCATION = 1;
    private Button bValideZone;
    private VuePlan vuePlan;
    private Point position;
    private Point sortie;
    public ImageView imagePosition;

    private View.OnClickListener localiserB=new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            lsignal = WifiScanner.scanWifi(Navigation.this);
            try {
                Localiser l = new Localiser();
                TreeMap<Double, Point> test = l.getPositin(lsignal, lzone);

                Map.Entry<Double, Point> entry = test.entrySet().iterator().next();
                CharSequence text = "Current Location is " + entry.getValue().getX() + " , " + entry.getValue().getY();
                position=new Point(entry.getValue().getX(),entry.getValue().getY());
                imagePosition.setVisibility(View.VISIBLE);
                imagePosition.setX(entry.getValue().getX());
                imagePosition.setY(entry.getValue().getY()+marge);

                // Sortie
                System.out.println("1");

                TreeMap<Double, Point> treeMapSorties = l.getDistanceSortie(position, lsorties);

                System.out.println("2");

                Map.Entry<Double, Point> entrySorties = treeMapSorties.entrySet().iterator().next();

                System.out.println("5");

                sortie = new Point(entrySorties.getValue().getX(),entrySorties.getValue().getY());
                System.out.println("4");

                if(MainActivity.nomBatiment.equals("Algeco")){
                    vuePlan.naviguer(position,sortie);

                    vuePlan.valideZone=true;
                    vuePlan.invalidate();
                }

                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                toast.show();
                try {
                    lsignal.clear();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }catch (Exception e){
                System.out.println(e);
            }



        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        vuePlan = findViewById(R.id.maVue);
        imagePosition = findViewById(R.id.imagePosition);

        /*********** Intent message ***********/
        Intent get=getIntent();
        Long recev=get.getLongExtra("data",1L);
        if(recev!=null){
            urlb+=recev.toString();
        }

        JsonObjectRequest resquest= getBatiment();
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(resquest);

        Button lbutton=(Button)findViewById(R.id.localiser);
        lbutton.setOnClickListener(localiserB);




    }



    private JsonObjectRequest getBatiment()
    {
        JsonObjectRequest jsonObjectRequest =  new JsonObjectRequest(Request.Method.GET,urlb,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                                 Gson gson = new Gson();
                                 batiment=gson.fromJson(response.toString(),Batiment.class);
                        lzone=batiment.getListZone();
                        lsorties=batiment.getListSortie();
                        System.out.println("List Sortie"+lsorties);
                                /* System.out.println("List Bâtiment"+batiment);
                                 System.out.println("List Zone"+lzone);
                                 System.out.println("List sortie"+batiment.getListSortie());*/

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        CharSequence text = "ERROR 500 !";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                        toast.show();


                    }
                }
        );



        return jsonObjectRequest;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        switch (requestCode) {
            case REQUEST_ACCESS_FINE_LOCATION : {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this,
                            "la permission a été autorisée :)",
                            Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(this,
                            "la permission a été refusée, ...:(",
                            Toast.LENGTH_LONG).show();
                }
                return;
            }

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }
}
