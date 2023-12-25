package MainConfigHandler.models;

public class ContainerInfo {
    private String containerName;
    private String containerIP;
    private String containerMac;
    private String networkID;
    private String networkName;

    public String fetchContainerName() {
        return containerName;
    }

    public String getContainerIP() {
        return containerIP;
    }

    public String getContainerMac() {
        return containerMac;
    }

    public String getNetworkID() {
        return networkID;
    }

    public String getNetworkName() {
        return networkName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public void setNetworkName(String networkName) {
        this.networkName = networkName;
    }

    public void setNetworkID(String networkID) {
        this.networkID = networkID;
    }

    public void setContainerIP(String containerIP) {
        this.containerIP = containerIP;
    }

    public void setContainerMac(String containerMac) {
        this.containerMac = containerMac;
    }
}
