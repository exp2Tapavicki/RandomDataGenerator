package com.rdg.util;

import com.rdg.constants.Const;

import java.lang.Thread.UncaughtExceptionHandler;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/*
* Copyright 2014 Radislav Tapavicki <radislavtt@gmail.com>
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

public class ThreadFactoryUtil implements ThreadFactory {
    protected final AccessControlContext accessControlContext;
    protected volatile AtomicLong counter = new AtomicLong();
    private volatile HashMap<Integer, ArrayList<Thread>> hmThreads;
    private volatile Integer iPriorityThreadLevel;
    private volatile String sClassName;
    private long stackSize;
    private String pattern;
    private ClassLoader classLoader;
    private ThreadGroup threadGroup;
    private int priority;
    private UncaughtExceptionHandler exceptionHandler;
    private boolean daemon;
    private boolean wrapRunnable;

    public ThreadFactoryUtil() {
        final Thread thread = Thread.currentThread();
        ClassLoader loader;
        AccessControlContext accessControlContext = null;
        hmThreads = new HashMap<>();
        try {
            loader = thread.getContextClassLoader();
            if (System.getSecurityManager() != null) {
                accessControlContext = AccessController.getContext();
                accessControlContext.checkPermission(new RuntimePermission("setContextClassLoader"));
            }
        } catch (SecurityException _skip) {
            loader = null;
            accessControlContext = null;
        }

        this.classLoader = loader;
        this.accessControlContext = accessControlContext;
        this.priority = thread.getPriority();
        this.daemon = true;
        this.wrapRunnable = true;

        StackTraceElement[] stack = new Exception().getStackTrace();
        setPattern(stack.length > Const.ONE ? getOuterClassName(stack[Const.ONE].getClassName()) : "ThreadFactoryUtil", true);
    }

    /**
     * get outer Class name
     *
     * @param className
     * @return
     */
    private static String getOuterClassName(String className) {
        int idx = className.lastIndexOf('.') + Const.ONE;
        return className.substring(idx);
    }

    /**
     * get created counted Threads
     *
     * @return
     */
    public long getCreatedThreadsCount() {
        return counter.get();
    }

    /**
     * create Thread
     */
    public Thread newThread(Runnable runnable) {
        final Thread thread = new Thread(threadGroup, wrapRunnable(runnable), composeName(runnable), stackSize);
        thread.setPriority(priority);
        thread.setDaemon(daemon);
        thread.setUncaughtExceptionHandler(exceptionHandler);
        applyClassLoader(thread);
        if (hmThreads.containsKey(iPriorityThreadLevel)) {
            hmThreads.get(iPriorityThreadLevel).add(thread);
        } else {
            ArrayList<Thread> alList = new ArrayList<>();
            alList.add(thread);
            hmThreads.put(iPriorityThreadLevel, alList);
        }
        return thread;
    }

    /**
     * setting AccessControlContext for ContextClassLoader
     *
     * @param t
     */
    private void applyClassLoader(final Thread t) {
        if (classLoader != null) {
            AccessController.doPrivileged((PrivilegedAction<Object>) () -> {
                t.setContextClassLoader(classLoader);
                return null;
            }, accessControlContext);
        }
    }

    /**
     * wrap Runnable
     *
     * @param r
     * @return
     */
    private Runnable wrapRunnable(final Runnable r) {
        if (accessControlContext == null || !wrapRunnable) {
            return r;
        }
        return () -> AccessController.doPrivileged((PrivilegedAction<Object>) () -> {
            r.run();
            return null;
        }, accessControlContext);
    }

    /**
     * create or compose name for Thread
     *
     * @param r
     * @return
     */
    protected String composeName(Runnable r) {
        return String.format(sClassName + " " + pattern, counter.incrementAndGet(), System.currentTimeMillis());
    }

    /**
     * set pattern for creating Thread name counter + creation time
     *
     * @param pattern
     * @param appendFormat
     */
    public void setPattern(String pattern, boolean appendFormat) {
        if (appendFormat) {
            pattern += ": %d @ %tF %<tT";
        }
        this.pattern = pattern;
    }

    /**
     * set Deamon
     *
     * @param daemon
     */
    public void setDaemon(boolean daemon) {
        this.daemon = daemon;
    }

    /**
     * set Priority of Thread
     *
     * @param priority
     */
    public void setPriority(int priority) {
        if (priority < Thread.MIN_PRIORITY || priority > Thread.MAX_PRIORITY) {
            throw new IllegalArgumentException("priority: " + priority);
        }
        this.priority = priority;
    }

    /**
     * set Stack Size of Thread
     *
     * @param stackSize
     */
    public void setStackSize(long stackSize) {
        this.stackSize = stackSize;
    }

    /**
     * get Thread Group
     *
     * @return
     */
    public ThreadGroup getThreadGroup() {
        return this.threadGroup;
    }

    /**
     * set Thread Group
     *
     * @param threadGroup
     */
    public void setThreadGroup(ThreadGroup threadGroup) {
        this.threadGroup = threadGroup;
    }

    /**
     * set Exception Handler
     *
     * @param exceptionHandler
     */
    public void exceptionHandler(UncaughtExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }

    /**
     * set wrapping
     *
     * @param wrapRunnable
     */
    public void setWrapRunnable(boolean wrapRunnable) {
        this.wrapRunnable = wrapRunnable;
    }

    /**
     * set Class Loader
     *
     * @param classLoader
     */
    public void classLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    /**
     * get Class Name for fill
     *
     * @return
     */
    public String getsClassName() {
        return sClassName;
    }

    /**
     * set Class Name for fill
     *
     * @return
     */
    public void setsClassName(String sClassName) {
        this.sClassName = sClassName;
    }

    /**
     * get all Threads created
     *
     * @return
     */
    public HashMap<Integer, ArrayList<Thread>> getHmThreads() {
        return hmThreads;
    }

    public void setHmThreads(HashMap<Integer, ArrayList<Thread>> hmThreads) {
        this.hmThreads = hmThreads;
    }

    /**
     * get priority of filling Classes
     *
     * @return
     */
    public Integer getiPriorityThreadLevel() {
        return iPriorityThreadLevel;
    }

    /**
     * set priority of filling Classes
     *
     * @return
     */
    public void setiPriorityThreadLevel(Integer iPriorityThreadLevel) {
        this.iPriorityThreadLevel = iPriorityThreadLevel;
    }
}
