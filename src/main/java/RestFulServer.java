//import org.apache.log4j.BasicConfigurator;
import spark.Request;
import spark.Response;
import spark.Spark;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

class RestfulServer{

    public static int counter = 52; //size of deck
    public static int dealerTotal = 0, playerTotal =0;
    public static ArrayList<String> deck = new ArrayList<String>(){
        {
            add("Ace Hearts");
            add("Ace Diamonds");
            add("Ace Clubs");
            add("Ace Spades");
            add("Two Hearts");
            add("Two Diamonds");
            add("Two Clubs");
            add("Two Spades");
            add("Three Hearts");
            add("Three Diamonds");
            add("Three Clubs");
            add("Three Spades");
            add("Four Hearts");
            add("Four Diamonds");
            add("Four Clubs");
            add("Four Spades");
            add("Five Hearts");
            add("Five Diamonds");
            add("Five Clubs");
            add("Five Spades");
            add("Six Hearts");
            add("Six Diamonds");
            add("Six Clubs");
            add("Six Spades");
            add("Seven Hearts");
            add("Seven Diamonds");
            add("Seven Clubs");
            add("Seven Spades");
            add("Eight Hearts");
            add("Eight Diamonds");
            add("Eight Clubs");
            add("Eight Spades");
            add("Nine Hearts");
            add("Nine Diamonds");
            add("Nine Clubs");
            add("Nine Spades");
            add("Ten Hearts");
            add("Ten Diamonds");
            add("Ten Clubs");
            add("Ten Spades");
            add("Jack Hearts");
            add("Jack Diamonds");
            add("Jack Clubs");
            add("Jack Spades");
            add("Queen Hearts");
            add("Queen Diamonds");
            add("Queen Clubs");
            add("Queen Spades");
            add("King Hearts");
            add("King Diamonds");
            add("King Clubs");
            add("King Spades");
        }
    };

    public static ArrayList<String> deckBackup = deck;

    public static String getCard(){
        Random rand = new Random();
        int x = rand.nextInt(counter);
        String card = deck.get(x);
        deck.remove(x);
        counter--;
        return card;
    }

    public static int getCardValue(String card){
        String temp = card.substring(0, card.indexOf(' '));
        switch (temp){
            case "Ace":
                return 1;
            case "Two":
                return 2;
            case "Three":
                return 3;
            case "Four":
                return 4;
            case "Five":
                return 5;
            case "Six":
                return 6;
            case "Seven":
                return 7;
            case "Eight":
                return 8;
            case "Nine":
                return 9;
            case "Ten":
                return 10;
            case "Jack":
                return 11;
            case "Queen":
                return 12;
            case "King":
                return 13;
            default:
                return 0;
        }

    }

    public RestfulServer(){
        this.configureRestfulApiServer();
        this.processRestfulAPIRequests();
    }

    private void configureRestfulApiServer(){
        //Starting a Spark Micro server listening on the port specified
        Spark.port(5542);
        System.out.println("Server Configured to listen on port 5542");
    }
    // configure Spark's API request routs
    private void  processRestfulAPIRequests(){
        //stub for the http get method and first
        Spark.get("/", this::echoRequest);
    }
    // method that implements a spark rout
    private String echoRequest(Request request, Response response){
        //set a response content type to application/json
        response.type("application/json");
        response.header("Access-Control-Allow-Origin", "*");
        response.status(200); // status is successful
        return HttpRequestToJson(request);
    }

    //return all the properties of the requests coming in
    private String HttpRequestToJson(Request request){


        String finRet = handleDealer(request);

        //System.out.println("Players choice - " +request.queryParams("playerChoice"));
        //System.out.println("We are in requestJSON "+ ret);
        //DealerCard_DealerTotal_PlayerCard_PlayerTotal

        return finRet;

    }

    public static String handleDealer(Request request) {

        String ret ="";
        System.out.print("Trying to Reset...\n");
        if (request.queryParams("reset")!=null){
            System.out.print("Getting closer ...\n");
            if(request.queryParams("reset").equals("true")){
                counter = 52;
                dealerTotal = 0;
                playerTotal = 0;
                deck = deckBackup;
                System.out.print("Game Reset.");

                return ret;
            }
        }


            if(request.queryParams("playerChoice").equals("hit")){
                String card = getCard();
                int cardValue = getCardValue(card);
                //System.out.println("Player got: " + card + " with Value of : "+ cardValue);
                playerTotal+=cardValue;
                if (playerTotal>21){
                    ret += "BUST: " + card + " --> TOTAL: "+playerTotal;
                }else {
                    ret += "HIT: " + card + " --> TOTAL: " + playerTotal;
                }
            }else{
                ret += "HOLD --> TOTAL: "+playerTotal;
            }

        if(dealerTotal<17){
            String card = getCard();
            int cardValue = getCardValue(card);
            //System.out.println("Player got: " + card + " with Value of : "+ cardValue);
            dealerTotal+=cardValue;
            if (dealerTotal>21){
                ret += ",BUST: " + card + " --> TOTAL: "+dealerTotal;
            }else {
                ret += ",HIT: " + card + " --> TOTAL: " + dealerTotal;
            }
        }else{
            ret += ",HOLD --> TOTAL: "+dealerTotal;
        }

        return ret;
    }
    public static void main(String[] argv)  {
        RestfulServer restfulServer = new RestfulServer();
        //org.apache.log4j.BasicConfigurator.configure();
    }
}