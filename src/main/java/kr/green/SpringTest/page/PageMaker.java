package kr.green.SpringTest.page;

public class PageMaker {
	private int totalCount; //해당 table 전체 컬럼 갯수 검색(select)
	private int startPage;  //해당 페이지네이션의 시작페이지(1,11,21,...)
	private int endPage;	//해당 페이지네이션의 마지막페이지(10,20,30,3,4,...)
	private boolean prev;	//이전
	private boolean next;	//이후
	
	//getter & setter 생성	
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcDate();
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}
	
	public void setPage(Page page) {
		this.page = page;
	}
	
	//생성자 생성
	private Page page;
	private int displayPageNum = 5; //한 페이지 네이션에서 나타내는 페이지 수
	
	
	
	
	public Page getPage() {
		return page;
	}

	//메서드 생성
	public void calcDate() {
		endPage = (int)(Math.ceil(page.getPage()/(double)displayPageNum)
				*displayPageNum);
		startPage = (endPage-displayPageNum)+1;
		int tmpEndPage = (int)(Math.ceil(totalCount/(double)page.getPerPageNum()));
		if(endPage > tmpEndPage)
			endPage = tmpEndPage;
		prev = startPage == 1 ? false: true;
		
		//한페이지씩 다음으로 넘긴다
		//next = page.getPage() * page.getPerPageNum() >= totalCount ? false: true; 
		// 10개 페이지씩 다음으로 넘긴다(네이버기준)
		next = endPage * page.getPerPageNum() >= totalCount ? false: true;
	
	}
	
	//prev & next 메서드
	public String query(int page) {
		return "/board/list?page="+page;
		
	}
	
	
	
	
}
