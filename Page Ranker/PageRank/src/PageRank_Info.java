import java.util.LinkedHashSet;

public class PageRank_Info {
	LinkedHashSet<String> inlinkPages;
	double pageRankvalue;
	
	public PageRank_Info() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LinkedHashSet<String> getInlinkPages() {
		return inlinkPages;
	}

	public void setInlinkPages(LinkedHashSet<String> inlinkPages) {
		this.inlinkPages = inlinkPages;
	}

	public double getPageRankvalue() {
		return pageRankvalue;
	}

	public void setPageRankval(double pageRankvalue) {
		this.pageRankvalue = pageRankvalue;
	}
}