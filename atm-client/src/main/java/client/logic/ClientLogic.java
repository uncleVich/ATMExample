package client.logic;

import client.message.Message;
import com.squareup.okhttp.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Scanner;

public class ClientLogic {

    private static double amount;
    private static final Scanner scanner = new Scanner(System.in);
    private static final OkHttpClient client = new OkHttpClient();
    private static final String baseUrl = "http://localhost:8090/atm/card";

    public static void printMenu() {
        System.out.println("1. Внесение наличных");
        System.out.println("2. Снятие наличных");
        System.out.println("3. Узнать баланс");
        System.out.println("4. Изменить PIN");
        System.out.println("5. Выйти");
        System.out.print("Введите номер операции: ");
    }

    public static String identification() {
        System.out.print("Введите номер карты: ");
        String cardNumber = scanner.next();
        System.out.print("Введите PIN код: ");
        int pin = scanner.nextInt();

        return baseUrl + "/authenticate?cardNumber=" + cardNumber + "&pin=" + pin;
    }

    public static String deposit(int cardId) {
        System.out.println("Введите сумму для внесения на счет: ");
        amount = scanner.nextDouble();

        return baseUrl + "/" + cardId + "/deposit?amount=" + amount;
    }

    public static String withdrawal(int cardId) {
        System.out.print("Введите сумму для снятия: ");
        amount = scanner.nextDouble();

        return baseUrl + "/" + cardId + "/withdraw?amount=" + amount;
    }

    public static String getBalance(int cardId) {
        return baseUrl + "/" + cardId + "/balance";
    }

    public static String changePIN(int cardId) {
        System.out.print("Введите новый PIN: ");
        int newPIN = scanner.nextInt();

        return baseUrl + "/" + cardId + "/changePin?newPin=" + newPIN;
    }

    public static int getCardId(String jsonData) throws JSONException {
        JSONObject cardObject = new JSONObject(jsonData);

        return cardObject.getInt("id");
    }

    public static Message doGetRequest(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        String data = response.body().string();
        int statusCode = response.code();

        return new Message(data, statusCode);
    }

    public static Message doPostRequest(String url) throws IOException {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, "");
        Request request = new Request.Builder().url(url).post(body).build();
        Response response = client.newCall(request).execute();
        String data = response.body().string();
        int statusCode = response.code();

        return new Message(data, statusCode);
    }

    public static Message doPutRequest(String url) throws IOException {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, "");
        Request request = new Request.Builder().url(url).put(body).build();
        Response response = client.newCall(request).execute();
        String data = response.body().string();
        int statusCode = response.code();

        return new Message(data, statusCode);
    }

}