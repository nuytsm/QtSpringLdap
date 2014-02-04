package be.nuytsm.QtSpringLdap.qt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.trolltech.qt.gui.QAction;
import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QMainWindow;
import com.trolltech.qt.gui.QMenu;

public class GuiStarter {
	
	private static final Logger logger = LoggerFactory.getLogger(GuiStarter.class);
	
	public static void main(String[] args){
		
		
		QApplication.initialize(args);
		logger.debug("Loading context");
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		
		logger.debug("Opening main window");
		new MainWindow();
		
		QApplication.exec();
	}

}
