package com.qpmLogger.listeners;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

/**
 * User: satimov
 * Date: 8/8/17 4:57 PM
 */
@Component
public class DestroyDisposableBean implements DisposableBean {
    @Override
    public void destroy() throws Exception {
        System.out.println("This is destroy method inside");
        System.out.println("This is destroy method inside");
        System.out.println("This is destroy method inside");
        System.out.println("This is destroy method inside");
        System.out.println("This is destroy method inside");
    }
}
