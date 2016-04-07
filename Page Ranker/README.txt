The zip also contains the following files including the current README.txt file
'PageRank': The file containing the source code
'1A-WG1.txt': The graph obtained after unfocused crawling 
'3B.txt': Solution to task 3b
'3C.txt': Solution to task 3c
'kendalltauvaluesofWG1.txt': The file containing the kendall tau value for graph WG1
'kendalltauvaluesofWG2.txt': The file containing the kendall tau value for graph WG2
'list_of_pages_sorted_by_rankWG1.txt': Page rank of pages from input file WG1
'list_of_pages_sorted_by_rankWG2.txt': Page rank of pages from input file WG2
'log-log_curve.xlsx' : a log-log-scale curve for number of web pages to number of inlinks count. Th graph shows that it follows power law distribution. 
'perplexityWG1.txt': The perplexity values of the WG1 file
'perplexityWG2.txt': The perplexity values of the WG2 file
'Answer4.txt': Solution to point 4 requested in the assignment
'WG2.txt': the WG2 inlinks file

Procedure to run the code:
1. Submission is the zipped version of the assignment and project which has to be loaded into Eclipse. This can be done by first extracting the .rar file.
2. Eclipse->File->Import->General->Existing Projects into Workspace-> Select root Directory -> Browse
3. Load the unzipped PageRank folder and click on Finish. 
4. To run page rank for WG2.txt run the file PageRank_Demo2 as a java application. 
5. To run page rank for WG1.txt run the file PageRank_Demo3 as a java application. 
6. To run the smaller 6 node test graph run the PageRank_Demo1 as a java application.
7. To calculate Kenall Tau rank coefficient for WG2.txt, run the program "KendallTauCalculator.java" as a java application. If you want the values for WG1.txt replace all the instances of WG2 in "KendallTauCalculator.java" with WG1 and run again. 

Note: For the purpose of comparison I did change the teleportation value from 0.85 to 0.95 and generated the same additional files. They can be identified with the tag 'higherTeleportVal' at the end of the file name. These files are inside the src folder of 'PageRank'. 