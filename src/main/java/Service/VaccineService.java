package Service;

import Interface.VaccineDAO;
import Model.Vaccine;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/*
 *  Name of file: VaccineService.java
 *  Author:  Kushang Mistry
 *  Purpose: Lists all available and approved Vaccines.
 *  Description: Fetches information from database and lists all vaccines available.
 * */
public class VaccineService implements VaccineDAO {

    @Override
    public List<Vaccine> getVaccines() {
        CustomConnection customConnection = new CustomConnection();
        Connection dbConnection = customConnection.Connect();

        List<Vaccine> vaccineList = new ArrayList<Vaccine>();
        return null;
    }
}
