package kr.green.SpringTest.page;

public class Page {
	private int page; //현재 페이지 번호
	private int perPageNum; //한 페이지에 게시 글 최대 갯수
	
	//getter & setter 생성
	public int getPage() {
		return page;
	}
	public void setPage(int page) { //page 0과 음수 예외처리
		if(page <= 0)
			this.page = 1;
		else
			this.page = page;
	}
	public int getPerPageNum() {
		return perPageNum;
	}
	public void setPerPageNum(int perPageNum) { //perPageNum 게시글에 대한 50까지 제한
		if(perPageNum <= 0)
			this.perPageNum = 10;
		else if(perPageNum > 50)
			this.perPageNum = 50;
		else
			this.perPageNum = perPageNum;
	}
	
	//생성자 생성
	public Page() {
		page = 1;
		perPageNum = 10;
		
	}
	
	public Page(int page, int perPageNum) {
		this.setPage(page); //setter에서 예외 처리 하였기 때문에 별도 예외처리 하지 않아도 된다
		this.setPerPageNum(perPageNum);		
	}

	//메서드 생성
	public int getPageStart() {
		return (page-1)*perPageNum;
	}
	
	

}
