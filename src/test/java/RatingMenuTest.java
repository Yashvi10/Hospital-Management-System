import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RatingMenuTest {

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
