package aopdemo;

import aopdemo.service.TrafficFortuneService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.logging.Logger;

public class AroundHandleExceptionDemoApp {

    private static Logger logger = Logger.getLogger(AroundHandleExceptionDemoApp.class.getName());

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

        TrafficFortuneService fortuneService = context.getBean("trafficFortuneService", TrafficFortuneService.class);

        logger.info("\nMaing Program: AroundDemoApp");

        logger.info("\nCalling getFortune");

        boolean tripWire = true;
        String data = fortuneService.getFortune(tripWire);

        logger.info("\nMy fortune is: " + data);

        logger.info ("Finished");

        context.close();
    }
}
