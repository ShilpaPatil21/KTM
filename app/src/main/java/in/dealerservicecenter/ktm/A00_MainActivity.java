package in.dealerservicecenter.ktm;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class A00_MainActivity extends AppCompatActivity {
    boolean doubleBackToExitPressedOnce = false;
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
                    fragment = new B01_StateName();
                    if(fragment!=null)
                    {
                        FragmentManager manager = getSupportFragmentManager();
                        manager.beginTransaction().replace(R.id.content_frame,fragment,fragment.getTag()).commit();
                    }

                    return true;
                case R.id.navigation_notifications:
                    fragment = new C01_ServiceCenter();
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
        setContentView(R.layout.a00_activity_main);

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


    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;

        Snackbar.make(findViewById(android.R.id.content), "Please click BACK again to exit", Snackbar.LENGTH_LONG).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
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

                Intent intent = new Intent(getApplicationContext(), D01_Wallpaper.class);
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
