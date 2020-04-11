package aopdemo;

import aopdemo.dao.AccountDAO;
import aopdemo.service.TrafficFortuneService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AroundDemoApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

        TrafficFortuneService fortuneService = context.getBean("trafficFortuneService", TrafficFortuneService.class);

        System.out.println("\nMaing Program: AroundDemoApp");

        System.out.println("\nCalling getFortune");

        String data = fortuneService.getFortune();

        System.out.println("\nMy fortune is: " + data);

        System.out.println("Finished");

        context.close();
    }
}
