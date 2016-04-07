/**
 * @author sange_000
 *
 */
import java.io.File;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class UnfocusedBFS 
{
	static String seed_url = "https://en.wikipedia.org/wiki/Sustainable_energy";
	public static final int MAX_PAGES_TO_SEARCH = 1000;
	public static int PAGES_SEARCHED = 0;
	public static int depth_counter = 1;
	public static String keyWord = "solar"; 
	public static int Ng = 1;
	public static TreeMap<String, LinkedHashMap<String, Integer>>  tokens = new TreeMap<>();
	public static HashMap<String, Integer> docID_df = new HashMap<String, Integer>();
	public static Set<String> pagesVisited = new HashSet<String>();
	public static Queue<String> pagesToVisit = new LinkedList<String>();
	public static ArrayList<String>   output = new ArrayList<String>();
	
	public static void search(String url) throws IOException
	{
		output.clear();
        int counter = 1;
		pagesVisited.add(url);
		PAGES_SEARCHED = 1;
		output.add(url);
		
		enQueue(url);
		while(!pagesToVisit.isEmpty() && PAGES_SEARCHED < 2)
		{
			String getPage = pagesToVisit.remove();
			output.add(getPage);
			PAGES_SEARCHED++;
			pagesVisited.add(getPage);
		
			enQueue(getPage);
		}
		for(String s : output)
		{
			System.out.println(counter + s);
			counter++;
		}
	}

	public static void enQueue(String url)
	{
		try
		{
			Document doc = Jsoup.connect(url).get();
			
			Elements urls = doc.select("a[href]");

			for (Element u : urls)
			{
				String nexturl = u.attr("abs:href");
				nexturl = nexturl.split("#")[0];
				if ((!pagesVisited.contains(nexturl)) &&
						( (nexturl.lastIndexOf(":") < 6)) && 
						(!nexturl.matches("https://en.wikipedia.org/wiki/Main_Page.*$"))
						&&
						nexturl.startsWith("https://en.wikipedia.org/wiki")  
						)
					pagesToVisit.add(nexturl);				
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void parseUrl(ArrayList<String> link) throws IOException
	{
		for(String s: link){
		org.jsoup.nodes.Document doc = Jsoup.connect(s).timeout(20000).get();
		doc.select("sup").remove();
		File file = new File("/Users/sange_000/workspace/Indexer/" + returnFileName(s));
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		String filename = returnFileName(s);
		org.jsoup.select.Elements contents = doc.select("title,p,h");

		for(Element p: contents)
		{			
			 String tmp = p.text().toLowerCase();
			 tmp = tmp.replaceAll("[\\Q][(){};!^\"?\\E]", "");			
			 tmp = tmp.replaceAll("/", " ");
			 tmp = tmp.replaceAll("(?<![0-9])[\\,\\.]", "");
			 tmp = tmp.replaceAll("[\\,\\.](?![0-9])", "");
			 bw.write(tmp + "\n");
			 nGramFunc(filename, tmp);
		}
				
		fw.close();
		         
		fw.close();
		}
		
		@SuppressWarnings("resource")
		FileWriter fw = new FileWriter("documentfrequency.txt");
		for(String word : tokens.keySet())
		{    		  
	    	fw.write(word + ": " + tokens.get(word) + "\n");
			System.out.println(word + ": " + tokens.get(word));
		}
	
		for(String word : tokens.keySet()){
			int frequency =0;
			LinkedHashMap<String, Integer> list = new LinkedHashMap<>();
			list = tokens.get(word);
			for(String docid: list.keySet()){
				frequency = frequency + list.get(docid);
				docID_df.put(word, frequency);
			}			
		}
		@SuppressWarnings("resource")
		FileWriter tw = new FileWriter("termfrequency.txt");
		for(String word : docID_df.keySet())
		{
			tw.write(word + "\t" + docID_df.get(word) + "\n");
			System.out.println(word + "\t" + docID_df.get(word));
		}
	}
	
	public static void nGramFunc(String fn, String tmp)
	{
		String[] temp = tmp.split(" ");
		for(int i = 0; i<temp.length-Ng+1; i++)
		{
            String str = "";
            int start = i;
            int end = i+Ng;
            
            for(int k = start; k<end;k++)
            {
            	str = str + " " + temp[k];
            }		
				
			if(tokens.containsKey(str))
			{
				LinkedHashMap<String, Integer> fileWithTerms = new LinkedHashMap<>();
				fileWithTerms = tokens.get(str);
				if(fileWithTerms.containsKey(fn))
				{
					fileWithTerms.put(fn, fileWithTerms.get(fn) + 1);					
				}
				else
				{
					fileWithTerms.put(fn,  1);						
				}
				tokens.put(str, fileWithTerms);
			}
			else
			{
				LinkedHashMap<String, Integer> fileWithTerms = new LinkedHashMap<>();
				fileWithTerms.put(fn, 1);
				tokens.put(str, fileWithTerms);
			}			
		}
	}
	
	public static String returnFileName(String url)
	{
			int pos = url.lastIndexOf("/");
			String x =url.substring(pos+1 , url.length()).toLowerCase();
			x = x.replaceAll("[\\Q][_-\\E]", "");		
			return x+".txt"; 			
	}	

	public static void printPages()
	{
		try
		{
			  int counter = 0;
			  Iterator<String> itr = output.iterator();
	    	  FileWriter fw = new FileWriter("FocusedBFS1.txt");
	    	 
	    	  while(itr.hasNext())
	    	  {	    		  
	    		  fw.write(itr.next() + "\n");
	    		  counter++;
	    	  }
	    	  System.out.println("number of urls : " + counter);
	    	  fw.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();			
		}
	}
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException
	{
		Scanner s = new Scanner(System.in);
    	
    	System.out.print("Enter the seed document link:");
    	seed_url = s.nextLine(); 
		
		 
    	
		search(seed_url);
		parseUrl(output);
		printPages();
		
	}	
}
