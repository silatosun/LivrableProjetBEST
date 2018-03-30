package fr.best.client.clientbest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.google.gson.Gson;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import fr.best.client.entity.Batiment;
import fr.best.client.singleton.MySingleton;

public class MainActivity extends AppCompatActivity
{
    public static String nomBatiment="";

    private ListView batimentListView ;

    private ArrayAdapter<String> listAdapter ;

    private List<Batiment> batimentList=new ArrayList<>();

    private String url=MySingleton.prefixUrl+"/BestServeur/webapi/batimentrest/findallbatiment";

    private int position=-1;
    private ImageView scroll;

    private View.OnClickListener addZone=new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {

            Intent request=new Intent(MainActivity.this,FingerPrint.class);
            if(position!=-1) {
                Batiment send = batimentList.get(position);
                request.putExtra("data",send.getId());
                startActivity(request);
            }else{
                CharSequence text = "Veuillez sélectionner un bâtiment !";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                toast.show();
            }

        }
    };

    private View.OnClickListener addNavg=new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            Intent request=new Intent(MainActivity.this,Navigation.class);
            if(position!=-1) {
                Batiment send = batimentList.get(position);
                request.putExtra("data",send.getId());
                startActivity(request);
            }else{
                CharSequence text = "Veuillez sélectionner un bâtiment !";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                toast.show();
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        batimentListView = (ListView) findViewById( R.id.listbatiment);
        listAdapter = new ArrayAdapter<String>(this, R.layout.batimentitem);
        batimentListView.setAdapter( listAdapter );

        JsonArrayRequest jsonArrayReq = getBuildings();
        MySingleton.getInstance(this).addToRequestQueue(jsonArrayReq);

        scroll=findViewById(R.id.scrooll_button);

        scroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(batimentListView.getVisibility()==View.GONE)
                    batimentListView.setVisibility(View.VISIBLE);
                else batimentListView.setVisibility(View.GONE);
            }
        });


        batimentListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    position=i;
                    nomBatiment= batimentList.get(position).getName();

                }
            }
        );

        Button addZbutton=(Button)findViewById(R.id.idzone);
        addZbutton.setOnClickListener(addZone);

        Button addNbutton=(Button)findViewById(R.id.idnav);
        addNbutton.setOnClickListener(addNavg);

    }

    private JsonArrayRequest getBuildings()
    {
        JsonArrayRequest jsonArrayReq = new JsonArrayRequest(Request.Method.GET,url,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int counter=0;
                        Gson gson = new Gson();
                        while(counter<response.length()){
                            try {
                                JSONObject dataObject = response.getJSONObject(counter);
                                Batiment batiment=gson.fromJson(dataObject.toString(),Batiment.class);
                                batimentList.add(batiment);
                                listAdapter.add(dataObject.getString("name"));
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
            }
        }
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;

            }
        };

        return jsonArrayReq;
    }


}
