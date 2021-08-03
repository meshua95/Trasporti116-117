package utils.errorCode;

import java.io.Serial;

/**
 * Time out to apply to any query
 */
public class QueryTimeOutException extends Exception {
    @Serial
    private static final long serialVersionUID = -7274703106581003686L;

    /**
     * Time out
     */
    public static final long TIME_OUT = 10000;
}
