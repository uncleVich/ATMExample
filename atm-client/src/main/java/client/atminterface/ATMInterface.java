package client.atminterface;

import client.logic.ClientLogic;
import client.message.Message;
import org.json.JSONException;

import java.io.IOException;
import java.util.Scanner;

public class ATMInterface {
    private final Scanner scanner = new Scanner(System.in);

    public ATMInterface() {
    }

    public void runClient() throws IOException, JSONException {

        String messageToServer;
        Message resultFromServer;

        while (true) {

            messageToServer = ClientLogic.identification();
            resultFromServer = ClientLogic.doGetRequest(messageToServer);

            if (resultFromServer.getStatusCode() == 200) {
                String jsonData = resultFromServer.getData();
                if (jsonData.equals("")) {
                    System.out.println("Клиент не найден");
                    continue;
                }
                int customerId = ClientLogic.getCardId(jsonData);
                String option;

                while (true) {

                    ClientLogic.printMenu();
                    option = scanner.next();

                    switch (option) {
                        case "1":
                            messageToServer = ClientLogic.deposit(customerId);
                            resultFromServer = ClientLogic.doPostRequest(messageToServer);
                            break;
                        case "2":
                            messageToServer = ClientLogic.withdrawal(customerId);
                            resultFromServer = ClientLogic.doPostRequest(messageToServer);
                            break;
                        case "3":
                            messageToServer = ClientLogic.getBalance(customerId);
                            resultFromServer = ClientLogic.doGetRequest(messageToServer);
                            break;
                        case "4":
                            messageToServer = ClientLogic.changePIN(customerId);
                            resultFromServer = ClientLogic.doPutRequest(messageToServer);
                            break;
                        case "5":
                            System.exit(0);
                        default:
                            throw new IllegalStateException("Не верный номер операции: " + option);
                    }

                    System.out.println("\n" + resultFromServer.getData() + "\n");
                }
            } else {
                 System.out.println(resultFromServer.getData());
            }
        }
    }
}