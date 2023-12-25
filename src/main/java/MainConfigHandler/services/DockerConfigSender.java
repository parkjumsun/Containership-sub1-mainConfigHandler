package MainConfigHandler.services;

import MainConfigHandler.models.ContainerInfo;
import MainConfigHandler.models.DeployInfo;
import MainConfigHandler.models.GroupInfo;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DockerConfigSender implements ContainerConnector {
    private String ip = "localhost";
    private String port = "8082";

    private RestTemplate restTemplate = new RestTemplate();
    @Override
    public List<GroupInfo> getGroupInfo() {
        String url = "http://" + ip + ":" + port + "/networks";
        ParameterizedTypeReference<List<GroupInfo>> responseType = new ParameterizedTypeReference<List<GroupInfo>>() {};
        ResponseEntity<List<GroupInfo>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity(new HttpHeaders()), responseType);
        return responseEntity.getBody();
    }

    @Override
    public List<ContainerInfo> getContainerInfo() {
        String url = "http://" + ip + ":" + port + "/containers";
        ParameterizedTypeReference<List<ContainerInfo>> responseType = new ParameterizedTypeReference<List<ContainerInfo>>() {};
        ResponseEntity<List<ContainerInfo>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity(new HttpHeaders()), responseType);
        return responseEntity.getBody();
    }
    @Override
    public ContainerInfo deployContainer(DeployInfo deployInfo) {
        String url = "http://" + ip + ":" + port + "/containers/deploy";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<DeployInfo> entity = new HttpEntity<>(deployInfo, headers);
        ResponseEntity<ContainerInfo> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, ContainerInfo.class);
        return responseEntity.getBody();
    }
}
