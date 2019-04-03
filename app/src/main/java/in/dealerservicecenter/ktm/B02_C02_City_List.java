package in.dealerservicecenter.ktm;

public class B02_C02_City_List {

    private  String cityname,cid;

    public B02_C02_City_List(String cityname , String cid) {
        this.cityname = cityname; this.cid = cid;
    }

    public String getCityname() {
        return cityname;
    }

    public String getcid() {
        return cid;
    }
}
