package aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

    @Pointcut("execution(* aopdemo.dao.*.*(..))")
    private void forDaoPackage(){}

    @Pointcut("execution(* aopdemo.dao.*.get*(..)))")
    private void getter(){}

    @Pointcut("execution(* aopdemo.dao.*.set*(..)))")
    private void setter(){}

    @Pointcut("forDaoPackage() && !(getter() || setter())")
    private void forDaoPackageNoGetterSetter(){}


//    @Before("execution(public void aopdemo.dao.AccountDAO.addAccount())")
//    @Before("execution(public void add*())")
//    @Before("execution(* add*(aopdemo.Account, ..))")
//    @Before("execution(* add*(..))")
//    @Before("execution(public void addAccount())")
//    @Before("execution(* aopdemo.dao.*.*(..))")


    @Before("forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice() {

        System.out.println("\n========== Executing @Before advice on addAccount()");
    }

    @Before("forDaoPackageNoGetterSetter()")
    public void performApiAnalytics(){
        System.out.println("========== Performing API analytics");
    }
}
