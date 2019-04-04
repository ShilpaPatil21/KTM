package in.dealerservicecenter.ktm;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class B02_CityName extends A00_ActivityBaseClass {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b02_c02_cityname_activity);

        if(CheckInternet.isInternetAvailable(this)) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
                progressDialog = new ProgressDialog(this);

                nodata = findViewById(R.id.nodata);
                Intent i = getIntent();

                Sid = i.getStringExtra("sid");
                state_name = i.getStringExtra("state_name");
                type = i.getStringExtra("type");

                HttpsTrustManager.allowAllSSL(); //SSl

                City_recyclerview = (RecyclerView) findViewById(R.id.cityname);
                City_recyclerview.setHasFixedSize(true);
                City_recyclerview.setLayoutManager(new LinearLayoutManager(this));


                getSupportActionBar().setTitle(capitalize(state_name+ "  "+"City"));

                b03City_lists = new ArrayList<>();

               LoadCityData();
        }else{
            Toast.makeText(context,"No internet",Toast.LENGTH_LONG).show();
        }

    }


    private  void  LoadCityData(){

        progressDialog.setMessage("Please Wait Wil Data Fetch From Server");
        progressDialog.show();
        StringRequest stringrequest  = new StringRequest(Request.Method.GET, B03_URLData + Sid,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            if ((progressDialog != null) && progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                        } catch (final IllegalArgumentException e) {
                            // Handle or log or ignore
                        } catch (final Exception e) {
                            // Handle or log or ignore
                        } finally {

                            try {
                                if (!response.equals("[]")) {
                                    JSONArray carray = new JSONArray(response);
                                    for (int i = 0; i < carray.length(); i++) {

                                        JSONObject c = carray.getJSONObject(i);

                                        B02_C02_City_List citem = new B02_C02_City_List(
                                                c.getString("city"),
                                                c.getString("id")
                                        );

                                        b03City_lists.add(citem);
                                    }
                                    City_adapter = new B02_C02_CityAdapter(b03City_lists, getApplicationContext(), state_name, Sid, type);
                                    City_recyclerview.setAdapter(City_adapter);
                                } else {
                                    NodataFound(context, "Currently We Don't Have City Name !..");

                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            progressDialog = null;
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(B02_CityName.this, error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
        stringrequest.setRetryPolicy(new DefaultRetryPolicy(MAXIMUM_TIMEOUT_IN_SECONDS * 1000, MAXIMUM_RETRY_STRING_REQUEST, 1.0f));
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringrequest);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_item_one) {
            try {

                Intent intent = new Intent(getApplicationContext(), A00_MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                startActivity(intent);
                return true;
            }catch (Exception ex){
                Log.e("Intent",ex.getMessage());
            }
        }
        return super.onOptionsItemSelected(item);
    }


}
