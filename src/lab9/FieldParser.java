package lab9;
/**
 * Created by Nek on 2017-01-08.
 */
public class FieldParser {
    protected String getStringFromField(String fieldName, StringBuilder sourceText, int j){
        String result = "";
        j = getIndexOfNextField(sourceText,fieldName,j);
        if(j == -1)
            return "";

        if(sourceText.substring(j,j+fieldName.length()).equals(fieldName)) {
            j = j + fieldName.length() + 2;
            while(sourceText.charAt(j) != '\"') {
                result = result + sourceText.charAt(j);
                j++;
            }
        }
        return result;
    }

    protected float getExpensesFromField(StringBuilder sejmSource, int j) {
        String expenseStringValue = "";

        while (sejmSource.charAt(j) != '\"') {
            expenseStringValue = expenseStringValue + sejmSource.charAt(j);
            j++;
        }
        float expenseFloatValue;
        try {
            expenseFloatValue = Float.parseFloat(expenseStringValue);
        } catch (NumberFormatException a) {
            return -1;
        }
        return expenseFloatValue;
    }

    protected float getFloatFromField(StringBuilder sourceText, String fieldName, int j) {
        String expenseStringValue = getStringFromField(fieldName,sourceText,j);
        return Float.parseFloat(expenseStringValue);
    }
    protected int getIndexOfNextField(StringBuilder sourceText, String fieldName, int j){
        while(!sourceText.substring(j,j+fieldName.length()).equals(fieldName)){
            j++;
            if(j+fieldName.length() >= sourceText.length())
                return -1;
        }
        return j;
    }
}
