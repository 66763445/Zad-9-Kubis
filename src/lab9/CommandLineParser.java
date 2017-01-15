package lab9;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Nek on 2017-01-15.
 */
public class CommandLineParser {
    protected void showTask9Answers(Sejm sejm, Scanner input){
        showAverageSumExpensesOfAllMembers(sejm);

        showTheMostAmountOfTripsMember(sejm);

        showTheLongestDurationSumMemeber(sejm);

        showTheBiggestTripCostMemeber(sejm);

        showMembersWhoWasInItalia(sejm);

        showSpecificExpensesOfSpecificMembers(sejm,input);
    }

    private void showSpecificExpensesOfSpecificMembers(Sejm sejm, Scanner input) {
        String text = "N";
        String expenseCategory;
        while(text.equals("N")){
            System.out.println("- Do you want sum of expenses or \"expense of repair or renovation\"? (sum/eoror)");
            expenseCategory = input.nextLine();
            while(!expenseCategory.equals("sum") && !expenseCategory.equals("eoror")){
                System.out.println("- Your answer should be \"sum\" or \"eoror\". Try again.");
                expenseCategory = input.nextLine();
            }
            if(expenseCategory.equals("sum") || expenseCategory.equals("eoror")){
                System.out.println("- Let me know which sejm member do you want to check. (Firstname Lastname)");
                text = input.nextLine();
                try{
                    System.out.println("- Expenses (" + expenseCategory + ") of " +
                            text +" ("+ sejm.getSejmMember(text).getId()
                            + ") are: " + sejm.getSejmMember(text).getExpenses(expenseCategory));
                } catch(IllegalArgumentException e){
                    System.out.println(e.toString());
                }
            }
            System.out.println("- Is that all? (Y/N)");
            text = input.nextLine();
            while(!text.equals("N") && !text.equals("Y")){
                System.out.println("- Your answer should be \"Y\" or \"N\". Try again.");
                text = input.nextLine();
            }
        }
    }

    private void showMembersWhoWasInItalia(Sejm sejm) {
        ArrayList<SejmMember> italiaTravelers = sejm.getMembersWhoWasInItalia();
        System.out.println("- Italia travelers: ");
        if(italiaTravelers.isEmpty())
            System.out.println("Nobody.");
        for(SejmMember member : italiaTravelers)
            System.out.println(member.getFirstnameAndLastname());
    }


    private void showTheBiggestTripCostMemeber(Sejm sejm) {
        System.out.println("- Member whose trip had the biggest cost: ");
        System.out.println(sejm.getTheBiggestTripCostMemeber().getFirstnameAndLastname());
    }

    private void showTheLongestDurationSumMemeber(Sejm sejm) {
        System.out.println("- Member whose business trips sum duration was the longest: ");
        System.out.println(sejm.getTheLongestDurationSumMemeber().getFirstnameAndLastname());
    }

    private void showTheMostAmountOfTripsMember(Sejm sejm) {
        System.out.println("- Member with the biggest amount of business trips: ");
        System.out.println(sejm.getTheMostAmountOfTripsMember().getFirstnameAndLastname());
    }

    private void showAverageSumExpensesOfAllMembers(Sejm sejm) {
        System.out.println("- Average value of sum of expenses all of the sejm members: ");
        System.out.println(sejm.getSumExpensesOfAllMembers()/460.0);
    }
}
