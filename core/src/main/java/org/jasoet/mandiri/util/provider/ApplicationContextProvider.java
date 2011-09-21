package org.jasoet.mandiri.util.provider;

/**
 * Author : Deny Prasetyo S.T.
 * E-mail : jasoet87@gmail.com
 */

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextProvider {
/*------------------------------ Fields ------------------------------*/

    private static ApplicationContextProvider provider;

    private ApplicationContext applicationContext;

/* -------------------------- Static Methods -------------------------- */

    public synchronized static ApplicationContextProvider getInstance() throws ExceptionInInitializerError {
        ApplicationContextProvider tempProvider = null;
        if (provider == null) {
            provider = new ApplicationContextProvider();
            tempProvider = provider;
        } else if (provider.getApplicationContext() == null) {
            provider = new ApplicationContextProvider();
            tempProvider = provider;
        } else {
            tempProvider = provider;
        }


        return tempProvider;
    }

/* --------------------------- Constructor ---------------------------*/

    private ApplicationContextProvider() throws ExceptionInInitializerError {
        try {
            this.applicationContext = new ClassPathXmlApplicationContext("classpath*:/META-INF/spring/applicationContext.xml");
        } catch (Throwable ex) {
            System.err.println("Initial ApplicationContext creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

/* --------------------- Getter and Setter ---------------------*/

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
