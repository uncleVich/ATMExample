package client.message;

public class Message {
    private final String data;
    private final int statusCode;

    public Message(String data, int statusCode) {
        this.data = data;
        this.statusCode = statusCode;
    }

    public String getData() {
        return data;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
