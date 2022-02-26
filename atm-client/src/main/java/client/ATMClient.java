package client;

import client.atminterface.ATMInterface;
import org.json.JSONException;

import java.io.IOException;

public class ATMClient {
    public static void main(String[] args) {
        ATMInterface atmInterface = new ATMInterface();
        try {
            atmInterface.runClient();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}
