package in.dealerservicecenter.ktm;

public class A03_KtmWallpaper_List {

    private  String PName;
    private  int pimg,pid;


    public String getPName() {
        return PName;
    }

    public int getPid() {
        return pid;
    }

    public int getPimg() {
        return pimg;
    }



    public A03_KtmWallpaper_List(int pid, String pName, int pimg) {

        this.PName = pName;
        this.pimg = pimg;
        this.pid = pid;
    }
}
