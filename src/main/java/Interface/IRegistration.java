package Interface;

import Model.User;

public interface IRegistration {
    String registerPatient(User user );
    String registerStaff(String role,User user);
    int loadRecord(User user );
    Integer getLastUserId();
}
