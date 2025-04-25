package rem.product.vo;

public class ProductVO {
	
	private int prod_no;
	private int mem_no;
	private int cate_main_id;
	private int cate_sub_id;
	private String prod_name;
	private int prod_condition;
	private String prod_content;
	private int prod_price;
	private String prod_at;
	private String prod_newing;
	private int prod_deleted;
	private int price_offer;
	private int prod_tr_status;
	private int prod_tr_approch;
	private int prod_view;
	private int wish_count;
	
	
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
	public int getCate_sub_id() {
		return cate_sub_id;
	}
	public void setCate_sub_id(int cate_sub_id) {
		this.cate_sub_id = cate_sub_id;
	}
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	public int getProd_condition() {
		return prod_condition;
	}
	public void setProd_condition(int prod_condition) {
		this.prod_condition = prod_condition;
	}
	public String getProd_content() {
		return prod_content;
	}
	public void setProd_content(String prod_content) {
		this.prod_content = prod_content;
	}
	public int getProd_price() {
		return prod_price;
	}
	public void setProd_price(int prod_price) {
		this.prod_price = prod_price;
	}
	public String getProd_at() {
		return prod_at;
	}
	public void setProd_at(String prod_at) {
		this.prod_at = prod_at;
	}
	public String getProd_newing() {
		return prod_newing;
	}
	public void setProd_newing(String prod_newing) {
		this.prod_newing = prod_newing;
	}
	public int getProd_deleted() {
		return prod_deleted;
	}
	public void setProd_deleted(int prod_deleted) {
		this.prod_deleted = prod_deleted;
	}
	public int getPrice_offer() {
		return price_offer;
	}
	public void setPrice_offer(int price_offer) {
		this.price_offer = price_offer;
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
	public int getProd_view() {
		return prod_view;
	}
	public void setProd_view(int prod_view) {
		this.prod_view = prod_view;
	}
	public int getWish_count() {
		return wish_count;
	}
	public void setWish_count(int wish_count) {
		this.wish_count = wish_count;
	}
	public int getCate_main_id() {
		return cate_main_id;
	}
	public void setCate_main_id(int cate_main_id) {
		this.cate_main_id = cate_main_id;
	}
	@Override
	public String toString() {
		return "ProductVO [prod_no=" + prod_no + ", mem_no=" + mem_no + ", cate_sub_id=" + cate_sub_id + ", prod_name="
				+ prod_name + ", prod_condition=" + prod_condition + ", prod_content=" + prod_content + ", prod_price="
				+ prod_price + ", prod_at=" + prod_at + ", prod_newing=" + prod_newing + ", prod_deleted="
				+ prod_deleted + ", price_offer=" + price_offer + ", prod_tr_status=" + prod_tr_status
				+ ", prod_tr_approch=" + prod_tr_approch + ", prod_view=" + prod_view + "]";
	}
	
	

}
