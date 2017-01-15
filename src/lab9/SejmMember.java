package lab9;

import java.util.ArrayList;

/**
 * Created by Nek on 2016-12-21.
 */
public class SejmMember extends FieldParser{
    private String firstnameAndLastname;
    private String id;
    private Expenses expenses;
    private ArrayList<BusinessTrip> businessTrips;
    private int tripAmount;

    public void putFirstnameAndLastname(int j, StringBuilder sejmSource){
        this.firstnameAndLastname = getStringFromField("\"ludzie.nazwa\"", sejmSource, j);
    }

    public void putId(int j, StringBuilder sejmSource) {
        this.id = getStringFromField("\"poslowie.id\"", sejmSource, j);
    }

    public void putExpenses(int j, StringBuilder sejmSource) {
            this.expenses = new Expenses();
            this.expenses.putSumAndOtherExpenses(j,sejmSource);
    }

    public void putBusinessTrips(int j, StringBuilder sejmSource){
        int i=0;
        this.businessTrips = new ArrayList<>();
        while(j + 6  < sejmSource.length()){
            if(sejmSource.substring(j,j+6).equals("\"kraj\"")){
                this.businessTrips.add(new BusinessTrip());
                this.businessTrips.get(i).putCountry(sejmSource,j);
                this.businessTrips.get(i).putCost(sejmSource, j);
                this.businessTrips.get(i).putDuration(sejmSource, j);
                i++;
            }
            j++;
        }
        this.tripAmount = i;
    }

    public String getId(){
        return this.id;
    }

    public String getFirstnameAndLastname(){
        return this.firstnameAndLastname;
    }

    public float getExpenses(String expenseCategory){
        if(expenseCategory.equals("eoror"))
            return this.expenses.getExpenseOfRepairOrRenovation();
        else
        if(expenseCategory.equals("sum"))
            return this.expenses.getSumOfExpenses();
        else
            throw new IllegalArgumentException("Wrong expense category!");
    }

    public ArrayList<BusinessTrip> getBusinessTrips(){
        return this.businessTrips;
    }

    public int getBusinsessTripsDurationSum(){
        int i = 0, sum = 0;
        while(i < this.getTripAmount()){
            sum += this.getBusinessTripDuration(i);
            i++;
        }
        return sum;
    }
    public int getTripAmount(){
        return this.tripAmount;
    }
    public float getBusinessTripCost(int i){
            return this.businessTrips.get(i).getCost();
    }

    public String getBusinessTripCountry(int i){
        if(!this.businessTrips.isEmpty()){
            return this.businessTrips.get(i).getCountry();
        }
        else
            return "No country!";
    }

    public float getBusinessTripDuration(int i){
        return this.businessTrips.get(i).getDuration();
    }

    public float getBusinessTripCostSum() {
        int i = 0, sum = 0;
        while(i < this.getTripAmount()){
            sum += this.getBusinessTripCost(i);
            i++;
        }
        return sum;
    }
}
