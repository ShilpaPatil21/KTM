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

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class B02_StateAdapter extends RecyclerView.Adapter<B02_StateAdapter.Viewholder>  {
    private InterstitialAd mInterstitialAd;

    private List<B02_State_List> stateLists;
    private Context context;
    public String Type;

    public B02_StateAdapter(List<B02_State_List> stateLists, Context context, String Type) {
        this.stateLists = stateLists;
        this.context = context;
        this.Type= Type;

    }



    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.b02_state_list,parent,false);
        return  new Viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int i) {
        final B02_State_List stateList = stateLists.get(i);
        MobileAds.initialize(context, "ca-app-pub-4662457729112553~5720713624");
        mInterstitialAd = new InterstitialAd(context);
        mInterstitialAd.setAdUnitId("ca-app-pub-4662457729112553/9847660890");
        mInterstitialAd.loadAd(new AdRequest.Builder()
                .addTestDevice("A33EB03807D43E634CB44901B918BB0B")
                .build());

        viewholder.TxtState.setText(capitalize(stateList.getStatename()));
        viewholder.SlinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    if(CheckInternet.isInternetAvailable(context)) //if connection available
                    {
                                Intent intent = new Intent(context, B03_CityName.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                                intent.putExtra("sid", stateList.getSid());
                                intent.putExtra("state_name", stateList.getStatename());
                                intent.putExtra("type", Type);
                                context.startActivity(intent);

                    }else{
                        //no connection
                        Toast.makeText(context, "No Internet Please Turn On Internet", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception ex){
                    Log.e("Log", String.valueOf(ex));
                }

            }
        });
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


}
