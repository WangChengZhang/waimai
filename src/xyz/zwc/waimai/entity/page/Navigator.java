package xyz.zwc.waimai.entity.page;

import xyz.zwc.waimai.entity.User;

public class Navigator {
	private String currentPage;
	private User user;
	public String getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
