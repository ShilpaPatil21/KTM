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

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class B03_CityAdapter extends RecyclerView.Adapter<B03_CityAdapter.Viewholder>  {

    private List<B03_City_List> cityLists;
    private Context context;
    public  String type,Sid,state_name;

    public B03_CityAdapter(List<B03_City_List> cityLists, Context context, String state_name, String state_id,String type){
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
                .inflate(R.layout.b03_city_list,parent,false);
        return  new Viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int i) {
        final B03_City_List cityList = cityLists.get(i);
        viewholder.TxtCity.setText(capitalize(cityList.getCityname()));


        viewholder.ClinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("State_name,Sid,type",state_name+"--"+Sid+"--"+type);
                Log.d("City Name,id",cityList.getcid()+"--"+cityList.getCityname());

                    Intent intent = new Intent(context, B04_Dealer_detail.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    intent.putExtra("cid", cityList.getcid());
                    intent.putExtra("cityname", cityList.getCityname());
                    intent.putExtra("sid", Sid);
                    intent.putExtra("state_name", state_name);
                    intent.putExtra("type", type);
                    context.startActivity(intent);

            }
        });


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
}
