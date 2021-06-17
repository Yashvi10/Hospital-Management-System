import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class StarterTest {

    @Test
    void getMsg(){
        assertEquals("Hello", new Starter().getMsg());
    }
}
