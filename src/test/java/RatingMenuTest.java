import Model.AppointmentModel;
import Model.Feedback;
import Service.RatingService;
import View.RatingMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyInt;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 *  Name of file: RatingMenuTest.java
 *  Author:  Nadish Maredia
 *  Purpose: This is the test class for the Rating and feedback feature
 *  Description: This class tests the functionality of the business logic for the feature
 *
 */
public class RatingMenuTest {

    @Mock
    RatingService ratingService;

    @BeforeEach
    void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void userAppointments() {
        //FAKE OBJ
        List<AppointmentModel> appointmentModelList = new ArrayList<>();
        AppointmentModel appointmentModel = new AppointmentModel();
        appointmentModel.setAppointment_id(1);
        appointmentModel.setAppointment_date("26/07/2021");
        appointmentModel.setAppointment_time("12:25");
        appointmentModel.setAppointment_status("confirmed");
        appointmentModelList.add(appointmentModel);

        Mockito.when(ratingService.userAppointments(anyString())).thenReturn(appointmentModelList);

        assertEquals(appointmentModelList,
                ratingService.userAppointments("5"),
                "No appointments found with this id");

    }

    @Test
    void addFeedback() {
        //FAKE OBJECT
        Feedback feedback = new Feedback();
        feedback.setUser_id(1);
        feedback.setRating(5);
        feedback.setFeedback("test feedback");
        feedback.setAppointment_id(2);

        Mockito.when(ratingService.addFeedback(feedback)).thenReturn(true);

        assertEquals(true, ratingService.addFeedback(feedback), "Feedback not added");
    }

    @Test
    void feedback_Exists() {

        Mockito.when(ratingService.feedbackExists(anyInt(), anyInt())).thenReturn(true);

        assertEquals(true, ratingService.feedbackExists(1,5), "You already submitted the feedback");
    }

    @Test
    void isNumber_true(){

        assertEquals(true, new RatingMenu().isNumber("12"), "This is not number");
    }

    @Test
    void isNumber_false(){

        assertEquals(false, new RatingMenu().isNumber("125f6"),"This is not number");
    }

    @Test
    void lengthBelow500_true(){

        assertEquals(true, new RatingMenu().lengthBelow500("hello this is testing")
                ,"The length is more than 500 characters");
    }

    @Test
    void lengthBelow500_false(){

        assertEquals(false, new RatingMenu().lengthBelow500("hello this is testing" +
                        "hello this is testinghello this is testinghello this is testing" +
                        "hello this is testinghello this is testinghello this is testing" +
                        "hello this is testinghello this is testinghello this is testing" +
                        "hello this is testinghello this is testinghello this is testing" +
                        "hello this is testinghello this is testinghello this is testing" +
                        "hello this is testinghello this is testinghello this is testing" +
                        "hello this is testinghello this is testinghello this is testing" +
                        "hello this is testinghello this is testinghello this is testing" +
                        "hello this is testinghello this is testinghello this is testing")
                ,"The length is more than 500 characters");
    }

    @Test
    void isValidRating_true(){
        assertEquals(true, new RatingMenu().isValidRating("5")," Rating should be between 1 to 5");
    }

    @Test
    void isValidRating_false(){
        assertEquals(false, new RatingMenu().isValidRating("6")," Rating should be between 1 to 5");
    }

    @Test
    void isRequired_true(){
        assertEquals(true, new RatingMenu().isRequired("Hello world"), "The text is required");
    }

    @Test
    void isRequired_false(){
        assertEquals(false, new RatingMenu().isRequired(""),"The text is required");
    }
}
