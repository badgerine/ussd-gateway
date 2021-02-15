package za.co.mamamoney.sbu.ussdGateway.model;

public class UserOptions {
    private String type;
    private String[] options;

    public UserOptions(String type, String[] options) {
        this.type = type;
        this.options = options;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }
}
