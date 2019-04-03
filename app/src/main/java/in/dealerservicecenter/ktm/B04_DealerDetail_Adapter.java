package in.dealerservicecenter.ktm;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class B04_DealerDetail_Adapter extends RecyclerView.Adapter<B04_DealerDetail_Adapter.Viewholder>  {


    private List<B04_DealerDetail_List> b04DealerDetail_lists;
    private Context context;


    public B04_DealerDetail_Adapter(List<B04_DealerDetail_List> b04DealerDetail_list, Context context) {
        this.b04DealerDetail_lists = b04DealerDetail_list;
        this.context = context;


    }



    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.b04_dealer_detail_list,parent,false);
        return  new Viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int i) {
        final B04_DealerDetail_List b04DealerDetail_list = b04DealerDetail_lists.get(i);

        viewholder.Txtname.setText(capitalize( b04DealerDetail_list.getBname()));
        viewholder.Txtadd.setText(capitalize(b04DealerDetail_list.getBadd()) + "   " + b04DealerDetail_list.getPincode());
        viewholder.Txtcontact.setText(capitalize(b04DealerDetail_list.getBcontact_person()+ " -" +b04DealerDetail_list.getBcontact()));
        viewholder.Txtslug.setText(capitalize(b04DealerDetail_list.getBtitle()));
        viewholder.Txtemail.setText(capitalize(b04DealerDetail_list.getBemail()));




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

}
