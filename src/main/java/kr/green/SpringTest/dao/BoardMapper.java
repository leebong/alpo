package kr.green.SpringTest.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.green.SpringTest.page.Page;

public interface BoardMapper {
	public void setBoard(@Param("title") String title,
			@Param("contents") String contents,@Param("author") String author);
	
	public List<Board> getBoards();
	
	public Board getBoardById(@Param("number") int number);
	
	public void modifyBoardById(@Param("title") String title,
			@Param("contents") String contents
			, @Param("number") int number);
	public List<Board> getPageBoards(Page p);
	public Integer getBoardsCount();
	
	public List<Board> getPageBoardsByTitle(@Param("p")Page p, @Param("search")String search);
	public Integer getBoardsCountByTitle(@Param("search")String search);
	
	public List<Board> getPageBoardsByContents(@Param("p")Page p, @Param("search")String search);
	public Integer getBoardsCountByContents(@Param("search")String search);
	
	public List<Board> getPageBoardsByAuthor(@Param("p")Page p, @Param("search")String search);
	public Integer getBoardsCountByAuthor(@Param("search")String search);
	
}


