package client;


public class AWSRestUsersTest 
{
    AWSRestClient client = new AWSRestClient();
    private final static String USERS_GET_URL ="http://ec2-34-205-84-73.compute-1.amazonaws.com:3000/users/3";
    private final static String USERS_PUT_URL ="http://ec2-34-205-84-73.compute-1.amazonaws.com:3000/users/1";
    private final static String USERS_POST_URL ="http://ec2-34-205-84-73.compute-1.amazonaws.com:3000/users";
    private final static String USERS_DELETE_URL ="http://ec2-34-205-84-73.compute-1.amazonaws.com:3000/users/10000";

    /*
    Testing basic get functionality
    get by ID -> compare results with existing values -> More towards golden copy testing
    Test to see if resource is returning what we are expected
     */
    public void testGetUsers() {
        String result = client.ClientGet(USERS_GET_URL, null);
        String output = "{\n" +
                "  \"id\": 3,\n" +
                "  \"name\": \"Clementine Bauch\",\n" +
                "  \"username\": \"Samantha\",\n" +
                "  \"email\": \"Nathan@yesenia.net\",\n" +
                "  \"address\": {\n" +
                "    \"street\": \"Douglas Extension\",\n" +
                "    \"suite\": \"Suite 847\",\n" +
                "    \"city\": \"McKenziehaven\",\n" +
                "    \"zipcode\": \"59590-4157\",\n" +
                "    \"geo\": {\n" +
                "      \"lat\": \"-68.6102\",\n" +
                "      \"lng\": \"-47.0653\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"phone\": \"1-463-123-4447\",\n" +
                "  \"website\": \"ramiro.info\",\n" +
                "  \"company\": {\n" +
                "    \"name\": \"Romaguera-Jacobson\",\n" +
                "    \"catchPhrase\": \"Face to face bifurcated interface\",\n" +
                "    \"bs\": \"e-enable strategic applications\"\n" +
                "  }\n" +
                "}";

        if(result.equals(output))
        {
            System.out.println("Get Users Test Passed !!!");
        }
        else
        {
            System.out.println("Get Users Test Failed !!!");
        }
    }

    /*
    Basic PUT testing. Given an id, testing if updating of values works
    checking if the result is what we expect
     */
    public void testPutUsers() {
        String resultPut = client.ClientPut(USERS_PUT_URL, "{\"name\":\"vaish testing\"}");
        String result = client.ClientGet(USERS_PUT_URL, null);
        String output = "{\n" +
                "  \"name\": \"vaish testing\",\n" +
                "  \"id\": 1\n" +
                "}";

        if(result.equals(output))
        {
            System.out.println("Put Users Test Passed !!!");
        }
        else
        {
            System.out.println("Put Users Test Failed !!!");
        }
    }

    /*
    Insertion of data using POST
    using GET command to check if the data inserted is as expected
     */
    public void testPostUsers() {
        String resultPost = client.ClientPost(USERS_POST_URL, "{\"postId\":100,\"id\":10000,\"name\":\"vaish testing\",\"email\":\"vaish@testing.something\",\"address\": { \"street\":\"something\",\"suite\":\"something\",\"city\":\"something\",\"zipcode\":\"something\",\"gep\": { \"lat\":\"something\",\"lng\":\"something\"} }, \"phone\":\"something\",\"website\":\"something.info\",\"company\": {\"name\":\"something\",\"catchPhrase\":\"something\", \"bs\":\"something\" } }");
        String result = client.ClientGet(USERS_POST_URL.concat("/10000"), null);
        String output = "{\n" +
                "  \"postId\": 100,\n" +
                "  \"id\": 10000,\n" +
                "  \"name\": \"vaish testing\",\n" +
                "  \"email\": \"vaish@testing.something\",\n" +
                "  \"address\": {\n" +
                "    \"street\": \"something\",\n" +
                "    \"suite\": \"something\",\n" +
                "    \"city\": \"something\",\n" +
                "    \"zipcode\": \"something\",\n" +
                "    \"gep\": {\n" +
                "      \"lat\": \"something\",\n" +
                "      \"lng\": \"something\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"phone\": \"something\",\n" +
                "  \"website\": \"something.info\",\n" +
                "  \"company\": {\n" +
                "    \"name\": \"something\",\n" +
                "    \"catchPhrase\": \"something\",\n" +
                "    \"bs\": \"something\"\n" +
                "  }\n" +
                "}";

        if(result.equals(output))
        {
            System.out.println("Post Users Test Passed !!!");
        }
        else
        {
            System.out.println("Post Users Test Failed !!!");
        }
    }

    /*
    Delete by ID.
    Get on an deleted row will fail. Hence doing a empty result set check.
     */
    public void testDeleteUsers() {
        String result = client.ClientDelete(USERS_DELETE_URL, null);
        String output = "{}";

        if(result.equals(output))
        {
            System.out.println("Delete Users Test Passed !!!");
        }
        else
        {
            System.out.println("Delete Users Test Failed !!!");
        }
    }

    public static void main(String[] args) throws Exception
    {
        AWSRestUsersTest clientTest = new AWSRestUsersTest();
        clientTest.testGetUsers();
        clientTest.testPutUsers();
        clientTest.testPostUsers();
        clientTest.testDeleteUsers();
    }
}
