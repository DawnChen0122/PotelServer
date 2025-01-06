package potel.account.service.impl;

import javax.naming.NamingException;

import potel.account.dao.AccountDao;
import potel.account.dao.impl.AccountDaoImpl;
import potel.account.service.AccountService;
import potel.account.vo.AccountBean;

public class AccountServiceImpl implements AccountService{
	private AccountDao ad;
	
	public AccountServiceImpl() throws NamingException {
		ad = new AccountDaoImpl();
	}
	
	@Override
	public AccountBean login(String loginid, String password) {
		if(loginid==null) {
			System.out.println("未帶loginid");
			return null;
		}else if(password==null) {
			System.out.println("未帶password");
			return null;
		}
		
		return ad.login(loginid, password);
	}

}
