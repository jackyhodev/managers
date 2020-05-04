/*
 * Licensed Materials - Property of IBM
 * 
 * (c) Copyright IBM Corp. 2019.
 */
package dev.galasa.zos3270;

public class TerminalInterruptedException extends Zos3270Exception {
    private static final long serialVersionUID = 1L;

    public TerminalInterruptedException() {
    }

    public TerminalInterruptedException(String message) {
        super(message);
    }

    public TerminalInterruptedException(Throwable cause) {
        super(cause);
    }

    public TerminalInterruptedException(String message, Throwable cause) {
        super(message, cause);
    }

    public TerminalInterruptedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
