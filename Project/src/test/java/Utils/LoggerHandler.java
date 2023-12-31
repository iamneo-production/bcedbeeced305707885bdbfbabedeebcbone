import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggerHandler {

    private static final Logger logger = Logger.getLogger(LoggerHandler.class);

    public static void main(String[] args) {
        initLog4j();
    }

    public static void initLog4j() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = sdf.format(new Date());
        String logFileName = "logs_" + timestamp + ".log";
        System.setProperty("logFile", logFileName);
        logger.error(logFileName +"*********** file name");
    
        // Initialize Log4j
        PropertyConfigurator.configure("/src/main/resources/log4j.properties");

        // Obtain the "file" appender and configure it
        Logger log = Logger.getLogger(LoggerHandler.class);
        DailyRollingFileAppender appender = (DailyRollingFileAppender) log.getAppender("file");

        // Configure the appender as needed
        PatternLayout layout = new PatternLayout();
        layout.setConversionPattern("%d %d{Z} [%t] %-5p (%F:%L) - %m%n");
        appender.setLayout(layout);
        appender.activateOptions(); // Activate the new options
    }
}
