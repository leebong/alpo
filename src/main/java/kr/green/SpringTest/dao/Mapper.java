package kr.green.SpringTest.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.green.SpringTest.dto.LoginDTO;

public interface Mapper {
	public String getId(@Param("id") String id);
	public String getPw(@Param("id") String id);
	public String getEmail(@Param("id") String id);
	public User getUser(@Param("id") String id);
	public void setUser(@Param("id") String id,@Param("pw") String pw,
						@Param("name") String name, @Param("email") String email);
	public User login(LoginDTO dto);

	
}
