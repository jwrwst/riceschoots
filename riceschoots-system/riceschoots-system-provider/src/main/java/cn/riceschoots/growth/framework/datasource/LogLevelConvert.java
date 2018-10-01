package cn.riceschoots.growth.framework.datasource;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogLevelConvert extends ClassicConverter {

    private static final Logger log = LoggerFactory.getLogger(LogLevelConvert.class);

    @Override
    public String convert(ILoggingEvent event) {
        String elkInt;
        switch (event.getLevel().levelStr){
            case "INFO":
                elkInt = "1";
                break;
            case "DEBUG":
                elkInt = "0";
                break;
            case "ERROR":
                elkInt = "4";
                break;
             case "WARN":
                elkInt = "3";
                break;
            default:
                elkInt = "1";
                break;
        }
        return elkInt;
    }
}
