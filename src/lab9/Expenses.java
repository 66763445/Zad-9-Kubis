package lab9;

/**
 * Created by Nek on 2016-12-21.
 */
public class Expenses extends FieldParser {
    private float expenseOfRepairOrRenovation;
    private float sumOfExpenses;

    public float getExpenseOfRepairOrRenovation(){
        return this.expenseOfRepairOrRenovation;
    }

    public void putExpenses(int j, StringBuilder sejmSource){
        float sum = 0;
        float floatFromField = 0;
        while(!sejmSource.substring(j,j+4).equals("pola"))
            j++;
        j+=8;
        if(sejmSource.length() <= j){
            this.sumOfExpenses = 0;
            return;
        }
        for(int i = 1; i < 11; i++){
            floatFromField = getFloatFromField(sejmSource,j);
            sum += floatFromField;
            if(i == 13)
                this.expenseOfRepairOrRenovation = floatFromField;
        }
        this.sumOfExpenses = sum;
    }

    public float getSumOfExpenses(){
        return this.sumOfExpenses;
    }
}
