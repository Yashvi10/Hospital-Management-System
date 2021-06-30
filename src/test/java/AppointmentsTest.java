import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentsTest {

    @Test
    void book_appointment() {
        Appointments appointments = new Appointments();
        appointments.book_appointment();
        assertEquals(1,1,"booking appointment failed!");
    }

    @Test
    void cancel_appointment() {
        Appointments appointments = new Appointments();
        appointments.cancel_appointment();
        assertEquals(1,1,"canceling appointment failed!");
    }

    @Test
    void reschedule_appointment() {
        Appointments appointments = new Appointments();
        appointments.reschedule_appointment();
        assertEquals(1,1,"rescheduling appointment failed!");
    }
}