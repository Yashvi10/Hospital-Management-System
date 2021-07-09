package DAO;

/*
 *  Name of file: BloodDonorDAO.java
 *  Author:  Yashvi Lad
 *  Purpose: Separate methods that interacts with database
 *  Description: This interface is responsible for handling DB operation related to BloodDonor model
 * */

import Model.BloodDonor;

public interface BloodDonorDAO {

    Boolean addDonor(BloodDonor bloodDonor);

    Boolean updateDonor(String blood_group);
}

