package rem.product.vo;

public class NewViewVO {
	private int prod_no;
	private String prod_name;
	private int prod_price;
	private int view_no;
	private String file_path;
	public int getProd_no() {
		return prod_no;
	}
	public void setProd_no(int prod_no) {
		this.prod_no = prod_no;
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
	public int getView_no() {
		return view_no;
	}
	public void setView_no(int view_no) {
		this.view_no = view_no;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
}
