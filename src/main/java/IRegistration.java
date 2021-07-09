import java.util.List;
import java.util.Map;

public interface IRegistration {
    String registerPatient(User user );
    String registerDoctor(User user);
     String registerStaff(User user );
    Map<Integer, List<String>> loadRecord(User user );
}
