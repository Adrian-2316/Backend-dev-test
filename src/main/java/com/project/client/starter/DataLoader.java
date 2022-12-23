package com.project.client.starter;

import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Component
@AllArgsConstructor
public class DataLoader implements ApplicationRunner {

    private DataLoaderVerifier dataLoaderVerifier;

    /**
     * Method used to save static values when the application starts.
     *
     * @param args - ApplicationArguments.
     */
    @Override
    public void run(ApplicationArguments args) throws IOException, ExecutionException, InterruptedException {
        dataLoaderVerifier.initializeCommonSearches();

    }
}
