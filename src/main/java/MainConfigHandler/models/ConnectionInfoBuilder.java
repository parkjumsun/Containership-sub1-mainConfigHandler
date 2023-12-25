package MainConfigHandler.models;

public class ConnectionInfoBuilder {
    private String c1IP;
    private String c1MAC;
    private String c1GroupName;

    private String c2IP;
    private String c2MAC;
    private String c2GroupName;

    public ConnectionInfoBuilder setC1IP(String c1IP) {
        this.c1IP = c1IP;
        return this;
    }

    public ConnectionInfoBuilder setC1MAC(String c1MAC) {
        this.c1MAC = c1MAC;
        return this;

    }

    public ConnectionInfoBuilder setC1GroupName(String c1GroupName) {
        this.c1GroupName = c1GroupName;
        return this;
    }

    public ConnectionInfoBuilder setC2IP(String c2IP) {
        this.c2IP = c2IP;
        return this;

    }


    public ConnectionInfoBuilder setC2MAC(String c2MAC) {
        this.c2MAC = c2MAC;
        return this;
    }

    public ConnectionInfoBuilder setC2GroupName(String c2GroupName) {
        this.c2GroupName = c2GroupName;
        return this;
    }

    public ConnectionInfo getConnectionInfo() {
        return new ConnectionInfo(this.c1IP, this.c1MAC, this.c1GroupName, this.c2IP, this.c2MAC, this.c2GroupName);
    }

}
