package org.youngjin.net.admin;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.youngjin.net.Container;
import org.youngjin.net.login.User;

@Service
public class AdminService {
	
	@Resource
	private AdminDao adminDao;

	public List<User> getAllUserList() {
		List<User> userList = adminDao.getAllUserList();
		
		return userList;
	}

	public List<Container> getContainerList() {
		return adminDao.getContainerList();
	}
	
	
}
