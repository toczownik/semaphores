package mypackage;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Dummy {
    private Long id;
    private Integer x;
    private Integer y;

    public void setId(Long id) {
        this.id = id;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Dummy(Long id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public void run() {
        updatePosition();
    }

    private void updatePosition() {
        String body = "{\"name\":\""+x.toString()+"\", \"y\":\""+y.toString()+"\"}";
        System.out.println(body);
        HttpRequest httpRequest = HttpRequest.newBuilder().
                uri(URI.create("http://localhost:8080/restApi/forklifts/"+id.toString())).
                header("Content-Type", "application/json").
                PUT(HttpRequest.BodyPublishers.ofString(body)).build();
        HttpClient httpClient = HttpClient.newHttpClient();
        try {
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            System.out.println(response.body());
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
