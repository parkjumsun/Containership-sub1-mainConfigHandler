package MainConfigHandler.services;

import MainConfigHandler.models.ContainerInfo;
import MainConfigHandler.models.DeployInfo;
import MainConfigHandler.models.GroupInfo;

import java.util.List;

public interface ContainerConnector {
    public List<GroupInfo> getGroupInfo();

    public List<ContainerInfo> getContainerInfo();
    public ContainerInfo deployContainer(DeployInfo deployInfo);
}
