package in.dealerservicecenter.ktm;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class D01_KtmWallpaper_Adapter extends RecyclerView.Adapter<D01_KtmWallpaper_Adapter.Viewholder>  {

    private List<D01_KtmWallpaper_List> d01_ktmWallpaper_lists;
    private Context context;


    public D01_KtmWallpaper_Adapter(Context context, List<D01_KtmWallpaper_List> d01_ktmWallpaper_lists) {
        this.d01_ktmWallpaper_lists = d01_ktmWallpaper_lists;
        this.context = context;


    }
    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.d01_wallpaper_list,parent,false);
        return  new Viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int i) {
        final D01_KtmWallpaper_List d01_ktmWallpaper_list = d01_ktmWallpaper_lists.get(i);

        try{
            viewholder.PImage.setImageDrawable(context.getResources().getDrawable(Integer.parseInt(String.valueOf(d01_ktmWallpaper_list.getPimg()))));


            viewholder.PImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);



                        String fnm = d01_ktmWallpaper_list.getPName(); //  this is image file name
                        String PACKAGE_NAME = context.getPackageName();
                        int imgId = context.getResources().getIdentifier(PACKAGE_NAME+":drawable/"+fnm , null, null);

                        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),imgId);

                        File file = Environment.getExternalStorageDirectory();
                        File dir = new File(file + "/KTM WallPaper/");
                        dir.mkdirs();

                        File file1 = new File(dir, d01_ktmWallpaper_list.getPName()+".jpg");
                        Toast.makeText(context, "Downloading...", Toast.LENGTH_SHORT).show();

                        OutputStream outputStream = null;
                        try {
                            outputStream = new FileOutputStream(file1);
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                            outputStream.flush();
                            outputStream.close();
                            Toast.makeText(context, "Download SuccessFully....\nCheck KTM Wallpaper Folder", Toast.LENGTH_SHORT).show();

                        } catch (java.io.IOException e) {
                            e.printStackTrace();
                        }


                    } catch (Exception ex) {
                        Log.e("log", ex.getMessage());
                    }
                }
            });
        }catch (Exception e){
            Toast.makeText(context, "Error:-"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public int getItemCount() {
        return d01_ktmWallpaper_lists.size();
    }

    public  class  Viewholder extends  RecyclerView.ViewHolder{

        public TextView Txtname;
        ImageView PImage;
        RelativeLayout KtmRv;



        public Viewholder(@NonNull View itemView) {
            super(itemView);
            //Txtname = (TextView) itemView.findViewById(R.id.textViewTitle);
            PImage = (ImageView) itemView.findViewById(R.id.imageView);
           // KtmRv = (RelativeLayout) itemView.findViewById(R.id.ktmlv);




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
