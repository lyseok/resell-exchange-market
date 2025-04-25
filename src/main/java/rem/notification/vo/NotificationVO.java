package rem.notification.vo;

public class NotificationVO {
	private int notif_id;
	private int mem_no;
	private int notif_type;
	private String notif_message;
	private int notif_is_read;
	private String notif_at;
	private String notif_url;
	public int getNotif_id() {
		return notif_id;
	}
	public void setNotif_id(int notif_id) {
		this.notif_id = notif_id;
	}
	public int getMem_no() {
		return mem_no;
	}
	public void setMem_no(int mem_no) {
		this.mem_no = mem_no;
	}
	public int getNotif_type() {
		return notif_type;
	}
	public void setNotif_type(int notif_type) {
		this.notif_type = notif_type;
	}
	public String getNotif_message() {
		return notif_message;
	}
	public void setNotif_message(String notif_message) {
		this.notif_message = notif_message;
	}
	public int getNotif_is_read() {
		return notif_is_read;
	}
	public void setNotif_is_read(int notif_is_read) {
		this.notif_is_read = notif_is_read;
	}
	public String getNotif_at() {
		return notif_at;
	}
	public void setNotif_at(String notif_at) {
		this.notif_at = notif_at;
	}
	public String getNotif_url() {
		return notif_url;
	}
	public void setNotif_url(String notif_url) {
		this.notif_url = notif_url;
	}
}
