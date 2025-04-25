package rem.store.vo;

public class MemberProgressVO {
	private int mem_no;
	private int prog_sell;
	private int prog_review;
	private int prog_viewcount;
	private int prog_ach_badges;
	private int prog_trans_request;
	private int prog_trans_direct;
	private int prog_trans_safe;
	private int prog_question;
	private int prog_reporting;
	private int prog_reported;
	
	/* Getter&Setter */
	public int getMem_no() {
		return mem_no;
	}
	public int getProg_sell() {
		return prog_sell;
	}
	public int getProg_review() {
		return prog_review;
	}
	public int getProg_viewcount() {
		return prog_viewcount;
	}
	public int getProg_ach_badges() {
		return prog_ach_badges;
	}
	public int getProg_trans_request() {
		return prog_trans_request;
	}
	public int getProg_trans_direct() {
		return prog_trans_direct;
	}
	public int getProg_trans_safe() {
		return prog_trans_safe;
	}
	public int getProg_question() {
		return prog_question;
	}
	public int getProg_reporting() {
		return prog_reporting;
	}
	public int getProg_reported() {
		return prog_reported;
	}
	public void setMem_no(int mem_no) {
		this.mem_no = mem_no;
	}
	public void setProg_sell(int prog_sell) {
		this.prog_sell = prog_sell;
	}
	public void setProg_review(int prog_review) {
		this.prog_review = prog_review;
	}
	public void setProg_viewcount(int prog_viewcount) {
		this.prog_viewcount = prog_viewcount;
	}
	public void setProg_ach_badges(int prog_ach_badges) {
		this.prog_ach_badges = prog_ach_badges;
	}
	public void setProg_trans_request(int prog_trans_request) {
		this.prog_trans_request = prog_trans_request;
	}
	public void setProg_trans_direct(int prog_trans_direct) {
		this.prog_trans_direct = prog_trans_direct;
	}
	public void setProg_trans_safe(int prog_trans_safe) {
		this.prog_trans_safe = prog_trans_safe;
	}
	public void setProg_question(int prog_question) {
		this.prog_question = prog_question;
	}
	public void setProg_reporting(int prog_reporting) {
		this.prog_reporting = prog_reporting;
	}
	public void setProg_reported(int prog_reported) {
		this.prog_reported = prog_reported;
	}
	
	/* toString() */
	@Override
	public String toString() {
		return "MemberProgressVO [mem_no=" + mem_no + ", prog_sell=" + prog_sell + ", prog_review=" + prog_review
				+ ", prog_viewcount=" + prog_viewcount + ", prog_ach_badges=" + prog_ach_badges
				+ ", prog_trans_request=" + prog_trans_request + ", prog_trans_direct=" + prog_trans_direct
				+ ", prog_trans_safe=" + prog_trans_safe + ", prog_question=" + prog_question + ", prog_reporting="
				+ prog_reporting + ", prog_reported=" + prog_reported + "]";
	}
	
	
}
