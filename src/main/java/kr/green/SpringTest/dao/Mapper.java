package kr.green.SpringTest.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface Mapper {
	public String getId(@Param("id") String id);
	public String getPw(@Param("id") String id);
	public String getEmail(@Param("id") String id);
	public User getUser(@Param("id") String id);
	public void setUser(@Param("id") String id,
			@Param("pw") String pw,@Param("email") String email);
	
}
