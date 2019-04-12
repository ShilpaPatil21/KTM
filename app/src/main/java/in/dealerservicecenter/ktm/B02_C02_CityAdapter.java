package in.dealerservicecenter.ktm;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.net.URLEncoder;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class B02_C02_CityAdapter extends RecyclerView.Adapter<B02_C02_CityAdapter.Viewholder>  {
    public  String  MailData ="https://www.dealerservicecenter.in/api/send_error/?url=";
    int MAXIMUM_TIMEOUT_IN_SECONDS = 20, MAXIMUM_RETRY_STRING_REQUEST = 3;
    private List<B02_C02_City_List> cityLists;
    private Context context;
    private InterstitialAd mInterstitialAd;
    public  String type,Sid,state_name;

    public B02_C02_CityAdapter(List<B02_C02_City_List> cityLists, Context context, String state_name, String state_id, String type){
        this.cityLists = cityLists;
        this.context = context;
        this.Sid = state_id;
        this.state_name = state_name;
        this.type = type;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.b02_c02_city_list,parent,false);
        return  new Viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int i) {
        final B02_C02_City_List cityList = cityLists.get(i);
        try {
            MobileAds.initialize(context, "ca-app-pub-4662457729112553~5720713624");
            mInterstitialAd = new InterstitialAd(context);
            mInterstitialAd.setAdUnitId("ca-app-pub-4662457729112553/9847660890");
            mInterstitialAd.loadAd(new AdRequest.Builder()
                    .addTestDevice("A33EB03807D43E634CB44901B918BB0B")
                    .build());
            viewholder.TxtCity.setText(capitalize(cityList.getCityname()));


            viewholder.ClinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (CheckInternet.isInternetAvailable(context)) //if connection available
                    {
                        try {
                            if (mInterstitialAd.isLoaded()) {
                                mInterstitialAd.show();
                            } else {


                                Intent intent = new Intent(context, B03_C03_DealerServicecenter_detail.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                                intent.putExtra("cid", cityList.getcid());
                                intent.putExtra("cityname", cityList.getCityname());
                                intent.putExtra("sid", Sid);
                                intent.putExtra("state_name", state_name);
                                intent.putExtra("type", type);
                                context.startActivity(intent);
                            }
                            try {
                                mInterstitialAd.setAdListener(new AdListener() {
                                    @Override
                                    public void onAdLoaded() {
                                        // Code to be executed when an ad finishes loading.
                                    }

                                    @Override
                                    public void onAdFailedToLoad(int errorCode) {
                                        // Code to be executed when an ad request fails.
                                        Toast.makeText(context, "error Ads" + errorCode, Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onAdOpened() {
                                        // Code to be executed when the ad is displayed.
                                    }

                                    @Override
                                    public void onAdLeftApplication() {
                                        // Code to be executed when the user has left the app.
                                    }

                                    @Override
                                    public void onAdClosed() {
                                        // Code to be executed when the interstitial ad is closed.

                                        Intent intent = new Intent(context, B03_C03_DealerServicecenter_detail.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                                        intent.putExtra("cid", cityList.getcid());
                                        intent.putExtra("cityname", cityList.getCityname());
                                        intent.putExtra("sid", Sid);
                                        intent.putExtra("state_name", state_name);
                                        intent.putExtra("type", type);
                                        context.startActivity(intent);
                                    }
                                });
                            }catch (Exception e){
                                StackTraceElement[] trace = e.getStackTrace();
                                System.out.println("Ktm App :- " + trace[0].getFileName()+" Line:-"+trace[0].getLineNumber()+" Error:- "+e.getMessage());

                            }

                        }catch (Exception e){
                            StackTraceElement[] trace = e.getStackTrace();
                            System.out.println("Ktm App :- " + trace[0].getFileName()+" Line:-"+trace[0].getLineNumber()+" Error:- "+e.getMessage());

                        }

                    } else {
                        //no connection
                        Toast.makeText(context, "No Internet Please Turn On Internet", Toast.LENGTH_SHORT).show();
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
    @Override
    public int getItemCount() {
        return cityLists.size();
    }

    public  class  Viewholder extends  RecyclerView.ViewHolder{

        public TextView TxtCity;
        public LinearLayout ClinearLayout;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            TxtCity = (TextView) itemView.findViewById(R.id.txtcity);
            ClinearLayout = (LinearLayout) itemView.findViewById(R.id.linearlycity);
        }
    }

    //Convert First Letter Into Captial
    private String capitalize(String capString){
        StringBuffer capBuffer = new StringBuffer();
        Matcher capMatcher = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(capString);
        while (capMatcher.find()){
            capMatcher.appendReplacement(capBuffer, capMatcher.group(1).toUpperCase() + capMatcher.group(2).toLowerCase());
        }

        return capMatcher.appendTail(capBuffer).toString();
    }
    public void  Send_Mail_Exception(String msg) {
        if (CheckInternet.isInternetAvailable(context)) {
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
                                Toast.makeText(context, "Volley Error - " + error.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });
                stringrequest.setRetryPolicy(new DefaultRetryPolicy(MAXIMUM_TIMEOUT_IN_SECONDS * 1000, MAXIMUM_RETRY_STRING_REQUEST, 1.0f));

                RequestQueue requestQueue = Volley.newRequestQueue(context);
                requestQueue.add(stringrequest);

            }
        }
    }
}
