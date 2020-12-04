package utilities;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class CukeLogs {
	
	/* Encapsulation of Java Logger Class */

	private Logger logger;
	
	private CukeLogs(Logger logger) {
		LogManager.getLogManager().reset();
		this.logger = logger;
		logger.setLevel(Level.ALL);

		ConsoleHandler consoleHandler = new ConsoleHandler();
		consoleHandler.setLevel(Level.ALL);
		logger.addHandler(consoleHandler);

		try {
			FileHandler fileHandler = new FileHandler("target/Cucumber.log", true);
			fileHandler.setFormatter(new SimpleFormatter());
			fileHandler.setLevel(Level.ALL);
			logger.addHandler(fileHandler);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static CukeLogs getLogger(String className) {
		return new CukeLogs(Logger.getLogger(className));
	}
	
	public void info(String info) {
		logger.info(info);
	}
	
	public void entering(String sourceClass, String sourceMethod) {
		logger.entering(sourceClass, sourceMethod);
	}
}
