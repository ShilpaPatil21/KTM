package in.dealerservicecenter.ktm;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class B03_C03_DealerServiceCenterDetail_Adapter extends RecyclerView.Adapter<B03_C03_DealerServiceCenterDetail_Adapter.Viewholder>  {

    public  String  MailData ="https://www.dealerservicecenter.in/api/send_error/?url=";
    int MAXIMUM_TIMEOUT_IN_SECONDS = 20, MAXIMUM_RETRY_STRING_REQUEST = 3;
    private List<B03_C03_DealerServiceCenterDetail_List> b04DealerDetail_lists;
    private Context context;


    public B03_C03_DealerServiceCenterDetail_Adapter(List<B03_C03_DealerServiceCenterDetail_List> b04DealerDetail_list, Context context) {
        this.b04DealerDetail_lists = b04DealerDetail_list;
        this.context = context;
    }
    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.b03_c03_dealer_detail_list,parent,false);
        return  new Viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int i) {
        try {
            final B03_C03_DealerServiceCenterDetail_List b04DealerDetail_list = b04DealerDetail_lists.get(i);
            viewholder.Txtname.setText(capitalize(b04DealerDetail_list.getBname()));
            viewholder.Txtadd.setText(capitalize(b04DealerDetail_list.getBadd()) + "   " + b04DealerDetail_list.getPincode());
            viewholder.Txtcontact.setText(capitalize(b04DealerDetail_list.getBcontact_person() + " -" + b04DealerDetail_list.getBcontact()));
            viewholder.Txtslug.setText(capitalize(b04DealerDetail_list.getBtitle()));
            viewholder.Txtemail.setText(capitalize(b04DealerDetail_list.getBemail()));


            Linkify.addLinks(viewholder.Txtcontact, Linkify.ALL);
            Linkify.addLinks(viewholder.Txtemail, Linkify.ALL);
        }catch (Exception e){
            StackTraceElement[] trace = e.getStackTrace();
            System.out.println("Ktm App :- " + trace[0].getFileName()+" Line:-"+trace[0].getLineNumber()+" Error:- "+e.getMessage());
            //Sending Mail
            Send_Mail_Exception("Ktm App :- " + trace[0].getFileName()+" Line:-"+trace[0].getLineNumber()+" Error:- "+e.getMessage());

        }

    }
    @Override
    public int getItemCount() {
        return b04DealerDetail_lists.size();
    }

    public  class  Viewholder extends  RecyclerView.ViewHolder{

        public TextView Txtname,Txtadd,Txtcontact,Txtslug,Txtpin,Txtemail;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            Txtname = (TextView) itemView.findViewById(R.id.txtname);
            Txtadd = (TextView) itemView.findViewById(R.id.txtadress);
            Txtcontact = (TextView) itemView.findViewById(R.id.txtcontect);
            Txtslug = (TextView) itemView.findViewById(R.id.txtslug);
            Txtemail = (TextView) itemView.findViewById(R.id.txtemail);
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
                            public void onErrorResponse(VolleyError e) {
                                StackTraceElement[] trace = e.getStackTrace();
                                System.out.println("Ktm App :- " + trace[0].getFileName()+" Line:-"+trace[0].getLineNumber()+" Error:- "+e.getMessage());
                            }
                        });
                stringrequest.setRetryPolicy(new DefaultRetryPolicy(MAXIMUM_TIMEOUT_IN_SECONDS * 1000, MAXIMUM_RETRY_STRING_REQUEST, 1.0f));
                RequestQueue requestQueue = Volley.newRequestQueue(context);
                requestQueue.add(stringrequest);
            }
        }
    }
}
