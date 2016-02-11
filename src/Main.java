/*
 The program should allow a user to do the following:
Add, edit, delete different types of animals
Select an animal, and the corresponding characteristics will be displayed (such as color, vertebrate or invertebrate, can swim, etc.)
The program must use ArrayList(s) to work with these animal objects.
 */

import java.util.List;
import java.util.Scanner;

/**
 * @author Eric
 */
public class Main {

    private static DB db = DB.getInstance();

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {


        try {

            db.initDB();

            //


            //

            Scanner keyboard = new Scanner(System.in); // setup for input
            Display menuDisplay = new Display(); // new display instance

            int menuSelection; // this will be used to drive prompt selections
            boolean exitCase = false; // initiate menu and not immediately exit

            while (!exitCase) { // go until exit case is satisfied

                menuSelection = menuDisplay.selectionProcess(5); //initialize display to display main menu (manual entry of number of choices for man menu required...fix later to have display class handle automatically)

                List<Animal> animalList = db.readAllAnimals();

                switch (menuSelection) {
                    case 1:

                        System.out.print("\n\n");
                        System.out.printf("%-15s | %-10s | %-10s | %-10s | %-15s\n",
                                "Name", "Skeleton", "Sound", "Habitat", "Color");
                        System.out.println("--------------------------------------------------------------------------");

                        for (Animal anAnimalList : animalList) {
                            anAnimalList.printAnimal();
                        }

                        System.out.print("\n\n");
                        break;

                    case 2:
                        Animal animalToAdd = new Animal();
                        db.insertAnimal(animalToAdd.addAnimal());
                        break;

                    case 3:
                        System.out.println("Select animal to be deleted"); // display animals in array and remove selected one
                        menuDisplay.displayArrayList(animalList);
                        int selectedDeleteAnimal = Integer.parseInt(keyboard.nextLine()) - 1;
                        db.deleteAnimal(animalList.get(selectedDeleteAnimal));
                        break;

                    case 4:
                        System.out.println("Select animal to be edited"); // display list of animals available for edit
                        menuDisplay.displayArrayList(animalList);
                        int selectedEditAnimal = Integer.parseInt(keyboard.nextLine()) - 1;
                        Display.editPrompt(); // display list of attributes to edit
                        int selectedAttribute = Integer.parseInt(keyboard.nextLine());
                        if (selectedAttribute == 1) {
                            System.out.println("Change name to:");
                            animalList.get(selectedEditAnimal).setName(keyboard.nextLine()); // edit name
                        } else if (selectedAttribute == 2) { // no user option since boolean is a binary choice, just inverting previous entry
                            if (animalList.get(selectedEditAnimal).getSkeletonPresent()) {
                                animalList.get(selectedEditAnimal).setSkeletonPresent(false);
                            } else {
                                animalList.get(selectedEditAnimal).setSkeletonPresent(true);
                            }
                            System.out.println("Skeleton has been set to " + animalList.get(selectedEditAnimal).getSkeletonPresent()); // display change so that user knows change was made
                        } else if (selectedAttribute == 3) { // edit sound
                            System.out.println("Change sound to:");
                            animalList.get(selectedEditAnimal).setSound(keyboard.nextLine());
                        } else if (selectedAttribute == 4) { // edit habitat
                            System.out.println("Change habitat to:");
                            animalList.get(selectedEditAnimal).setHabitat(keyboard.nextLine());
                        } else if (selectedAttribute == 5) { // edit color
                            System.out.println("Change color to:");
                            animalList.get(selectedEditAnimal).setColor(keyboard.nextLine());
                        } else {
                            System.out.println("Error in Edit Selection");
                        }

                        db.updateAnimal(animalList.get(selectedEditAnimal));
                        break;

                    case 5:
//                        System.out.println("case 5\n");  //exit display loop ending program
                        exitCase = true;
                        break;

                    default:
                        System.out.println("****SOMETHING WENT WRONG SOMEWHERE****"); // shouldn't ever happen....
                        break;
                }
            }
        } finally {
            db.close();
        }
    }
}