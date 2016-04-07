

public class CrawlerEntryPoint {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	String seed = "http://en.wikipedia.org/wiki/Hugh_of_Saint-Cher";
	String keyPhrase = "concordance";
    FocusedCrawler crawler = new FocusedCrawler();
    crawler.lookUpForKeyPhrase(seed, "");
 
    System.out.println("PAGES CRAWLED WITHOUT KEY PHRASE :");
    
    for(String page : crawler.getKeys())
    {
    	System.out.println(page);
    } 
    System.out.println("\n");
    FocusedCrawler crawlerForKeyPhrase = new FocusedCrawler();
    crawlerForKeyPhrase.lookUpForKeyPhrase(seed, keyPhrase);
    
    System.out.println("RELEVANT PAGES CRAWLED");
    for(String page : crawlerForKeyPhrase.getPhraseKeys())
    {
    	System.out.println(page);
    }
	
   
	
	}
}
