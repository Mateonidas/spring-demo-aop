package aopdemo;

import aopdemo.service.TrafficFortuneService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.logging.Logger;

public class AroundWithLoggerDemoApp {

    private static Logger logger = Logger.getLogger(AroundWithLoggerDemoApp.class.getName());

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

        TrafficFortuneService fortuneService = context.getBean("trafficFortuneService", TrafficFortuneService.class);

        logger.info("\nMaing Program: AroundDemoApp");

        logger.info("\nCalling getFortune");

        String data = fortuneService.getFortune();

        logger.info("\nMy fortune is: " + data);

        logger.info ("Finished");

        context.close();
    }
}
