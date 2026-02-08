package com.acme.notifications;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public abstract class BaseTest {

    protected ExecutorService executorService =
            Executors.newFixedThreadPool(10);

}
