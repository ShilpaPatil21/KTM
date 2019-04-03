package in.dealerservicecenter.ktm;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    Fragment fragment = null;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {



        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new A01_KTM_Model();
                    if(fragment!=null)
                    {
                        FragmentManager manager = getSupportFragmentManager();
                        manager.beginTransaction().replace(R.id.content_frame,fragment,fragment.getTag()).commit();
                    }
                    return true;
                case R.id.navigation_dashboard:
                    fragment = new B02_StateName();
                    if(fragment!=null)
                    {
                        FragmentManager manager = getSupportFragmentManager();
                        manager.beginTransaction().replace(R.id.content_frame,fragment,fragment.getTag()).commit();
                    }

                    return true;
                case R.id.navigation_notifications:
                    fragment = new A02_ServiceCenter();
                    if(fragment!=null)
                    {
                        FragmentManager manager = getSupportFragmentManager();
                        manager.beginTransaction().replace(R.id.content_frame,fragment,fragment.getTag()).commit();
                    }
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      //  mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fragment = new A01_KTM_Model();
        if(fragment!=null)
        {
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_frame,fragment,fragment.getTag()).commit();
        }
    }

}
