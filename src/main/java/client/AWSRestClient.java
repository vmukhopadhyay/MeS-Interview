package client;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

public class AWSRestClient
{
    public String ClientGet(String url, String input) {
        String result = null;
        try {
            ClientRequest request = new ClientRequest(
                    url);
            request.accept("application/json");
            ClientResponse<String> response = request.get(String.class);

            if (response.getStatus() != 200) {
                return result = response.getResponseStatus().toString();
            }

            result = response.getEntity();
            result = response.getEntity();
        }
        catch (Exception e) { //not good to catch top level exceptions. but since this is going to be sed for a test, we should catch top level exceptions to make sure we aren't missing anything
            {
                e.printStackTrace(); //this is bad, but not sure what to print //don't have logger :(
            }
        }
        return result;
    }

    public String ClientPut(String url, String input) {
        String result = null;
        try {
            ClientRequest request = new ClientRequest(
                    url);
            request.accept("application/json");

            request.body("application/json", input);
            ClientResponse<String> response = request.put(String.class);

            if (response.getStatus() != 200) {
                return result = response.getResponseStatus().toString();
            }

            result = response.getEntity();
        }
        catch (Exception e) {
            {
                e.printStackTrace(); 
            }
        }
        return result;
    }

    public String ClientPost(String url, String input) {
        String result = null;
        try {
            ClientRequest request = new ClientRequest(
                    url);
            request.accept("application/json");

            request.body("application/json", input);
            ClientResponse<String> response = request.post(String.class);

            if (response.getStatus() != 201) {
                return result = response.getResponseStatus().toString();
            }

            result = response.getEntity();

        }
        catch (Exception e) {
            {
                e.printStackTrace(); 
            }
        }
        return result;
    }

    public String ClientDelete(String url, String input) {
        String result = null;
        try {
            ClientRequest request = new ClientRequest(
                    url);
            request.accept("application/json");
            ClientResponse<String> response = request.delete(String.class);

            if (response.getStatus() != 200) {
                return result = response.getResponseStatus().toString();
            }

            result = response.getEntity();
        }
        catch (Exception e) {
            {
                e.printStackTrace(); 
            }
        }
        return result;
    }
}
