/**
 * Title:          Week 4 - Program Improvement III
 * Author:         Team B : ( Elijah Cornell / Eric Landeis / Gordon Doskas / James Rippon /
 *                            Joseph Hart / Keith Green / Lance Branford )
 * Creation Date:  2016-02-11
 * Class:          PRG/421 - Roland Morales
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

    public int mainPrompt (){ // main menu display allows for automatic choice selection by returning number of choices
        System.out.print("Main Menu\n"
                         + "+++++++++\n");
        
        ArrayList<String> mainPromptList = new ArrayList<>();
        
        mainPromptList.add("Display animal(s)");
        mainPromptList.add("Add animal");
        mainPromptList.add("Clone animal");
        mainPromptList.add("Breed animals");
        mainPromptList.add("Delete animal");
        mainPromptList.add("Edit animal");
        mainPromptList.add("Exit");
        
        for(int i = 0; i < mainPromptList.size(); i++){
            System.out.println((i + 1) + ". " + mainPromptList.get(i));
        }
        System.out.println("Choice:");
   
        return mainPromptList.size();
    }

    public int editPrompt (){ // edit attribute display .. fix later to pull from enumerated attributes and build automatically
        System.out.print("Edit Animal\n"
                     + "+++++++++\n");

        ArrayList<String> editPromptList = new ArrayList<>();

        editPromptList.add("Edit name");
        editPromptList.add("Edit skeleton present");
        editPromptList.add("Edit sound");
        editPromptList.add("Edit habitat");
        editPromptList.add("Edit color");
        editPromptList.add("Back");

        for(int i = 0; i < editPromptList.size(); i++){
            System.out.println((i + 1) + ". " + editPromptList.get(i));
        }
        System.out.println("Choice:");
        
        return editPromptList.size();

    }

    public int selectionProcess(int numChoices){ // takes input and validates against allowed options

        int currentSelection;
        int finalSelection = 0;

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
            System.out.print((i + 1) + ". " + list.get(i).getName() + "\n");
        }
        displayPrompt();
    }
    public void displayPrompt()
    {
        System.out.print("Choice: ");
    }
}
