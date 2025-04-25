package rem.admin.transaction.safetransaction.vo;

public class SafeTXNVO {

	private int safe_no;
	
	private int prod_price; 
	private int safe_status;
	private int prod_no;
	private String prod_name;
	private int sell;
	private int buy;
	private String safe_at;
	
	
	
	
	public int getProd_no() {
		return prod_no;
	}
	public void setProd_no(int prod_no) {
		this.prod_no = prod_no;
	}
	public int getProd_price() {
		return prod_price;
	}
	public void setProd_price(int prod_price) {
		this.prod_price = prod_price;
	}
	public int getSafe_no() {
		return safe_no;
	}
	public void setSafe_no(int safe_no) {
		this.safe_no = safe_no;
	}

	public int getSafe_status() {
		return safe_status;
	}
	public void setSafe_status(int safe_status) {
		this.safe_status = safe_status;
	}
	public String getSafe_at() {
		return safe_at;
	}
	public void setSafe_at(String safe_at) {
		this.safe_at = safe_at;
	}
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	public int getSell() {
		return sell;
	}
	public void setSell(int sell) {
		this.sell = sell;
	}
	public int getBuy() {
		return buy;
	}
	public void setBuy(int buy) {
		this.buy = buy;
	}
	
	
	
}
