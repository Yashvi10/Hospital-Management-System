import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BloodBankTest {

    @Test
    void firstname() {
        BloodBank bloodbank = new BloodBank();
        String ip = String.format("xyz");
        ByteArrayInputStream b = new ByteArrayInputStream(ip.getBytes());
        System.setIn(b);

        String expected = "xyz";
        ByteArrayOutputStream b1 = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(b1);
        System.setOut(ps);

        assertEquals(expected, bloodbank.firstname(),"Not a valid input!");
    }

    @Test
    void middlename() {
        BloodBank bloodbank = new BloodBank();
        String ip = String.format("k");
        ByteArrayInputStream b = new ByteArrayInputStream(ip.getBytes());
        System.setIn(b);

        String expected = "k";
        ByteArrayOutputStream b1 = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(b1);
        System.setOut(ps);

        assertEquals(expected, bloodbank.middlename(),"Not a valid input!");
    }

    @Test
    void lastname() {
        BloodBank bloodbank = new BloodBank();
        String ip = String.format("abc");
        ByteArrayInputStream b = new ByteArrayInputStream(ip.getBytes());
        System.setIn(b);

        String expected = "abc";
        ByteArrayOutputStream b1 = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(b1);
        System.setOut(ps);

        assertEquals(expected, bloodbank.lastname(),"Not a valid input!");
    }

    @Test
    void blood_group() {
        BloodBank bloodbank = new BloodBank();
        String ip = String.format("B+");
        ByteArrayInputStream b = new ByteArrayInputStream(ip.getBytes());
        System.setIn(b);

        String expected = "B+";
        ByteArrayOutputStream b1 = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(b1);
        System.setOut(ps);

        assertEquals(expected, bloodbank.blood_group(),"Not a valid input!");
    }

    @Test
    void contact() {
        BloodBank bloodbank = new BloodBank();
        String ip = String.format("1234567890");
        ByteArrayInputStream b = new ByteArrayInputStream(ip.getBytes());
        System.setIn(b);

        String expected = "1234567890";
        ByteArrayOutputStream b1 = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(b1);
        System.setOut(ps);

        assertEquals(expected, bloodbank.blood_group(),"Not a valid input!");
    }

    @Test
    void Date() throws ParseException {
        BloodBank bloodbank = new BloodBank();
        String ip = String.format("2020-01-01");
        ByteArrayInputStream b = new ByteArrayInputStream(ip.getBytes());
        System.setIn(b);

        String expected = "2020-01-01";
        ByteArrayOutputStream b1 = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(b1);
        System.setOut(ps);

        assertEquals(expected, bloodbank.Date(),"Not a valid input!");
    }

    @Test
    void requiredBlood(){
        BloodBank bloodbank = new BloodBank();
        String ip = String.format("1");
        ByteArrayInputStream b = new ByteArrayInputStream(ip.getBytes());
        System.setIn(b);

        String expected = "1";
        ByteArrayOutputStream b1 = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(b1);
        System.setOut(ps);

        assertEquals(expected, bloodbank.Bloodbottles().toString(),"Not a valid input!");
    }

    @Test
    void Age(){
        BloodBank bloodbank = new BloodBank();
        String ip = String.format("1");
        ByteArrayInputStream b = new ByteArrayInputStream(ip.getBytes());
        System.setIn(b);

        String expected = "1";
        ByteArrayOutputStream b1 = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(b1);
        System.setOut(ps);

        assertEquals(expected, bloodbank.donationTest_age().toString(),"Not a valid input!");
    }

    @Test
    void Weight(){
        BloodBank bloodbank = new BloodBank();
        String ip = String.format("46.0");
        ByteArrayInputStream b = new ByteArrayInputStream(ip.getBytes());
        System.setIn(b);

        String expected = "46.0";
        ByteArrayOutputStream b1 = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(b1);
        System.setOut(ps);

        assertEquals(expected, bloodbank.donationTest_Weight().toString(),"Not a valid input!");
    }

    @Test
    void Haemoglobin(){
        BloodBank bloodbank = new BloodBank();
        String ip = String.format("16.5");
        ByteArrayInputStream b = new ByteArrayInputStream(ip.getBytes());
        System.setIn(b);

        String expected = "16.5";
        ByteArrayOutputStream b1 = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(b1);
        System.setOut(ps);

        assertEquals(expected, bloodbank.donationTest_Haemoglobin().toString(),"Not a valid input!");
    }

}