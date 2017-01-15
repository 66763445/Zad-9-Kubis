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

    public void putSumAndOtherExpenses(int j, StringBuilder sejmSource){
        float sum = 0;
        float floatFromField = 0;
        j = getIndexOfNextField(sejmSource,"pola",j);
        if(j == -1){
            this.sumOfExpenses = 0;
            return;
        }
        j += 8;
        for(int i = 1; i < 11; i++){
            floatFromField = getExpensesFromField(sejmSource,j);
            if(floatFromField != -1){
                sum += floatFromField;
                j = getIndexOfNextField(sejmSource,",\"",j) + 2;
            }
            else {
                i = 21;
            }
        }
        for(int i = 11; i < 21; i++){
            floatFromField = getExpensesFromField(sejmSource,j);
            if(floatFromField != -1){
                sum += floatFromField;
                j = getIndexOfNextField(sejmSource,",\"",j) + 2;
                if(i == 13)
                    this.expenseOfRepairOrRenovation = floatFromField;
            }
            else {
                i = 21;
            }
        }
        this.sumOfExpenses = sum;
    }

    public float getSumOfExpenses(){
        return this.sumOfExpenses;
    }
}
