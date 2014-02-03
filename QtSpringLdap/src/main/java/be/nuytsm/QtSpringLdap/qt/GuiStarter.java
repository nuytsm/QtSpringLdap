package be.nuytsm.QtSpringLdap.qt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.trolltech.qt.gui.QApplication;

public class GuiStarter {
	
	private static final Logger logger = LoggerFactory.getLogger(GuiStarter.class);
	
	public static void main(String[] args){
		
		
		QApplication.initialize(args);
		logger.debug("Loading context");
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
//		new SpringLdapWidget(); is declared as spring component ->  automatic initialization
		QApplication.exec();
	}

}
