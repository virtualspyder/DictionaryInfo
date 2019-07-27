package DictionaryInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

public enum dictionary{

  Adjective_01("Adjective", "Adjective is a word that describes a person or thing, for example big, red and clever in a big house, red wine and a clever idea.", "noun"),
  Interjection_01("Interjection", "Interjection is a short sound, word or phrase spoken suddenly to express an emotion. Oh!, Look out! and Ow! are interjections.", "noun"),
  Conjunction_01("Conjunction", "Conjunction is a word that joins words, phrases or sentences, for example 'and', 'but', 'or'.", "noun"),
  Bookable_01("Bookable", "Can be ordered in advance.", "adjective"),
  Verb_01("Verb", "Verb is a word or group of words that expresses an action (such as eat), an event (such as happen) or a state (such as exist).", "noun"),
  Book_01("Book", "A set of pages.", "noun"),  
  Book_02("Book", "A written work published in printed or electronic form.", "noun"),  
  Book_03("Book", "To arrange for someone to have a seat on a plane.", "verb"),  
  Book_04("Book", "To arrange something on a particular date.", "verb"),    
  CSC210_01("CSC210", "Comfortable with Objects and Classes.", "adjective"),
  CSC210_02("CSC210", "Ready for CSC 220.", "adjective"),
  CSC210_03("CSC210", "Intro to Java.", "noun"),
  CSC210_04("CSC210", "To learn Java.", "verb"),
  CSC220_01("CSC220", "Ready to create complex data structures.", "adjective"),
  CSC220_02("CSC220", "Data Structures.", "noun"),
  CSC220_03("CSC220", "To create data structures.", "verb"),
  CSC340_01("CSC340", "= C++ version of CSC210 + CSC220 + more.", "adjective"),
  CSC340_02("CSC340", "A CS upper division course.", "noun"),
  CSC340_03("CSC340", "Many hours outside of class.", "noun"),
  CSC340_04("CSC340", "Programming Methodology.", "noun"),
  Placeholder_01("Placeholder", " To be updated...", "adjective"),
  Placeholder_02("Placeholder", " To be updated...", "adjective"),
  Placeholder_03("Placeholder", " To be updated...", "adverb"),
  Placeholder_04("Placeholder", " To be updated...", "conjunction"),
  Placeholder_05("Placeholder", " To be updated...", "interjection"),
  Placeholder_06("Placeholder", " To be updated...", "noun"),
  Placeholder_07("Placeholder", " To be updated...", "noun"),
  Placeholder_08("Placeholder", " To be updated...", "noun"),
  Placeholder_09("Placeholder", " To be updated...", "preposition"),
  Placeholder_10("Placeholder", " To be updated...", "pronoun"),
  Placeholder_11("Placeholder", " To be updated...", "verb");
  
  private String word;
  private String definition;
  private String type;
  
  dictionary(){
    this("CSC000","Empty Course", "Unknown");
  }
  
  dictionary(String t, String d, String g){
    this.word = t;
    this.definition = d;
    this.type = g;
  }
  
  public String getTitle() { return this.word; }
  public String getDefinition() { return this.definition; }
  public String getType() { return this.type; }  
  
  public static void main(String[] args)
  { 	  
    Map<String, List<dictionaryInfo>> bucket = new HashMap<String, List<dictionaryInfo>>(); // Store dictionary items.
    Map<String, String> bucket_names = new HashMap<String, String>(); // Stores the original name of each title.
    
    Scanner input = new Scanner(System.in);
   
	 System.out.println("! Loading data...");
	 
    for(dictionary entry : dictionary.values()){
    	  
      String breakDefinition = (entry.getDefinition()).trim(); 
      String breakType = (entry.getType()).trim();
      
      String title = entry.getTitle().toLowerCase();
      List<dictionaryInfo> tempCourse = bucket.get(title);
      
      if(tempCourse == null) // If a title does not exist, add a new one.
      {
    	  bucket.put(title, new ArrayList<dictionaryInfo>());
          bucket_names.put(title, entry.getTitle());    

          tempCourse = bucket.get(title);  
      }
      
      tempCourse.add(new dictionaryInfo(breakDefinition, breakType));
    }
   
	System.out.println("! Loading completed...\n");
	
    String output = ""; 
    String tab = "    ";
    System.out.println("-----DICTIONARY 340 JAVA-----\n");
        
    //User Interaction Starts Here
    do
    {
    	// If there is program output, print it and clear it.
    	if(output.length() > 0)
    	{
    		System.out.print("|\n" + output + "|\n");
    		output = "";
    	}
    	    	
      System.out.print("Search: ");
     
      //Input
      String[] inputArray = (input.nextLine()).split("[ ]"); // Split user input by space " "
     
      for(int i = 0; i < inputArray.length; i++){
    	  inputArray[i] = inputArray[i].toLowerCase().trim();    	  
      }
      
      String searchWord = "";
      String searchType = ""; // "distinct" is a special case. 
      String searchType2 = ""; // "distinct" is a special case.
      
      if(inputArray.length >= 3) {
    	  searchType2 = inputArray[2];    	  
      }
      if(inputArray.length >= 2) {
    	  searchType = inputArray[1];    	  
      }
      searchWord = inputArray[0];
      
      // Check if a user wants to exit the program.
      if(inputArray.length == 1 && searchWord.compareTo("!q") == 0) {
          System.out.println("\n-----Thank You-----");
          break;    	  
      }
      
      if(searchType.compareTo("oops") == 0){
    	  output += tab + "<2nd argument must be a part of speech or \"distinct\">\n";
    	  continue;
       }
      
      boolean distinct;
      if(searchType.compareTo("distinct") == 0){
    	  distinct = true;    	  
      }
      else distinct = false;

      // Begin searching.
      List<dictionaryInfo> tempCourse = bucket.get(searchWord);
      
      if(tempCourse == null) {
    	  output += tab + "<Not found>\n";
    	  continue;    	  
      }
      String original_title = bucket_names.get(searchWord);
      
      // Print all items in "distinct" mode.
      if(distinct == true)
      {
    	  Vector<String> knownDistincts = new Vector<String>();
    	      	  
    	  // One item per type only.
          for(int i = 0; i < tempCourse.size(); i++)
          {
        	  boolean exist = false;
        	  String compare = tempCourse.get(i).getType().toLowerCase();
            
        	  for(int k = 0; k < knownDistincts.size(); k++) {
        		  if(knownDistincts.get(k).compareTo(compare) == 0) { exist = true; break; }
              }
        	  
        	  if(exist) { continue; }
        	 
        	  output += tab + original_title + "  [" + tempCourse.get(i).getType() + "] : " + tempCourse.get(i).getDefinition() + "\n";

        	  knownDistincts.add(compare);        	  
          }    	  
          continue;
      }
 
      // If a type is not specified, then all items are printed.
      if(searchType.length() == 0)
      {
    	    for(int i = 0; i < tempCourse.size(); i++) {
      		  output += tab + original_title + "  [" + tempCourse.get(i).getType() + "] : " + tempCourse.get(i).getDefinition() + "\n";    		     	  
    	    }   	      	  
    	    continue;
      }
      
      if(searchType2.compareTo("distinct") == 0) {
    	  distinct = true;    	  
      }
      
      int count = 0;
      
      // Continue searching.
      for(int i = 0; i < tempCourse.size(); i++)
      {
    	  String compare = tempCourse.get(i).getType().toLowerCase();

    	  if(searchType.compareTo(compare) == 0) {
    		  count += 1;
    		  output += tab + original_title + "  [" + tempCourse.get(i).getType() + "] : " + tempCourse.get(i).getDefinition() + "\n";  
    		  
    		  if(distinct == true) { break; } // If the distinct mode is enabled, only an item per type.    		  
    	  }
      }     
      
      if(count == 0) {
    	  output += tab + "<Not found>\n";    	  
      }
    
    // End of the loop.
    }
    while(true);
  }
}