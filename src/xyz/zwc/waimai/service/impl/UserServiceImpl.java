package xyz.zwc.waimai.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.zwc.waimai.dao.UserMapper;
import xyz.zwc.waimai.entity.User;
import xyz.zwc.waimai.service.UserService;
import xyz.zwc.waimai.util.Encode;
import xyz.zwc.waimai.util.Text;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public User select(User user) {
		// TODO Auto-generated method stub
		return userMapper.select(user);
	}

	@Override
	public User login(String nOrMOrP, String password, String type)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		String encodeStr = Encode.sha256(password);
		if (type.equals("1")) {
			return userMapper.loginByPhone(nOrMOrP, encodeStr);
		} else if (type.equals("2")) {
			return userMapper.loginByName(nOrMOrP, encodeStr);
		} else if (type.equals("3")) {
			return userMapper.loginByMail(nOrMOrP, encodeStr);
		} else {
			return null;
		}
	}

	@Override
	public int insert(User user) {
		if (userMapper.selectByPhone(user.getUqTelephone()) != null) {
			return 2;
		} else if (user.getName() != null && !user.getName().isEmpty()
				&& userMapper.selectByName(user.getName()) != null) {
			return 1;
		} else if (user.getUqEmail() != null && !user.getUqEmail().isEmpty()
				&& userMapper.selectByMail(user.getUqEmail()) != null) {
			return 3;
		} else {
			User lastuser = userMapper.selectLastUser();
			if (lastuser != null) {
				int flag = 0;
				user.setUserid(lastuser.getUserid() + 1);
				if (user.getName() == null || user.getName().isEmpty()) {
					do {
						user.setName("user_" + Text.getRandomString(7));
					} while (userMapper.selectByName(user.getName()) != null);
				}
				flag = userMapper.insert(user);
				while (flag == 0) {
					user.setUserid(user.getUserid() + 1);
					flag = userMapper.insert(user);
				}
				return 5;
			} else {
				user.setUserid(1);
				if (user.getName() == null || user.getName().isEmpty()) {
					user.setName("user_" + Text.getRandomString(7));
				}
				user.setFkFilesIcon(1);
				userMapper.insert(user);
				return 5;
			}
		}
	}

	@Override
	public int update(User user) {
		// TODO Auto-generated method stub
		return userMapper.update(user);
	}

	@Override
	public User selectByName(String name) {
		// TODO Auto-generated method stub
		return userMapper.selectByName(name);
	}

	@Override
	public User selectByPhone(String phone) {
		// TODO Auto-generated method stub
		return userMapper.selectByPhone(phone);
	}

	@Override
	public User selectByMail(String mail) {
		// TODO Auto-generated method stub
		return userMapper.selectByMail(mail);
	}

}
