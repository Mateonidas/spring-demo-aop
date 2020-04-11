package aopdemo.dao;

import org.springframework.stereotype.Component;

@Component
public class AccountDAO {

    //    public void addAccount(){
    public boolean addTestAccount() {
        System.out.println(getClass() + ": ADDING ACCOUNT");

        return true;
    }
}
