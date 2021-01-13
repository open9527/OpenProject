package com.android.open9527.crash;


public abstract class ExceptionHandler {
    final void uncaughtExceptionHappened(Thread thread, Throwable throwable) {
        try {//Catch exceptions in monitoring to prevent repeated calls when the user code throws exceptions
            onUncaughtExceptionHappened(thread, throwable);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }


    final void bandageExceptionHappened(Throwable throwable) {
        try {//Catch exceptions in monitoring to prevent repeated calls when the user code throws exceptions
            onBandageExceptionHappened(throwable);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    final void enterSafeMode() {
        try {
            onEnterSafeMode();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    final void mayBeBlackScreen(Throwable e) {
        try {
            onMayBeBlackScreen(e);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }


    //This method is always called when a child thread throws an exception.
    // The main thread will call this method only when an exception is thrown for the first time,
    // and all throwables in this method will be reported to bugly. In the future,
    // the exception of the main thread will only call
    //{@link #onBandageExceptionHappened(Throwable)}
    protected abstract void onUncaughtExceptionHappened(Thread thread, Throwable throwable);

    /**
     * This method will be called when the main thread exception that caused the app to crash occurs,
     * and the main thread throws another exception that caused the app to crash.
     * (The exception you try to catch will not cause the app to crash) * (The throwable in this method will not be reported to bugly,
     * and there is no need to report to bugly,
     * because this exception may be caused by the app not crashing when the main thread is abnormal for the first time It just happened,
     * as long as the bug is fixed, the exception will not occur) * * @param throwable the exception of the main thread
     */
    protected abstract void onBandageExceptionHappened(Throwable throwable);

    protected abstract void onEnterSafeMode();

    protected void onMayBeBlackScreen(Throwable e) {

    }
}
