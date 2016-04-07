import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FocusedCrawler {

	final int MAX_DEPTH = 5;
	final int MAX_PAGES = 1000;
    private LinkedHashMap<String, Integer> pagesVisited = new LinkedHashMap<String, Integer>();
 
    public ArrayList<String> getKeys()
    {
    	ArrayList<String> pages = new ArrayList<String>();
    	for(Map.Entry<String, Integer> entry : pagesVisited.entrySet())
    	{
    		String key = entry.getKey();
    		pages.add(key);	
    	}
    	return pages;
    }
     
	public LinkedHashMap<String, Integer> getPagesVisited() {
		return pagesVisited;
	}

	public void setPagesVisited(LinkedHashMap<String, Integer> pagesVisited) {
		this.pagesVisited = pagesVisited;
	}

	private ArrayList<LandingPage> pagesToVisit = new ArrayList<LandingPage>();

	public ArrayList<LandingPage> getPagesToVisit() {
		return pagesToVisit;
	}

	public void setPagesToVisit(ArrayList<LandingPage> pagesToVisit) {
		this.pagesToVisit = pagesToVisit;
	}
	
	private LinkedHashMap<String, Integer> keyPhrasePages = new LinkedHashMap<String, Integer>();

	public LinkedHashMap<String, Integer> getKeyPhrasePages() {
		return keyPhrasePages;
	}
	
	public ArrayList<String> getPhraseKeys()
    {
    	ArrayList<String> phraseKeys = new ArrayList<String>();
    	for(Map.Entry<String, Integer> entry : keyPhrasePages.entrySet())
    	{
    		String key = entry.getKey();
    		phraseKeys.add(key);	
    	}
    	return phraseKeys;
    }

	public void setKeyPhrasePages(LinkedHashMap<String, Integer> keyPhrasePages) {
		this.keyPhrasePages = keyPhrasePages;
	}
	
	public int getLastID()
	{
		int id=0;
		for(String s : this.pagesVisited.keySet())
		{
		  id = this.pagesVisited.get(s);	
		}
		return id;
	}
 
	public void lookUpForKeyPhrase(String seed, String keyPhrase)
	{
			int i=0;
			while(this.keyPhrasePages.size() < MAX_PAGES 
					 && getLastID() <= MAX_DEPTH)
		  {
			  
			  String presentUrl;
			  AuxCrawler aux = new AuxCrawler();
			  if(this.pagesToVisit.isEmpty())
			  {
				  presentUrl = seed;
				  this.pagesVisited.put(presentUrl,1);
				  this.keyPhrasePages.put(presentUrl, 1);
			  }
			  
			  else
			  {
				  presentUrl = this.fetchUrl();
			  }
			 
			  aux.crawl(presentUrl);
			
			  boolean result = aux.lookUpWord(keyPhrase);
			  
			  if(result)
			  {
				  LinkedList<String> loLinks = new LinkedList<String>();
				  loLinks = (LinkedList<String>)aux.getURLs();
				  for(String l : loLinks)
				  {
					  LandingPage lp = new LandingPage();
					  lp.setUrl(l);
					  lp.setDepth(this.pagesVisited.get(presentUrl) + 1);
					  this.pagesToVisit.add(lp);
				  }
				  if(presentUrl!=seed)
				  {
				  this.keyPhrasePages.put(presentUrl, this.pagesVisited.get(presentUrl));
				  }
			  }
			  
			 }
			  
		  
		  System.out.println("\nDone! Visited "+this.pagesVisited.size()+ "webpages");
		
	}
	
	private boolean isvalidURL(String presentUrl) {
	
		if(presentUrl.contains("#") | presentUrl.indexOf(":", 6) > 5 |
				 presentUrl.startsWith("http://en.wikipedia.org/wiki/Main_Page") |
				!presentUrl.startsWith("https://en.wikipedia.org/wiki/"))
				
		{
		return false;
		}
		else return true;
	}
	
	private String fetchUrl() {
		// TODO Auto-generated method stub
		String url;
		int depth;
		do
		{
			LandingPage lp = this.pagesToVisit.remove(0);
			url = lp.getUrl();
			depth = lp.getDepth();
			
		}while(this.pagesVisited.containsKey(url) || !(isvalidURL(url)));
		this.pagesVisited.put(url,depth);
		return url;
	}
			
}


