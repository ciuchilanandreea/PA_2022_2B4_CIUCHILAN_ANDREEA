package optionalIntegration.commands;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class CommandRegister implements Command {

    private String name;

    public CommandRegister(String name) {
        this.name = name;
    }

    @Override
    public String run()
    {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>("{ \"name\": \"" + name + "\" }", headers);
        ResponseEntity<String> response = restTemplate.postForEntity(uri, request, String.class);
        return "success";
    }
}
