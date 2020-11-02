//import org.apache.log4j.BasicConfigurator;
import spark.Request;
import spark.Response;
import spark.Spark;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

class RestfulServer{
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
        System.out.println("This is my body- " + request.body());
        return "{\n"
                +
                "\"body\":\"" + request.body() + "\", \n"
                + "}";

    }
    public static void main(String[] argv)  {
        RestfulServer restfulServer = new RestfulServer();
        //org.apache.log4j.BasicConfigurator.configure();
    }
}