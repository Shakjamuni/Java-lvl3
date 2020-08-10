package dev.rumyancev.geekbrains.lessons.Lesson7add.server;

import org.apache.log4j.Logger;

public class MyServerAppenderLog {
    private final Logger log = Logger.getLogger(MyServer.class);

    public void logInfo() {
        log.info("Awaiting users!!!");
    }

    public void logAuth() {
        log.info("Auth ok.");
    }

    public void logErr() {
        log.error("Some error");
    }
}
