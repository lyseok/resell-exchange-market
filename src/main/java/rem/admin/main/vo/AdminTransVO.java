package rem.admin.main.vo;

public class AdminTransVO {
	private int txn_no;
	private String buyer;
	private String txn_create_at;
	private String prod_name;
	private int prod_price;
	private String seller;
	public int getTxn_no() {
		return txn_no;
	}
	public void setTxn_no(int txn_no) {
		this.txn_no = txn_no;
	}
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	public String getTxn_create_at() {
		return txn_create_at;
	}
	public void setTxn_create_at(String txn_create_at) {
		this.txn_create_at = txn_create_at;
	}
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	public int getProd_price() {
		return prod_price;
	}
	public void setProd_price(int prod_price) {
		this.prod_price = prod_price;
	}
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
}
