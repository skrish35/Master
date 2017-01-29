Hadoop

This folder consists of three files :
Map.java
Reduce.java
SortFile.java

Steps to execute the program :

1)After the configuration of Hadoop as explained in the report, we need to generate the input file using gensort using the below format.
  gensort -a 100000 input

2)Then we need to start scripts running on hadoop using :
  start-all.sh

3)Then we need to create a directory in the hadoop file system.
  hdfs dfs -mkdir /abc

4) In the next step we need to put the input file in the hadop file system.
  hdfs dfs -put 'input file path' /abc

5) Then we need to compile the mapper and reducer program.
  javac -cp <jar file paths> Map.java 
  javac -cp <jar file paths> Reduce.java 

6) We need to create a jar file for the Mapper and Reducer class files.
   jar -cvf hadoop.jar HadoopMap.class HadoopRed.class

7) Then we need to compile the main program that is TeraSort.java. After the compilation we will again add all the three class files into a jar file.
   jar -cvf hadoop.jar Map.class Reduce.class SortFile.class

8) We can run the program using the below command:
   hadoop jar <jar file test.jar path > TeraSort /abc/input /abc/output
