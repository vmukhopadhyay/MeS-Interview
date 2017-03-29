package client;


public class AWSRestCommentsTest 
{
    AWSRestClient client = new AWSRestClient();
    private final static String COMMENTS_GET_URL ="http://ec2-34-205-84-73.compute-1.amazonaws.com:3000/comments/3";
    private final static String COMMENTS_PUT_URL ="http://ec2-34-205-84-73.compute-1.amazonaws.com:3000/comments/1";
    private final static String COMMENTS_POST_URL ="http://ec2-34-205-84-73.compute-1.amazonaws.com:3000/comments";
    private final static String COMMENTS_DELETE_URL ="http://ec2-34-205-84-73.compute-1.amazonaws.com:3000/comments/10000";

    /*
    Testing basic get functionality
    get by ID -> compare results with existing values -> More towards golden copy testing
    Test to see if resource is returning what we are expected
     */
    public void testGetComments() {
        String result = client.ClientGet(COMMENTS_GET_URL, null);
        String output = "{\n" +
                "  \"postId\": 1,\n" +
                "  \"id\": 3,\n" +
                "  \"name\": \"odio adipisci rerum aut animi\",\n" +
                "  \"email\": \"Nikita@garfield.biz\",\n" +
                "  \"body\": \"quia molestiae reprehenderit quasi aspernatur\\naut expedita occaecati aliquam eveniet laudantium\\nomnis quibusdam delectus saepe quia accusamus maiores nam est\\ncum et ducimus et vero voluptates excepturi deleniti ratione\"\n" +
                "}";

        if(result.equals(output))
        {
            System.out.println("Get Comments Test Passed !!!");
        }
        else
        {
            System.out.println("Get Comments Test Failed !!!");
        }
    }

    /*
    Basic PUT testing. Given an id, testing if updating of values works
    checking if the result is what we expect
     */
    public void testPutComments() {
        String resultPut = client.ClientPut(COMMENTS_PUT_URL, "{\"name\":\"vaish testing\"}");
        String result = client.ClientGet(COMMENTS_PUT_URL, null);
        String output = "{\n" +
                "  \"name\": \"vaish testing\",\n" +
                "  \"id\": 1\n" +
                "}";

        if(result.equals(output))
        {
            System.out.println("Put Comments Test Passed !!!");
        }
        else
        {
            System.out.println("Put Comments Test Failed !!!");
        }
    }

    /*
    Insertion of data using POST
    using GET command to check if the data inserted is as expected
     */
    public void testPostComments() {
        String resultPost = client.ClientPost(COMMENTS_POST_URL, "{\"postId\":100,\"id\":10000,\"name\":\"vaish testing\",\"email\":\"vaish@testing.something\",\"body\":\"something\"}");
        String result = client.ClientGet(COMMENTS_POST_URL.concat("/10000"), null);
        String output = "{\n" +
                "  \"postId\": 100,\n" +
                "  \"id\": 10000,\n" +
                "  \"name\": \"vaish testing\",\n" +
                "  \"email\": \"vaish@testing.something\",\n" +
                "  \"body\": \"something\"\n" +
                "}";

        if(result.equals(output))
        {
            System.out.println("Post Comments Test Passed !!!");
        }
        else
        {
            System.out.println("Post Comments Test Failed !!!");
        }
    }

    /*
    Delete by ID.
    Get on an deleted row will fail. Hence doing a enpty result set check.
     */
    public void testDeleteComments() {
        String result = client.ClientDelete(COMMENTS_DELETE_URL, null);
        String output = "{}";

        if(result.equals(output))
        {
            System.out.println("Delete Comments Test Passed !!!");
        }
        else
        {
            System.out.println("Delete Comments Test Failed !!!");
        }
    }

    public static void main(String[] args) throws Exception
    {
        AWSRestCommentsTest clientTest = new AWSRestCommentsTest();
        clientTest.testGetComments();
        clientTest.testPutComments();
        clientTest.testPostComments();
        clientTest.testDeleteComments();
    }
}
