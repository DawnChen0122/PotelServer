package potel.myorders.vo;

import lombok.Data;

@Data
public class Order {
	private int orderid;
	private int memberid;
	private String orderstate;
	private int score;
	private String comment;
//	public int getOrderid() {
//		return orderid;
//	}
//	public void setOrderid(int orderid) {
//		this.orderid = orderid;
//	}
//	public int getMemberid() {
//		return memberid;
//	}
//	public void setMemberid(int memberid) {
//		this.memberid = memberid;
//	}
//	public String getOrderstate() {
//		return orderstate;
//	}
//	public void setOrderstate(String orderstate) {
//		this.orderstate = orderstate;
//	}
//	public int getScore() {
//		return score;
//	}
//	public void setScore(int score) {
//		this.score = score;
//	}
//	public String getComment() {
//		return comment;
//	}
//	public void setComment(String comment) {
//		this.comment = comment;
//	}
}
