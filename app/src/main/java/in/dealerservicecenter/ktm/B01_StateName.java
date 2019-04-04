package in.dealerservicecenter.ktm;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class B01_StateName extends A00_FragmentBaseClass {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.b01_statename_fragment, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Dealer: State Name");
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        NoInternet = (View)this.getView().findViewById(R.id.nointernet);
        nodata = (View)this.getView().findViewById(R.id.nodata);
        if (CheckInternet.isInternetAvailable(getContext())) {
            HttpsTrustManager.allowAllSSL(); //SSl

            State_recyclerview = (RecyclerView)this.getView().findViewById(R.id.statename);
            State_recyclerview.setHasFixedSize(true);
            State_recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));


            b01C01_state_lists = new ArrayList<>();
           // NoInternet.findViewById(View.GONE);
            //---------------------Data From Server Call---------------------------------//
            LoadStatedata();

        } else {
            Snackbar.make(getView(), "No InterNet Please Turn On Mobile Data Or Hotspot", Snackbar.LENGTH_LONG).show();
            NoInternet.setVisibility(View.VISIBLE);

        }
    }

    //---------------------Data From Server ---------------------------------//
    private  void  LoadStatedata(){
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please Wait Wil Data Fetch From Server");
        progressDialog.show();


        StringRequest stringrequest  = new StringRequest(Request.Method.GET, B02_URLData,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            if(!response.equals("[]")) {
                                JSONArray Sarray = new JSONArray(response);
                                for(int i=0;i<Sarray.length();i++)
                                {
                                    JSONObject J = Sarray.getJSONObject(i);
                                    B01_C01_State_List sitem = new B01_C01_State_List(
                                            J.getString("state"),
                                            J.getString("id")
                                    );
                                    b01C01_state_lists.add(sitem);
                                }
                                state_adapter = new B01_C01_StateAdapter(b01C01_state_lists,getContext(),"Dealer");
                                State_recyclerview.setAdapter(state_adapter);
                                State_recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
                            }else
                            {
                                NodataFound(getContext(),"Currently We Don't Have City Name !..");
                               // Toast.makeText(getContext(), "nodata", Toast.LENGTH_SHORT).show();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
        stringrequest.setRetryPolicy(new DefaultRetryPolicy(MAXIMUM_TIMEOUT_IN_SECONDS * 1000, MAXIMUM_RETRY_STRING_REQUEST, 1.0f));
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringrequest);


    }
}
