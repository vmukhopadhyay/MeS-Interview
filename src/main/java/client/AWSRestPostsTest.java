package client;


public class AWSRestPostsTest
{
    AWSRestClient client = new AWSRestClient();
    private final static String POSTS_GET_URL ="http://ec2-34-205-84-73.compute-1.amazonaws.com:3000/posts/3";
    private final static String POSTS_PUT_URL ="http://ec2-34-205-84-73.compute-1.amazonaws.com:3000/posts/1";
    private final static String POSTS_POST_URL ="http://ec2-34-205-84-73.compute-1.amazonaws.com:3000/posts";
    private final static String POSTS_DELETE_URL ="http://ec2-34-205-84-73.compute-1.amazonaws.com:3000/posts/10000";
    private final static String POSTS_MALFORMED_RESOURCE ="http://ec2-34-205-84-73.compute-1.amazonaws.com:3000/posts1565/10000";

    /*
    Testing basic get functionality
    get by ID -> compare results with existing values -> More towards golden copy testing
    Test to see if resource is returning what we are expected
     */
    public void testGetPosts() {
        String result = client.ClientGet(POSTS_GET_URL, null);
        String output = "{\n" +
                "  \"userId\": 1,\n" +
                "  \"id\": 3,\n" +
                "  \"title\": \"ea molestias quasi exercitationem repellat qui ipsa sit aut\",\n" +
                "  \"body\": \"et iusto sed quo iure\\nvoluptatem occaecati omnis eligendi aut ad\\nvoluptatem doloribus vel accusantium quis pariatur\\nmolestiae porro eius odio et labore et velit aut\"\n" +
                "}";

        if(result.equals(output))
        {
            System.out.println("Get posts Test Passed !!!");
        }
        else
        {
            System.out.println("Get posts Test Failed !!!");
        }
    }

    /*
    non existant id -> should return empty result
     */
    public void testGetByNonExistantIdPosts() {
        String result = client.ClientGet(POSTS_POST_URL.concat("/5248"), null);
        String output = "Not Found";

        if(result.equals(output))
        {
            System.out.println("Get posts Test Passed !!!");
        }
        else
        {
            System.out.println("Get posts Test Failed !!!");
        }
    }

    /*
    Basic PUT testing. Given an id, testing if updating of values works
    checking if the result is what we expect
     */
    public void testPutPosts() {
        String resultPut = client.ClientPut(POSTS_PUT_URL, "{\"title\":\"something\"}");
        String result = client.ClientGet(POSTS_PUT_URL, null);
        String output = "{\n" +
                "  \"title\": \"something\",\n" +
                "  \"id\": 1\n" +
                "}";

        if(result.equals(output))
        {
            System.out.println("Put posts Test Passed !!!");
        }
        else
        {
            System.out.println("Put posts Test Failed !!!");
        }
    }

    /*
    Insertion of data using POST
    Posting twice for same Id will return 500
    using GET command to check if the data inserted is as expected
     */
    public void testPostPosts() {
        String resultPost = client.ClientPost(POSTS_POST_URL, "{\"userId\":100,\"id\":10000,\"title\":\"vaish testing\",\"body\":\"vaish testing\"}");
        String result = client.ClientGet(POSTS_POST_URL.concat("/10000"), null);
        String output = "{\n" +
                "  \"userId\": 100,\n" +
                "  \"id\": 10000,\n" +
                "  \"title\": \"vaish testing\",\n" +
                "  \"body\": \"vaish testing\"\n" +
                "}";

        if(result.equals(output))
        {
            System.out.println("Post posts Test Passed !!!");
        }
        else
        {
            System.out.println("Post posts Test Failed !!!");
        }
    }

    /*
    Insertion of data using POST
    Posting twice for same Id will return 500
     */
    public void testDoublePostPosts() {
        String result = client.ClientPost(POSTS_POST_URL, "{\"userId\":100,\"id\":10000,\"title\":\"vaish testing\",\"body\":\"vaish testing\"}");
        String output = "Internal Server Error";

        if(result.equals(output))
        {
            System.out.println("Double Post posts Test Passed !!!");
        }
        else
        {
            System.out.println("Double Post posts Test Failed !!!");
        }
    }

    /*
    Delete by ID.
    Get on an deleted row will fail. Hence doing a empty result set check.
     */
    public void testDeletePosts() {
        String result = client.ClientDelete(POSTS_DELETE_URL, null);
        String output = "{}";

        if(result.equals(output))
        {
            System.out.println("Delete posts Test Passed !!!");
        }
        else
        {
            System.out.println("Delete posts Test Failed !!!");
        }
    }

    /*
    Checking malformed URL.
    Failure expected
     */
    public void testMalformedURL() {
        String result = client.ClientGet(POSTS_MALFORMED_RESOURCE, null);
        String output = "Not Found";

        if(result.equals(output))
        {
            System.out.println("Delete posts Test Passed !!!");
        }
        else
        {
            System.out.println("Delete posts Test Failed !!!");
        }
    }

        public static void main(String[] args) throws Exception
    {
        AWSRestPostsTest clientTest = new AWSRestPostsTest();
        clientTest.testGetPosts();
        clientTest.testGetByNonExistantIdPosts();
        clientTest.testPutPosts();
        clientTest.testPostPosts();
        clientTest.testDoublePostPosts();
        clientTest.testDeletePosts();
        clientTest.testMalformedURL();
    }
}
