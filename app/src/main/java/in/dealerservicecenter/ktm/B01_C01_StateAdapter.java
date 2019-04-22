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
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.net.URLEncoder;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class B01_C01_StateAdapter extends RecyclerView.Adapter<B01_C01_StateAdapter.Viewholder>  {

    public  String  MailData ="https://www.dealerservicecenter.in/api/send_error/?url=";
    int MAXIMUM_TIMEOUT_IN_SECONDS = 20, MAXIMUM_RETRY_STRING_REQUEST = 3;
    private List<B01_C01_State_List> stateLists;
    private Context context;
    public String Type;

    public B01_C01_StateAdapter(List<B01_C01_State_List> stateLists, Context context, String Type) {
        this.stateLists = stateLists;
        this.context = context;
        this.Type= Type;

    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.b01_c01_state_list,parent,false);
        return  new Viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int i) {
        final B01_C01_State_List stateList = stateLists.get(i);
        try {
            viewholder.TxtState.setText(capitalize(stateList.getStatename()));
            viewholder.SlinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {
                        if (CheckInternet.isInternetAvailable(context)) //if connection available
                        {
                            Intent intent = new Intent(context, B02_CityName.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                            intent.putExtra("sid", stateList.getSid());
                            intent.putExtra("state_name", stateList.getStatename());
                            intent.putExtra("type", Type);
                            context.startActivity(intent);

                        } else {
                            //no connection
                            Toast.makeText(context, "No Internet Please Turn On Internet", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception ex) {
                        Log.e("Log", String.valueOf(ex));
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
        return stateLists.size();
    }

    public  class  Viewholder extends  RecyclerView.ViewHolder{

        public TextView TxtState;
        public LinearLayout SlinearLayout;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            TxtState = (TextView) itemView.findViewById(R.id.txtstate);
            SlinearLayout = (LinearLayout) itemView.findViewById(R.id.linearly1);



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
                                StackTraceElement[] trace = error.getStackTrace();
                                System.out.println("Ktm App :- " + trace[0].getFileName()+" Line:-"+trace[0].getLineNumber()+" Error:- "+error.getMessage());
                            }
                        });
                stringrequest.setRetryPolicy(new DefaultRetryPolicy(MAXIMUM_TIMEOUT_IN_SECONDS * 1000, MAXIMUM_RETRY_STRING_REQUEST, 1.0f));
                RequestQueue requestQueue = Volley.newRequestQueue(context);
                requestQueue.add(stringrequest);

            }
        }
    }

}
