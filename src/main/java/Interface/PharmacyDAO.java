package Interface;

import Model.Pharmacy;

import java.util.List;

/*
 *  Name of file: PharmacyDAO.java
 *  Author:  Nadish Maredia
 *  Purpose: Separate the logic which interact with DB
 *  Description: This interface is responsible for handling DB operation related to Pharmacy model
 * */
public interface PharmacyDAO {

    List<Pharmacy> getAllMedicines();
}
