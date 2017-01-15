package lab9;

import org.json.JSONException;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Nek on 2016-12-21.
 */
public class SejmSystem {


    public static void main(String[] args){
        try {
        Sejm sejm = new Sejm();
        System.out.println("- Okresl kadencje sejmu:");

        Scanner input = new Scanner(System.in);
        String text = input.nextLine();
        CommandLineParser parser = new CommandLineParser();

        if(!text.equals("8") && !text.equals("7"))
            throw new IllegalArgumentException("- Wrong term of Office!");

        sejm.putMembers(text);

        parser.showTask9Answers(sejm,input);

        System.out.println("- Thank you for using The Sejm System. Happy Easter!");


        } catch (IOException e) {
            System.out.println("Error related to reading from given url occured.");
            e.printStackTrace();
        } catch (JSONException e) {
            System.out.println("Error related to given url occured.");
            e.printStackTrace();
        }
    }
}
