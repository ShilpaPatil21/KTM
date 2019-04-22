package in.dealerservicecenter.ktm;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.app.Fragment;
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

import java.net.URLEncoder;
import java.util.List;

public class A00_FragmentBaseClass extends Fragment {
    View nodata;
    RecyclerView State_recyclerview;
    List<B01_C01_State_List> b01C01_state_lists;
    RecyclerView.Adapter state_adapter;
    int MAXIMUM_TIMEOUT_IN_SECONDS = 20, MAXIMUM_RETRY_STRING_REQUEST = 3;

    public   String B02_URLData = "https://www.dealerservicecenter.in/backend/api/get_state_list";
    public  String  MailData ="https://www.dealerservicecenter.in/api/send_error/?url=";
    View NoInternet;


    ProgressDialog progressDialog ;


    public void NodataFound(final Context context, final String Title_Nodata){
        nodata.setVisibility(View.VISIBLE);
        TextView back_btn = (TextView)this.getView().findViewById(R.id.back_btn);
        TextView t1 = (TextView)this.getView().findViewById(R.id.title_data);
        t1.setText(Title_Nodata);
        back_btn.setVisibility(View.GONE);

    }
    public void  Send_Mail_Exception(String msg) {
        if (CheckInternet.isInternetAvailable(getActivity())) {
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

                RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                requestQueue.add(stringrequest);

            }
        }
    }


}
