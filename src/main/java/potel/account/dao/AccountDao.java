package potel.account.dao;

import java.util.List;

import potel.account.vo.Member;

public interface AccountDao {
	
	List<Member> selectAll() ;
	
	List<Member> selectAccount();
	
	boolean insertMember(Member member);
}
