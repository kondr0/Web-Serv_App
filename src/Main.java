import java.io.*;
import java.net.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String url = "http://example.com";
        sendGetRequest(url);
        sendPostRequest(url);
    }

    private static void sendGetRequest(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        System.out.println("Response Code: " + connection.getResponseCode());
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
            }
        }
    }

    private static void sendPostRequest(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        try (OutputStream os = connection.getOutputStream()) {
            os.write("param1=value1&param2=value2".getBytes());
        }

        System.out.println("Response Code: " + connection.getResponseCode());
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
            }
        }
    }
}