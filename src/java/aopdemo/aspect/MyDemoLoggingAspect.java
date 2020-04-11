package aopdemo.aspect;

import aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

//    @Before("execution(public void aopdemo.dao.AccountDAO.addAccount())")
//    @Before("execution(public void add*())")
//    @Before("execution(* add*(aopdemo.Account, ..))")
//    @Before("execution(* add*(..))")
//    @Before("execution(public void addAccount())")
//    @Before("execution(* aopdemo.dao.*.*(..))")

    @After("aopdemo.aspect.AopExpressions.forFindAccounts()")
    public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint) {

        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n========== Executing @After (finally) on method: " + method);

    }

    @AfterThrowing(
            pointcut = "aopdemo.aspect.AopExpressions.forFindAccounts()",
            throwing = "exc")
    public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable exc) {

        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n========== Executing @AfterThrowing on method: " + method);

        System.out.println("\n========== The exception is: " + exc);

    }

    @AfterReturning(
            pointcut = "aopdemo.aspect.AopExpressions.forFindAccounts()",
            returning = "result")
    public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result) {

        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n========== Executing @AfterReturning on method: " + method);

        System.out.println("\n========== result is: " + result);

        convertAccountNamesToUpperCase(result);
    }

    private void convertAccountNamesToUpperCase(List<Account> result) {

        for (Account account : result) {
            String upperName = account.getName().toUpperCase();
            account.setName(upperName);
        }

    }

    @Before("aopdemo.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint) {

        System.out.println("========== Executing @Before advice on addAccount()");

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("Method: " + methodSignature);

        Object[] args = joinPoint.getArgs();

        for (Object tempArg : args) {
            System.out.println(tempArg);

            if (tempArg instanceof Account) {

                Account account = (Account) tempArg;

                System.out.println("Account name: " + account.getName());
                System.out.println("Account level: " + account.getLevel());

            }
        }

    }

}
