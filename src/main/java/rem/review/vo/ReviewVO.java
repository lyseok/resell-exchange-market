package rem.review.vo;

public class ReviewVO {
	private int txn_no;
	private int review_rating;
	private String review_cont;
	private String review_timestamp;
	private int review_status;
	public int getTxn_no() {
		return txn_no;
	}
	public int getReview_rating() {
		return review_rating;
	}
	public String getReview_cont() {
		return review_cont;
	}
	public String getReview_timestamp() {
		return review_timestamp;
	}
	public int getReview_status() {
		return review_status;
	}
	public void setTxn_no(int txn_no) {
		this.txn_no = txn_no;
	}
	public void setReview_rating(int review_rating) {
		this.review_rating = review_rating;
	}
	public void setReview_cont(String review_cont) {
		this.review_cont = review_cont;
	}
	public void setReview_timestamp(String review_timestamp) {
		this.review_timestamp = review_timestamp;
	}
	public void setReview_status(int review_status) {
		this.review_status = review_status;
	}
	@Override
	public String toString() {
		return "ReviewVO [txn_no=" + txn_no + ", review_rating=" + review_rating + ", review_cont=" + review_cont
				+ ", review_timestamp=" + review_timestamp + ", review_status=" + review_status + "]";
	}
	
	
	
}
