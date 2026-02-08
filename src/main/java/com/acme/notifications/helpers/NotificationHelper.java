package com.acme.notifications.helpers;

import com.acme.notifications.domains.Result;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class NotificationHelper {

    private static final String SUCCESS_MESSAGE = "SUCCESS";

    public static Result buildSuccessResult(final String providerName) {
        return new Result(true, providerName, SUCCESS_MESSAGE);
    }

    public static Result buildFailureResult(final String providerName, final String messageException) {
        return new Result(false, providerName, messageException);
    }

    public static void printResults(final List<Result> results) {
        results.forEach(r ->
                log.info("Result: [{}]", r));
    }
}
