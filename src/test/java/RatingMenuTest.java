import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RatingMenuTest {

    @Test
    void isNumber_true(){

        assertEquals(true, new RatingMenu().isNumber("12"));
    }

    @Test
    void isNumber_false(){

        assertEquals(false, new RatingMenu().isNumber("125f6"));
    }
}
