import java.util.List;
import java.util.Map;

public interface IRegistration {
    String registerPatient(User user );
    String registerStaff(String role,User user);
    int loadRecord(User user );
}
