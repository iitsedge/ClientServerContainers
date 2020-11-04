//import org.apache.log4j.BasicConfigurator;

import spark.Request;
import spark.Response;
import spark.Spark;

import java.util.*;

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

    public String getCard(){
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
        String hold, hit, str;
        hit = "You have decided to take another card!\n";
        hold = "You have decided to keep your current hand!\n";
        str = "Could not parse decision.\n";

        if (request.body().equalsIgnoreCase("HIT")){
            String card = getCard();
            System.out.println(hit);
            System.out.println("Card dealt: "+card);
            System.out.println("Cards remaining in deck: "+counter);
            System.out.println("Size of Array: "+deck.size());
            return card;
        }
        else if(request.body().equalsIgnoreCase("HOLD")){
            System.out.println(hold);
            System.out.println("Request Body: " + request.body());
            return hold;
        }else{
            System.out.println(str);
            System.out.println("Request Body: " + request.body());
            return str;
        }
        //System.out.println("This is my body- " + request.body());


    }
    public static void main(String[] argv)  {
        RestfulServer restfulServer = new RestfulServer();
        //org.apache.log4j.BasicConfigurator.configure();
    }
}