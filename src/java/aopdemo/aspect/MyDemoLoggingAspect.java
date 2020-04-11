package aopdemo.aspect;

import aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    private Logger logger = Logger.getLogger(getClass().getName());

//    @Before("execution(public void aopdemo.dao.AccountDAO.addAccount())")
//    @Before("execution(public void add*())")
//    @Before("execution(* add*(aopdemo.Account, ..))")
//    @Before("execution(* add*(..))")
//    @Before("execution(public void addAccount())")
//    @Before("execution(* aopdemo.dao.*.*(..))")

    @Around("execution(* aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        String method = proceedingJoinPoint.getSignature().toShortString();
        logger.info("\n========== Executing @After (finally) on method: " + method);

        long begin = System.currentTimeMillis();

        Object result;

        try {
            result = proceedingJoinPoint.proceed();
        } catch (Exception e){
            logger.warning(e.getMessage());

            result = "Major accident! But no worries.";
        }


        long end = System.currentTimeMillis();

        long duration = end - begin;
        logger.info("\n========== Duration: " + duration/1000.0 + " seconds");

        return result;
    }

    @After("aopdemo.aspect.AopExpressions.forFindAccounts()")
    public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint) {

        String method = joinPoint.getSignature().toShortString();
        logger.info("\n========== Executing @After (finally) on method: " + method);

    }

    @AfterThrowing(
            pointcut = "aopdemo.aspect.AopExpressions.forFindAccounts()",
            throwing = "exc")
    public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable exc) {

        String method = joinPoint.getSignature().toShortString();
        logger.info("\n========== Executing @AfterThrowing on method: " + method);

        logger.info("\n========== The exception is: " + exc);

    }

    @AfterReturning(
            pointcut = "aopdemo.aspect.AopExpressions.forFindAccounts()",
            returning = "result")
    public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result) {

        String method = joinPoint.getSignature().toShortString();
        logger.info("\n========== Executing @AfterReturning on method: " + method);

        logger.info("\n========== result is: " + result);

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

        logger.info("========== Executing @Before advice on addAccount()");

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        logger.info("Method: " + methodSignature);

        Object[] args = joinPoint.getArgs();

        for (Object tempArg : args) {
            logger.info(tempArg.toString());

            if (tempArg instanceof Account) {

                Account account = (Account) tempArg;

                logger.info("Account name: " + account.getName());
                logger.info("Account level: " + account.getLevel());

            }
        }

    }

}
