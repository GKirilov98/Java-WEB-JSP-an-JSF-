package main.java.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Application {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder response = new StringBuilder();
    private static Map<String, String> headers;

    public static void main(String[] args) {
        List<String> validUrls = getValidUrls();
        List<String> request = getRequest();

        String method = getMethod(request.get(0));
        String url = getUrl(request.get(0));
        headers = getHeaders(request);
        Map<String, String> bodyParameters = getBodyParam(request);

        if (!validUrls.contains(url)) {
            responseAppendMessage("HTTP/1.1 404 Not Found", "The requested functionality was not found.");
        } else if (!headers.containsKey("Authorization")) {
            responseAppendMessage("HTTP/1.1 401 Unauthorized", "You are not authorized to access the requested functionality.");
        } else if (method.equals("POST") && bodyParameters.size() == 0) {
            responseAppendMessage("HTTP/1.1 400 Bad Request",
                    "There was an error with the requested functionality due to malformed request.");
        } else {
            String username = new String(Base64.getDecoder().decode(headers.get("Authorization").split("\\s+")[1]));
            String first = "";
            List<String> bp = new ArrayList<>();
            boolean firstTime = true;
            for (Map.Entry<String, String> kvp : bodyParameters.entrySet()) {
                if (firstTime) {
                    first = kvp.getValue();
                    firstTime = false;
                } else {
                    bp.add(kvp.getKey() + " - " + kvp.getValue());
                }
            }

            String messageResponseBody = String.format("Greetings %s! You have successfully created %s with %s.",
                    username, first, String.join(", ", bp));
            responseAppendMessage("HTTP/1.1 200 OK", messageResponseBody);
        }

        System.out.println(response.toString());
    }

    private static void responseAppendMessage(String codeStatus, String message) {
        response.append(codeStatus).append(System.lineSeparator());
        for (Map.Entry<String, String> kvp : headers.entrySet()) {
            if (kvp.getKey().equals("Date") ||
                    kvp.getKey().equals("Host") ||
                    kvp.getKey().equals("Content-Type")) {
                response.append(kvp.getKey()).append(": ").append(kvp.getValue()).append(System.lineSeparator());
            }
        }
        response.append(System.lineSeparator()).append(message);
    }

    private static Map<String, String> getBodyParam(List<String> request) {
        Map<String, String> bodyParamaters = new LinkedHashMap<>();
        if (request.get(request.size() - 1).equals("\r\n")) {
            return bodyParamaters;
        }

        Arrays.stream(request.get(request.size() - 1).split("&"))
                .map(bp -> bp.split("="))
                .forEach(bpKvp -> bodyParamaters.put(bpKvp[0], bpKvp[1]));
        return bodyParamaters;
    }

    private static Map<String, String> getHeaders(List<String> request) {
        Map<String, String> headers = new LinkedHashMap<>();
        request.stream()
                .skip(1)
                .filter(h -> h.contains(": "))
                .map(h -> h.split(": "))
                .forEach(headerKvp -> headers.put(headerKvp[0], headerKvp[1]));
        return headers;
    }

    private static String getMethod(String line) {
        return line.split("\\s+")[0];
    }

    private static String getUrl(String line) {
        return line.split("\\s+")[1];
    }

    private static List<String> getValidUrls() {
        try {
            return Arrays.asList(reader.readLine().split("\\s+"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static List<String> getRequest() {
        List<String> request = new ArrayList<>();
        String line = "";
        try {
            while ((line = reader.readLine()) != null && line.length() > 0) {
                request.add(line);
            }

            request.add(System.lineSeparator());
            if ((line = reader.readLine()) != null && line.length() > 0) {
                request.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return request;
    }
}
