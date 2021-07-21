import Model.Vaccine;
import Service.VaccineRegistration;
import Service.VaccineService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 *  Name of file: VaccinePageTest.java
 *  Author:  Kushang Mistry
 *  Purpose: To test the logic and correctness of VaccinePage class
 *  Description: This class tests logic related to vaccine feature
 *  It also checks the correctness of the data and availability of data
 * */
public class VaccinePageTest {

    /*
     * This method tests availability of data and boundary conditions
     * If database has no vaccine details this test case will fail
     *
     * The test checks weather database has at-least one vaccine detail
     */
    @Test
    void checkVaccineAvailability()
    {
        List<Vaccine> vaccineList = new VaccineService().getVaccines();
        assertEquals(true, vaccineList.size() > 0);
    }


    /*
     * This method tests weather user is already registered for the vaccination or not
     * This tests the logic, if user is already registered
     *
     * then the method returns 0 if which confirms user is not registered for vaccination
     */
    @Test
    void checkUnregisteredUser()
    {
        VaccineRegisterBL vaccineRegisterBL = new VaccineRegisterBL(new VaccineRegistration());
        assertEquals(0, vaccineRegisterBL.checkUserRegistration(9));
    }


    /*
     * The methods tests user who got first dose should get return value 1 here
     */
    @Test
    void checkFistDoseCompleted()
    {
        VaccineRegisterBL vaccineRegisterBL = new VaccineRegisterBL(new VaccineRegistration());
        assertEquals(1, vaccineRegisterBL.checkUserRegistration(8));
    }


}
