package MainConfigHandler.models;

import java.util.List;

public class SwitchInfo {
    private String switchName;
    private List<String> groupList;

    public SwitchInfo(String switchName, List<String> groupList) {
        this.switchName = switchName;
        this.groupList = groupList;
    }

    public List<String> getGroupList() {
        return groupList;
    }

    public String fetchSwitchName() {
        return switchName;
    }

    public void setGroupList(List<String> groupList) {
        this.groupList = groupList;
    }
}
