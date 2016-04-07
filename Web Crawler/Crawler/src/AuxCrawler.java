import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AuxCrawler {

	private List<String> URLs = new LinkedList<String>(); 
	public void setURLs(List<String> uRLs) {
		URLs = uRLs;
	}

	private Document webDocument;
	private static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 6.1; WOW64) "
            + "AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
	
	public boolean crawl(String url)
	{
		try
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
			Document webDocument = connection.get();
			this.webDocument = webDocument;
			if(connection.response().statusCode() == 200)
			{
				System.out.println("Visiting Webpage at " +url);
			}
			if(!connection.response().contentType().contains("text/html"))
			{
				System.out.println("\nFound a page that wasn't a HTML document!");
				return false;
			}
			Elements URLsOnPage = webDocument.select("a[href]");
			for (Element link : URLsOnPage)
			{
				this.URLs.add(link.absUrl("href"));
			}
			return true;
		}
		catch(IOException ioe)
		{
			return false;		
		}
	}
	

	public boolean lookUpWord(String keyPhrase)
	{
		if(this.webDocument == null)
		{
			System.out.println("Error found!");
			return false;
		}
		String webText = this.webDocument.body().text();
		return webText.toLowerCase().contains(keyPhrase.toLowerCase());
	}
	
	public List<String> getURLs()
	{
		return this.URLs;			
	}
	
}

