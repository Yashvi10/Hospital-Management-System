package Interface;

/*
 *  Name of file: CovidBedBLInterface.java
 *  Author:  Kushang Mistry
 *  Purpose: An Interface having methods of Business Logic to view bed info, register for bed.
 *  Description: The Interface is responsible for declaration of methods which should be implemented in Business logic
 * */
public interface CovidBedBLInterface {

  Integer getBedCount(Integer userChoice);

  Integer registerBed(Integer userChoice);
}
