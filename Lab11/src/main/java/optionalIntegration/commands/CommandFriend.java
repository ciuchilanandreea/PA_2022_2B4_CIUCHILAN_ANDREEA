package optionalIntegration.commands;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class CommandFriend implements Command {
    private String friend1;
    private String friend2;

    final String uri = "https://localhost:449/friends/";

    public CommandFriend(String friend1, String friend2) {
        this.friend1 = friend1;
        this.friend2 = friend2;
    }

    @Override
    public String run() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>("{ \"name1\": \"" + friend1 + "\", \"name2\": \"" + friend2 +"\" }", headers);
        ResponseEntity<String> response = restTemplate.postForEntity(uri, request, String.class);

        if(response.getStatusCode().is2xxSuccessful()) {
            return "success";
        }
        else {
            return "exception";
        }
    }
}
