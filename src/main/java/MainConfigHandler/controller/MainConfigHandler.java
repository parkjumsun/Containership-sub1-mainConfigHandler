package MainConfigHandler.controller;


import MainConfigHandler.models.*;
import MainConfigHandler.models.requestTemplate.ConnectionRequest;
import MainConfigHandler.models.responseTemplate.MessageResponse;
import MainConfigHandler.services.ContainerConnector;
import MainConfigHandler.services.SwitchConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MainConfigHandler {

    private SwitchConnector switchConnector;
    private ContainerConnector containerConnector;

    private Map<String, SwitchInfo> switchInfoMap = new HashMap<>();

    private Map<String, GroupInfo> grMapWithNetworkNameKey = new HashMap<>();
    private Map<String, GroupInfo> grMapWithNetworkIdKey = new HashMap<>();
    private Map<String, ContainerInfo> containerInfoMap = new HashMap<>();



    @Autowired
    public MainConfigHandler(SwitchConnector switchConnector, ContainerConnector containerConnector) {
        this.switchConnector = switchConnector;
        this.containerConnector = containerConnector;

        this.getContainers();
        this.getGroups();

        this.getSwitches();
    }

    @GetMapping("/switches")
    public Map<String, SwitchInfo> getSwitches() {
        List<SwitchInfo> switchInfoList = this.switchConnector.getSwitchInfo();
        for (SwitchInfo switchInfo : switchInfoList) {
            List<String> groupIdList = switchInfo.getGroupList();
            List<String> groupNameList = new ArrayList<>();

            for (String groupId : groupIdList) {
                String grRemoved = groupId.replace("br-", "");
                GroupInfo groupInfo = grMapWithNetworkIdKey.get(grRemoved);
                if(groupInfo != null){
                    groupNameList.add(groupInfo.fetchNetworkName());
                }
            }
            switchInfo.setGroupList(groupNameList);
            this.switchInfoMap.put(switchInfo.fetchSwitchName(), switchInfo);
        }
        return this.switchInfoMap;
    }

    @GetMapping("/groups")
    public Map<String, GroupInfo> getGroups() {
        List<GroupInfo> groupInfoList = this.containerConnector.getGroupInfo();
        for (GroupInfo groupInfo : groupInfoList) {
            this.grMapWithNetworkNameKey.put(groupInfo.fetchNetworkName(), groupInfo);
            this.grMapWithNetworkIdKey.put(groupInfo.fetchNetworkID(), groupInfo);
        }
        return this.grMapWithNetworkNameKey;
    }

    @GetMapping("/containers")
    public Map<String, ContainerInfo> getContainers() {
        List<ContainerInfo> containerInfoList = this.containerConnector.getContainerInfo();
        for (ContainerInfo containerInfo : containerInfoList) {
            String networkID = containerInfo.getNetworkID();
            GroupInfo groupInfo = this.grMapWithNetworkIdKey.get(networkID);
            if(groupInfo != null)
                containerInfo.setNetworkName(groupInfo.fetchNetworkName());
            this.containerInfoMap.put(containerInfo.fetchContainerName(), containerInfo);
        }
        return this.containerInfoMap;
    }

    @PostMapping("/containers/connect")
    public MessageResponse connectContainers(@RequestBody ConnectionRequest connectionRequest) {
        ContainerInfo containerInfo1 = this.containerInfoMap.get(connectionRequest.getContainer1Name());
        ContainerInfo containerInfo2 = this.containerInfoMap.get(connectionRequest.getContainer2Name());

        ConnectionInfoBuilder builder = new ConnectionInfoBuilder();
        ConnectionInfo connectionInfo = builder.setC1IP(containerInfo1.getContainerIP())
                .setC1MAC(containerInfo1.getContainerMac())
                .setC1GroupName(containerInfo1.getNetworkID())
                .setC2IP(containerInfo2.getContainerIP())
                .setC2MAC(containerInfo2.getContainerMac())
                .setC2GroupName(containerInfo2.getNetworkID())
                .getConnectionInfo();
        return this.switchConnector.connectContainers(connectionInfo);
    }

    @PostMapping("/containers/deploy")
    public ContainerInfo deployContainers(@RequestBody DeployInfo deployInfo) {
        ContainerInfo containerInfo = this.containerConnector.deployContainer(deployInfo);
        containerInfo.setNetworkID(this.grMapWithNetworkNameKey.get(deployInfo.getNetworkID()).fetchNetworkID());
        containerInfo.setNetworkName(this.grMapWithNetworkNameKey.get(deployInfo.getNetworkID()).fetchNetworkName());
        containerInfo.setContainerName(deployInfo.getContainerName());
        this.containerInfoMap.put(containerInfo.fetchContainerName(), containerInfo);
        List<String> containerList = deployInfo.getContainerList();
        for (String containerName : containerList) {
            ConnectionRequest connectionRequest = new ConnectionRequest();
            connectionRequest.setContainer1Name(deployInfo.getContainerName());
            connectionRequest.setContainer2Name(containerName);
            this.connectContainers(connectionRequest);
        }
        return containerInfo;
    }
}
