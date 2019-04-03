package in.dealerservicecenter.ktm;

public class B02_State_List {

    private  String statename,sid;

    public B02_State_List(String statename , String Sid) {
        this.statename = statename; this.sid = Sid;
    }

    public String getStatename() {
        return statename;
    }

    public String getSid() {
        return sid;
    }
}
