package com.eviware.soapui.support.swing;

import com.eviware.soapui.support.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;


import com.eviware.soapui.support.UIUtils;

import java.awt.Component;
import java.awt.Container;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;

public class SwingUtilsCrackPlanC implements UIUtils {

    public void invokeLater(Runnable runnable) {
        SwingUtilities.invokeLater(runnable);
    }

    public void invokeAndWait(Runnable runnable) throws Exception {
        SwingUtilities.invokeAndWait(runnable);
    }

    public void invokeAndWaitIfNotInEDT(Runnable runnable) {
        if (SwingUtilities.isEventDispatchThread()) {
            runnable.run();
        } else {
            try {
                SwingUtilities.invokeAndWait(runnable);
            } catch (InterruptedException var3) {
                throw new RuntimeException(var3);
            } catch (InvocationTargetException var4) {
                throw new RuntimeException(var4);
            }
        }

    }

    public static void exit(int status) {
        //try {
        //    System.exit(status);
        //} catch (SecurityException var7) {
        //    StackTraceElement[] stackTrace = var7.getStackTrace();
        //    StackTraceElement[] var6 = stackTrace;
        //    int var5 = stackTrace.length;
        //
        //    for (int var4 = 0; var4 < var5; ++var4) {
        //        StackTraceElement stackTraceElement = var6[var4];
        //        if (stackTraceElement.getClassName().equals("com.smartbear.ready.license.LicenseHandlingTest")) {
        //            throw var7;
        //        }
        //    }
        //}
        //
        //SwingUtilities.invokeLater(new Runnable() {
        //    public void run() {
        //        while (true) {
        //            try {
        //                Thread.sleep(42L);
        //            } catch (InterruptedException var2) {
        //                var2.printStackTrace();
        //            }
        //        }
        //    }
        //});
    }

    public static java.util.List<Component> findAllComponentsRecursively(Container container, Class<? extends Component>... filter) {
        return findAllComponentsRecursively(container, 2147483646, filter);
    }

    public static java.util.List<Component> findAllComponentsRecursively(Container container, int depth, Class<? extends Component>... filter) {
        java.util.List<Component> result = new ArrayList();
        internalFindAllComponentsRecursively(result, container, depth, filter);
        return result;
    }

    private static void internalFindAllComponentsRecursively(List<Component> result, Container container, int depth, Class<? extends Component>... filter) {
        if (depth >= 0) {
            Component[] var7;
            int var6 = (var7 = container.getComponents()).length;

            for (int var5 = 0; var5 < var6; ++var5) {
                Component component = var7[var5];
                Class[] var11 = filter;
                int var10 = filter.length;

                for (int var9 = 0; var9 < var10; ++var9) {
                    Class<? extends Component> aClass = var11[var9];
                    if (aClass != null && aClass.isInstance(component)) {
                        result.add(component);
                        break;
                    }
                }

                if (component instanceof Container) {
                    Container childContainer = (Container) component;
                    if (childContainer.getComponentCount() > 0) {
                        internalFindAllComponentsRecursively(result, childContainer, depth - 1, filter);
                    }
                }
            }

        }
    }
}
