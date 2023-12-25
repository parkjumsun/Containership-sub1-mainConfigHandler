package MainConfigHandler.services;

import MainConfigHandler.models.ConnectionInfo;
import MainConfigHandler.models.ContainerInfo;
import MainConfigHandler.models.DeployInfo;
import MainConfigHandler.models.SwitchInfo;
import MainConfigHandler.models.responseTemplate.MessageResponse;
import MainConfigHandler.services.responseTemplate.SwitchListResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SwitchConfigSender implements SwitchConnector{
    private String ip = "localhost";
    private String port = "8081";

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<SwitchInfo> getSwitchInfo() {
        String url = "http://" + ip + ":" + port + "/switches";

        ParameterizedTypeReference<Map<String, SwitchListResponse>> responseType = new ParameterizedTypeReference<Map<String, SwitchListResponse>>() {};

        ResponseEntity<Map<String, SwitchListResponse>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity(new HttpHeaders()), responseType);
        Map<String, SwitchListResponse> switchInfoMap = responseEntity.getBody();

        List<SwitchInfo> switchInfoList = new ArrayList<>();
        for (String switchName : switchInfoMap.keySet()) {
            SwitchListResponse switchListResponse = switchInfoMap.get(switchName);
            List<String> groupIDList = switchListResponse.getPortList();
            switchInfoList.add(new SwitchInfo(switchName, groupIDList));

        }
        /*
            groupList의 name -> container에서 받아와서 연결 필요..
         */


        return switchInfoList;
    }

    @Override
    public MessageResponse connectContainers(ConnectionInfo connectionInfo) {
        String url = "http://" + ip + ":" + port + "/containers/connect";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ConnectionInfo> entity = new HttpEntity<>(connectionInfo, headers);
        ResponseEntity<MessageResponse> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, MessageResponse.class);
        return responseEntity.getBody();
    }

}
