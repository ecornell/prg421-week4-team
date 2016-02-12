/**
 * Title:          Week 4 - Program Improvement III
 * Author:         Team B : ( Elijah Cornell / Eric Landeis / Gordon Doskas / James Rippon /
 *                            Joseph Hart / Keith Green / Lance Branford )
 * Creation Date:  2016-02-11
 * Class:          PRG/421 - Roland Morales
 */
import java.util.List;
import java.util.Scanner;

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

            Scanner keyboard = new Scanner(System.in); // setup for input
            Display menuDisplay = new Display(); // new display instance

            int menuSelection; // this will be used to drive prompt selections
            boolean exitCase = false; // initiate menu and not immediately exit

            while (!exitCase) { // go until exit case is satisfied

                menuSelection = menuDisplay.selectionProcess(6); //initialize display to display main menu (manual entry of number of choices for man menu required...fix later to have display class handle automatically)

                System.out.print("\n");

                List<Animal> animalList = db.readAllAnimals();

                switch (menuSelection) {
                    case 1:
                        System.out.printf("%-15s | %-10s | %-10s | %-10s | %-15s\n",
                                "Name", "Skeleton", "Sound", "Habitat", "Color");
                        System.out.println("--------------------------------------------------------------------------");

                        for (Animal anAnimalList : animalList) {
                            anAnimalList.printAnimal();
                        }

                        break;

                    case 2:
                        Animal animalToAdd = new Animal();
                        db.insertAnimal(animalToAdd.addAnimal());
                        break;

                    case 3:
                        System.out.println("Select animal to be cloned"); // display animals in array and clone selected one
                        menuDisplay.displayArrayList(animalList);
                        int selectedCloneAnimal = Integer.parseInt(keyboard.nextLine()) - 1;
                        db.insertAnimal(new Animal(animalList.get(selectedCloneAnimal)));
                        break;

                    case 4:
                        System.out.println("Select animal to be deleted"); // display animals in array and remove selected one
                        menuDisplay.displayArrayList(animalList);
                        int selectedDeleteAnimal = Integer.parseInt(keyboard.nextLine()) - 1;
                        db.deleteAnimal(animalList.get(selectedDeleteAnimal));
                        break;

                    case 5:
                        System.out.println("Select animal to be edited"); // display list of animals available for edit
                        menuDisplay.displayArrayList(animalList);
                        int selectedEditAnimal = Integer.parseInt(keyboard.nextLine()) - 1;
                        Display.editPrompt(); // display list of attributes to edit
                        int selectedAttribute = Integer.parseInt(keyboard.nextLine());
                        if (selectedAttribute == 1) {
                            System.out.print("Change name to: ");
                            animalList.get(selectedEditAnimal).setName(keyboard.nextLine()); // edit name
                        } else if (selectedAttribute == 2) { // no user option since boolean is a binary choice, just inverting previous entry
                            if (animalList.get(selectedEditAnimal).getSkeletonPresent()) {
                                animalList.get(selectedEditAnimal).setSkeletonPresent(false);
                            } else {
                                animalList.get(selectedEditAnimal).setSkeletonPresent(true);
                            }
                            System.out.print("Skeleton has been set to " + animalList.get(selectedEditAnimal).getSkeletonPresent()); // display change so that user knows change was made
                        } else if (selectedAttribute == 3) { // edit sound
                            System.out.print("Change sound to: ");
                            animalList.get(selectedEditAnimal).setSound(keyboard.nextLine());
                        } else if (selectedAttribute == 4) { // edit habitat
                            System.out.print("Change habitat to: ");
                            animalList.get(selectedEditAnimal).setHabitat(keyboard.nextLine());
                        } else if (selectedAttribute == 5) { // edit color
                            System.out.print("Change color to: ");
                            animalList.get(selectedEditAnimal).setColor(keyboard.nextLine());
                        } else {
                            System.out.println("Error in Edit Selection");
                        }

                        db.updateAnimal(animalList.get(selectedEditAnimal));
                        break;

                    case 6:
                        exitCase = true;
                        break;

                    default:
                        System.out.println("****SOMETHING WENT WRONG SOMEWHERE****"); // shouldn't ever happen....
                        break;
                }

                System.out.print("\n");

            }

        } finally {

            db.close();

        }
    }
}
