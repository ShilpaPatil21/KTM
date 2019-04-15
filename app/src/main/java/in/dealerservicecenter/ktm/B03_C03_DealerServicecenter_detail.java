package in.dealerservicecenter.ktm;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

public class B03_C03_DealerServicecenter_detail extends A00_ActivityBaseClass {
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b03_c03_dealer_detail_activity);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        try {
            mAdView1 = findViewById(R.id.adView1); // Add

            nodata = findViewById(R.id.nodata);

            Intent i = getIntent();

            S_id = i.getStringExtra("sid");
            C_id = i.getStringExtra("cid");
            state_name = capitalize(i.getStringExtra("state_name"));
            cityname = capitalize(i.getStringExtra("cityname"));
            type = capitalize(i.getStringExtra("type"));
            HttpsTrustManager.allowAllSSL(); //SSl

            Dealer_recyclerview = (RecyclerView) findViewById(R.id.dealerdata);
            Dealer_recyclerview.setHasFixedSize(true);
            Dealer_recyclerview.setLayoutManager(new LinearLayoutManager(this));

            cname1 = (TextView) findViewById(R.id.statecityname);
            cname1.setText(capitalize(state_name + " of " + cityname + " " + type));
            progressDialog = new ProgressDialog(this);

            b04DealerDetail_lists = new ArrayList<>();

            if (type.equals("Dealer")) {
                UrlDataKtm = B04_URLData_DD;
                getSupportActionBar().setTitle("Dealer Details");
                Title_Nodata = "Currently We Don't Have Any Dealer Data for " + state_name + " Of " + cityname;

            } else if (type.equals("Servicecenter")) {
                UrlDataKtm = B04_URLData_SS;
                getSupportActionBar().setTitle("Service Center Details");
                Title_Nodata = "Currently We Don't Have Any Service-Center Data for " + state_name + " Of " + cityname;

            }
            Log.d("type", type);
            handler = new Handler();
            handler.post(new Runnable() {
                public void run() {
                    try {
                        LoadDealerDetailData();
                    } catch (Exception e) {
                        System.err.println("Error in catch is " + e);
                    }
            }
           });
        }catch (Exception e){
            StackTraceElement[] trace = e.getStackTrace();
            System.out.println("Ktm App :- " + trace[0].getFileName()+" Line:-"+trace[0].getLineNumber()+" Error:- "+e.getMessage());
            //Sending Mail
            Send_Mail_Exception("Ktm App :- " + trace[0].getFileName()+" Line:-"+trace[0].getLineNumber()+" Error:- "+e.getMessage());

        }
    }
    // FOR SEARCHING THE VALUE
    private void filter(String text) {
        ArrayList<B03_C03_DealerServiceCenterDetail_List> filteredList = new ArrayList<>();

        for (B03_C03_DealerServiceCenterDetail_List sitem : b04DealerDetail_lists) {
            if (sitem.getBname().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(sitem);
            }
        }
        Dealer_adapter = new B03_C03_DealerServiceCenterDetail_Adapter(filteredList, getApplicationContext());
        Dealer_recyclerview.setAdapter(Dealer_adapter);
    }

    private  void  LoadDealerDetailData(){
    try {
        if (!B03_C03_DealerServicecenter_detail.this.isFinishing()) {
            progressDialog.setMessage("Please Wait Wil Data Fetch From Server");
            progressDialog.show();

            StringRequest stringrequest = new StringRequest(Request.Method.GET, UrlDataKtm + "/" + C_id + "/" + S_id,
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
                                            // Toast.makeText(A03_StateCity.this, c.getString("id"), Toast.LENGTH_SHORT).show();
                                            B03_C03_DealerServiceCenterDetail_List citem = new B03_C03_DealerServiceCenterDetail_List(
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
                                        Dealer_adapter = new B03_C03_DealerServiceCenterDetail_Adapter(b04DealerDetail_lists, getApplicationContext());
                                        Dealer_recyclerview.setAdapter(Dealer_adapter);

                                        // Add Call---
                                        Add_Call(mAdView1);

                                    } else {
                                        cname1.setVisibility(View.GONE);
                                        //NodataFound(context,Title_Nodata);

                                        nodata.setVisibility(View.VISIBLE);
                                        nodata_msg = (TextView) findViewById(R.id.nodata_msg);
                                        nodata_msg.setVisibility(View.VISIBLE);
                                        nodata_msg.setText(Title_Nodata);
                                        //getting the recyclerview from xml
                                        recyclerView = (RecyclerView) findViewById(R.id.ktmmodel);
                                        recyclerView.setHasFixedSize(true);
                                        recyclerView.setLayoutManager(new LinearLayoutManager(context));

                                        mAdView = findViewById(R.id.adView);

                                        mAdView1.setVisibility(View.GONE);

                                        //initializing the productlist
                                        productList = new ArrayList<>();


                                        //adding some items to our list
                                        productList.add(
                                                new A01_KtmDetail_List(
                                                        1,
                                                        "KTM 125 Duke",
                                                        "₹ 1,17,331",
                                                        "4.4",
                                                        "13.3 inch, Silver, 1.35 kg",
                                                        R.drawable.ktm_duke_125));
                                        productList.add(
                                                new A01_KtmDetail_List(
                                                        2,
                                                        "KTM 200 Duke",
                                                        "₹ 1,50,925",
                                                        "4.7",
                                                        "199.5cc , 35 Kmpl,24.6bhp,148 kg",
                                                        R.drawable.ktm_duke_200));
                                        productList.add(
                                                new A01_KtmDetail_List(
                                                        3,
                                                        "KTM RC200",
                                                        "₹ 1,78,496",
                                                        "4.7",
                                                        "199.5 cc, 25.1 bhp, 137 kg",
                                                        R.drawable.ktmrc_200));

                                        productList.add(
                                                new A01_KtmDetail_List(
                                                        4,
                                                        "KTM 390 Duke",
                                                        "₹ 2,43,562",
                                                        "4.8",
                                                        "373.2 cc, 23 kmpl, 42.9 bhp, 163 kg",
                                                        R.drawable.ktm_duke390));

                                        productList.add(
                                                new A01_KtmDetail_List(
                                                        5,
                                                        "KTM 250 Duke",
                                                        "₹ 1,80,058",
                                                        "4.7",
                                                        "248.76 cc, 29.6 bhp, 161 kg",
                                                        R.drawable.ktm_250_duke));

                                        productList.add(
                                                new A01_KtmDetail_List(
                                                        6,
                                                        "KTM RC390",
                                                        "₹ 2,40,234",
                                                        "4.8",
                                                        "373.2 cc, 42.3 bhp, 147 kg",
                                                        R.drawable.ktm_rc390));


                                        //creating recyclerview adapter

                                        A01_KtmDetail_Adapter adapter = new A01_KtmDetail_Adapter(context, productList);
                                        //setting adapter to recyclerview
                                        recyclerView.setAdapter(adapter);

                                        Add_Call(mAdView);
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
                        public void onErrorResponse(VolleyError e) {
                            StackTraceElement[] trace = e.getStackTrace();
                            System.out.println("Ktm App :- " + trace[0].getFileName() + " Line:-" + trace[0].getLineNumber() + " Error:- " + e.getMessage());
                            //Sending Mail
                            Send_Mail_Exception("Ktm App :- " + trace[0].getFileName() + " Line:-" + trace[0].getLineNumber() + " Error:- " + e.getMessage());

                        }
                    });
            stringrequest.setRetryPolicy(new DefaultRetryPolicy(MAXIMUM_TIMEOUT_IN_SECONDS * 1000, MAXIMUM_RETRY_STRING_REQUEST, 1.0f));
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringrequest);
        }
    }catch (Exception e){
        StackTraceElement[] trace = e.getStackTrace();
        System.out.println("Ktm App :- " + trace[0].getFileName()+" Line:-"+trace[0].getLineNumber()+" Error:- "+e.getMessage());
        //Sending Mail
        Send_Mail_Exception("Ktm App :- " + trace[0].getFileName()+" Line:-"+trace[0].getLineNumber()+" Error:- "+e.getMessage());

    }

    }

    public  void Add_Call(AdView mAdView1){
        try{
                AdRequest adRequest = new AdRequest.Builder()
                        .addTestDevice("A33EB03807D43E634CB44901B918BB0B")
                        .build();
                mAdView1.loadAd(adRequest);


                mAdView1.setAdListener(new AdListener() {
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
        }catch (Exception e){
            StackTraceElement[] trace = e.getStackTrace();
            System.out.println("Ktm App :- " + trace[0].getFileName()+" Line:-"+trace[0].getLineNumber()+" Error:- "+e.getMessage());

        }
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
