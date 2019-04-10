public class Operator {
    private String type;
    private String password;

    Operator(String type, String password) {
        this.type = type;
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public String getPassword() {
        return password;
    }
}
