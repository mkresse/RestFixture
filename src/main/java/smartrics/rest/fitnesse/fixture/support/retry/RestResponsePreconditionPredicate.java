package smartrics.rest.fitnesse.fixture.support.retry;

import org.awaitility.core.Predicate;
import smartrics.rest.client.RestResponse;

import java.util.regex.Pattern;

public class RestResponsePreconditionPredicate implements Predicate<RestResponse> {
    private final Pattern statusPattern;
    private final Pattern bodyPattern;
    private final RetryFeedback retryFeedback;

    public RestResponsePreconditionPredicate(Pattern statusPattern, Pattern bodyPattern, RetryFeedback retryFeedback) {
        this.statusPattern = statusPattern;
        this.bodyPattern = bodyPattern;
        this.retryFeedback = retryFeedback;
    }

    @Override
    public boolean matches(RestResponse response) {
        boolean statusSuccess = true;
        if (statusPattern != null) {
            statusSuccess = statusPattern.matcher(response.getStatusCode().toString()).matches();
            retryFeedback.statusMatch(statusSuccess);
        }

        boolean bodySuccess = true;
        if (bodyPattern != null) {
            bodySuccess = bodyPattern.matcher(response.getBody()).matches();
            retryFeedback.bodyMatch(bodySuccess);
        }

        return statusSuccess && bodySuccess;
    }

    public Pattern getStatusPattern() {
        return statusPattern;
    }

    public Pattern getBodyPattern() {
        return bodyPattern;
    }

    public RetryFeedback getRetryFeedback() {
        return retryFeedback;
    }
}
