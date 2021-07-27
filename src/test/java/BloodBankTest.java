import Model.*;
import Service.BloodDonorService;
import Service.BloodRequesterService;
import Service.BloodService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.mockito.Matchers.anyInt;
import View.BloodBank;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BloodBankTest {

    @Mock
    BloodDonorService bloodDonorService;
    @Mock
    BloodRequesterService bloodRequesterService;
    @Mock
    BloodService bloodService;

    @BeforeEach
    void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

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
    void date() throws ParseException {
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
    void age(){
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
    void weight(){
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
    void haemoglobin(){
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

    @Test
    void bloodDonorList() {
        //FAKE OBJECT
        List<BloodDonor> bloodDonorList = new ArrayList<>();
        BloodDonor bloodDonor1 = new BloodDonor("xyz","jkl","lmn","A+","1234567890","2020-01-01");
        BloodDonor bloodDonor2 = new BloodDonor("pqr","abc","xyz","A+","1234567890","2020-01-01");
        bloodDonorList.add(bloodDonor1);
        bloodDonorList.add(bloodDonor2);
        Mockito.when(bloodDonorService.getAllDonors()).thenReturn(bloodDonorList);
        assertEquals(bloodDonorList, bloodDonorService.getAllDonors());
    }

    @Test
    void bloodRequesterList() {
        //FAKE OBJECT
        List<BloodRequester> bloodRequesterList = new ArrayList<>();
        BloodRequester bloodRequester1 = new BloodRequester("xyz","jkl","lmn","A+","1234567890","2020-01-01");
        BloodRequester bloodRequester2 = new BloodRequester("pqr","abc","xyz","A+","1234567890","2020-01-01");
        bloodRequesterList.add(bloodRequester1);
        bloodRequesterList.add(bloodRequester2);
        Mockito.when(bloodRequesterService.getAllRequesters()).thenReturn(bloodRequesterList);
        assertEquals(bloodRequesterList, bloodRequesterService.getAllRequesters());
    }

    @Test
    void bloodInventoryList() {
        //FAKE OBJECT
        List<BloodInventory> bloodInventoryList = new ArrayList<>();
        BloodInventory bloodInventory1 = new BloodInventory("A+",20);
        BloodInventory bloodInventory2 = new BloodInventory("A-",90);
        bloodInventoryList.add(bloodInventory1);
        bloodInventoryList.add(bloodInventory2);
        Mockito.when(bloodService.getAllBloodGroup()).thenReturn(bloodInventoryList);
        assertEquals(bloodInventoryList, bloodService.getAllBloodGroup());
    }
}
