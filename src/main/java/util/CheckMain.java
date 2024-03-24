package util;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class CheckMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new CheckMain().check();
		
	}
	void check() {
		Logger log = CommonLogger.getCommon().getLogger(CheckMain.class);
//		Logger log = Logger.getLogger(CheckMain.class);
//		PropertyConfigurator.configure(getClass().getResource("/log4.properties"));
		log.info("working");
	}
}
