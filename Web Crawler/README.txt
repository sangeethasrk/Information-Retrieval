The zip also contains the following files including the current README.txt file
'Crawler' : A folder containing the source code
'Task 1 Solution.txt' : 1000 links of unfocused crawling for task 1
'Task 2A Solution.txt' : 1000 links of focused breadth first crawling for task 2A
'Task 2B Solution.txt' : 1000 links of focused depth first crawling for task 2B
'Task 2C Solution.txt' : a brief explanation and comparitive study of the two approaches

Procedure to run the code:
1. JSoup is the JAR used within the Java project to enable HTTP Parsing. Save this JAR file onto your system so that this can be loaded into the project later.
2. Submission is the zipped version of the assignment and project which has to be loaded into Eclipse. This can be done by first extracting the .rar file.
3. Eclipse->File->Import->General->Existing Projects into Workspace-> Select root Directory -> Browse
4. Load the unzipped Crawler folder and click on Finish. 
5. You will have a couple of warnings which need to be resolved. Most important ne being 'Adding external Jars -> jsoup.jar'
6. Right click on the project and say Properties. Click on 'Java Build path', and then click on the 'libraries' tab and click on Add External JARs. Browse and find the jsoup-1.8.3.jar file and add it.
7. Next, click on the JRE System Library and say 'Edit', and then selected JavaSE-1.7(jdk1.7.0_71) as the execution environment.
8. This will resolve all the errors and warnings. Click on Run As -> "Java Application". 

References and citings:
To implement the web crawler I used the source code from the following online sources and manipulated them to fit the requirements. I also have included links to a few tutorial pdfs and videos which I used to learn to implement the web crawler in Java:
https://www.youtube.com/watch?v=TIdF6_MvHzM
http://www.netinstructions.com/how-to-make-a-simple-web-crawler-in-java/
http://media.techtarget.com/searchDomino/downloads/29713C06.pdf
http://www.programcreek.com/2012/12/how-to-make-a-web-crawler-using-java/
http://stackoverflow.com/questions/19759713/is-this-web-crawler-doing-a-breadth-first-search-or-a-depth-first-search

Used the following links for resolution of issues i faced while implementing the code:
http://stackoverflow.com/questions/19632560/installing-a-jar-file
http://stackoverflow.com/questions/4853718/missing-classpath-file-in-eclipse-project
http://stackoverflow.com/questions/31724241/eclipse-does-not-recognize-jsoup-jar-file-in-dynamic-web-project
http://stackoverflow.com/questions/31729714/just-installed-windows-10-and-eclipse-no-more-starts