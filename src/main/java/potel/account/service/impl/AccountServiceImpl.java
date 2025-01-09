package potel.account.service.impl;

import javax.naming.NamingException;

import potel.account.dao.impl.AccountDaoImpl;
import potel.account.service.AccountService;
import potel.account.vo.Member;

public class AccountServiceImpl implements AccountService {
	private AccountDaoImpl accountDao;

	public AccountServiceImpl() throws NamingException {
		this.accountDao = new AccountDaoImpl();
	}

	@Override
	public boolean addMember(Member member) {
		return accountDao.insertMember(member);
	}
}
