/*
 The program should allow a user to do the following:
Add, edit, delete different types of animals
Select an animal, and the corresponding characteristics will be displayed (such as color, vertebrate or invertebrate, can swim, etc.)
The program must use ArrayList(s) to work with these animal objects.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Eric
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception{
        

        ArrayList<Animal> animalList = new ArrayList<>(); // create arraylist of Animal object
        Scanner keyboard = new Scanner(System.in); // setup for input

        String  thisLine = null;
        try{
            BufferedReader br = new BufferedReader(new FileReader("zoo.list")); // open/read existing file            
            while ((thisLine = br.readLine()) != null) { // check if line is available to read
                
                String tempStringArray[] = thisLine.split(","); //split comma delineated string that was read from file into individual strings
                Animal animalToAdd = new Animal(); // animal instance to take read file input
                animalList.add(animalToAdd.addAnimalSetAll(tempStringArray[0],Boolean.parseBoolean(tempStringArray[1]),
                        tempStringArray[2], tempStringArray[3], tempStringArray[4])); // use broken down strings to create animal object and add to arraylist...convert true/false to boolean value using parse
                System.out.println("IMPORT SUCCESS:  " + thisLine + "\n"); // verification to user of what was read from file
            }
        }catch(Exception e){ // just in case
            e.printStackTrace();
        }
        
        Display menuDisplay = new Display(); // new display instance
        /* print imported animals */
        System.out.println("***Begin Imported Animal List***");
        int arrayListSize = animalList.size();
        System.out.println("Name\t::  " + "Skeleton Present ::  " +
                            "Sound\t::  " + "Habitat\t::  " + "Color\n" +
                            "+++++++++++++++++++++++++++++++++++++++++++++++");
        for(int i = 0;i < arrayListSize;i++){ 
            animalList.get(i).printAnimal(); 
            System.out.println("\n");
        }
        System.out.println("***End Imported Animal List***");

        int menuSelection; // this will be used to drive prompt selections
        boolean exitCase = false; // initiate menu and not immediately exit
        
        while(!exitCase){ // go until exit case is satisfied
            menuSelection = menuDisplay.selectionProcess(5); //initialize display to display main menu (manual entry of number of choices for man menu required...fix later to have display class handle automatically)

            switch(menuSelection){
                case 1: arrayListSize = animalList.size(); //get current size of array
                            System.out.println("Name\t::  " + "Skeleton Present ::  " +
                                "Sound\t::  " + "Habitat\t::  " + "Color\n" +
                                "+++++++++++++++++++++++++++++++++++++++++++++++");
                        for(int i = 0;i < arrayListSize;i++){ 
                            animalList.get(i).printAnimal(); //print out each object in the array...make read max size and format at later time
                            System.out.println("\n");
                        }
                    break;
                case 2: Animal animalToAdd = new Animal();
                    animalList.add(animalToAdd.addAnimal()); // create and add new animal to array
                    break;
                case 3: System.out.println("Select animal to be deleted"); // display animals in array and remove selected one
                        menuDisplay.displayArrayList(animalList);
                        int selectedDeleteAnimal = Integer.parseInt(keyboard.nextLine()) - 1;
                        animalList.remove(selectedDeleteAnimal);
                    break;
                case 4: System.out.println("Select animal to be edited"); // display list of animals available for edit
                        menuDisplay.displayArrayList(animalList);
                        int selectedEditAnimal = Integer.parseInt(keyboard.nextLine()) - 1;
                        Display.editPrompt(); // display list of attributes to edit
                        int selectedAttribute = Integer.parseInt(keyboard.nextLine());
                        if(selectedAttribute == 1){
                            System.out.println("Change name to:");
                            animalList.get(selectedEditAnimal).setName(keyboard.nextLine()); // edit name
                        }
                        else if(selectedAttribute == 2){ // no user option since boolean is a binary choice, just inverting previous entry
                            if(animalList.get(selectedEditAnimal).getSkeletonPresent() == true){
                                animalList.get(selectedEditAnimal).setSkeletonPresent(false);
                            }
                            else {
                                animalList.get(selectedEditAnimal).setSkeletonPresent(true);
                            }
                            System.out.println("Skeleton has been set to " + animalList.get(selectedEditAnimal).getSkeletonPresent()); // display change so that user knows change was made
                        }
                        else if(selectedAttribute == 3){ // edit sound
                            System.out.println("Change sound to:");
                            animalList.get(selectedEditAnimal).setSound(keyboard.nextLine());
                        }
                        else if(selectedAttribute == 4){ // edit habitat
                            System.out.println("Change habitat to:");
                            animalList.get(selectedEditAnimal).setHabitat(keyboard.nextLine());
                        }
                        else if(selectedAttribute == 5){ // edit color
                            System.out.println("Change color to:");
                            animalList.get(selectedEditAnimal).setColor(keyboard.nextLine());
                        }
                        else{System.out.println("Error in Edit Selection");}
                    break;
                case 5: System.out.println("case 5\n");  //exit display loop ending program
                    exitCase = true;
                    break;
                default: System.out.println("****SOMETHING WENT WRONG SOMEWHERE****"); // shouldn't ever happen....
                    break;
            }
        }
    }
}