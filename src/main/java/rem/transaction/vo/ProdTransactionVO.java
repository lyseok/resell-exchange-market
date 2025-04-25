package rem.transaction.vo;

public class ProdTransactionVO {
	private int txn_no;
	private int prod_no;
	private int mem_no;
	private int txn_status;
	private String txn_create_at;
	private String prod_name;
	private int prod_price;
	private int prod_tr_status;
	private int prod_tr_approch;
	
	
	public int getTxn_no() {
		return txn_no;
	}
	public void setTxn_no(int txn_no) {
		this.txn_no = txn_no;
	}
	public int getProd_no() {
		return prod_no;
	}
	public void setProd_no(int prod_no) {
		this.prod_no = prod_no;
	}
	public int getMem_no() {
		return mem_no;
	}
	public void setMem_no(int mem_no) {
		this.mem_no = mem_no;
	}
	public int getTxn_status() {
		return txn_status;
	}
	public void setTxn_status(int txn_status) {
		this.txn_status = txn_status;
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
	public int getProd_tr_status() {
		return prod_tr_status;
	}
	public void setProd_tr_status(int prod_tr_status) {
		this.prod_tr_status = prod_tr_status;
	}
	public int getProd_tr_approch() {
		return prod_tr_approch;
	}
	public void setProd_tr_approch(int prod_tr_approch) {
		this.prod_tr_approch = prod_tr_approch;
	}
}
