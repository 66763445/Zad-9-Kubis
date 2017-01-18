package Test;

import lab9.Sejm;
import org.json.JSONException;
import org.junit.Assert;

import java.io.IOException;

/**
 * Created by Nek on 2017-01-18.
 */
public class SejmSystemTest {
    @org.junit.Test
    public void testPutMembers() {
        Sejm sejm = new Sejm();
        try {
            sejm.putMembers("7");
            Assert.assertEquals(sejm.getTheMostAmountOfTripsMember().getFirstnameAndLastname(),"Tadeusz Iwiński");
            Assert.assertEquals(sejm.getTheLongestDurationSumMemeber().getFirstnameAndLastname(),"Tadeusz Iwiński");
            Assert.assertEquals(sejm.getTheBiggestTripCostMemeber().getFirstnameAndLastname(),"Adam Szejnfeld");
            sejm.putMembers("8");
            Assert.assertEquals(sejm.getTheMostAmountOfTripsMember().getFirstnameAndLastname(),"Jan Dziedziczak");
            Assert.assertEquals(sejm.getTheLongestDurationSumMemeber().getFirstnameAndLastname(),"Jan Dziedziczak");
            Assert.assertEquals(sejm.getTheBiggestTripCostMemeber().getFirstnameAndLastname(),"Witold Waszczykowski");

        } catch (IOException e) {
            System.out.println("Error related to reading from given url occured.");
            e.printStackTrace();
        } catch (JSONException e) {
            System.out.println("Error related to given url occured.");
            e.printStackTrace();
        }
    }


}