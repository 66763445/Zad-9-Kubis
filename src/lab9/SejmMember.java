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
        this.firstnameAndLastname = getStringFromField("ludzie.nazwa", sejmSource, j);
    }

    public void putId(int j, StringBuilder sejmSource) {
        this.id = getStringFromField("poslowie.id", sejmSource, j);
    }

    public void putExpenses(int j, StringBuilder sejmSource) {
            this.expenses.putExpenses(j,sejmSource);
    }

    public void putBusinessTrips(int j, StringBuilder sejmSource){
        int i=0;
        while(j < sejmSource.length()){
            if(sejmSource.substring(j,j+5).equals("kraj")){
                businessTrips.add(new BusinessTrip());
                businessTrips.get(i).putCountry(sejmSource,j);
                businessTrips.get(i).putCost(sejmSource, j);
                businessTrips.get(i).putDuration(sejmSource, j);
                i++;
            }
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
        if(expenseCategory == "eoror")
            return this.expenses.getExpenseOfRepairOrRenovation();
        else
        if(expenseCategory == "sum")
            return this.expenses.getSumOfExpenses();
        else
            throw new IllegalArgumentException("Wrong expense category!");
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
        return this.businessTrips.get(i).getCountry();
    }

    public int getBusinessTripDuration(int i){
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
