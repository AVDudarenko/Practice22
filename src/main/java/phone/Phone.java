package phone;

public class Phone {

	private int id;
	private String phoneBrand;
	private String phoneModel;
	private String macAddress;

	public Phone(int id, String phoneBrand, String phoneModel, String macAddress) {
		this.id = id;
		this.phoneBrand = phoneBrand;
		this.phoneModel = phoneModel;
		this.macAddress = macAddress;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhoneBrand() {
		return phoneBrand;
	}

	public void setPhoneBrand(String phoneBrand) {
		this.phoneBrand = phoneBrand;
	}

	public String getPhoneModel() {
		return phoneModel;
	}

	public void setPhoneModel(String phoneModel) {
		this.phoneModel = phoneModel;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
