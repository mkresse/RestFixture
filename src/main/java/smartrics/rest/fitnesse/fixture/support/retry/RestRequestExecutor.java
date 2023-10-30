package smartrics.rest.fitnesse.fixture.support.retry;

import smartrics.rest.client.RestClient;
import smartrics.rest.client.RestRequest;
import smartrics.rest.client.RestResponse;

import java.util.concurrent.Callable;

public class RestRequestExecutor implements Callable<RestResponse> {
    private final RestClient restClient;
    private final RestRequest request;
    private RestResponse lastResponse;

    public RestRequestExecutor(RestClient restClient, RestRequest request) {
        this.restClient = restClient;
        this.request = request;
    }

    @Override
    public RestResponse call() {
        lastResponse = restClient.execute(request);
        return lastResponse;
    }

    public RestResponse getLastResponse() {
        return lastResponse;
    }
}
