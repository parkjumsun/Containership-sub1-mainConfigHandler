package MainConfigHandler.services;

import MainConfigHandler.models.ConnectionInfo;
import MainConfigHandler.models.ContainerInfo;
import MainConfigHandler.models.DeployInfo;
import MainConfigHandler.models.SwitchInfo;
import MainConfigHandler.models.responseTemplate.MessageResponse;

import java.util.List;

public interface SwitchConnector {
    List<SwitchInfo> getSwitchInfo();
    MessageResponse connectContainers(ConnectionInfo connectionInfo);

}
