package rem.chat.vo;

public class ChatMemberVO {
	private int chat_user; 
    private int mem_no;
    private String mem_name;
    private String mem_alias;
    private String mem_tel;
    private int msg_no;
    private int mem_status;
    private String mem_join_at;
	public int getChat_user() {
		return chat_user;
	}
	public void setChat_user(int chat_user) {
		this.chat_user = chat_user;
	}
	public int getMem_no() {
		return mem_no;
	}
	public void setMem_no(int mem_no) {
		this.mem_no = mem_no;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_alias() {
		return mem_alias;
	}
	public void setMem_alias(String mem_alias) {
		this.mem_alias = mem_alias;
	}
	public String getMem_tel() {
		return mem_tel;
	}
	public void setMem_tel(String mem_tel) {
		this.mem_tel = mem_tel;
	}
	public int getMsg_no() {
		return msg_no;
	}
	public void setMsg_no(int msg_no) {
		this.msg_no = msg_no;
	}
	public int getMem_status() {
		return mem_status;
	}
	public void setMem_status(int mem_status) {
		this.mem_status = mem_status;
	}
	public String getMem_join_at() {
		return mem_join_at;
	}
	public void setMem_join_at(String mem_join_at) {
		this.mem_join_at = mem_join_at;
	}
    
    

}
