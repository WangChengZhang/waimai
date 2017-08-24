package xyz.zwc.waimai.dao;

import org.apache.ibatis.annotations.Param;

import xyz.zwc.waimai.entity.User;

public interface UserMapper extends Mapper<User> {

	public User selectByName(@Param("username") String username);

	public User selectByPhone(@Param("telephone") String telephone);

	public User selectByMail(@Param("email") String email);

	public User loginByName(@Param("username") String username, @Param("password") String password);

	public User loginByPhone(@Param("telephone") String telephone, @Param("password") String password);

	public User loginByMail(@Param("email") String email, @Param("password") String password);

	public User selectLastUser();
}
