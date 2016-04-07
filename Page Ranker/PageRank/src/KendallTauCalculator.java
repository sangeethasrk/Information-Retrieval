import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class KendallTauCalculator 
{
	public static LinkedHashMap<String, ArrayList<String>> input = new LinkedHashMap<String,ArrayList<String>>();	
	public static LinkedHashMap<String,Integer> pageInlinks = new LinkedHashMap<String,Integer>();
	public static LinkedHashMap<String,Integer> pageOutlinks = new LinkedHashMap<String,Integer>();
	public static LinkedHashMap<String,Double> pagePageRank = new LinkedHashMap<String,Double>();
	public static LinkedHashMap<String,Double> newPageRank = new LinkedHashMap<String,Double>();
	public static ArrayList<String> sortPageRank = new ArrayList<String>();
	public static ArrayList<String> sortInlinks = new ArrayList<String>();
	public static ArrayList<String> sinks = new ArrayList<String>();
	public static final double d = 0.85;
	public static int count = 0;
	public static double perplexity = 0.00;
	public static FileWriter output;
	public static int iterations = 0;
	public static String ipFile = "WG2";

	public static void startPageRank() throws IOException{
		for(String page : input.keySet()){
			double pageRank = 1/(double)input.size();
			pagePageRank.put(page,pageRank);
			newPageRank.put(page,0.00);
		}
		perplexity = getPerplexity();
		int counter = 4;
		while(counter > 0){
			double sinkPR = 0.00;
			for(String sink: sinks){
				double d1 = pagePageRank.get(sink);
				sinkPR += d1;
			}
			for(String page: input.keySet()){
				double newPR = (1-d)/(double)input.size();
				newPageRank.put(page,newPR);
				newPR += d*sinkPR/(double)input.size();
				newPageRank.put(page,newPR);
				for(String inlinks: input.get(page)){
					newPR += d*pagePageRank.get(inlinks)/pageOutlinks.get(inlinks);
				}
				newPageRank.put(page,newPR);
			}
			for (String page : input.keySet()){
				pagePageRank.put(page,newPageRank.get(page));
			}
			double newPerplexity = getPerplexity();
			if (Math.abs((newPerplexity - perplexity))<1)
				counter --;
			else counter = 4;
			perplexity = newPerplexity;
		}
	}

	public static void getPagesWithItsOutlinks()
	{
		for (String key : input.keySet())
		{
			pageOutlinks.put(key, 0);
		}		
		for (ArrayList<String> values : input.values())
		{
			for(String element: values){
				pageOutlinks.put(element, (pageOutlinks.get(element) + 1));
			}
		}
	}
	
	public static int getOutlinksCount(String node)
	{
		int count = 0;
		Set<String> x = input.keySet();
		for(String key : x){
			ArrayList<String> values = new ArrayList<String>();
			values = input.get(key);
			if(values.contains(node))
				count++;
		}		
		return count;
	}
	
	public static double getPerplexity() throws IOException
	{
		double entropy = 0.00;
		double newPerplexity;
		for(String file: pagePageRank.keySet()){
			double oldPerplexity = pagePageRank.get(file);
			entropy += oldPerplexity * (Math.log(oldPerplexity)/Math.log(2));
		}
		entropy = 0 - entropy;
		newPerplexity = Math.pow(2,entropy);
		iterations++;
		return newPerplexity;
	}	
	
	public static void getSinks()
	{
		Set<String> s = pageOutlinks.keySet();
		for (String key: s){
			if(pageOutlinks.get(key) == 0)
				sinks.add(key);
		}
	}	
	
	public static void getPageWithInlinkCount()
	{
		for(String key : input.keySet()){
			pageInlinks.put(key,input.get(key).size());
		}
	}
	
	public static void getOutput() throws IOException
	{
		Comparator<Map.Entry<String, Double>> byMapValues = new Comparator<Map.Entry<String, Double>>() {
			@Override
			public int compare(Map.Entry<String, Double> left, Map.Entry<String, Double> right) {
				return left.getValue().compareTo(right.getValue());
			}
		};
		List<Map.Entry<String, Double>> temp = new ArrayList<Map.Entry<String, Double>>();
		temp.addAll(pagePageRank.entrySet());
		Collections.sort(temp,byMapValues);		
		for(int i = temp.size()-1; i >= (temp.size() - 50) ; i--){
			sortPageRank.add(temp.get(i).getKey());
		}		
		Comparator<Map.Entry<String, Integer>> byInlinkCount = new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> left, Map.Entry<String, Integer> right) {
				return left.getValue().compareTo(right.getValue());
			}
		};		
		List<Map.Entry<String, Integer>> temp1 = new ArrayList<Map.Entry<String, Integer>>();
		temp1.addAll(pageInlinks.entrySet());		
		Collections.sort(temp1,byInlinkCount);		
		String fname = "kendalltauvaluesof"+ipFile+".txt";
		output = new FileWriter(fname);
		output.write("Top 50 pages Arranged by PageRank: \n");
		for(int i = temp.size()-1; i >= (temp.size() - 50) ; i--){
			output.write(temp.get(i).getKey() + " " + temp.get(i).getValue() + "\n");
		}		
		output.write("\n \n Top 50 pages Arranged by In-Links: \n");
		for(int i = temp1.size()-1; i >= (temp1.size() - 50) ; i--)
		{
			output.write(temp1.get(i).getKey() + " " + temp1.get(i).getValue() + "\n");
		}		
		for(int i = temp1.size()-1; i >= temp1.size() - 50; i--)
		{
			sortInlinks.add(temp1.get(i).getKey());
		}			
		ArrayList<String> list1 = new ArrayList<String>();
		ArrayList<String> list2 = new ArrayList<String>();		
		for(String element: sortInlinks){
			if(sortPageRank.contains(element))
				list1.add(element);
		}		
		for(String element: sortPageRank){
			if(sortInlinks.contains(element))
				list2.add(element);
		}
		int concordant = 0;
		int discordant = 0;
		int totalCount = 0;		
		for (int i = 0; i<list1.size()-1; i++){	
			String checkString1 = list1.get(i);
			for(int j= i+1; j<list1.size();j++){
				String checkString2 = list2.get(j);
				if(list2.indexOf(checkString2)>list2.indexOf(checkString1))
					concordant++;
				else discordant++;
			}
		}		
		totalCount = list1.size()*(list1.size()-1)/2;
		double coefficient = ((double)concordant - (double)discordant)/(double)totalCount;
		System.out.println("Concordant is : " + concordant + " ; Discordant is :  " + discordant);
		System.out.println("KendallTau Coefficient: " + coefficient);
		output.write("KendallTau Coefficient: " + coefficient);
		output.close();
	}

	public static void main(String[] args) throws IOException {
		String fileName = ipFile + ".txt";
		File file = new File(fileName);
		try {
			Scanner sc = new Scanner(file);
			sc.useDelimiter("\n");
			while (sc.hasNext()){
				String s = sc.next();
				s = s.trim();
				Scanner sc1 = new Scanner(s);
				sc1.useDelimiter(" ");
				ArrayList<String> arr = new ArrayList<String>();
				while(sc1.hasNext()){
					String s1 = sc1.next();
					if (!arr.contains(s1))
						arr.add(s1);
				}
				String key = arr.remove(0);
				input.put(key, arr);
				sc1.close();
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		getPagesWithItsOutlinks(); 
		getPageWithInlinkCount(); 
		getSinks(); 
		startPageRank(); 
		getOutput();

	}

}