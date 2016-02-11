/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Eric
 */
public class Display {
    Scanner keyboard = new Scanner(System.in);
    
    public static void mainPrompt (){ // main menu display .. fix later to pull from enumerated attributes and build automatically
        System.out.print("Main Menu\n"
                         + "+++++++++\n"
                         + "1. Display animal list\n"
                         + "2. Add animal\n"
                         + "3. Delete animal\n"
                         + "4. Edit animal\n"
                         + "5. Exit\n"
                         + "Choice: ");
    }

    public static void editPrompt (){ // edit attribute display .. fix later to pull from enumerated attributes and build automatically
    System.out.print("Edit Animal\n"
                     + "+++++++++\n"
                     + "1. Name\n"
                     + "2. Skeleton Present\n"
                     + "3. Sound\n"
                     + "4. Habitat\n"
                     + "5. Color\n"
                     + "6. Back\n"
                     + "Choice: ");
    }
        
    public int selectionProcess(int numChoices){ // takes input and validates against allowed options
        
        int currentSelection;
        int finalSelection = 0;
        
        Display.mainPrompt();
        currentSelection = Integer.parseInt(keyboard.nextLine());
        
        if((currentSelection >= 1) && (currentSelection <= numChoices-1)){
            finalSelection = currentSelection;
        }
        
        else if(currentSelection == numChoices){
            finalSelection = currentSelection;
        }
        
        else{
            System.out.println("****INVALID SELECTION, PLEASE SELECT VALID OPTION****\n");
            finalSelection = selectionProcess(numChoices);
        }
        
        return finalSelection; // returns validated selection
    }
    
    public void displayArrayList(List<Animal> list){ // takes animal arraylist and generates list based off of names
        for(int i = 0; i < list.size(); i++){
            System.out.println((i + 1) + ". " + list.get(i).getName() + "\n");
        }
        System.out.println("Choice: ");
    }
}