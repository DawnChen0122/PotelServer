package potel.account.dao;

import potel.account.vo.AccountBean;

public interface AccountDao {
	public AccountBean login(String loginid, String password);
}
