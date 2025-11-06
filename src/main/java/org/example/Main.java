package org.example;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.example.accounts.services.InterestCronService;

public class Main {

    public static void main(String[] args) {
        // App app = new App();
        // app.run();

        Injector injector = Guice.createInjector(new BankInjector());
        InterestCronService cronService = injector.getInstance(InterestCronService.class);
        cronService.start();
        App app = injector.getInstance(App.class);
        app.run();
    }
}