package potel.account.service;

import potel.account.vo.AccountBean;

public interface AccountService {
	public AccountBean login(String loginid, String password);
}
