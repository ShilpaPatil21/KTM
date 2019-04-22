package in.dealerservicecenter.ktm;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
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
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class A01_KTM_Model extends A00_FragmentBaseClass {
    List<A01_KtmDetail_List> productList;
    private AdView mAdView;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.a01_ktm_model, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Ktm Model");
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        try {
                //getting the recyclerview from xml
                recyclerView = (RecyclerView) this.getView().findViewById(R.id.ktmmodel);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

                mAdView = getView().findViewById(R.id.adView);
                //-------------- BANNER ADD ------------------------//
                Add_Banner();

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

                A01_KtmDetail_Adapter adapter = new A01_KtmDetail_Adapter(getActivity(), productList);
                //setting adapter to recyclerview
                recyclerView.setAdapter(adapter);
        }catch (Exception error){
            StackTraceElement[] trace = error.getStackTrace();
            System.out.println("Ktm App :- " + trace[0].getFileName()+" Line:-"+trace[0].getLineNumber()+" Error:- "+error.getMessage());
            //Sending Mail
            Send_Mail_Exception("Ktm App :- " + trace[0].getFileName()+" Line:-"+trace[0].getLineNumber()+" Error:- "+error.getMessage());


        }
    }

    public void Add_Banner(){
        try {
            mAdView.setVisibility(View.GONE);
                AdView adView = new AdView(getActivity());
                adView.setAdSize(AdSize.MEDIUM_RECTANGLE);

                AdRequest adRequest = new AdRequest.Builder()
                        .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
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

        }catch (Exception error){
            StackTraceElement[] trace = error.getStackTrace();
            System.out.println("Ktm App :- " + trace[0].getFileName()+" Line:-"+trace[0].getLineNumber()+" Error:- "+error.getMessage());
        }
    }


}
