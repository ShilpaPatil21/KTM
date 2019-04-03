package in.dealerservicecenter.ktm;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class A02_KtmSpecification extends AppCompatActivity {
    String kid,kname;
    Context context = this;
    private AdView mAdView;
    TextView m1;
    TextView m2;
    TextView m3;
    TextView m4;
    ViewFlipper viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a02_ktm_specification_activity);

        Intent i = getIntent();
        kid = i.getStringExtra("kid");
        kname = i.getStringExtra("kname");


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(kname);

        /*---------------------------------TAB HOST------------------------------------*/
        final TabHost host = (TabHost)findViewById(R.id.tabHost);
        host.setup();

        host.setOnTabChangedListener(new TabHost.OnTabChangeListener() {

            @Override
            public void onTabChanged(String arg0) {

                setTabColor(host);
            }
        });


        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("ERGONOMICS & COMFORT");
        spec.setContent(R.id.tab1);
        spec.setIndicator("", getResources().getDrawable(R.drawable.icon_1));
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("OverView");
        spec.setContent(R.id.tab2);
        spec.setIndicator("", getResources().getDrawable(R.drawable.settings));
        host.addTab(spec);

        //Tab 3
        spec = host.newTabSpec("BODYWORK & GRAPHICS");
        spec.setContent(R.id.tab3);
        spec.setIndicator("", getResources().getDrawable(R.drawable.icon_3));
        host.addTab(spec);

        spec = host.newTabSpec("Price");
        spec.setContent(R.id.tab5);
        spec.setIndicator("", getResources().getDrawable(R.drawable.icon_4));
        host.addTab(spec);


        //        //Tab 3
        spec = host.newTabSpec("Mileage");
        spec.setContent(R.id.tab4);
        spec.setIndicator("", getResources().getDrawable(R.drawable.icon_5));
        host.addTab(spec);


        /*----------------------END TAB HOST -------------------------------------------*/

        //---------------------Milage--------------------------------------

        m1=(TextView) findViewById(R.id.m1);
        m2=(TextView) findViewById(R.id.m2);
        m3=(TextView) findViewById(R.id.m3);
        m4=(TextView) findViewById(R.id.m4);

        if(kid.equals("2")){
            m1.setText("33kmpl");
            m2.setText("52.75kmpl");
            m3.setText("60%");
            m4.setText("200 Duke has better mileage than 60% of commuters");
        }
        if(kid.equals("4")){
            m1.setText("25kmpl");
            m2.setText("52.75kmpl");
            m3.setText("57%");
            m4.setText("390 Duke has better mileage than 57% of commuters");
        }
        if(kid.equals("5")){
            m1.setText("30kmpl");
            m2.setText("52.75kmpl");
            m3.setText("59%");
            m4.setText("250 Duke has better mileage than 59% of commuters");
        }
        if(kid.equals("3"))
        {
            m2.setText("35 Kmpl");
            m1.setText("Max Power\n" + "25.00 bhp @ 10000 rpm ");
            m3.setText("Engine Capacity");
            m4.setText("199.5 CC");
        }
        if(kid.equals("6")){
            m2.setText("25 Kmpl");
            m1.setText("Max Power\n"+"43.50 PS @ 9000 rpm");
            m3.setText("Engine Capacity");
            m4.setText("373.2 cc");
        }

    /*-----------------END MILAGe--------------------------------------*/

    /*----------------------------------Slider -------------------------------*/
        viewPager = findViewById(R.id.vpage);


        if(kid.equals("2")) {
            int images[] = {R.drawable.slider_200duke_1, R.drawable.slider_200duke_2, R.drawable.slider_200duke_3};

            for (int image : images) {
                fliperimages(image);
            }
        }
        if(kid.equals("1")) {
            int images[] = {R.drawable.slider1_125duke, R.drawable.slider2_125duke, R.drawable.slider_200duke_3};

            for(int image :images){
                fliperimages(image);
            }
        }
        if(kid.equals("3")) {
            int images[] = {R.drawable.ktmrc_200, R.drawable.slider2_rc200};

            for(int image :images){
                fliperimages(image);
            }

        }
        if(kid.equals("4")) {
            int images[] = {R.drawable.slider1_390duke, R.drawable.slider2_390duke};

            for(int image :images){
                fliperimages(image);
            }

        }
        if(kid.equals("5")) {
            int images[] = {R.drawable.ktm_250_duke, R.drawable.slider2_250duke};

            for(int image :images){
                fliperimages(image);
            }

        }
        if(kid.equals("6")) {
            int images[] = {R.drawable.slider1_390rc, R.drawable.ktm_rc390};

            for(int image :images){
                fliperimages(image);
            }

        }


        /*--------------------------End SLIDER-----------------------------------------*/

        /*---------------------------PRICE -------------------------------------------*/

        TextView p1 =(TextView)findViewById(R.id.panvel);
        TextView nm =(TextView)findViewById(R.id.navi_mumbai);
        TextView th =(TextView)findViewById(R.id.thane);
        TextView ab =(TextView)findViewById(R.id.alibag);
        TextView vs =(TextView)findViewById(R.id.vasai);
        TextView pn =(TextView)findViewById(R.id.pen);
        TextView db =(TextView)findViewById(R.id.dombivali);
        TextView wh =(TextView)findViewById(R.id.wadkhal);
        TextView bh =(TextView)findViewById(R.id.bhiwandi);
        TextView pen_kalyan =(TextView)findViewById(R.id.pen_Kalyan);
        TextView wh_un =(TextView)findViewById(R.id.wh_un);



        if(kid.equals("5")){
            p1.setText("₹ 2,13,024");
            nm.setText("₹ 2,13,024");
            th.setText("₹ 2,13,024");
            ab.setText("₹ 2,13,024");
            vs.setText("₹ 2,13,024");
            pn.setText("₹ 2,10,748");
            wh.setText("₹ 2,10,748");
            db.setText("₹ 2,13,024");
            bh.setText("₹ 2,13,024");
        }
        if(kid.equals("4")){
            p1.setText("₹ 2,96,370");
            nm.setText("₹ 2,96,370");
            th.setText("₹ 2,96,370");
            ab.setText("₹ 2,96,370");
            db.setText("₹ 2,96,370");
            vs.setText("₹ 2,96,370");
            pn.setText("₹ 2,92,860");
            wh.setText("₹ 2,92,860");
            bh.setText("₹ 2,96,370");

        }
        if(kid.equals("2")){
            p1.setText("₹ 1,80,017");
            nm.setText("₹ 1,80,017");
            th.setText("₹ 1,80,017");
            ab.setText("₹ 1,80,017");
            db.setText("₹ 1,80,017");
            vs.setText("₹ 1,80,017");
            pn.setText("₹ 1,77,951");
            wh.setText("₹ 1,77,951");
            bh.setText("₹ 1,80.017");
        }
        if(kid.equals("3")){
            p1.setText("₹ 2,11,098");
            nm.setText("₹ 2,11,098");
            th.setText("₹ 2,11,098");
            ab.setText("₹ 2,11,098");
            db.setText("₹ 2,11,098");
            vs.setText("₹ 2,11,098");
            pn.setText("₹ 2,09,317");
            wh.setText("₹ 1,77,951");
            bh.setText("₹ 2,11,098");
        }
        if(kid.equals("6")){

            p1.setText("₹ 2,92,566");
            nm.setText("₹ 2,92,566");
            th.setText("₹ 2,92,566");
            ab.setText("₹ 2,92,566");
            db.setText("₹ 2,92,566");
            vs.setText("₹ 2,92,566");
            pn.setText("₹ 2,89,037");
            wh.setText("₹ 2,89,037");
            bh.setText("₹ 2,92,566");
        }
        if(kid.equals("1")){

            pen_kalyan.setText("Kalyan");
            wh_un.setText("Ulhasnagar");

            p1.setText("₹ 1,39,220");
            nm.setText("₹ 1,39,220");
            th.setText("₹ 1,39,220");
            ab.setText("₹ 1,39,220");
            db.setText("₹ 1,39,220");
            vs.setText("₹ 1,39,220");
            pn.setText("₹ 1,39,220");
            wh.setText("₹ 1,39,220");
            bh.setText("₹ 1,39,220");

        }
        /*-------------------------TECHNICAL DETAIL--------------------------------------------*/

        TextView design =(TextView)findViewById(R.id.design);
        TextView displacement =(TextView)findViewById(R.id.displacement);
        TextView bore =(TextView)findViewById(R.id.bore);
        TextView  stroke =(TextView)findViewById(R.id.stroke);
        TextView power =(TextView)findViewById(R.id.power);
        TextView starter =(TextView)findViewById(R.id.starter);
        TextView lubrication =(TextView)findViewById(R.id.lubrication);
        TextView transmission =(TextView)findViewById(R.id.transmission);
        TextView cooling =(TextView)findViewById(R.id.cooling);
        TextView clutch =(TextView)findViewById(R.id.clutch);
        TextView ems =(TextView)findViewById(R.id.ems);
        TextView fuel =(TextView)findViewById(R.id.fuel);
        TextView c02 =(TextView)findViewById(R.id.c02);

        TextView  fdesign = (TextView)findViewById(R.id.fdesign);
        TextView  fsuspension = (TextView)findViewById(R.id.fsuspension);
        TextView  readsuspension = (TextView)findViewById(R.id.readsuspension);
        TextView  f_suspension_travel = (TextView)findViewById(R.id.f_suspension_travel);
        TextView  r_suspension_travel = (TextView)findViewById(R.id.r_suspension_travel);
        TextView  rare_break = (TextView)findViewById(R.id.rare_break);
        TextView  front_brake = (TextView)findViewById(R.id.front_brake);
        TextView  fbdd = (TextView)findViewById(R.id.fbdd);
        TextView  rbdd = (TextView)findViewById(R.id.rbdd);
        TextView  abs = (TextView)findViewById(R.id.abs);
        TextView  chain = (TextView)findViewById(R.id.chain);
        TextView  steering = (TextView)findViewById(R.id.steering);
        TextView  ground_c = (TextView)findViewById(R.id.ground_c);
        TextView  tank = (TextView)findViewById(R.id.tank);
        TextView dry_weight = (TextView)findViewById(R.id.dry_weight);
        TextView seat_h = (TextView)findViewById(R.id.seat_h);

        TableRow  abs_r = (TableRow) findViewById(R.id.abs_r);


        // 2 ,5
        if(kid.equals("3")){

            design.setText("1-cylinder, 4-stroke engine");
            displacement.setText("199.5 cm³");
            bore.setText("72 mm");
            stroke.setText("49 mm");
            power.setText("19 kW");
            starter.setText("Electric starter");
            lubrication.setText("Wet sump");
            transmission.setText("6-speed");
            cooling.setText("Liquid cooled");
            clutch.setText("Wet multi-disc clutch, mechanically actuated");
            ems.setText("Bosch EMS");


            fdesign.setText("Steel trellis frame, powder coated");
            fsuspension.setText("WP upside-down Ø 43 mm");
            readsuspension.setText("WP monoshock");
            f_suspension_travel.setText("125 mm");
            r_suspension_travel.setText("150 mm");
            front_brake.setText("Four-piston radial fixed calliper, brake disc");
            rare_break.setText("Single-piston floating calliper, brake disc");
            fbdd.setText("300 mm");
            rbdd.setText("230 mm");
            //abs.setText("Bosch 9MB two-channel ABS");
            chain.setText("X-Ring 5/8 x 1/4\"");
            steering.setText("66.5 °");
            ground_c.setText("178.5 mm");
            tank.setText("10 l");
            dry_weight.setText("137 kg");
            seat_h.setText("820 mm");
            abs_r.setVisibility(View.GONE);
        }
        if(kid.equals("1")){
            design.setText("1-cylinder, 4-stroke engine");
            displacement.setText("124.7 cm³");
            bore.setText("58 mm");
            stroke.setText("47.2 mm");
            power.setText("11 kW");
            starter.setText("Electric starter");
            lubrication.setText("Wet sump");
            transmission.setText("6-speed");
            cooling.setText("Liquid cooled");
            clutch.setText("Wet multi-disc clutch, mechanically actuated");
            ems.setText("Bosch EMS");
            fuel.setText("2.42 l/100 km");
            c02.setText("55.2 g/km");


            fdesign.setText("Steel trellis frame, powder coated");
            fsuspension.setText("WP upside-down Ø 43 mm");
            readsuspension.setText("WP monoshock");
            f_suspension_travel.setText("142 mm");
            r_suspension_travel.setText("150 mm");
            front_brake.setText("Four-piston radial fixed calliper, brake disc");
            rare_break.setText("Single-piston floating calliper, brake disc");
            fbdd.setText("300 mm");
            rbdd.setText("230 mm");
            abs.setText("Bosch 9MB two-channel ABS");
            chain.setText("X-Ring 5/8 x 1/4\"");
            steering.setText("65 °");
            ground_c.setText("185 mm");
            tank.setText("13.4 l");
            dry_weight.setText("137 kg");
            seat_h.setText("830 mm");

        }
        if(kid.equals("4")){
            design.setText("1-cylinder, 4-stroke engine");
            displacement.setText("373.2 cm³");
            bore.setText("89 mm");
            stroke.setText("60 mm");
            power.setText("32 kW");
            starter.setText("Electric starter");
            lubrication.setText("Wet sump");
            transmission.setText("6-speed");
            cooling.setText("Liquid cooled");
            clutch.setText("PASC™ antihopping clutch, mechanically actuated");
            ems.setText("Bosch EMS");
            fuel.setText("3.46 l/100 km");
            c02.setText("80.2 g/km");


            fdesign.setText("Steel trellis frame, powder coated");
            fsuspension.setText("WP upside-down Ø 43 mm");
            readsuspension.setText("WP monoshock");
            f_suspension_travel.setText("142 mm");
            r_suspension_travel.setText("150 mm");
            front_brake.setText("Four-piston radial fixed calliper, brake disc");
            rare_break.setText("Single-piston floating calliper, brake disc");
            fbdd.setText("320 mm");
            rbdd.setText("230 mm");
            abs.setText("Bosch 9MP two-channel ABS (incl. Supermoto mode, diesengageable)");
            chain.setText("X-Ring 5/8 x 1/4");
            steering.setText("65 °");
            ground_c.setText("185 mm");
            tank.setText("13.4 l");
            dry_weight.setText("149 kg");
            seat_h.setText("830 mm");




        }
        if(kid.equals("6")){
            design.setText("1-cylinder, 4-stroke engine");
            displacement.setText("373.2 cm³");
            bore.setText("89 mm");
            stroke.setText("60 mm");
            power.setText("32 kW");
            starter.setText("Electric starter");
            lubrication.setText("Semi-dry sump");
            transmission.setText("6-speed");
            cooling.setText("Liquid cooled");
            clutch.setText("PASC™ antihopping clutch, mechanically operated");
            ems.setText("Bosch EMS with RBW");
            fuel.setText("3.52 l/100 km");
            c02.setText("82 g/km");

            fdesign.setText("Steel trellis frame, powder coated");
            fsuspension.setText("WP upside-down Ø 43 mm");
            readsuspension.setText("WP monoshock");
            f_suspension_travel.setText("125 mm");
            r_suspension_travel.setText("150 mm");
            front_brake.setText("Four-piston radial fixed calliper, brake disc");
            rare_break.setText("Single-piston floating calliper, brake disc");
            fbdd.setText("320 mm");
            rbdd.setText("230 mm");
            abs.setText("Bosch 9MB two-channel ABS");
            chain.setText("X-Ring 5/8 x 1/4\"");
            steering.setText("66.5 °");
            ground_c.setText("178.5 mm");
            tank.setText("10 l");
            dry_weight.setText("147 kg");
            seat_h.setText("820 mm");



        }

        /*------------------------------Body Work-------------------------------------------------*/
        ImageView image_b2 = (ImageView)findViewById(R.id.body_img2);
        ImageView image_b1 = (ImageView)findViewById(R.id.body_img1);
        ImageView image_b3 = (ImageView)findViewById(R.id.body_img3);
        ImageView image_b4 = (ImageView)findViewById(R.id.body_img4);

        TextView t1 = (TextView)findViewById(R.id.title_big1);
        TextView t2 = (TextView)findViewById(R.id.title_big2);
        TextView t3 = (TextView)findViewById(R.id.title_big3);
        TextView t4 = (TextView)findViewById(R.id.title_big4);

        TextView d1 = (TextView)findViewById(R.id.desc1);
        TextView d2 = (TextView)findViewById(R.id.desc2);
        TextView d3 = (TextView)findViewById(R.id.desc3);
        TextView d4 = (TextView)findViewById(R.id.desc4);

        TextView s1 = (TextView)findViewById(R.id.title_small1);
        TextView s2 = (TextView)findViewById(R.id.title_small2);
        TextView s3 = (TextView)findViewById(R.id.title_small3);
        TextView s4 = (TextView)findViewById(R.id.title_small4);

        TableLayout bodypart_3= (TableLayout) findViewById(R.id.bodypart_3);
        TableLayout bodypart_4= (TableLayout) findViewById(R.id.bodypart_4);
        TableLayout bodypart_2= (TableLayout) findViewById(R.id.bodypart_2);

        if(kid.equals("6")){
            image_b1.setImageDrawable(context.getResources().getDrawable(R.drawable.headlight_rc390));
            image_b2.setImageDrawable(context.getResources().getDrawable(R.drawable.instrument_rc_390));

            t1.setText("HEADLIGHT");
            t2.setText("INSTRUMENT");

            s1.setText("True superSport Headlight");
            s2.setText("Adavance LCD display");

            d1.setText("The powerful twin headlight provides superb visibility in low-light or dark conditions and perfectly" +
                    " matches the modern, supersport race look of the KTM RC 390.");

            d2.setText("A 100% digital LCD display has everything you would expect from a state-of-the-art KTM motorcycle, including engaged gear, " +
                    "fuel gauge and service reminder information. It also comes with an RPM alert to indicate the optimum moment for up-shifting, " +
                    "just like they have on the supersport racing machines.");
            bodypart_3.setVisibility(View.GONE);
            bodypart_4.setVisibility(View.GONE);
        }
        if(kid.equals("3")){
            image_b1.setImageDrawable(context.getResources().getDrawable(R.drawable.a02_rc200_1));
            image_b2.setImageDrawable(context.getResources().getDrawable(R.drawable.a02_rc200_2));

            t2.setText("HANDLEBAR");
            s2.setText("Racing profile handlebars");
            d2.setText("The handlebars on the KTM RC 200 offer the perfect riding position for on-road domination. The clip-on bars and strategically positioned switches contribute greatly to the bike's sporty and aggressive riding position, whilst also enhancing control for perfect lines through any corner.");
            t1.setText("WINDSHIELD");
            s1.setText("Advanced aerodynamic protection");
            d1.setText("The advanced design of the KTM RC 200's windshield works with the aerodynamic optimization of the front end to ensure ultimate wind protection for riders who like to open it up on the open road.");
            bodypart_3.setVisibility(View.GONE);
            bodypart_4.setVisibility(View.GONE);
        }

        if (kid.equals("1")){
            image_b1.setImageDrawable(context.getResources().getDrawable(R.drawable.a02_125duke_1));
            image_b2.setImageDrawable(context.getResources().getDrawable(R.drawable.a02_125duke_2));
            image_b3.setImageDrawable(context.getResources().getDrawable(R.drawable.a02_125duke_3));
            image_b4.setImageDrawable(context.getResources().getDrawable(R.drawable.a02_125duke_4));

            t1.setText("DESIGN");
            s1.setText("Pure BEAST style");
            d1.setText("At first you notice that LED headlight. That aggressive front. That predatory stance. When you come closer, you feel its steel fuel tank and multiple high-quality cast parts. In the end you realize that the design and production standards of this bike were set sky-high. Just like its big brother, the KTM 1290 SUPER DUKE R, the KTM 125 DUKE looks like a naked, angry, untamed little beast.");
            t2.setText("TFT DISPLAY");
            s2.setText("---------");
            d2.setText("As an absolute first in its class, the KTM 125 DUKE boasts an innovative multicolor TFT display, presenting all the data you need to go faster in a clear-cut, uncluttered way. As you rev the engine, the display bars change color, either spurring you on or telling you to back off when the engine’s still cold. The display also automatically adapts its color to best suit the light conditions around you. Innovation at its best!!!");

            t3.setText("LED HEADLIGHT");
            s3.setText("-------------");
            d3.setText("The aggressively styled LED headlight on the KTM 125 DUKE is a big highlight in this bike’s design and a clear referral to its big brother, the KTM 1290 SUPER DUKE R. Practical as well as looking ultra-mean, it produces outstanding visibility for riding in low light or dark conditions. The parking light, which consists of 20 LEDs, ensures that the bike is highly visible to other road users, keeping you safe while you dominate the streets.   ");

            t4.setText("MENU SWITCH");
            s4.setText("----------");
            d4.setText("Different modes for the various built-in assistance systems can be selected using an illuminated menu switch on the left side of the handlebar. It's very simple and intuitive: flick \"up\" and \"down\" through the menu, select the desired setting with the right button or go a step back with the left. Job done. Race on.");

        }
        if(kid.equals("4")){
            image_b1.setImageDrawable(context.getResources().getDrawable(R.drawable.a02_390duke_1));
            image_b2.setImageDrawable(context.getResources().getDrawable(R.drawable.a02_390duke_2));
            image_b3.setImageDrawable(context.getResources().getDrawable(R.drawable.a02_390duke_3));
            t1.setText("TFT COLOR DISPLAY");
            s1.setText("Multicolor display");
            d1.setText("As an absolute first in its class, the KTM 390 DUKE boasts an innovative multicolor TFT display, presenting all the data you need to go faster in a clear-cut, uncluttered way. As you rev the engine, the display bars change color, either spurring you on or telling you to back off when the engine’s still cold. The display also automatically adapts its color to best suit the light conditions around you. Innovation at its best!!!");

            t2.setText("LED HEADLIGHT");
            s2.setText("Mean illumination");
            d2.setText("The aggressively styled LED headlight on the KTM 390 DUKE is a big highlight in this bike’s design and a clear referral to its big brother, the KTM 1290 SUPER DUKE R. Practical as well as looking ultra-mean, it produces outstanding visibility for riding in low light or dark conditions. The parking light, which consists of 20 LEDs, ensures that the bike is highly visible to other road users, keeping you safe while you dominate the streets.");

            t3.setText("MENU SWITCH");
            s3.setText("-----------");
            d3.setText("Different modes for the various built-in assistance systems can be selected using an illuminated menu switch on the left side of the handlebar. It's very simple and intuitive: flick \"up\" and \"down\" through the menu, select the desired setting with the right button or go a step back with the left. Job done. Race on.");
            bodypart_4.setVisibility(View.GONE);
        }
        if(kid.equals("5")){
            image_b1.setImageDrawable(context.getResources().getDrawable(R.drawable.a02_250duke_1));
            image_b2.setImageDrawable(context.getResources().getDrawable(R.drawable.a02_250duke_2));
            image_b3.setImageDrawable(context.getResources().getDrawable(R.drawable.a02_250duke_3));
            t1.setText("DESIGN");
            s1.setText("Predatory design");
            d1.setText("At first you notice that aggressive front end. That predatory, READY TO RACE stance. When you come closer, you feel its steel fuel tank and multiple high-quality cast parts. In the end, you realize that the design and production standards of this bike were set sky-high. Just like its big brother, the KTM 1290 SUPER DUKE R, the KTM 250 DUKE looks like a naked, angry, untamed beast.\n");

            t2.setText("HEADLIGHT");
            s2.setText("--------");
            d2.setText("With integrated Daytime Running Lights (DRL) consisting of 20 individual LEDs, this halogen headlight makes a bold statement, with its an unmistakable similarity to THE BEAST. Just get on and hunt them all – the 250 DUKE invites you to do exactly that.");

            t3.setText("LCD DISPLAY");
            s3.setText("----------");
            d3.setText("A 100% digital LCD display has everything you would expect from a state-of-the-art KTM motorcycle, including engaged gear, fuel gauge and service reminder information. It also comes with an RPM alert to indicate the optimum moment for up-shifting, just like they have on the supersport racing machines.");
            bodypart_4.setVisibility(View.GONE);
        }
        if(kid.equals("2")){
            image_b1.setImageDrawable(context.getResources().getDrawable(R.drawable.a02_200duke_1));
            t1.setText("INSTRUMENT");
            s1.setText("Multi-function cockpit");
            d1.setText("The KTM 200 DUKE has everything that distinguishes a true KTM: from the high-quality chassis components to the hi-tech multi-function cockpit. The advanced dashboard even keeps you up to date with fuel consumption and remaining range. The extensive KTM PowerParts range gives you the option of adding your own individual style to your dashboard for the ultimate personalized touch.  ");
            bodypart_3.setVisibility(View.GONE);
            bodypart_4.setVisibility(View.GONE);
            bodypart_2.setVisibility(View.GONE);
        }
        /*------------------------------Body Work-------------------------------------------------*/
        ImageView image_e1 = (ImageView)findViewById(R.id.ec_img1);
        ImageView image_e2 = (ImageView)findViewById(R.id.ec_img2);
        ImageView image_e3 = (ImageView)findViewById(R.id.ec_img3);
        ImageView image_e4 = (ImageView)findViewById(R.id.ec_img4);

        TextView e1 = (TextView)findViewById(R.id.ec_big_1);
        TextView e2 = (TextView)findViewById(R.id.ec_bg_2);
        TextView e3 = (TextView)findViewById(R.id.ec_big_3);
        TextView e4 = (TextView)findViewById(R.id.ec_bg_4);

        TextView de1 = (TextView)findViewById(R.id.ec_dec1);
        TextView de2 = (TextView)findViewById(R.id.ec_dec2);
        TextView de3 = (TextView)findViewById(R.id.ec_dec3);
        TextView de4 = (TextView)findViewById(R.id.ec_dec4);

        TextView se1 = (TextView)findViewById(R.id.ec_sm_1);
        TextView se2 = (TextView)findViewById(R.id.ec_sm_2);
        TextView se4 = (TextView)findViewById(R.id.ec_sm_3);
        TextView se3 = (TextView)findViewById(R.id.ec_sm_4);

        TableLayout ebodypart_1= (TableLayout) findViewById(R.id.ec_1);
        TableLayout ebodypart_2= (TableLayout) findViewById(R.id.ec_2);
        TableLayout ebodypart_3= (TableLayout) findViewById(R.id.ec_3);
        TableLayout ebodypart_4= (TableLayout) findViewById(R.id.ec_4);


        if(kid.equals("5")){
            image_e1.setImageDrawable(context.getResources().getDrawable(R.drawable.a02_ec_250duke_1));
            e1.setText("SEATS");
            se1.setText("Aggressive riding position");
            de1.setText("The two-part seat offers first class sport riding ergonomics with perfect support for both rider and pillion.  A seat height of 820 mm enables riders to have a super- secure position, whilst also being high enough for ultimate cornering fun. The Pillion seat has been integrated into the bodywork so as not to compromise the stunt-ready look of the KTM 250 DUKE, whilst also ensuring excellent comfort for the passenger.");
            ebodypart_2.setVisibility(View.GONE);
            ebodypart_3.setVisibility(View.GONE);
            ebodypart_4.setVisibility(View.GONE );
        }
        if(kid.equals("2")){
            image_e1.setImageDrawable(context.getResources().getDrawable(R.drawable.a02_ec_200duke_1));
            e1.setText("SEATS");
            se1.setText("Aggressive riding position");
            de1.setText("The two-part seat offers first class sport riding ergonomics with perfect support for both rider and pillion.  A seat height of 820 mm enables riders to have a super-secure position, whilst also being high enough for ultimate cornering fun. The Pillion seat has been integrated into the bodywork so as not to compromise the stunt-ready look of the KTM 200 DUKE, whilst also ensuring excellent comfort for the passenger. ");
            ebodypart_2.setVisibility(View.GONE);
            ebodypart_3.setVisibility(View.GONE);
            ebodypart_4.setVisibility(View.GONE );
        }
        if(kid.equals("3")){
            image_e1.setImageDrawable(context.getResources().getDrawable(R.drawable.a02_ec_rc200_1));
            e1.setText("HANDLEBAR");
            se1.setText("Racing profile handlebars");
            de1.setText("The handlebars on the KTM RC 200 offer the perfect riding position for on-road domination. The clip-on bars and strategically positioned switches contribute greatly to the bike's sporty and aggressive riding position, whilst also enhancing control for perfect lines through any corner. ");

            image_e2.setImageDrawable(context.getResources().getDrawable(R.drawable.a02_rc_390windshield));
            e2.setText("WINDSHIELD");
            se2.setText("Advanced aerodynamic protection");
            de2.setText("The advanced design of the KTM RC 200's windshield works with the aerodynamic optimization of the front end to ensure ultimate wind protection for riders who like to open it up on the open road.");

            image_e3.setImageDrawable(context.getResources().getDrawable(R.drawable.a02_ec_rc200_3));
            e3.setText("FOOTPEGS");
            se3.setText("Aircraft-grade aluminum");
            de3.setText("Crafted from high-grade, CNC-machined aircraft aluminum, these lightweight yet super-strong footpegs offer premium purchase for your feet when you need it most. The pegs are fully customizable with three positions available to accommodate for different rider shapes and riding styles.");
            ebodypart_4.setVisibility(View.GONE);

        }
        if(kid.equals("6")){

            image_e1.setImageDrawable(context.getResources().getDrawable(R.drawable.a02_ec_390duke_2));
            image_e2.setImageDrawable(context.getResources().getDrawable(R.drawable.a02_ec_390duke_1));
            e1.setText("SEATS");
            de1.setText("The two-part seat offers first class sport riding ergonomics with perfect support for both rider and pillion.  A seat height of 820 mm enables riders to have a super- secure position, whilst also being high enough for ultimate cornering fun. The Pillion seat has been integrated into the bodywork so as not to compromise the stunt-ready look of the KTM 390 DUKE, whilst also ensuring excellent comfort for the passenger.");
            se1.setText("Seated for speed");


            e2.setText("HANDLEBAR");
            se2.setText("Ultimate control");
            de2.setText("Hi-tech upright handlebars offer KTM 390 DUKE riders a controlled yet aggressive body position, allowing you to dominate in all aspects of riding. From destroying corners to pulling the perfect stoppie, these bars will give you the balance and confidence you need to attack the tarmac like never before. Clutch and brake levers are fully adjustable, allowing you to customize your cockpit to suit your individual riding style.");

            ebodypart_3.setVisibility(View.GONE);
            ebodypart_4.setVisibility(View.GONE );
        }
        if(kid.equals("4")){
            image_e1.setImageDrawable(context.getResources().getDrawable(R.drawable.a02_egc_rc390));
            image_e2.setImageDrawable(context.getResources().getDrawable(R.drawable.a02_seat_rc390));
            image_e3.setImageDrawable(context.getResources().getDrawable(R.drawable.a02_rc_390windshield));
            image_e4.setImageDrawable(context.getResources().getDrawable(R.drawable.a02_footpage_rc390));
            e1.setText("HANDLEBAR");
            se1.setText("Racing profile bars");
            de1.setText("The clip-on bars and strategically positioned switches contribute greatly to the bike’s sporty and aggressive riding position, whilst also enhancing control for perfect lines through any corner.");

            e2.setText("SEATS");
            se2.setText("Supersport ergonomics");
            de2.setText("The two-part seat offers first class, supersport ergonomics with perfect support for both rider and pillion.  A seat height of 820 mm enables riders to have a secure position, whilst also being high enough for circuit-style riding. The Pillion seat has been integrated into the bodywork so as not to compromise the supersport look of the KTM RC 390, whilst also ensuring excellent comfort for the passenger.");

            e3.setText("WINDSHIELD");
            se2.setText("Advanced aerodynamic protection.");
            de3.setText("The advanced design of the KTM RC 390's windshield works with the aerodynamic optimization of the front end to ensure ultimate wind protection for riders who like to open it up on the open road. ");

            e4.setText("FOOTPEGS");
            se4.setText("Race-ready footpegs");
            de4.setText("Crafted from high-grade, CNC-machined aircraft aluminum, these lightweight yet super-strong footpegs offer premium purchase for your feet when you need it most. The pegs are fully customizable with three positions available to accommodate for different rider shapes and riding styles.");
        }

    }
    public static void setTabColor(TabHost tabhost) {

        for (int i = 0; i < tabhost.getTabWidget().getChildCount(); i++) {
            tabhost.getTabWidget().getChildAt(i)
                    .setBackgroundResource(R.color.colorPrimary); // unselected
        }
        tabhost.getTabWidget().setCurrentTab(0);
        tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab())
                .setBackgroundResource(R.color.colorwhite); // selected

    }
    public void fliperimages(int  images){
        ImageView imageView =new ImageView(this);
        imageView.setBackgroundResource(images);
        viewPager.addView(imageView);
        viewPager.setFlipInterval(4000);
        viewPager.setAutoStart(true);
        viewPager.setInAnimation(this,android.R.anim.slide_in_left);
        viewPager.setOutAnimation(this,android.R.anim.slide_out_right);
    }
    /*------------------------ BACK BUTTON ----------------------------*/
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    public void Add_Banner(){
        AdView adView = new AdView(context);
        adView.setAdSize(AdSize.MEDIUM_RECTANGLE);

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("A33EB03807D43E634CB44901B918BB0B")
                .build();


        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });


    }

}
