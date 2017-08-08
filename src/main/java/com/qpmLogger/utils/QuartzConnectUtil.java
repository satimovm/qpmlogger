package com.qpmLogger.utils;

import com.qpmLogger.dto.QuartzInstanceTO;
import org.springframework.util.StringUtils;

import javax.management.ObjectName;

/**
 * User: satimov
 * Date: 8/8/17 3:42 PM
 */
public class QuartzConnectUtil {
    public static boolean isSupported(String version) {
        return StringUtils.isEmpty(version) && version.startsWith("2");
    }

    /**
     * util method for printing all methods an mbean has exposed.
     *
     * @param quartzInstance
     */
    public static void printMBeanProperties(QuartzInstanceTO quartzInstance, ObjectName objectName) {
        if (quartzInstance == null || objectName == null) {
            return;
        }
        try {
//            final QuartzJMXAdapter adapter = quartzInstance.getJmxAdapter();

//            adapter.printAttributes(quartzInstance, objectName);
//            adapter.printConstructors(quartzInstance, objectName);
//            adapter.printOperations(quartzInstance, objectName);
//            adapter.printNotifications(quartzInstance, objectName);
//            adapter.printClassName(quartzInstance, objectName);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
