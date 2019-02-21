package in.dealerservicecenter.ktm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class A01_KTM_Model extends AppCompatActivity {
    List<A01_KtmDetail_List> productList;

    //the recyclerview
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.a01_ktm_model);

           //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.ktmmodel);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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

        A01_KtmDetail_Adapter adapter = new A01_KtmDetail_Adapter(this, productList);
        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_item_one) {
            try {

                Intent intent = new Intent(getApplicationContext(), A03_Wallpaper.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                startActivity(intent);
                return true;
            }catch (Exception ex){
                Log.e("Intent",ex.getMessage());
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
