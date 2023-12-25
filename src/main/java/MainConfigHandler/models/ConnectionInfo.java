package MainConfigHandler.models;

public class ConnectionInfo {
    private String c1IP;
    private String c1MAC;
    private String c1GroupName;

    private String c2IP;
    private String c2MAC;
    private String c2GroupName;

    public ConnectionInfo(String c1IP, String c1MAC, String c1GroupName, String c2IP, String c2MAC, String c2GroupName) {
        this.c1IP = c1IP;
        this.c1MAC = c1MAC;
        this.c1GroupName = c1GroupName;
        this.c2IP = c2IP;
        this.c2MAC = c2MAC;
        this.c2GroupName = c2GroupName;
    }

    public String getC1IP() {
        return c1IP;
    }

    public String getC1MAC() {
        return c1MAC;
    }

    public String getC1GroupName() {
        return c1GroupName;
    }

    public String getC2IP() {
        return c2IP;
    }

    public String getC2MAC() {
        return c2MAC;
    }

    public String getC2GroupName() {
        return c2GroupName;
    }


}
