package com.stady.cars;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextClosedEvent;


import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class ContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        WireMockServer wmServer = new WireMockServer(WireMockConfiguration.wireMockConfig().port(8082).usingFilesUnderClasspath("wiremock/"));
        wmServer.start();
        configurableApplicationContext.getBeanFactory().registerSingleton("wireMock", wmServer);
        configurableApplicationContext.addApplicationListener(event -> {
            if (event instanceof ContextClosedEvent) {
                wmServer.stop();
            }
        });
        TestPropertyValues testPropertyValues = TestPropertyValues.of(
                "adapter.rostelecom.url=http://localhost:" + wmServer.port()
        );
        testPropertyValues.applyTo(configurableApplicationContext.getEnvironment());
    }
}