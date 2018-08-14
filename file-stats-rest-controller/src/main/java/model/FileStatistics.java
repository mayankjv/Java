package model;

import java.util.*;
import java.io.*;

/**
 * @author mayank.patel
 * This program takes the path to a directory(folder) as an input from the user and monitors the directory continuously for changes.
 * Apart from monitoring, it also allows user to list all the files present in that directory as well as the subdirectories.
 * The listing can be done in sorted manner or in random manner according to user requirements.
 * The user can also perform a search based on attributes of the files like Name and size .
 * The only public class is names FileStatistics which contains the main method and is responsible for allowing user to interact with the application.
 * 
 * 
 */
//The public class that contains the main method, the interaction with user will be done in this class.
public class FileStatistics{

	
	public PathMap path;
	private int mainMenu;
	private int choice;
	String searchString;
	int valueSearch;
	ArrayList<DirectoryFile> result;
//	private int mainMenu;
//	private int mainMenu;
	public FileStatistics(String pathString){
		path= new PathMap(pathString);
		mainMenu=1;
		choice=-1;
	}
	public FileStatistics(int mainMenu, int choice){
		mainMenu=this.mainMenu;
		choice=this.choice;		
	}
	public FileStatistics(int mainMenu, int choice, String searchString){
		mainMenu=this.mainMenu;
		choice=this.choice;		
		searchString=this.searchString;
		valueSearch=-1;
	}
	public FileStatistics(int mainMenu, int choice, int value){
		mainMenu=this.mainMenu;
		choice=this.choice;		
		searchString=this.searchString;
		valueSearch= value;
	}
	//Main method 
	public void execute() throws IOException, InterruptedException{
		

			if(mainMenu == 1){
				result = path.get_files();
			}	
			
			//Condition when a user wants the files listed in a sorted manner.
			else if(mainMenu == 2){
				if(choice ==1) {
					ArrayList<DirectoryFile> temp = new ArrayList<>();
					Collections.sort(temp, new NameComparatorAsc());
					result = temp;
				}
				else if(choice == 2) {
					ArrayList<DirectoryFile> temp = new ArrayList<>();
					Collections.sort(temp, new LastModifiedComparatorAsc());
					result = temp;					
				}
				else {
					ArrayList<DirectoryFile> temp = new ArrayList<>();
					Collections.sort(temp, new SizeComparatorAsc());
					result = temp;
				}
			}
			//When the user wants to search for a file
			else if(choice == 3){
				ArrayList<DirectoryFile> search_results = new ArrayList<DirectoryFile>();
				ArrayList<DirectoryFile> temp= path.get_files();				
				if(valueSearch==-1) {
					KMPSearch obj = new KMPSearch();
					for(int i=0;i<temp.size();i++){
						int[] arr= obj.kmp(temp.get(i).get_file_name().toCharArray(), searchString.toCharArray());
						if(arr.length!=0){
							search_results.add(temp.get(i));
						}
					}		
					result = search_results;
				}
				else {
					for(int i=0;i<temp.size();i++){
						if(temp.get(i).get_size()>valueSearch){
							search_results.add(temp.get(i));
						}
					}
					result = search_results;
				}
			}
			else
				result = new ArrayList<DirectoryFile>();
	}
/*				int flag=0;
				while(true) {
					clear_screen();
					ArrayList<DirectoryFile> search_results = new ArrayList<DirectoryFile>();
					ArrayList<DirectoryFile> temp= path.get_files();
					System.out.println("1. By Name\n2. By Size");
					int ch;
					try{
						Scanner sc1=new Scanner(System.in);
						ch= sc1.nextInt();
					}
					catch(InputMismatchException e){
						System.out.println("Enter a valid Integer!!");
						continue;
					}					switch(ch){
						//When the search is to be done by name
						case 1:
								clear_screen();
								System.out.println("Enter Search string: ");
								String pattern= sc.next();
								KMPSearch obj= new KMPSearch();
								for(int i=0;i<temp.size();i++){
									int[] arr= obj.kmp(temp.get(i).get_file_name().toCharArray(), pattern.toCharArray());
									if(arr.length!=0){
										search_results.add(temp.get(i));
									}
								}
								clear_screen();
								if(search_results.size()==0)
									System.out.println("Nothing to Display!");
								DirectoryFile to_display= new DirectoryFile();
								to_display.print_file(search_results);
								System.out.println("Enter 1 to go to the Main menu and any other character to go to the previous menu.");
								if(take_choice()==1) {
									flag=1;
								}
								else {
									flag=0;
								}
								break;
						//When the search is to be done by size.
						case 2:
								clear_screen();
								System.out.println("1. Greater than\n2. Smaller than");
								int ch1;
								try{
									Scanner sc1=new Scanner(System.in);
									ch1= sc1.nextInt();
								}
								catch(InputMismatchException e){
									System.out.println("Enter a valid Integer!!");
									continue;
								}								clear_screen();
								System.out.println("Value: ");
								long val= sc.nextLong();
								switch(ch1){
									case 1:
										for(int i=0;i<temp.size();i++){
											if(temp.get(i).get_size()>val){
												search_results.add(temp.get(i));
											}
										}
										clear_screen();
										if(search_results.size()==0)
											System.out.println("Nothing to Display!");
										to_display= new DirectoryFile();
										to_display.print_file(search_results);
										if(take_choice()==1) {
											flag=1;
										}
										else {
											flag=0;
										}
										break;
									case 2:
										for(int i=0;i<temp.size();i++){
											if(temp.get(i).get_size()<val){
												search_results.add(temp.get(i));
											}
										}
										clear_screen();
										if(search_results.size()==0)
											System.out.println("Nothing to Display!");
	
										to_display= new DirectoryFile();
										to_display.print_file(search_results);
										System.out.println("Enter 1 to go to the Main menu and any other character to go to the previous menu.");
										if(take_choice()==1) {
											flag=1;
										}
										else {
											flag=0;
										}
	
										break;
									default:
										clear_screen();
										System.out.println("Invalid Option selected!");
										System.out.println("Enter 1 to go to the Main menu and any other character to go to the previous menu.");
										if(take_choice()==1) {
											flag=1;
										}
										else {
											flag=0;
										}
										break;
								}
								break;
						default:
								break;
					}
					if(flag==1)
						break;
				}
*/
/*			else if(choice == 4){
				//th.join();
				sc.close();
			}
			else{
				clear_screen();
				System.out.println("Invalid Choice");
			}
			//System.out.println(choice);
		}
*/
//	}


	//Function that waits for the user to hit the return(Enter) key
	private static int take_choice(){ 
        //System.out.println("Press Enter key to continue...");
        try
        {
            Scanner in= new Scanner(System.in);
            return in.nextInt();
        }  
        catch(Exception e)
        {
        	return -1;
        }  
 	}


 	//Funtion to display main menu
 	private static void displayMainMenu(){
 		System.out.println("Enter your choice\n\n");
 		System.out.println("1. Display\n2. Sort\n3. Search\n4. Exit" );
 	}
 	
 	//Function that clears screen and then displays message to assist user to go to the main menu
/* 	private static void displayNavigationMessage() throws IOException, InterruptedException{
 		clear_screen();
		System.out.println("PRESS ENTER TO GO TO THE MAIN MENU \n\n\n\n");
 	}
*/

 	//Function that clears the screen
 	private static void clear_screen() throws InterruptedException,IOException{
 		try{
 			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
 		}
 		catch(InterruptedException e){
 			System.out.println("Interrupted Exeption!!");
 		}
 		catch(IOException e){
 			System.out.println("IO Exeption!!");
 		}
 	}
 	
 	//a static method that is called from the WatchThread class when an event is encountered.
 	//This method passes the file name, the path and the kind of the event to PathMap class where the List and HashMap are modified.
 	public void call_for_change(String name, String path_string, int kind){
 		try {
 			path.modify_file_list(name,path_string,kind);
 		}
 		catch(Exception e) {
 			//System.out.println("Here is the exception!");
 		}
 	}

 	public ArrayList<DirectoryFile> getResult(){
 		return result;
 	}
}
