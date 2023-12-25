package MainConfigHandler.models;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class GroupInfo {
    private String networkName;
    private String networkID;
    private String subnet;

    private List<String> containers;


    public GroupInfo(String networkName, String networkID, String subnet, List<String> containers) {
        this.networkName = networkName;
        this.networkID = networkID;
        this.subnet = subnet;
        this.containers = containers;
    }

    public String fetchNetworkName() {
        return networkName;
    }

    public String fetchNetworkID() {
        return networkID;
    }

    public String getSubnet() {
        return subnet;
    }

    public List<String> getContainers() {
        return containers;
    }
}
