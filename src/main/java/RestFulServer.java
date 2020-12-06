//import org.apache.log4j.BasicConfigurator;
import spark.Request;
import spark.Response;
import spark.Spark;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

class RestfulServer{

    public static int counter = 4; //size of deck
    public static ArrayList<String> deck = new ArrayList<String>(){
        {
            add("Ace Hearts");
            add("Ace Diamonds");
            add("Ace Clubs");
            add("Ace Spades");
        }
    };

    public static String getCard(){
        Random rand = new Random();
        int x = rand.nextInt(counter);
        String card = deck.get(x);
        deck.remove(x);
        counter--;
        return card;
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


        String ret = "default";
        ret = handleDealer(request);

        System.out.println("Dealers choice - " +request.queryParams("DealerHit"));
        System.out.println("Players choice - " +request.queryParams("PlayerHit"));
        System.out.println("We are in requestJSON "+ ret);
            //DealerCard_DealerTotal_PlayerCard_PlayerTotal

        return ret;

    }

    public static String handleDealer(Request request){
            if(request.queryParams("DealerHit")!=null){
                String card = getCard();
                System.out.println("We are here " + card);
                return card;
            }
        System.out.println("We are in the else part ");
        return "lol";
    }
    public static void main(String[] argv)  {
        RestfulServer restfulServer = new RestfulServer();
        //org.apache.log4j.BasicConfigurator.configure();
    }
}