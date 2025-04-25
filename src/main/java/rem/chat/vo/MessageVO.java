package rem.chat.vo;

public class MessageVO {
	private int msg_no;
	private int chat_room_no;
	private int mem_no;
	private String msg_cont;
	private String msg_create_at;
	private int msg_imgck;
	private String file_path;
	private String mem_alias;
	
	public String getMem_alias() {
		return mem_alias;
	}
	public void setMem_alias(String mem_alias) {
		this.mem_alias = mem_alias;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public int getMsg_no() {
		return msg_no;
	}
	public void setMsg_no(int msg_no) {
		this.msg_no = msg_no;
	}
	public int getChat_room_no() {
		return chat_room_no;
	}
	public void setChat_room_no(int chat_room_no) {
		this.chat_room_no = chat_room_no;
	}
	public int getMem_no() {
		return mem_no;
	}
	public void setMem_no(int mem_no) {
		this.mem_no = mem_no;
	}
	public String getMsg_cont() {
		return msg_cont;
	}
	public void setMsg_cont(String msg_cont) {
		this.msg_cont = msg_cont;
	}
	public String getMsg_create_at() {
		return msg_create_at;
	}
	public void setMsg_create_at(String msg_create_at) {
		this.msg_create_at = msg_create_at;
	}
	public int getMsg_imgck() {
		return msg_imgck;
	}
	public void setMsg_imgck(int msg_imgck) {
		this.msg_imgck = msg_imgck;
	}
	
	
}
