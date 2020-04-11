package aopdemo;

import aopdemo.dao.AccountDAO;
import aopdemo.dao.MembershipDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainDemoApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

        AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);

        Account account = new Account();
        account.setName("John");
        account.setLevel("Platinum");

        accountDAO.addAccount(account);
        accountDAO.doWork();

        accountDAO.setName("Test");
        accountDAO.setServiceCode("Silver");

        String name = accountDAO.getName();
        String serviceCode = accountDAO.getServiceCode();

        MembershipDAO membershipDAO = context.getBean("membershipDAO", MembershipDAO.class);
        membershipDAO.addAccount();
        membershipDAO.goToSleep();

        context.close();
    }
}
