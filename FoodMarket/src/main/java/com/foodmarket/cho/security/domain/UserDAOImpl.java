package com.foodmarket.cho.security.domain;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {

	private UserDAO mapper;
	
	@Autowired
	public UserDAOImpl(SqlSession sqlSession) {
		mapper = sqlSession.getMapper(UserDAO.class);
	}
	
	   
    @Override
    public int addUser(UserDTO user) {
        return mapper.addUser(user);
    }

    @Override
    public int addAuth(String id, String auth) {
        return mapper.addAuth(id, auth);
    }


    @Override
    public int deleteUser(UserDTO user) {
        return mapper.deleteUser(user);
    }

    @Override
    public int deleteAuth(String id, String auth) {
        return mapper.deleteAuth(id, auth);
    }

    @Override
    public int deleteAuths(String id) {
        return mapper.deleteAuths(id);
    }

    @Override
    public UserDTO findById(String id) {
        return mapper.findById(id);
    }


	@Override
	public List<String> selectAuthorityById(String id) {
		return mapper.selectAuthorityById(id);
	}


	@Override
	public int checkID(String id) {
		return mapper.checkID(id);
	}


	@Override
	public int checkEmail(String email) {
		return mapper.checkEmail(email);
	}


	@Override
	public String selectUserName(String id) {
		return mapper.selectUserName(id);
	}

}













