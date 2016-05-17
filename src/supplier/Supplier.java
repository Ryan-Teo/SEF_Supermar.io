package supplier;

public class Supplier {
	
	private String sID, sName, sPhone, sEmail;
	
	public Supplier(String sID, String sName, String sPhone, String sEmail){
		this.sID = sID;
		this.sName = sName;
		this.sPhone = sPhone;
		this.sEmail = sEmail;
	}

	public String getsID() {
		return sID;
	}

	public String getsName() {
		return sName;
	}

	public String getsPhone() {
		return sPhone;
	}

	public String getsEmail() {
		return sEmail;
	}

}
