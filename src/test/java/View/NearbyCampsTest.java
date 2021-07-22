package View;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NearbyCampsTest {

  @Test
  void isNumber() {
    assertEquals(true, new NearbyCamps().isNumber("6"),"Not a number!");
  }

}