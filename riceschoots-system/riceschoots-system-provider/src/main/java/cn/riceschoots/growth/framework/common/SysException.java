package cn.riceschoots.growth.framework.common;

/**
 * 系统业务异常.
 */
public class SysException extends RuntimeException {

    public SysException() {
        super();
    }

    public SysException(String message) {
        super(message);
    }

    public SysException(Throwable cause) {
        super(cause);
    }

    public SysException(String message, Throwable cause) {
        super(message, cause);
    }
}