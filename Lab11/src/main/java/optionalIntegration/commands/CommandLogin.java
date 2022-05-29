package optionalIntegration.commands;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class CommandLogin implements Command {
    private String name;

    public CommandLogin(String name) {
        this.name = name;
    }

    @Override
    public String run() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<String> response = restTemplate.getForEntity(uri + name + "/", String.class);

        if(response.getStatusCode().is2xxSuccessful()) {
            return "success";
        }
        else {
            return "exception";
        }
    }
}
