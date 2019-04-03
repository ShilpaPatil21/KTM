package in.dealerservicecenter.ktm;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class A00_FragmentBaseClass extends Fragment {

    RecyclerView State_recyclerview;
    List<B02_State_List> b02_state_lists;
    RecyclerView.Adapter state_adapter;
    int MAXIMUM_TIMEOUT_IN_SECONDS = 20, MAXIMUM_RETRY_STRING_REQUEST = 3;

    public   String B02_URLData = "https://www.dealerservicecenter.in/backend/api/get_state_list";
}
