package rem.transaction.vo;

public class ShippingVO {
	private int ship_id;
	private int txn_no;
	private String ship_porv;
	private String track_num;
	
	private String prod_name;
	private int prod_price;
	private int mem_no;
	
	
	public int getShip_id() {
		return ship_id;
	}
	public void setShip_id(int ship_id) {
		this.ship_id = ship_id;
	}
	public int getTxn_no() {
		return txn_no;
	}
	public void setTxn_no(int txn_no) {
		this.txn_no = txn_no;
	}
	public String getShip_porv() {
		return ship_porv;
	}
	public void setShip_porv(String ship_porv) {
		this.ship_porv = ship_porv;
	}
	public String getTrack_num() {
		return track_num;
	}
	public void setTrack_num(String track_num) {
		this.track_num = track_num;
	}
	
	
	
	
	
}
