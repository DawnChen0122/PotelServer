package potel.account.vo;

import lombok.Data;

@Data
public class AccountBean {
	private String account;

	public AccountBean(String account) {
		super();
		this.account = account;
	}
}
