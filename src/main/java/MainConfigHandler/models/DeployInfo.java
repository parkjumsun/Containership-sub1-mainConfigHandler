package MainConfigHandler.models;

import java.util.ArrayList;
import java.util.List;

public class DeployInfo {
    private String image;
    private String networkID;
    private String containerName;

    private List<String> containerList;


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNetworkID() {
        return networkID;
    }

    public void setNetworkID(String networkID) {
        this.networkID = networkID;
    }

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public List<String> getContainerList() {
        return containerList;
    }

    public void setContainerList(List<String> containerList) {
        this.containerList = containerList;
    }
}
