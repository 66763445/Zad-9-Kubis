package lab9;

/**
 * Created by Nek on 2016-12-21.
 */
public class BusinessTrip extends FieldParser{
    private float cost;
    private String country;
    private int duration;

    public void putDuration(StringBuilder sejmSource, int j){
        this.duration = getIntFromField(sejmSource,"liczba_dni",j);
    }
    public void putCost(StringBuilder sejmSource, int j){
        this.cost = getIntFromField(sejmSource,"koszt_suma",j);
    }
    public void putCountry(StringBuilder sejmSource, int j){
        this.country = getStringFromField("kraj",sejmSource,j);
    }

    public int getDuration(){
        return this.duration;
    }
    public String getCountry(){
        return this.country;
    }
    public float getCost(){
        return this.cost;
    }
}
