package rem.deposit.vo;

public class DepositVO {
	private String tfr_create_at;
	private String tfr_cont;
	private int pay_no;
	private int tfr_mem_no;
	private int tran_type;
	private int price;
	private int mem_bal;
	public String getTfr_create_at() {
		return tfr_create_at;
	}
	public void setTfr_create_at(String tfr_create_at) {
		this.tfr_create_at = tfr_create_at;
	}
	public String getTfr_cont() {
		return tfr_cont;
	}
	public void setTfr_cont(String tfr_cont) {
		this.tfr_cont = tfr_cont;
	}
	public int getPay_no() {
		return pay_no;
	}
	public void setPay_no(int pay_no) {
		this.pay_no = pay_no;
	}
	public int getTfr_mem_no() {
		return tfr_mem_no;
	}
	public void setTfr_mem_no(int tfr_mem_no) {
		this.tfr_mem_no = tfr_mem_no;
	}
	public int getTran_type() {
		return tran_type;
	}
	public void setTran_type(int tran_type) {
		this.tran_type = tran_type;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getMem_bal() {
		return mem_bal;
	}
	public void setMem_bal(int mem_bal) {
		this.mem_bal = mem_bal;
	}
}
