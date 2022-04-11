package org.Base;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Network extends Base {
// build request
    // build client
    //using client send request
@Test
    public void request(){

        String uri ="https://production.ilearningengines.com/api/v0.1/delete_entity" ;
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Content-Type","application/json")
                .header("Cookie","G_ENABLED_IDPS=google")
                .header("Accept","application/json")
                .header("x-access-token","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJtZW1iZXJJZCI6IjQ2IiwibWVtYmVyVHlwZSI6MSwiaWF0IjoxNjQ5MjM1ODQ5LCJleHAiOjE2NDkzMjIyNDl9.XD4MamfsptZV1gfLXnt4zQkt-m4eAIqUdXhZl_xdPjk")
                .POST(HttpRequest.BodyPublishers.ofString("{\"entityName\":\"module\",\"entityId\":\"4517\"}"))
                .build();

        HttpClient client = HttpClient.newBuilder().build();
        try {
            HttpResponse <String> res = client.send(req, HttpResponse.BodyHandlers.ofString());
            System.out.println(res.statusCode());
            System.out.println(res.body());
            System.out.println(res.sslSession().toString());
            System.out.println(getValuesForGivenKey(res.body(),"ple"));
            System.out.println(res.headers());
            sleep(500000);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public List<String> getValuesForGivenKey(String jsonArrayStr, String key) {
        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        return IntStream.range(0, jsonArray.length())
                .mapToObj(index -> ((JSONObject)jsonArray.get(index)).optString(key))
                .collect(Collectors.toList());

    }


//{\"chapterId\":\"0\",\"status"\:\"1\",\"name\":\"testChap1\",\"shortName\":\"testChap1\",\"description\":\"xdzfdfdfsdgvszdegvbsfrzgrez\",\"stillImage\":\""\,\"categoryId\":\"3\",\"curriculumId\":\"1\",\"modifiable\":1,\"moduleIds\":\"6458|1\",\"testIds\":\"\",\"confirm\":1}
//"{\"memberLogin\":\"duraiaida2@gmail.com\",\"memberPwd\":\"Teacher@123\"}"
}