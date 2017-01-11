package lab9;

/**
 * Created by Nek on 2017-01-08.
 */
public class FieldParser {
    protected String getStringFromField(String fieldName, StringBuilder sourceText, int j){
        String result = "";

        while(!sourceText.substring(j,j+fieldName.length()).equals(fieldName))
            j++;

        if(sourceText.substring(j,j+fieldName.length()).equals(fieldName)) {
            j = j + fieldName.length() + 3;
            while(sourceText.charAt(j) != '\"') {
                result = result + sourceText.charAt(j);
                j++;
            }
        }
        System.out.println(result);
        return result;
    }

    protected float getFloatFromField(StringBuilder sejmSource, int j) {
        String expenseStringValue = "";

        while(sejmSource.charAt(j) != '\"'){
            expenseStringValue = expenseStringValue + sejmSource.charAt(j);
        }

        return Float.parseFloat(expenseStringValue);
    }

    protected int getIntFromField(StringBuilder sourceText, String fieldName,int j) {
        String expenseStringValue = getStringFromField(fieldName,sourceText,j);
        return Integer.parseInt(expenseStringValue);
    }
}
