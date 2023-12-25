package MainConfigHandler.models.requestTemplate;

public class ConnectionRequest {
    private String container1Name;
    private String container2Name;

    public void setContainer1Name(String container1Name) {
        this.container1Name = container1Name;
    }

    public void setContainer2Name(String container2Name) {
        this.container2Name = container2Name;
    }

    public String getContainer1Name() {
        return container1Name;
    }

    public String getContainer2Name() {
        return container2Name;
    }
}
