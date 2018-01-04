import java.util.*;
import java.io.*;
public class spellCheckerTest{
	public static void main(String[] args) throws IOException{
		//variable i and j which store the index locations of the user file and the 
			int i;
			int j;
			int accepting = 0;

			//Create an object called query
			spellChecker query = new spellChecker();
			//Create an new scanner object 'scan' to read the user inputs 
			Scanner scan = new Scanner(System.in);

			//local dictionary
			//this is the dictionary which contains the words not in the main dictionary that the user has entered 
			//new words are added to this dictionary 
			Scanner localDictionary = new Scanner(new File("localDictionary.txt"));
			//Create an array list to store the words within the local dictionary 
			ArrayList<String> local = new ArrayList<String>();
			//the while loop iterates through each word in the local dictionary file
			//adding them to the array list 
			while(localDictionary.hasNext()){
				//adds the contents of the local dictionary to the arraylis 
				local.add(localDictionary.next());
			}
		
		//scanner which produces values scanned from the specified file.
		Scanner dictionary = new Scanner(new File("dictionary.txt"));
		//declare an arraylsit of type string to store the contents of the dictionary file
		ArrayList<String> TheDictionary = new ArrayList<String>();
		while(dictionary.hasNext()){
			//adds the words from the dictionary in to the array list
			TheDictionary.add(dictionary.next());
		}
		//while loop which allows the user to retry the try methond 
	while (accepting == 0){
		try{
		//method which outputs a string prompting the user to enter the name of file they want to check
		System.out.println("Enter the name of the file to spell Check");
		//A variable of type string, which stores the input of the user
		//Stores the name of the file entered by the user
		String filetocheck;
		filetocheck = scan.next();
		
		//scanner which produces values scanned from the specified file from the suer
		Scanner userFile = new Scanner(new File(filetocheck));
		//Creates an arraylist of type string to store the contents of the file specified by the user
		ArrayList<String> user = new ArrayList<String>();
		//adds the contents of the specified to the array list 
	
	while(userFile.hasNext()){
			user.add(userFile.next());
		
		}
		//iterates through the file specifed by the user
			for(j = 0; j < user.size();j++){
				//checks the dictionary and the local dictioanry to see if the contents of the Users file does not match  
				if(!TheDictionary.contains(user.get(j)) && !local.contains(user.get(j))){
					//method which alerts the user of a misspelled word 
					System.out.println(user.get(j) + "is not spelled correctly");
					//Method which asks the user if they would like to add the misspelled word to the local dictionary 
					System.out.println("would you like to add the word to the local dictionary y or n");
					//String which stores the inputs from the scanner object scan
					String ans = scan.next();
						//checks if the users input matches "y"
						if(ans.equals("y")){
							//calls the append method which adds the word to the local dictionary
							query.append(user.get(j));
						}
						//checks if the users input mathes "n"
						else if(ans.equals("n")){
							//method prompting the user to choose a replacment or enter thier own word to replace the incorrect word
							System.out.println("please choose a replacement from suggested word below or create your own");
							//iterates through the dictionary
							for(i = 0; i < TheDictionary.size();i++){
								//if the levenshtein distance between the incorrect and the current word in the dictionary
								//is less or equal to 2 print the word as an suggestion
								if(query.LDistance(user.get(j),TheDictionary.get(i))<=2){
									System.out.println(TheDictionary.get(i));
								}
							}
						
									
				ans = scan.next();
				//the word in array list to the word the user has entered 'j' referes to the location of the word
				user.set(j,ans);
				//if the answer that the user provides is not in the dictionary 
				//that user is asked if they want to add it to the local dictionary 
				if(!TheDictionary.contains(ans)){
					System.out.println("would you like to add " + ans + "to the local dictionary(y or n)");
					//String for storing the answer for the question above 
					String add = scan.next();
					if(add.equals("y")){
					query.append(ans);
					}
				}

			}

			
			}
		}
		//Method which alerts the user that the fille has been saved and asks the user for a new file to write the spell checked 
		System.out.println("****************"+ 
		"\nSpell Check is Complete" + 
		"\nWhere would you like to save the corrected file?" + 
		"\nEnter a new file OR create a new file(.txt)" +
		"\n****************");

		//varible which stores the name of file typed by the user
		String filetowrite = scan.next();
		//overwrites an existing file
		//creates and new file
		PrintWriter print = new PrintWriter(new FileOutputStream(filetowrite,true));
     	PrintStream print1 = new PrintStream(filetowrite);
     	for(j=0 ; j<user.size(); j++)
		{
		  print1.print(user.get(j) + " ");
		}
		//Close the printstream method
		print1.close();
		System.out.println("New File has been written");
		accepting = 1;
	}
		//catches java.io.FileNotFoundException 
		//this occurs when the user enters the file to scan but the file can not be find
		catch(FileNotFoundException exception){
			System.out.println("****** the file hasnt been found ******");
				}

	}	
}
}