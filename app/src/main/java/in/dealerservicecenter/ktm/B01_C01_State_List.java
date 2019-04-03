package in.dealerservicecenter.ktm;

public class B01_C01_State_List {

    private  String statename,sid;

    public B01_C01_State_List(String statename , String Sid) {
        this.statename = statename; this.sid = Sid;
    }

    public String getStatename() {
        return statename;
    }

    public String getSid() {
        return sid;
    }
}
