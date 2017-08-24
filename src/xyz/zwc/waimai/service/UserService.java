package xyz.zwc.waimai.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import xyz.zwc.waimai.entity.User;

public interface UserService {
	public User select(User user);

	/**
	 * 注册用户
	 * 
	 * @param user
	 * @return 5代表成功3代表邮箱重复2代表手机号重复1代表用户名重复
	 */
	public int insert(User user);

	/**
	 * 
	 * @param nOrMOrP
	 *            名字，邮箱或者手机
	 * @param password
	 *            密码(原文，无需sha256加密)
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public User login(String nOrMOrP, String password, String type)
			throws NoSuchAlgorithmException, UnsupportedEncodingException;

	public int update(User user);

	public User selectByName(String name);

	public User selectByPhone(String phone);

	public User selectByMail(String mail);
}
