package in.dealerservicecenter.ktm;

public class B03_C03_DealerServiceCenterDetail_List {

    private  String bname,bslug,pincode,badd,bcontact,bemail,btitle,bcontact_person;

    public B03_C03_DealerServiceCenterDetail_List(String bname , String bslug, String badd , String pincode , String  bcontact , String bemail , String btitle , String contact_person) {
        this.bname = bname;
        this.bslug = bslug;
        this.badd = badd;
        this.pincode = pincode;
        this.bcontact = bcontact;
        this.bemail = bemail;
        this.btitle = btitle;
        this.bcontact_person = contact_person;
    }

    public String getBadd() {
        return badd;
    }

    public String getBcontact() {
        return bcontact;
    }

    public String getBemail() {
        return bemail;
    }

    public String getBtitle() {
        return btitle;
    }

    public String getBname() {
        return bname;
    }

    public String getBslug() {
        return bslug;
    }

    public String getBcontact_person() {
        return bcontact_person;
    }

    public String getPincode() {
        return pincode;
    }

}
