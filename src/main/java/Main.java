/*
 *  Name of file: Appointments.java
 *  Author:  Sanket Ushangbhai Mehta
 *  Purpose: This is the main starter class which will initiate appointment module
 *  Description: This class calls Appointments menu to initiate
 * */

import java.sql.SQLException;
import java.text.ParseException;

public class Main {

    public static void main(String[] args) throws ParseException, SQLException {

        Appointments appointments = new Appointments();
        appointments.Menu();

    }
}
