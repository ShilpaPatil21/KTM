package in.dealerservicecenter.ktm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class A03_Wallpaper extends AppCompatActivity {

    List<A03_KtmWallpaper_List> a03_ktmWallpaper_lists;

    //the recyclerview
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a03_wallpaper_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.Ktmwallpaper);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        try {
            //initializing the productlist
            a03_ktmWallpaper_lists = new ArrayList<>();
            //adding some items to our list
            a03_ktmWallpaper_lists.add(
                    new A03_KtmWallpaper_List(
                            1,
                            "kw_1",
                            R.drawable.th_kw_1));

            a03_ktmWallpaper_lists.add(
                    new A03_KtmWallpaper_List(
                            1,
                            "kw_3",
                            R.drawable.th_kw_3));

            a03_ktmWallpaper_lists.add(
                    new A03_KtmWallpaper_List(
                            1,
                            "kw_4",
                            R.drawable.th_kw_4));
            a03_ktmWallpaper_lists.add(
                    new A03_KtmWallpaper_List(
                            1,
                            "kw_5",
                            R.drawable.th_kw_5));

            a03_ktmWallpaper_lists.add(
                    new A03_KtmWallpaper_List(
                            1,
                            "kw_6",
                            R.drawable.th_kw_6));
            a03_ktmWallpaper_lists.add(
                    new A03_KtmWallpaper_List(
                            1,
                            "kw_7",
                            R.drawable.th_kw_7));
            a03_ktmWallpaper_lists.add(
                    new A03_KtmWallpaper_List(
                            1,
                            "kw_8",
                            R.drawable.th_kw_8));
            a03_ktmWallpaper_lists.add(
                    new A03_KtmWallpaper_List(
                            1,
                            "kw_9",
                            R.drawable.th_kw_9));
            a03_ktmWallpaper_lists.add(
                    new A03_KtmWallpaper_List(
                            1,
                            "kw_10",
                            R.drawable.th_kw_10));
//            a03_ktmWallpaper_lists.add(
//                    new A03_KtmWallpaper_List(
//                            1,
//                            "KTM 125 Duke",
//                            R.drawable.kw_11));
//            a03_ktmWallpaper_lists.add(
//                    new A03_KtmWallpaper_List(
//                            1,
//                            "KTM 125 Duke",
//                            R.drawable.kw_12));
//            a03_ktmWallpaper_lists.add(
//                    new A03_KtmWallpaper_List(
//                            1,
//                            "KTM 125 Duke",
//                            R.drawable.kw_13));
//            a03_ktmWallpaper_lists.add(
//                    new A03_KtmWallpaper_List(
//                            1,
//                            "KTM 125 Duke",
//                            R.drawable.kw_14));
//
//            a03_ktmWallpaper_lists.add(
//                    new A03_KtmWallpaper_List(
//                            1,
//                            "KTM 125 Duke",
//                            R.drawable.kw_15));
//
//            a03_ktmWallpaper_lists.add(
//                    new A03_KtmWallpaper_List(
//                            1,
//                            "KTM 125 Duke",
//                            R.drawable.kw_16));
//
//            a03_ktmWallpaper_lists.add(
//                    new A03_KtmWallpaper_List(
//                            1,
//                            "KTM 125 Duke",
//                            R.drawable.kw_17));
//
//            a03_ktmWallpaper_lists.add(
//                    new A03_KtmWallpaper_List(
//                            1,
//                            "KTM 125 Duke",
//                            R.drawable.kw_18));
//
//            a03_ktmWallpaper_lists.add(
//                    new A03_KtmWallpaper_List(
//                            1,
//                            "KTM 125 Duke",
//                            R.drawable.kw_19));
//            a03_ktmWallpaper_lists.add(
//                    new A03_KtmWallpaper_List(
//                            1,
//                            "KTM 125 Duke",
//                            R.drawable.kw_20));
//            a03_ktmWallpaper_lists.add(
//                    new A03_KtmWallpaper_List(
//                            1,
//                            "KTM 125 Duke",
//                            R.drawable.kw_21));
//            a03_ktmWallpaper_lists.add(
//                    new A03_KtmWallpaper_List(
//                            1,
//                            "KTM 125 Duke",
//                            R.drawable.kw_22));
//            a03_ktmWallpaper_lists.add(
//                    new A03_KtmWallpaper_List(
//                            1,
//                            "KTM 125 Duke",
//                            R.drawable.kw_23));
//            a03_ktmWallpaper_lists.add(
//                    new A03_KtmWallpaper_List(
//                            1,
//                            "KTM 125 Duke",
//                            R.drawable.kw_24));


        //creating recyclerview adapter
        A03_KtmWallpaper_Adapter a03_ktmWallpaper_adapter = new A03_KtmWallpaper_Adapter(this, a03_ktmWallpaper_lists);
        recyclerView.setAdapter(a03_ktmWallpaper_adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));

        }catch (Exception ex){
            Log.e("Images",ex.getMessage());
        }
    }
    /*------------------------ BACK BUTTON ----------------------------*/
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
