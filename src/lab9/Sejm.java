package lab9;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static lab9.JsonReader.readJsonFromUrl;

/**
 * Created by Nek on 2016-12-21.
 */
public class Sejm extends FieldParser{
    private ArrayList<SejmMember>  sejmMembers;

    public void putMembers(String termOfOffice) throws IOException, JSONException {

        int j = 0;

        JSONObject json = readJsonFromUrl("https://api-v3.mojepanstwo.pl/dane/poslowie.json?conditions[poslowie.kadencja]="+termOfOffice);

        StringBuilder sejmSource = new StringBuilder(json.toString());
        String nextUrl = getStringFromField("\"next\"",sejmSource,0);
        String lastUrl = getStringFromField("\"last\"",sejmSource,j);

        this.sejmMembers = new ArrayList<>();
        j = 0;
        int k = 1, o = 0;
        System.out.print("-Loading sejm members: \n[");
        int l = 2;
        while(k != 0){
            if(nextUrl.equals(lastUrl))
                k = 0;
            for(int i = 0; i < 50; i++) {
                sejmMembers.add(new SejmMember());
                sejmMembers.get(o).putFirstnameAndLastname(j,sejmSource);
                sejmMembers.get(o).putId(j,sejmSource);
                System.out.print("-");
                if(l == 200){
                    System.out.print("\n");
                    l = 0;
                }
                o++;
                l++;
                j = getIndexOfNextField(sejmSource,"\"poslowie.id\"",j) +  1;
                if(j == 0)
                    i = 50;
            }
            if(k != 0){
                nextUrl = getStringFromField("\"next\"",sejmSource,0);
                json = readJsonFromUrl(nextUrl);
                sejmSource = new StringBuilder(json.toString());
                j = 0;
            }
        }
        j = 0;
        l = 1;
        System.out.print("] \n-Loading expenses and business trips:\n[");
        for(SejmMember member : sejmMembers){
            json = readJsonFromUrl("https://api-v3.mojepanstwo.pl/dane/poslowie/" + member.getId() +".json?layers[]=wyjazdy&layers[]=wydatki");
            sejmSource = new StringBuilder(json.toString());
            member.putExpenses(j,sejmSource);
            member.putBusinessTrips(0,sejmSource);
            System.out.print("-");
            l++;
            if(l == 200){
                System.out.print("\n");
                l = 0;
            }
        }
        System.out.print("]\n");
    }

    public SejmMember getSejmMember(int i){
        return this.sejmMembers.get(i);
    }

    public SejmMember getSejmMember(String name){
        SejmMember xMember = null;
        for(SejmMember member : this.sejmMembers)
            if(member.getFirstnameAndLastname().equals(name))
                xMember = member;
        if(xMember == null)
            throw new IllegalArgumentException("There is no such sejm member!");
        else
            return xMember;
    }
    public float getSumExpensesOfAllMembers(){
        float sum = 0;
        for(SejmMember member : sejmMembers){
            sum += member.getExpenses("sum");
        }
        return sum;
    }

    public SejmMember getTheMostAmountOfTripsMember(){
        SejmMember xMember = this.getSejmMember(0);
        for(SejmMember member : this.sejmMembers)
            if(member.getTripAmount() > xMember.getTripAmount())
                xMember = member;
        return xMember;
    }

    public SejmMember getTheLongestDurationSumMemeber(){
        SejmMember xMember = this.getSejmMember(0);
        for(SejmMember member : this.sejmMembers)
            if(member.getBusinsessTripsDurationSum() > xMember.getBusinsessTripsDurationSum())
                xMember = member;
        return xMember;
    }

    public SejmMember getTheBiggestTripCostMemeber(){
        SejmMember xMember = this.getSejmMember(0);
        float maxCost = 0;
        for(SejmMember member : this.sejmMembers)
            for(BusinessTrip trip : member.getBusinessTrips())
                if(trip.getCost() > maxCost){
                    xMember = member;
                    maxCost = trip.getCost();
                }
        return xMember;
    }

    public ArrayList<SejmMember> getMembersWhoWasInItalia(){
        boolean k;
        ArrayList<SejmMember> italiaTravelers = new ArrayList<>();
        for(SejmMember member : this.sejmMembers){
            k = true;
            for(BusinessTrip trip : member.getBusinessTrips()){
                if(trip.getCountry().equals("Włochy") && k){
                    italiaTravelers.add(member);
                    k = false;
                }
            }

        }
    return italiaTravelers;
    }
}
