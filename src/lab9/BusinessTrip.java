package lab9;

/**
 * Created by Nek on 2016-12-21.
 */
public class BusinessTrip extends FieldParser{
    private float cost;
    private String country;
    private float duration;

    public void putDuration(StringBuilder sejmSource, int j){
        this.duration = getFloatFromField(sejmSource,"\"liczba_dni\"",j);
    }
    public void putCost(StringBuilder sejmSource, int j){
        this.cost = getFloatFromField(sejmSource,"\"koszt_suma\"",j);
    }
    public void putCountry(StringBuilder sejmSource, int j){
        this.country = getStringFromField("\"kraj\"",sejmSource,j);
    }

    public float getDuration(){
        return this.duration;
    }
    public String getCountry(){
        return this.country;
    }
    public float getCost(){
        return this.cost;
    }
}
