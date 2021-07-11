package Model;

public class GenerateLabReports {

    private Integer test_id;

    private String test_name;

    public GenerateLabReports(Integer test_id, String test_name) {
        this.test_id = test_id;
        this.test_name = test_name;
    }

    public Integer getTest_id() {
        return test_id;
    }

    public void setTest_id(Integer test_id) {
        this.test_id = test_id;
    }

    public String getTest_name() {
        return test_name;
    }

    public void setTest_name(String test_name) {
        this.test_name = test_name;
    }
}
