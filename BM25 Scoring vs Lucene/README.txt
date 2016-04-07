Lucene Document Ranking and Bm25 Document Scoring are the two excel sheets containing the 8 tables for the 4 queries given.
BM25Scoring is the folder with BM25 indexing and scoring code
Lucenecode is the folder with the Lucene code. 

To Run BM25Scoring
1. Submission is the zipped version of the assignment and project which has to be loaded into Eclipse. This can be done by first extracting the .rar file.
2. Eclipse->File->Import->General->Existing Projects into Workspace-> Select root Directory -> Browse
3. Load the unzipped BM25Scoring folder and click on Finish. 
4. Run the Project as a Java Application. 
5. Results.eval contain query results. Index.out contains the inverted index. 


To run Lucene code 
1. Submission is the zipped version of the assignment and project which has to be loaded into Eclipse. This can be done by first extracting the .rar file.
2. Eclipse->File->Import->General->Existing Projects into Workspace-> Select root Directory -> Browse
3. Load the unzipped Lucenecode folder and click on Finish. 
4. I have included 3 Jars within the unzipped Lucenecode folder. Save this JAR file onto your system so that this can be loaded into the project later.
5. You will have a couple of warnings which need to be resolved. Most important ne being 'Adding external Jars'
6. Right click on the project and say Properties. Click on 'Java Build path', and then click on the 'libraries' tab and click on Add External JARs. Browse and find the lucene-analyzers-common-4.7.2.jar, lucene-core-4.7.2.jar, lucene-queryparser-4.7.2.jar files and add them.
7. Next, click on the JRE System Library and say 'Edit', and then selected JavaSE-1.7(jdk1.7.0_71) as the execution environment.
8. This will resolve all the errors and warnings. Click on Run As -> "Java Application". 
7. Run HW3.java as a java application.
8. You will be prompted to "Enter the FULL path where the index will be created:" I have included a folder called IR within Lucenecode. Add the path of this folder 
9. Now you will be prompted to "Enter the FULL path to add into the index (q=quit):" I have included another set of raw files in a folder called RawDocs, add the path of this folder. You will be prompted to add files till u press q to quit. 
10. You will now be asked to enter the query. From the given 4 queries, type any this gives u a result of 100 documents. Rerun steps 7-10 for all other queries. 