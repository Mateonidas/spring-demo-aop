package aopdemo.dao;

import aopdemo.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountDAO {

    //    public void addAccount(){
    public void addAccount(Account account) {
        System.out.println(getClass() + ": ADDING ACCOUNT");
    }

    public boolean doWork(){
        System.out.println(getClass() + ": doWork()");
        return false;
    }
}
