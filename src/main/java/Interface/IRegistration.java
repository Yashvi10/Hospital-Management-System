package Interface;

import Model.User;

public interface IRegistration {
    boolean registerPatient(User user );
    boolean registerStaff(String role,User user);
    Integer getLastUserId();
}
