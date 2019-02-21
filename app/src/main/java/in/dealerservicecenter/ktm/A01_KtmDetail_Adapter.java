package in.dealerservicecenter.ktm;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class A01_KtmDetail_Adapter extends RecyclerView.Adapter<A01_KtmDetail_Adapter.Viewholder>  {


    private List<A01_KtmDetail_List> a01DealerDetail_lists;
    private Context context;


    public A01_KtmDetail_Adapter(Context context, List<A01_KtmDetail_List> a01_ktmDetail_lists) {
        this.a01DealerDetail_lists = a01_ktmDetail_lists;
        this.context = context;


    }



    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ktm_list,parent,false);
        return  new Viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int i) {
        final A01_KtmDetail_List a01DealerDetail_list = a01DealerDetail_lists.get(i);

        viewholder.Txtname.setText(capitalize( a01DealerDetail_list.getPName()));
        viewholder.TxtShortdes.setText(capitalize( a01DealerDetail_list.getPdesc()));
        viewholder.Txtrate.setText(capitalize( a01DealerDetail_list.getPrate()));
        viewholder.TxtPrice.setText(capitalize( a01DealerDetail_list.getPprice()));
        viewholder.PImage.setImageDrawable(context.getResources().getDrawable(Integer.parseInt(String.valueOf(a01DealerDetail_list.getPimg()))));

        viewholder.KtmRv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o = new Intent(context,A02_KtmSpecification.class);
                o.putExtra("kid",String.valueOf(a01DealerDetail_list.getPid()));
                o.putExtra("kname",a01DealerDetail_list.getPName());
                context.startActivity(o);
            }
        });
    }
    @Override
    public int getItemCount() {
        return a01DealerDetail_lists.size();
    }

    public  class  Viewholder extends  RecyclerView.ViewHolder{

        public TextView Txtname,TxtShortdes,Txtrate,TxtPrice;
        ImageView PImage;
        RelativeLayout KtmRv;



        public Viewholder(@NonNull View itemView) {
            super(itemView);
            Txtname = (TextView) itemView.findViewById(R.id.textViewTitle);
            TxtShortdes = (TextView) itemView.findViewById(R.id.textViewShortDesc);
            Txtrate = (TextView) itemView.findViewById(R.id.textViewRating);
            TxtPrice = (TextView) itemView.findViewById(R.id.textViewPrice);
            PImage = (ImageView) itemView.findViewById(R.id.imageView);
            KtmRv = (RelativeLayout) itemView.findViewById(R.id.ktmlv);

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
