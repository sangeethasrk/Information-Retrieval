The zip folder contains the code and text files for assignment 3
1. DocumentFrequencyUnigram.txt, DocumentFrequencyBigram.txt and DocumentFrequency3Gram.txt contain the term. document Id and document frequency of all unigrams, bigrams and trigrams from the inverted list generated respectively
2. Similarly termFrequencyUniGram.txt, termFrequencyBigram.txt and termFrequency3Gram.txt contain the term frequency of each of the ngram terms from the inverted list generated. 
3. Term and Document frequencies.xlsx is an excel sheet containing the a cumulated result of all n grams with term and document frequencies
4. ZipfianCurve.png is the curve for unigram
5. stopListCutOff explanation.txt is the solution to Task 3 part 4.

Procedure to run the code:
1. Submission is the zipped version of the assignment and project which has to be loaded into Eclipse. This can be done by first extracting the .rar file.
2. Eclipse->File->Import->General->Existing Projects into Workspace-> Select root Directory -> Browse
3. Load the unzipped Indexer folder and click on Finish. 
4. JSoup is the JAR used within the Java project to enable HTTP Parsing. Save this JAR file onto your system so that this can be loaded into the project later.
5. You will have a couple of warnings which need to be resolved. Most important ne being 'Adding external Jars -> jsoup.jar'
6. Right click on the project and say Properties. Click on 'Java Build path', and then click on the 'libraries' tab and click on Add External JARs. Browse and find the jsoup-1.8.3.jar file and add it.
7. Next, click on the JRE System Library and say 'Edit', and then selected JavaSE-1.7(jdk1.7.0_71) as the execution environment.
8. This will resolve all the errors and warnings. Click on Run As -> "Java Application". 
7. Run UnfocusedBFS.java as a java application.
8. This generates two text files namely documentfrequency.txt and termfrequency.txt which contain term and document frequency of the inverted list generated. I have initialised the ngram value as 1. Inorder to carry out the procedure for bigrams and trigrams change the Ng variable value in the code respectively to 2 and 3 and see the results in the respective text files. 
