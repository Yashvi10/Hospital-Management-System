package Interface;

public interface VaccineDosesDAO {

  Boolean updateVaccineDoses(Integer vaccineId);

  Integer getTotalVaccineDoses(Integer vaccineId);
}
