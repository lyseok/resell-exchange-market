package rem.transaction.vo;


public class TransactionVO {
	private int txn_no;
	private int prod_no;
	private int mem_no;
	private String txn_status;
	private String txn_create_at;
	private String track_completed;
	
	
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
	public String getTxn_status() {
		return txn_status;
	}
	public void setTxn_status(String txn_status) {
		this.txn_status = txn_status;
	}
	public String getTxn_create_at() {
		return txn_create_at;
	}
	public void setTxn_create_at(String txn_create_at) {
		this.txn_create_at = txn_create_at;
	}
	public String getTrack_completed() {
		return track_completed;
	}
	public void setTrack_completed(String track_completed) {
		this.track_completed = track_completed;
	}
	
	
	
	
	
}
