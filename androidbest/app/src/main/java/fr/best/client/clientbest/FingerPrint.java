package fr.best.client.clientbest;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.icu.lang.UProperty;
import android.opengl.EGLExt;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import 	android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.best.client.entity.Batiment;
import fr.best.client.entity.Point;
import fr.best.client.entity.WifiScanner;
import fr.best.client.entity.Zone;
import fr.best.client.entity.Sortie;
import fr.best.client.entity.Signal;
import fr.best.client.singleton.MySingleton;



public class FingerPrint extends AppCompatActivity {

    static final int REQUEST_ACCESS_FINE_LOCATION = 1;

    private VuePlan vuePlan;

    private Spinner dropdownZone;

    private ArrayAdapter<String> listAdapterzone ;

    private List<Zone> zoneList=new ArrayList<>();

    private Spinner dropdownSortie;

    private ArrayAdapter<String> listAdapterSortie ;

    private List<Sortie> sortieList=new ArrayList<>();

    private String urlz=MySingleton.prefixUrl+"/BestServeur/webapi/zonerest/findzonebybatiment/";

    private String urls=MySingleton.prefixUrl+"/BestServeur/webapi/sortierest/findsortiebybatiment/";

    private String urlzu=MySingleton.prefixUrl+"/BestServeur/webapi/zonerest/add/";

    private String urlsu=MySingleton.prefixUrl+"/BestServeur/webapi/sortierest/add/";

    private Point coordonnee;

    private Point coordonnees;

    private int positionZone=-1;

    private int positionSortie=-1;

    private List<Signal> lsignal=new ArrayList<>();

    private View.OnClickListener updateZone;

    {
        updateZone = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (positionZone != -1) {
                    System.out.println("Zone position " + positionZone);

                    coordonnee = VuePlan.point;
                    if (coordonnee != null) {
                        lsignal = WifiScanner.scanWifi(FingerPrint.this);
                        coordonnee.setListSignal(lsignal);
                            try {
                                if (!lsignal.isEmpty()) {
                                    System.out.println("POINT UPDATE" + coordonnee);
                                    Zone update = zoneList.get(positionZone);
                                    update.getListPoint().add(coordonnee);
                                    Gson gson = new Gson();
                                    String jsonString = gson.toJson(update);
                                    JSONObject zoneJson;

                                    try {
                                        zoneJson = new JSONObject(jsonString);
                                        JsonObjectRequest resquest = updateZone(zoneJson);
                                        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(resquest);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    } finally {
                                        VuePlan.point = null;
                                        coordonnee = null;
                                        try {
                                            lsignal.clear();
                                        } catch (Exception e) {
                                            System.out.println(e);
                                        }
                                    }
                                }
                            }catch (Exception e){
                                System.out.println(e);
                            }

                    } else {
                        CharSequence text = "Veuillez cliquer sur le map !";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                        toast.show();
                    }
                } else {
                    CharSequence text = "Veuillez sélectionner une zone !";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                    toast.show();
                }

            }
        };
    }

    private View.OnClickListener updateSortie=new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {

            if(positionSortie!=-1) {
                System.out.println("positionSortie "+positionSortie);



                coordonnees=VuePlan.point;
                if(coordonnees!=null) {

                    //System.out.println("SORTIE: "+coordonnees);

                    Sortie sortie = sortieList.get(positionSortie);
                    sortie.setCoordonne(coordonnees);
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(sortie);
                    JSONObject sortieJson;

                    try {
                        sortieJson = new JSONObject(jsonString);
                        JsonObjectRequest resquest = updateSortie(sortieJson);
                        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(resquest);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }finally {
                        VuePlan.point=null;
                        coordonnees=null;
                    }
                }else{
                    CharSequence text = "Veuillez cliquer sur le map !";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                    toast.show();
                 }
            }else{
                CharSequence text = "Veuillez sélectionner une sortie !";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                toast.show();
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger_print);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        vuePlan = findViewById(R.id.maVue);
        /************Zone Spinner*************/
        dropdownZone=(Spinner) findViewById(R.id.spinzoneid);
        listAdapterzone =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
        listAdapterzone.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdownZone.setAdapter(listAdapterzone );

        /************Sortie Spinner*************/
        dropdownSortie=(Spinner) findViewById(R.id.spinsortieid);
        listAdapterSortie =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
        listAdapterSortie.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdownSortie.setAdapter(listAdapterSortie );

        /*********** Intent message ***********/
        Intent get=getIntent();
        Long recev=get.getLongExtra("data",1L);
        if(recev!=null){
            urlz+=recev.toString();
            urls+=recev.toString();
        }

        /********* REST ZONE *******************/
        JsonArrayRequest jsonArrayReqzone = getZones();
        MySingleton.getInstance(this).addToRequestQueue(jsonArrayReqzone);

        dropdownZone.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                positionZone=position;
                System.out.println("Zone position item:  "+positionZone);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /************* REST SORTIE ****************/

        JsonArrayRequest jsonArrayReqsortie = getSorties();
        MySingleton.getInstance(this).addToRequestQueue(jsonArrayReqsortie);

        dropdownSortie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                positionSortie=position;
                System.out.println("Sortie position item:  "+positionSortie);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /**********************Button ********************/
        Button updateZbutton=(Button)findViewById(R.id.updatezone);
        updateZbutton.setOnClickListener(updateZone);

        Button updateSbutton=(Button)findViewById(R.id.updatesortie);
        updateSbutton.setOnClickListener(updateSortie);

    }

    private JsonArrayRequest getZones()
    {
        JsonArrayRequest jsonArrayReq = new JsonArrayRequest(Request.Method.GET,urlz,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int counter=0;
                        Gson gson = new Gson();
                        while(counter<response.length()){
                            try {
                                JSONObject dataObject = response.getJSONObject(counter);
                                Zone zone=gson.fromJson(dataObject.toString(),Zone.class);
                                zoneList.add(zone);
                                listAdapterzone.add(dataObject.getString("name"));

                                counter+=1;
                            }catch (JSONException e){
                                e.printStackTrace();
                            }

                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                CharSequence text = "Zone error 505 !";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                toast.show();
            }
        }
        );


        return jsonArrayReq;
    }

    private JsonArrayRequest getSorties()
    {
        JsonArrayRequest jsonArrayReq = new JsonArrayRequest(Request.Method.GET,urls,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int counter=0;
                        Gson gson = new Gson();
                        while(counter<response.length()){
                            try {

                                JSONObject dataObject = response.getJSONObject(counter);
                                Sortie sortie=gson.fromJson(dataObject.toString(),Sortie.class);
                                sortieList.add(sortie);
                                listAdapterSortie.add(dataObject.getString("name"));
                                counter+=1;
                            }catch (JSONException e){
                                e.printStackTrace();
                            }
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                CharSequence text = "Sortie error 505 !";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                toast.show();
            }
        }
        );


        return jsonArrayReq;
    }

    private JsonObjectRequest updateZone(JSONObject req)
    {
        JsonObjectRequest jsonObjectRequest =  new JsonObjectRequest(Request.Method.POST, urlzu, req,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response!=null) {
                            CharSequence text = "une nouvelle empreinte vient d'être ajoutée !";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                            toast.show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {



                    }
                }
        );

        return jsonObjectRequest;
    }

    private JsonObjectRequest updateSortie(JSONObject req)
    {
        JsonObjectRequest jsonObjectRequest =  new JsonObjectRequest(Request.Method.POST, urlsu, req,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response!=null) {
                            CharSequence text = "une nouvelle empreinte vient d'être ajoutée !";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                            toast.show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
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
