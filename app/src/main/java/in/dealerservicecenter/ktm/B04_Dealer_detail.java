package in.dealerservicecenter.ktm;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class B04_Dealer_detail extends A00_ActivityBaseClass {
String UrlDataKtm="";
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b04_dealer_detail_activity);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        mAdView = findViewById(R.id.adView); // Add

        nodata =findViewById(R.id.nodata);

        Intent i = getIntent();

        S_id = i.getStringExtra("sid");
        C_id = i.getStringExtra("cid");
        state_name =capitalize(i.getStringExtra("state_name"));
        cityname = capitalize(i.getStringExtra("cityname"));
        type = capitalize(i.getStringExtra("type"));
        HttpsTrustManager.allowAllSSL(); //SSl

        Dealer_recyclerview = (RecyclerView) findViewById(R.id.dealerdata);
        Dealer_recyclerview.setHasFixedSize(true);
        Dealer_recyclerview.setLayoutManager(new LinearLayoutManager(this));

        cname1=(TextView)findViewById(R.id.statecityname);
        cname1.setText(capitalize( state_name+ " of "+cityname+ "Delaers"));


        b04DealerDetail_lists = new ArrayList<>();

        if(type.equals("Dealer")){
            UrlDataKtm = B04_URLData_DD;
            getSupportActionBar().setTitle("Dealer Details");
        }else if(type.equals("Servicecenter")){
            UrlDataKtm = B04_URLData_SS;
            getSupportActionBar().setTitle("Service Center Details");
        }
        Log.d("type",type);
        LoadDealerDetailData();
    }
    // FOR SEARCHING THE VALUE
    private void filter(String text) {
        ArrayList<B04_DealerDetail_List> filteredList = new ArrayList<>();

        for (B04_DealerDetail_List sitem : b04DealerDetail_lists) {
            if (sitem.getBname().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(sitem);
            }
        }
        Dealer_adapter = new B04_DealerDetail_Adapter(filteredList, getApplicationContext());
        Dealer_recyclerview.setAdapter(Dealer_adapter);
    }

    private  void  LoadDealerDetailData(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait Wil Data Fetch From Server");
        progressDialog.show();

        StringRequest stringrequest  = new StringRequest(Request.Method.GET, UrlDataKtm + "/"+C_id+ "/" +S_id ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();


                        try {
                            if(!response.equals("[]")) {
                            JSONArray carray = new JSONArray(response);
                            for (int i = 0; i < carray.length(); i++) {

                                JSONObject c = carray.getJSONObject(i);
                                // Toast.makeText(A03_StateCity.this, c.getString("id"), Toast.LENGTH_SHORT).show();
                                B04_DealerDetail_List citem = new B04_DealerDetail_List(
                                        c.getString("name"),
                                        c.getString("slug"),
                                        c.getString("address"),
                                        c.getString("pincode"),
                                        c.getString("contact_no"),
                                        c.getString("email"),
                                        c.getString("title"),
                                        c.getString("contact_person")
                                );

                                b04DealerDetail_lists.add(citem);
                            }
                            Dealer_adapter = new B04_DealerDetail_Adapter(b04DealerDetail_lists, getApplicationContext());
                            Dealer_recyclerview.setAdapter(Dealer_adapter);

                            // Add Call---
                            Add_Call();

                        }else
                        {
//                            Title_Nodata= "There Is No Dealer Detail For "+state_name+ " Of "+cityname;
//                            NodataFound(context,Title_Nodata);
                            Toast.makeText(context, "No data sorry", Toast.LENGTH_SHORT).show();
                        }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(B04_Dealer_detail.this, "Volley Error - " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        stringrequest.setRetryPolicy(new DefaultRetryPolicy(MAXIMUM_TIMEOUT_IN_SECONDS * 1000, MAXIMUM_RETRY_STRING_REQUEST, 1.0f));
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringrequest);


    }

    public  void Add_Call(){
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("A33EB03807D43E634CB44901B918BB0B")
                .build();
        mAdView.loadAd(adRequest);


        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });
    }


}
