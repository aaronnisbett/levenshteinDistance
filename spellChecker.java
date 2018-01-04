import java.util.*;
import java.io.*;
public class spellChecker{
	   //intialise a two-dimensional array 

 //length of the source string
 //this is the length of the word the user first inputs
 private int lengthOfSource; 
//length of the target string
 //this is the length of the target word the user wants to comapre with the source string 
 private int lengthOfTarget;
 //iterates through the source string(index)
 private  int i;
 //iterates through the target string
 private  int j;
private int distance[][];
 private  char sourcei; // ith character of s
 private  char targetj; // jth character of t
 private  int match; // cost
 
public int LDistance (String source, String target) {


    lengthOfSource = source.length ();
    lengthOfTarget = target.length ();
    //if the length of source string is 0 meaning the string is emtpty
    //the value of the other string is returned (target) is returned as there
    //the distance is the length of the other string
    if (lengthOfSource == 0) {
      return lengthOfTarget;
    }
    if (lengthOfTarget == 0) {
      return lengthOfSource;
    }
    //initialise the array with the length of the target and source string + 1
    //as just length of the string would cause an array out of bounds exception 
    distance = new int[lengthOfSource + 1][lengthOfTarget + 1];

  
    //this initalises the first row from 0 to length on n(length of the source word)
    //for exmample if the word is 3 the row is initalised from 0 - 3 
    for (i = 0; i <= lengthOfSource; i++) {
      distance[i][0] = i;
    }
    //this initalises the first column from 0 to length on n(length of the target word)
    //for exmample if the secound word is 4 the r is initalised from 0 - 4
    for (j = 0; j <= lengthOfTarget; j++) {
      distance[0][j] = j;
    }

    

    //iterate through the source word character by character 
    for (i = 1; i <= lengthOfSource; i++) {
      //returns the character located at i
      sourcei = source.charAt (i - 1);

      
      //returns the character located at j
      for (j = 1; j <= lengthOfTarget; j++) {

        targetj = target.charAt (j - 1);

        //if the character at index i the same as the character at index j they are matching 
        //other wise that location is given a value of 1
        //this creates an matricx of differences
        //cocidering the number of steps required for the source string to become the target string 
        if (sourcei == targetj) {
          match = 0;
        }
        else {
          match = 1;
        }

        
        //the location of i(row) j(column) is the minimum 
        //the minimun is smallest number of changes (additions,deletions) needed 
        //for the source word at that point to be the target string at that point
        //distance[i-1][j]+1,  the cell above + 1
        //distance[i][j-1]+1, cell to the left + 1

        distance[i][j] = Minimum (distance[i-1][j]+1, distance[i][j-1]+1, distance[i-1][j-1] + match);

      }

    }

    //returns the levenshtein distance of the two strings
    //this returns the value that position in the matrix which is the value at the end in the bottom right
    return distance[lengthOfSource][lengthOfTarget];

  }
   private int Minimum (int a, int b, int c) {
  int min;

    min = a;
    //if the value fromm the b cell is smaller than the min
    //min is assigned the value in b
    if (b < min) {
      min = b;
    }
    //if the value fromm the c cell is smaller than the min
    //min is assigned the value in c
    if (c < min) {
      min = c;
    }
    return min;

  }

   public static void append(String s) throws IOException{
     File file = new File("localDictionary.txt");  
     PrintWriter print = new PrintWriter(new FileOutputStream(file,true));
     print.println(s);
     print.close();
}

}