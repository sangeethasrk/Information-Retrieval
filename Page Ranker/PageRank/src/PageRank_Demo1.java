import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class PageRank_Demo1 
{
	public static double SHANNON_ENTROPY = 0;
	public final static double TELEPORTATION_FACTOR = 0.85;
	
	public static void main(String[] args) 
	{	
		LinkedHashMap<String,PageRank_Info> pgs = new LinkedHashMap<String,PageRank_Info> ();
		ArrayList<String> sinkpages = new ArrayList<String>();
		LinkedHashMap<String,Integer> outlinks = new LinkedHashMap<String,Integer>();		
		calOutlinksFromIpFile("testgraph.txt",pgs,outlinks); 		
		getsinks(sinkpages,pgs,outlinks); 		
		calPageRank(pgs,sinkpages,outlinks); 
	}
	
	private static void calOutlinksFromIpFile(String p,LinkedHashMap<String, PageRank_Info> pages, LinkedHashMap<String, Integer> outlinks) {
		BufferedReader bufferRdr = null;
		try {

			String m;
			bufferRdr = new BufferedReader(new FileReader(p));
			while ((m = bufferRdr.readLine()) != null) {				
				String pgEle[] = m.split(" ");				
				PageRank_Info pageInfo = new PageRank_Info();				
				LinkedHashSet<String> inlinks = new LinkedHashSet<String>();
				if (outlinks.get(pgEle[0]) == null)
					outlinks.put(pgEle[0], 0);
				for(int j=1; j< pgEle.length ; j++)
				{
					int oldsize = inlinks.size();
					inlinks.add(pgEle[j]);
					int newSize = inlinks.size();
					if (outlinks.get(pgEle[j]) == null)
					outlinks.put(pgEle[j], 1);
					else
					{
						if(oldsize != newSize)
						outlinks.put(pgEle[j],outlinks.get(pgEle[j]) + 1);
					}					
				}
				pageInfo.setInlinkPages(inlinks);
				pages.put(pgEle[0], pageInfo);
			}				
		}
		 catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferRdr != null)bufferRdr.close();
			} 
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	private static void getsinks(ArrayList<String> sinks, LinkedHashMap<String, PageRank_Info> pgs, LinkedHashMap<String, Integer> outlinks) 
	{
		for(Map.Entry<String,PageRank_Info> entry : pgs.entrySet() )
		{
			String pgName = entry.getKey();
			if (outlinks.get(pgName) == 0)
			{
				sinks.add(pgName);
			}
		}
	}

	private static void calPageRank(LinkedHashMap<String, PageRank_Info> pgs, ArrayList<String> sinks, LinkedHashMap<String, Integer> outlinks) 
	{
		double sinkPR;
		int count = 1;
		for(Map.Entry<String, PageRank_Info> entry : pgs.entrySet())
		{
			PageRank_Info pagerankInfo = entry.getValue();
			pagerankInfo.setPageRankval((double)1/pgs.size());
			
		}
		BufferedWriter output = null;
        try 
        {
		 File file = new File("testGraphResults.txt");
         output = new BufferedWriter(new FileWriter(file));
         String text = "";		
		while(count <= 100)
		{		
			sinkPR = 0;
			for (String p : sinks)
				sinkPR+= pgs.get(p).getPageRankvalue();			
			for (Map.Entry<String, PageRank_Info> entry : pgs.entrySet())
			{
				PageRank_Info p = entry.getValue();
				double newPageRankvalue;
				newPageRankvalue = (double) (1 - TELEPORTATION_FACTOR)/pgs.size();
				newPageRankvalue += (double) (TELEPORTATION_FACTOR * sinkPR/pgs.size());				
				for (String q : p.getInlinkPages())
				{
					newPageRankvalue += (TELEPORTATION_FACTOR * pgs.get(q).getPageRankvalue()/getoutlinks(q, outlinks));
				}				
				p.setPageRankval(newPageRankvalue);
			}			
			if(count == 1 || count == 10 || count == 100 )
			{  
	            text = "Page Rank val for iteration "+count+"\n";
	            output.write(text);	            
	            for(Map.Entry<String, PageRank_Info> entry : pgs.entrySet())
	    		{
	    			String pgName = entry.getKey();
	    			PageRank_Info pginfo = entry.getValue();
	    		    text = pgName + ":";
	    			text += " Page Rank val: "+pginfo.getPageRankvalue() + "\n";	    			
	    			output.write(text);	    			 
	    		}
			}			
			count ++;
		}
        }
        catch ( IOException e ) {
            e.printStackTrace();
        } finally {
            if ( output != null )
				try {
					output.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        }
		System.out.println("Total no of iterations:"+ count);		
	}

	private static int getoutlinks(String m, LinkedHashMap<String, Integer> outlinks) 
	{
		return outlinks.get(m);
	}
}

