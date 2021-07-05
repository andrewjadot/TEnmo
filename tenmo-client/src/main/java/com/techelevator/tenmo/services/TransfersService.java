package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfers;

import com.techelevator.tenmo.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class TransfersService {
    private String BASE_URL;
    private RestTemplate restTemplate = new RestTemplate();
    private AuthenticatedUser currentUser;


    public TransfersService(String url, AuthenticatedUser currentUser){
        this.currentUser = currentUser;
        BASE_URL = url;
    }

    public Transfers[] transfersList(){
        Transfers[] transfers = null;
        transfers = restTemplate.exchange(BASE_URL + "accounts/transfers/" + currentUser.getUser().getId(), HttpMethod.GET, makeAuthEntity(), Transfers[].class).getBody();
        System.out.println("----------------------------------\n" +
                            "Transfers List\n" +
                            "ID        FROM/TO          AMOUNT\n" +
                            "----------------------------------\n");
        String fromTo = "";
        String username = "";
        for (Transfers newTransfer : transfers){
            if (currentUser.getUser().getId() == newTransfer.getAccountFrom()){
                fromTo = "From: ";
                username = newTransfer.getUserFrom();
            } else {
                fromTo = "To: ";
                username = newTransfer.getUserTo();
            }
            System.out.println(newTransfer.getTransferId() + "       " + fromTo + " " + username + "    " + newTransfer.getAmount());
        }
        System.out.println("---------------------------------\n");
        return transfers;
    }

    public void sendBucks() {
        User[] users = null;
        try {
            Transfers transfers = new Transfers();
            Scanner scanner = new Scanner(System.in);
            users = restTemplate.exchange(BASE_URL + "listusers", HttpMethod.GET, makeAuthEntity(), User[].class).getBody();
            System.out.println("----------------------------\n" +
                    "USERS\n" +
                    "ID         NAME\n" +
                    "---------------------------\n");

            for (User newUser : users) {
                        System.out.println(newUser.getId() + "      " + newUser.getUsername());
                }
                System.out.println("------------------------\n" +
                        "Enter ID of user you are sending money to (0 to cancel): ");
                transfers.setAccountTo(Integer.parseInt(scanner.nextLine()));
                transfers.setAccountFrom(currentUser.getUser().getId());
                if (transfers.getAccountTo() > 0) {
                    System.out.println("Enter amount: ");
                    try {
                        String newInput = scanner.nextLine();
                        BigDecimal big = new BigDecimal(newInput);
                        transfers.setAmount(big);
                    } catch (NumberFormatException e){
                        System.out.println("Error for amount entered");
                    }
                    String result = restTemplate.exchange(BASE_URL + "transfers/", HttpMethod.POST, makeTransferEntity(transfers), String.class).getBody();
                    System.out.println(result);
                }

            }catch(Exception e){
            System.out.println("Bad Input");
        }
    }


    //Helper Methods
    private HttpEntity<Transfers> makeTransferEntity(Transfers transfer) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(currentUser.getToken());
        HttpEntity<Transfers> entity = new HttpEntity<>(transfer, headers);
        return entity;
    }

    private HttpEntity makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(currentUser.getToken());
        HttpEntity entity = new HttpEntity<>(headers);
        return entity;
    }
}
