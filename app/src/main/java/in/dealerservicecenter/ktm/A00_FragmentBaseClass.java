package in.dealerservicecenter.ktm;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class A00_FragmentBaseClass extends Fragment {
    View nodata;
    RecyclerView State_recyclerview;
    List<B01_C01_State_List> b01C01_state_lists;
    RecyclerView.Adapter state_adapter;
    int MAXIMUM_TIMEOUT_IN_SECONDS = 20, MAXIMUM_RETRY_STRING_REQUEST = 3;

    public   String B02_URLData = "https://www.dealerservicecenter.in/backend/api/get_state_list";

    View NoInternet;


    ProgressDialog progressDialog ;


    public void NodataFound(final Context context, final String Title_Nodata){
        nodata.setVisibility(View.VISIBLE);
        TextView back_btn = (TextView)this.getView().findViewById(R.id.back_btn);
        TextView t1 = (TextView)this.getView().findViewById(R.id.title_data);
        t1.setText(Title_Nodata);
        back_btn.setVisibility(View.GONE);

    }


}
