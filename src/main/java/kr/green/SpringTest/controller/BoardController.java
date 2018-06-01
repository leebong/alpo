package kr.green.SpringTest.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.green.SpringTest.dao.Board;
import kr.green.SpringTest.dao.BoardMapper;
import kr.green.SpringTest.dao.User;
import kr.green.SpringTest.page.Page;
import kr.green.SpringTest.page.PageMaker;

@Controller
@RequestMapping(value="/board/*")
public class BoardController {
	@Autowired
	BoardMapper boardMapper;
	
	@RequestMapping(value="/write", method= RequestMethod.GET)
	public String boardWriteGet(Model model, HttpServletRequest request) {
		//글쓰기에 로그인 아이디를 작성자로 표기하기
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		model.addAttribute("author", user.getName());
		
		return "/WEB-INF/views/board/write.jsp";
	}
	
	@RequestMapping(value="/write", method= RequestMethod.POST)
	public String boardWritePOST(Model model, HttpServletRequest request) {
			
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		String author;

		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		author = user.getName();
		System.out.println(author);
		
		boardMapper.setBoard(title, contents, author);
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="/list", method= RequestMethod.GET)
	public String boardListGet(Model model, Integer page, HttpServletRequest r) {
		//ArrayList<Board> list = (ArrayList)boardMapper.getBoards();
		//로그인 아이디 콘솔 확인
		
		HttpSession session = r.getSession();
		User user = (User)session.getAttribute("user");
		System.out.println(user.getId());
		
		
		if(page == null)
			page = 1;
		Page p = new Page(page,3);
		ArrayList<Board> list;
		int totalCount = 0;
		String search = r.getParameter("search");
		Integer searchType;
		String tmp = r.getParameter("searchType");
		//if(tmp == null || tmp.length() == 0) page error일때 "searchType=" -> "searchType=0"
		if(tmp == null)
			searchType = 0;
		else
			searchType = Integer.parseInt(tmp);
		
		//검색어가 없을 때
		
		if(search == null || search.length() == 0 || searchType == null) {
			
			list = (ArrayList)boardMapper.getPageBoards(p);
			
			totalCount = boardMapper.getBoardsCount();
		}
		//검색어가 있을 때
		/**
		 * Controller에서 각 sql문을 골라서 처리하는 방법
		 * 2.1. BoardMapper.java에서 메서드 선언
		 * 	2.1.1 내용을 검색을 위한 메서드
		 * 	2.1.1.1 getBoardsCountByContents
		 * 	2.1.1.2 getPageBoardsByContents
		 * 	2.1.2 저자 검색을 위한 메서드
		 * 	2.1.2.1 getBoardscountByAuthor
		 * 	2.1.2.2 getPageBoardsByAuthor
		 */
		else {
			
			if(searchType == 0) {
				list = (ArrayList)boardMapper.getPageBoardsByTitle(p, "%"+search+"%");
				totalCount = boardMapper.getBoardsCountByTitle("%"+search+"%");
				model.addAttribute("searchType", 0);
			}
			else if(searchType == 1) {
				list = (ArrayList)boardMapper.getPageBoardsByContents(p, "%"+search+"%");
				totalCount = boardMapper.getBoardsCountByContents("%"+search+"%");
				model.addAttribute("searchType", 1);
			}
			else {
				list = (ArrayList)boardMapper.getPageBoardsByAuthor(p, "%"+search+"%");
				totalCount = boardMapper.getBoardsCountByAuthor("%"+search+"%");
				model.addAttribute("searchType", 2);
			}
		}	
		
		PageMaker pm = new PageMaker();
		pm.setPage(p);
		pm.setTotalCount(totalCount);
		model.addAttribute("list", list);
		model.addAttribute("pm", pm);
		model.addAttribute("search", search);
		model.addAttribute("searchType", searchType);
		System.out.println("Get 검색 : " + search);
		return "/WEB-INF/views/board/list.jsp";
	}
	
	
	@RequestMapping(value="/list", method= RequestMethod.POST)
	public String boardListPost(Model model, Integer page, HttpServletRequest r) {
		/*페이지의 값이 없을 때 1페이지로 설정*/
		if(page == null)
			page = 1;
		/*현재페이지와 한페이지에 뿌려줄 갯수를 설정*/
		Page p = new Page(page,3);
		ArrayList<Board> list;
		int totalCount = 0;
		/*HttpServletRequest를 이용하여 jsp의 값을 가져옴*/
		String search = r.getParameter("search");
		Integer searchType;
		String tmp = r.getParameter("searchType");
		if(tmp == null)
			searchType = 0;
		else
			searchType = Integer.parseInt(tmp);
		System.out.println(searchType);
		//검색어가 없으면 모든 게시글 가져옴
				if(search == null || search.length() == 0 || searchType == null) {
					list = (ArrayList)boardMapper.getPageBoards(p);
					totalCount = boardMapper.getBoardsCount();
				}
				//검색어가 있을 때
				/**
				 * Controller에서 각 sql문을 골라서 처리하는 방법
				 * 2.1. BoardMapper.java에서 메서드 선언
				 * 	2.1.1 내용을 검색을 위한 메서드
				 * 	2.1.1.1 getBoardsCountByContents
				 * 	2.1.1.2 getPageBoardsByContents
				 * 	2.1.2 저자 검색을 위한 메서드
				 * 	2.1.2.1 getBoardscountByAuthor
				 * 	2.1.2.2 getPageBoardsByAuthor
				 */
				else {
					
					if(searchType == 0) {
						list = (ArrayList)boardMapper.getPageBoardsByTitle(p, "%"+search+"%");
						totalCount = boardMapper.getBoardsCountByTitle("%"+search+"%");
						model.addAttribute("searchType", 0);
					}
					else if(searchType == 1) {
						list = (ArrayList)boardMapper.getPageBoardsByContents(p, "%"+search+"%");
						totalCount = boardMapper.getBoardsCountByContents("%"+search+"%");
						model.addAttribute("searchType", 1);
					}
					else {
						list = (ArrayList)boardMapper.getPageBoardsByAuthor(p, "%"+search+"%");
						totalCount = boardMapper.getBoardsCountByAuthor("%"+search+"%");
						model.addAttribute("searchType", 2);
					}
				}		
		PageMaker pm = new PageMaker();
		pm.setPage(p);
		pm.setTotalCount(totalCount);
		model.addAttribute("list", list);
		model.addAttribute("pm", pm);
		model.addAttribute("search", search);
		System.out.println("Post 검색 : " + search);
		return "/WEB-INF/views/board/list.jsp";
	}
	
	
	
	@RequestMapping(value="/detail", method= RequestMethod.GET)
	public String boardDetailGet(Model model, int number) {
		Board board = boardMapper.getBoardById(number);
		model.addAttribute("board", board);
		return "/WEB-INF/views/board/detail.jsp";
	}
	
	
	@RequestMapping(value="/detail", method= RequestMethod.POST)
	public String boardDetailGet(Model model, HttpServletRequest r) {
		int number = Integer.parseInt(r.getParameter("number"));
		System.out.println(number);
		String title = r.getParameter("title");
		String contents = r.getParameter("contents");
		String author = r.getParameter("author");
		
		boardMapper.modifyBoardById(title, contents, number);
		return "redirect:/board/list"; //URL을 넘겨준다
	}
		
	
	
	
}


