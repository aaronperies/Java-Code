/*mentioning https://docs.openexchangerates.org/ as required to, although free,
still needed to generate an API key in order to use the free conversion endpoint*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Scanner;

import org.json.JSONObject;

//class declaration
public class CurrencyConverter {

    public static void main(String[] args) throws MalformedURLException, IOException{
        //this hashmap will be used to store the currency names
        HashMap<Integer, String> currencyCode = new HashMap<Integer, String>();

        //we also need to grab input from the user, which we can do using Scanner
        Scanner input = new Scanner(System.in);

        //adding the code
        currencyCode.put(1, "EUR");
        currencyCode.put(2, "AUD");
        currencyCode.put(3, "CAD");
        currencyCode.put(4, "USD");
        currencyCode.put(5, "GBP");
        currencyCode.put(6, "Exit System");
        
        //variables declared to take input from the user.
        Integer fromCurrency, toCurrency;
        String fr, to;
        Double amount;

        //display menu
        System.out.println("<<<<<<<< Welcome to the Currency Exchange! >>>>>>>>>\n");
        System.out.println("What is your input currency?");

        //this for loop will iterate through the values of the hashmap and display them
        for (int i = 1; i <= currencyCode.size(); i++) {
            System.out.println(i + ": " + currencyCode.get(i));
        }

        //collect input from user in numerical form to make it simple
        System.out.println("Enter: ");
        //fromCurrency = currencyCode.get(input.nextInt());
        fromCurrency = input.nextInt();

        //this while loop will validate the input we are getting from user and retry
        while (fromCurrency < 1 || fromCurrency > 5) {
            System.out.println("\n You have entered an invalid input, please try again!");
            fromCurrency = input.nextInt();
        }
        fr = currencyCode.get(fromCurrency);

        System.out.println("\n What is the currency you desire? ");
        //this for loop will iterate through the values of the hashmap and display them
        for (int i = 1; i <= currencyCode.size(); i++) {
            System.out.println(i + ": " + currencyCode.get(i));
        }

        System.out.println("Enter: ");
        toCurrency = input.nextInt();
        while (toCurrency < 1 || toCurrency > 5) {
            System.out.println("\n You have entered an invalid input, please try again!");
            fromCurrency = input.nextInt();
        }
        to = currencyCode.get(toCurrency);

        System.out.println("\nHow much do you wish to convert? ");
        amount = input.nextDouble();

        //we need the exchange rates
        sendHttpGETRequest(fr, to, amount);

        //final print statements
        System.out.println("Here are your funds, thank you for using the currency converter!\n");

        input.close();
    }

    /*the reason we have added exception throws is because some of our code has the potential to throw an exception
     and we want to be able to deal with that without causing the program to crash */
    private static void sendHttpGETRequest(String fr, String to, Double am) throws MalformedURLException, IOException{

        //this class allows us to format values that contain decimal points
        DecimalFormat format = new DecimalFormat("00.00");

        //setting up API call using the obtained API_KEY
        String GET_URL = "https://v6.exchangerate-api.com/v6/79c6beb4266369fa97b6fdfe/pair/" + fr + "/" + to;
        URL url = new URL(GET_URL);

        //setting up HTTP connection
        HttpURLConnection httpURLConnection =  (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        int resCode = httpURLConnection.getResponseCode();

        if (resCode == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLineString;
            StringBuffer response = new StringBuffer();

            while ((inputLineString = br.readLine()) != null) {
                response.append(inputLineString);
            }

            //close the buffered reader
            br.close();

            //since the API returns a JSON object, we will store the return value inside a similar data type
            JSONObject json = new JSONObject(response.toString());
            /*JSON objects tend to store data in the form of key-value pairs that make it easy to 
            access what we want from the object without having to use the entire set of data*/
            Double exchangeRate = json.getDouble("conversion_rate");
            System.out.println("The exchange rate is currently: " + exchangeRate + "\n");
            System.out.println(format.format(am) + " " + fr + " = " + format.format(am/exchangeRate) + " " + to);
        }
        else{
            System.out.println("GET request failed!");
        }
    }
}