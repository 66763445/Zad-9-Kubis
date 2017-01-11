package lab9;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import static lab9.JsonReader.readJsonFromUrl;

/**
 * Created by Nek on 2016-12-21.
 */
public class Sejm extends FieldParser{
    private SejmMember  sejmMembers[] = new SejmMember[470];


    public void putMembers(String termOfOffice) throws IOException, JSONException {

        int j = 0;

        JSONObject json = readJsonFromUrl("https://api-v3.mojepanstwo.pl/dane/poslowie.json?conditions[poslowie.kadencja]="+termOfOffice);

        StringBuilder sejmSource = new StringBuilder(json.toString());
        String nextUrl = "";
        String lastUrl = getStringFromField("last",sejmSource,j);

        j = 0;
        int k = 1, l = 0;
        while(k != 0){

            if(nextUrl.equals(lastUrl))
                k = 0;
            if(k != 0)
                nextUrl = getStringFromField("next",sejmSource,j);

            for(int i = 0; i < 50; i++) {
                System.out.print(l+". ");
                l++;
                sejmMembers[i] = new SejmMember();
                sejmMembers[i].putFirstnameAndLastname(j,sejmSource);
                sejmMembers[i].putId(j,sejmSource);
                while(!sejmSource.substring(j,j+"ludzie.nazwa".length()).equals("ludzie.nazwa")){
                    j++;
                    if(j >= sejmSource.length())
                        i = 50;
                }
                j++;
            }
            json = readJsonFromUrl(nextUrl);
            sejmSource = new StringBuilder(json.toString());
            j = 0;

        }
        j = 0;
        for(SejmMember member : sejmMembers){
            json = readJsonFromUrl("https://api-v3.mojepanstwo.pl/dane/poslowie/" + member.getId() +".json?layers[]=wyjazdy&layers[]=wydatki");
            sejmSource = new StringBuilder(json.toString());
            member.putExpenses(j,sejmSource);
            member.putBusinessTrips(0,sejmSource);
        }

    }

    public SejmMember getSejmMember(int i){
        return this.sejmMembers[i];
    }

    public float getSumExpensesOfAllMembers(){
        float sum = 0;
        for(SejmMember member : sejmMembers){
            sum += member.getExpenses("sum");
        }
        return sum;
    }

    public SejmMember theMostAmountOfTripsMember(){
        SejmMember member = this.getSejmMember(0);
        for(int i = 0; i < 460; i++)
            if(this.getSejmMember(i).getTripAmount() > member.getTripAmount())
                member = this.getSejmMember(i);
        return member;
    }

    public SejmMember theLongestDurationSumMemeber(){
        SejmMember member = this.getSejmMember(0);
        for(int i = 0; i < 460; i++)
            if(this.getSejmMember(i).getBusinsessTripsDurationSum() > member.getBusinsessTripsDurationSum())
                member = this.getSejmMember(i);
        return member;
    }

    public SejmMember theBiggestTripCostMemeber(){
        SejmMember member = this.getSejmMember(0);
        for(int i = 0; i < 460; i++)
            if(this.getSejmMember(i).getBusinessTripCostSum() > member.getBusinessTripCostSum())
                member = this.getSejmMember(i);
        return member;
    }

    public void showMembersWhoWasInItalia(){
        SejmMember member = this.getSejmMember(0);
        for(int i = 0; i < 460; i++)
            for(int j = 0; j < this.getSejmMember(i).getTripAmount(); j++)
                if(this.getSejmMember(i).getBusinessTripCountry(j) == "Włochy")
                    System.out.println(getSejmMember(i).getFirstnameAndLastname());
    }
}
