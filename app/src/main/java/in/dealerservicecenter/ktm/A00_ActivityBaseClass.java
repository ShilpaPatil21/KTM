package in.dealerservicecenter.ktm;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.google.android.gms.ads.AdView;

import java.net.URLEncoder;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class A00_ActivityBaseClass extends AppCompatActivity {

    int MAXIMUM_TIMEOUT_IN_SECONDS = 20, MAXIMUM_RETRY_STRING_REQUEST = 3;

    String Sid,state_name,type;
    RecyclerView City_recyclerview;
    RecyclerView.Adapter City_adapter;
    TextView cname1;
    public   String B03_URLData = "https://www.dealerservicecenter.in/backend/api/get_state_city_list/";
    public List<B02_C02_City_List> b03City_lists;

    String S_id,C_id,cityname;
    RecyclerView Dealer_recyclerview;
    RecyclerView.Adapter Dealer_adapter;

    String UrlDataKtm="";
    List<A01_KtmDetail_List> productList;
    String Title_Nodata;
    RecyclerView recyclerView;
    TextView nodata_msg;

    View nodata;
    ProgressDialog progressDialog;
    Context context = this;

    AdView mAdView1,mAdView; // add

    public   String B04_URLData_DD = "https://www.dealerservicecenter.in/backend/api/get_product_dea_serv/dd/1638/328/";
    public   String B04_URLData_SS = "https://www.dealerservicecenter.in/backend/api/get_product_dea_serv/ss/1638/328/";
    public  String  MailData ="https://www.dealerservicecenter.in/api/send_error/?url=";
    public List<B03_C03_DealerServiceCenterDetail_List> b04DealerDetail_lists;



    //------------------Convert First Letter Into Captial---------------------------
    public String capitalize(String capString){
        StringBuffer capBuffer = new StringBuffer();
        Matcher capMatcher = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(capString);
        while (capMatcher.find()){
            capMatcher.appendReplacement(capBuffer, capMatcher.group(1).toUpperCase() + capMatcher.group(2).toLowerCase());
        }

        return capMatcher.appendTail(capBuffer).toString();
    }
    //------------------------ BACK BUTTON ----------------------------
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void NodataFound(final Context context,final String Title_Nodata){
        nodata.setVisibility(View.VISIBLE);
        TextView back_btn = (TextView) findViewById(R.id.back_btn);
        TextView t1 = (TextView) findViewById(R.id.title_data);
        t1.setText(Title_Nodata);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckInternet.isInternetAvailable(context)) //if connection available
                {
                    onBackPressed();
                } else {
                    //no connection
                    Toast.makeText(context, "No Internet Please Turn On Internet", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void  Send_Mail_Exception(String msg) {
        if (CheckInternet.isInternetAvailable(this)) {
            String url = null;
            try {
                // encode() method
                System.out.println("URL after encoding :");
                url = new String(MailData + URLEncoder.encode(msg.toLowerCase(), "UTF-8"));
                Log.d("url exception", url);

            } catch (Exception e) {
                Log.d("url exception", e.getMessage());
            } finally {
                StringRequest stringrequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {


                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(A00_ActivityBaseClass.this, "Volley Error - " + error.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });
                stringrequest.setRetryPolicy(new DefaultRetryPolicy(MAXIMUM_TIMEOUT_IN_SECONDS * 1000, MAXIMUM_RETRY_STRING_REQUEST, 1.0f));

                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(stringrequest);

            }
        }
    }


}
