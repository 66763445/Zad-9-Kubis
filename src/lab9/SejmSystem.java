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
        System.out.println("Okresl kadencje sejmu:");

        Scanner input = new Scanner(System.in);
        String text = input.nextLine();

        sejm.putMembers(text);

        System.out.println("Średnia wartość sumy wydatków wszystkich posłów: ");
        System.out.println(sejm.getSumExpensesOfAllMembers()/460.0);

        System.out.println("Najwięcej podróży zagranicznych ma: ");
        System.out.println(sejm.theMostAmountOfTripsMember().getFirstnameAndLastname());

        System.out.println("Najdłużej przebywał/a za granicą: ");
        System.out.println(sejm.theLongestDurationSumMemeber().getFirstnameAndLastname());

        System.out.println("Najdroższy wyjazd miał/a: ");
        System.out.println(sejm.theBiggestTripCostMemeber().getFirstnameAndLastname());

        System.out.println("Włochy odwiedzili: ");
        sejm.showMembersWhoWasInItalia();




        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
