/*
 The program should allow a user to do the following:
Add, edit, delete different types of animals
Select an animal, and the corresponding characteristics will be displayed (such as color, vertebrate or invertebrate, can swim, etc.)
The program must use ArrayList(s) to work with these animal objects.
 */

import java.util.Scanner;

/**
 *
 * @author Eric
 */
public class Animal { // animal class creation
    private String name = "";
    private boolean skeletonPresent;
    private String sound;
    private String habitat;
    private String color;
    private int id;
    
    Scanner keyboard = new Scanner(System.in);
    
    public Animal(){ // default constructor
        this.name = "Name";
        this.skeletonPresent = true;
        this.sound = "Sound";
        this.habitat = "Habitat";
        this.color = "Color";
        
    }

    public Animal(String name, boolean skeletonPresent, String sound, String habitat, String color){
        this.name = name;
        this.skeletonPresent = skeletonPresent;
        this.sound = sound;
        this.habitat = habitat;
        this.color = color;        
    }

    public void setName(String name) { // assign name method
        this.name = name;
    }
    
    public String getName() { // retrieve name method
        return this.name;
    }
    
    public void setSkeletonPresent(boolean skeletonPresent) { // assign skeleton present method
          this.skeletonPresent = skeletonPresent;
    }

    public boolean getSkeletonPresent() { // retrieve skeleton present method
        return this.skeletonPresent;
    }

    public void setSound(String sound) { // assign sound method
        this.sound = sound;
    }

    public String getSound() { // retrieve sound method
        return this.sound;
    }

    public void setHabitat(String habitat) {  //assign habitat method
        this.habitat = habitat;
    }

    public String getHabitat() { // retrieve habitat method
        return this.habitat;
    }

    public void setColor(String color) { // assign color method
        this.color = color;
    }

    public String getColor() { // retrieve color method
        return this.color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void printAnimal(){ // print delineated attributes....fix to display it more tabular format later
        System.out.printf("%-15s | %-10s | %-10s | %-10s | %-15s\n", name, skeletonPresent, sound, habitat,color);
    }

    public Animal addAnimalSetAll(String name, boolean skeletonPresent, String sound, String habitat, String color){
        Animal tempAnimal = new Animal(name, skeletonPresent, sound, habitat, color);
        return tempAnimal;
    }
    
    public Animal addAnimal(){ // add animal method
       
        Animal tempAnimal = new Animal(); // temporary animal object using default contructor then assign individual attributes
                
        try{ 
            System.out.println("Enter the name of the animal: ");
            tempAnimal.setName(keyboard.nextLine());
        }
        catch(Exception e2){
            System.out.println("Incorrect Entry\n*******\n" + e2);
        }
        
        try{
            System.out.println("Does the animal have a skeleton?\n"
                                + "Enter 1 for yes or 0 for no:"); // get 1 or 0 entry and convert 1 to true and 0 to false for boolean attribute
            int tempSkeleton = Integer.parseInt(keyboard.nextLine());
            if(tempSkeleton == 1){tempAnimal.setSkeletonPresent(true);}
            else if(tempSkeleton == 0){tempAnimal.setSkeletonPresent(false);}
            else{System.out.println("Exiting");}
        }
        catch(Exception e3){
            System.out.println("Incorrect Entry\n*******\n" + e3);
        }
        try{
            System.out.println("What sound does the animal make?");
            tempAnimal.setSound(keyboard.nextLine());
        }
        catch(Exception e4){
            System.out.println("Incorrect Entry\n*******\n" + e4);
        }

        try{
            System.out.println("Where does the animal live?");
            tempAnimal.setHabitat(keyboard.nextLine());
        }
        catch(Exception e5){
            System.out.println("Incorrect Entry\n*******\n" + e5);
        }

        try{
            System.out.println("What color is the animal?");
            tempAnimal.setColor(keyboard.nextLine());
        }
        catch(Exception e6){
            System.out.println("Incorrect Entry\n*******\n" + e6);
        }
        return tempAnimal; //return "filled" animal object
    }


}
