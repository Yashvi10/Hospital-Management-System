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


    /*
     * Tests the logic if person got two doses they should not allow to register
     * or to get another dose
     *
     * The methods tests user who got both doses should get return value 2 here
     */
    @Test
    void checkSecondDoseCompleted()
    {
        VaccineRegisterBL vaccineRegisterBL = new VaccineRegisterBL(new VaccineRegistration());
        assertEquals(2, vaccineRegisterBL.checkUserRegistration(7));
    }

    /*
     * Tests the logic of emailId validation
     *
     * The method returns true as emailId is in accepted format
     */
    @Test
    void validateMailId_true()
    {
        VaccineRegisterBL vaccineRegisterBL = new VaccineRegisterBL(new VaccineRegistration());
        assertEquals(true, vaccineRegisterBL.validateEmailId("kush@dal.ca"));
    }

    /*
     * Tests the logic of emailId validation
     *
     * The method returns false as emailId is invalid
     */
    @Test
    void validateMailId_false()
    {
        VaccineRegisterBL vaccineRegisterBL = new VaccineRegisterBL(new VaccineRegistration());
        assertEquals(false, vaccineRegisterBL.validateEmailId("hello123"));
    }

    /*
     * Tests the logic of age validation
     *
     * The method returns true as age is in accepted format
     */
    @Test
    void validateAge_true()
    {
        VaccineRegisterBL vaccineRegisterBL = new VaccineRegisterBL(new VaccineRegistration());
        assertEquals(true, vaccineRegisterBL.validateAge("23"));
    }

    /*
     * Tests the logic of date validation
     *
     * The method returns true as date is in accepted format
     */
    @Test
    void validateDateTest_true()
    {
        VaccineRegisterBL vaccineRegisterBL = new VaccineRegisterBL(new VaccineRegistration());
        assertEquals(true, vaccineRegisterBL.validateDate("2021-08-28"));
    }

    /*
     * Tests the logic of date validation
     *
     * The method returns false as date is in invalid format
     */
    @Test
    void validateDateTest_false()
    {
        VaccineRegisterBL vaccineRegisterBL = new VaccineRegisterBL(new VaccineRegistration());
        assertEquals(false, vaccineRegisterBL.validateDate("2021a-08-28"));
    }
}
