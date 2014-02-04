package be.nuytsm.QtSpringLdap.qt;

import com.trolltech.qt.gui.QAction;
import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QMainWindow;
import com.trolltech.qt.gui.QMenu;

public class MainWindow extends QMainWindow{
	
	public MainWindow() {
		createMenu();
		SpringLdapWidget slw = new SpringLdapWidget();
		this.setCentralWidget(slw);
		resize(800, 600);
        move(300, 300);
        setWindowTitle("QtSpringLdap");
		show();
	}
	
	private void createMenu() {
		QMenu file = this.menuBar().addMenu("&File");
		
		QAction quit = new QAction("&Quit", this);
		file.addAction(quit);
		quit.triggered.connect(QApplication.instance(), "quit()");
		
		QMenu settings = this.menuBar().addMenu("&Settings");
		QAction edit = new QAction("&Edit", this);
		settings.addAction(edit);
		edit.triggered.connect(this, "editSettings()");
	}
	
	private void editSettings(){
		
	}

}
