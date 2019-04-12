package in.dealerservicecenter.ktm;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class D01_Wallpaper extends A00_ActivityBaseClass {

    List<D01_KtmWallpaper_List> d01_ktmWallpaper_lists;
    Context context = this;
    //the recyclerview
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d01_wallpaper_activity);

        try{

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.Ktmwallpaper);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        try {
            //initializing the productlist
            d01_ktmWallpaper_lists = new ArrayList<>();
            //adding some items to our list
            d01_ktmWallpaper_lists.add(
                    new D01_KtmWallpaper_List(
                            1,
                            "kw_1",
                            R.drawable.th_kw_1));

            d01_ktmWallpaper_lists.add(
                    new D01_KtmWallpaper_List(
                            1,
                            "kw_3",
                            R.drawable.th_kw_3));

            d01_ktmWallpaper_lists.add(
                    new D01_KtmWallpaper_List(
                            1,
                            "kw_4",
                            R.drawable.th_kw_4));
            d01_ktmWallpaper_lists.add(
                    new D01_KtmWallpaper_List(
                            1,
                            "kw_5",
                            R.drawable.th_kw_5));

            d01_ktmWallpaper_lists.add(
                    new D01_KtmWallpaper_List(
                            1,
                            "kw_6",
                            R.drawable.th_kw_6));
            d01_ktmWallpaper_lists.add(
                    new D01_KtmWallpaper_List(
                            1,
                            "kw_7",
                            R.drawable.th_kw_7));
            d01_ktmWallpaper_lists.add(
                    new D01_KtmWallpaper_List(
                            1,
                            "kw_8",
                            R.drawable.th_kw_8));
            d01_ktmWallpaper_lists.add(
                    new D01_KtmWallpaper_List(
                            1,
                            "kw_9",
                            R.drawable.th_kw_9));
            d01_ktmWallpaper_lists.add(
                    new D01_KtmWallpaper_List(
                            1,
                            "kw_10",
                            R.drawable.th_kw_10));
//            d01_ktmWallpaper_lists.add(
//                    new D01_KtmWallpaper_List(
//                            1,
//                            "KTM 125 Duke",
//                            R.drawable.kw_11));
//            d01_ktmWallpaper_lists.add(
//                    new D01_KtmWallpaper_List(
//                            1,
//                            "KTM 125 Duke",
//                            R.drawable.kw_12));
//            d01_ktmWallpaper_lists.add(
//                    new D01_KtmWallpaper_List(
//                            1,
//                            "KTM 125 Duke",
//                            R.drawable.kw_13));
//            d01_ktmWallpaper_lists.add(
//                    new D01_KtmWallpaper_List(
//                            1,
//                            "KTM 125 Duke",
//                            R.drawable.kw_14));
//
//            d01_ktmWallpaper_lists.add(
//                    new D01_KtmWallpaper_List(
//                            1,
//                            "KTM 125 Duke",
//                            R.drawable.kw_15));
//
//            d01_ktmWallpaper_lists.add(
//                    new D01_KtmWallpaper_List(
//                            1,
//                            "KTM 125 Duke",
//                            R.drawable.kw_16));
//
//            d01_ktmWallpaper_lists.add(
//                    new D01_KtmWallpaper_List(
//                            1,
//                            "KTM 125 Duke",
//                            R.drawable.kw_17));
//
//            d01_ktmWallpaper_lists.add(
//                    new D01_KtmWallpaper_List(
//                            1,
//                            "KTM 125 Duke",
//                            R.drawable.kw_18));
//
//            d01_ktmWallpaper_lists.add(
//                    new D01_KtmWallpaper_List(
//                            1,
//                            "KTM 125 Duke",
//                            R.drawable.kw_19));
//            d01_ktmWallpaper_lists.add(
//                    new D01_KtmWallpaper_List(
//                            1,
//                            "KTM 125 Duke",
//                            R.drawable.kw_20));
//            d01_ktmWallpaper_lists.add(
//                    new D01_KtmWallpaper_List(
//                            1,
//                            "KTM 125 Duke",
//                            R.drawable.kw_21));
//            d01_ktmWallpaper_lists.add(
//                    new D01_KtmWallpaper_List(
//                            1,
//                            "KTM 125 Duke",
//                            R.drawable.kw_22));
//            d01_ktmWallpaper_lists.add(
//                    new D01_KtmWallpaper_List(
//                            1,
//                            "KTM 125 Duke",
//                            R.drawable.kw_23));
//            d01_ktmWallpaper_lists.add(
//                    new D01_KtmWallpaper_List(
//                            1,
//                            "KTM 125 Duke",
//                            R.drawable.kw_24));


        //creating recyclerview adapter
        D01_KtmWallpaper_Adapter d01_ktmWallpaper_adapter = new D01_KtmWallpaper_Adapter(this, d01_ktmWallpaper_lists);
        recyclerView.setAdapter(d01_ktmWallpaper_adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));

        }catch (Exception e){
            StackTraceElement[] trace = e.getStackTrace();
            System.out.println("Ktm App :- " + trace[0].getFileName()+" Line:-"+trace[0].getLineNumber()+" Error:- "+e.getMessage());
            //Sending Mail
            Send_Mail_Exception("Ktm App :- " + trace[0].getFileName()+" Line:-"+trace[0].getLineNumber()+" Error:- "+e.getMessage());

        }

        }catch (Exception e){
            StackTraceElement[] trace = e.getStackTrace();
            System.out.println("Ktm App :- " + trace[0].getFileName()+" Line:-"+trace[0].getLineNumber()+" Error:- "+e.getMessage());
            //Sending Mail
            Send_Mail_Exception("Ktm App :- " + trace[0].getFileName()+" Line:-"+trace[0].getLineNumber()+" Error:- "+e.getMessage());

        }
    }
    /*------------------------ BACK BUTTON ----------------------------*/
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
