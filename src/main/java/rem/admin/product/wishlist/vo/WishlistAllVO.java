package rem.admin.product.wishlist.vo;

public class WishlistAllVO {

	private int mem_no;
	private int prod_no;
	private String wish_create_at;
	private String prod_name;
	
	public WishlistAllVO(int mem_no, int prod_no, String wish_create_at, String prod_name) {
		
		this.mem_no = mem_no;
		this.prod_no = prod_no;
		this.wish_create_at = wish_create_at;
		this.prod_name = prod_name;
	}

	public int getMem_no() {
		return mem_no;
	}
	public void setMem_no(int mem_no) {
		this.mem_no = mem_no;
	}
	public int getProd_no() {
		return prod_no;
	}
	public void setProd_no(int prod_no) {
		this.prod_no = prod_no;
	}
	public String getWish_create_at() {
		return wish_create_at;
	}
	public void setWish_create_at(String wish_create_at) {
		this.wish_create_at = wish_create_at;
	}
	public String getProd_name() {
		return prod_name;
	}

	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
}
