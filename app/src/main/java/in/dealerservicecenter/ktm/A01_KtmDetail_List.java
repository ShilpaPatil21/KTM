package in.dealerservicecenter.ktm;

public class A01_KtmDetail_List {

    private  String PName,Pprice,Prate,Pdesc;
    private  int pimg,pid;


    public String getPName() {
        return PName;
    }

    public String getPprice() {
        return Pprice;
    }

    public String getPrate() {
        return Prate;
    }

    public String getPdesc() {
        return Pdesc;
    }

    public int getPimg() {
        return pimg;
    }

    public int getPid() {
        return pid;
    }

    public A01_KtmDetail_List(int pid, String pName, String pPrice, String pRate, String pSdesc, int pimg) {
        this.Pdesc = pSdesc;
        this.PName = pName;
        this.Pprice = pPrice;
        this.Prate = pRate;
        this.pimg = pimg;
        this.pid = pid;
    }
}
