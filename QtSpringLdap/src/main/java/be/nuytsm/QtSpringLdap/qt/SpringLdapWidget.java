package be.nuytsm.QtSpringLdap.qt;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import be.nuytsm.QtSpringLdap.ApplicationContextProvider;
import be.nuytsm.QtSpringLdap.LdapQueryService;
import be.nuytsm.QtSpringLdap.LdapQueryService.QueryResult;

import com.trolltech.qt.gui.QAction;
import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QLabel;
import com.trolltech.qt.gui.QLineEdit;
import com.trolltech.qt.gui.QMainWindow;
import com.trolltech.qt.gui.QMenu;
import com.trolltech.qt.gui.QPushButton;
import com.trolltech.qt.gui.QTabWidget;
import com.trolltech.qt.gui.QTextEdit;
import com.trolltech.qt.gui.QWidget;

public class SpringLdapWidget extends QWidget {
	
	private LdapQueryService ldapQueryService = ApplicationContextProvider.getApplicationContext().getBean("LdapQueryService", LdapQueryService.class);
	
//	QTextEdit result;
	QLineEdit accountEdit, serviceNumberEdit;
	QTabWidget resultTab;
	
	public SpringLdapWidget() {
		initUI();
		
//		setWindowTitle("QtSpringLdap"); 
		resize(800, 600);
//        move(300, 300);        
        
        
	}

	private void initUI() {
		QGridLayout grid = new QGridLayout(this);
		createQueryPart(grid);
		createResultPart(grid);
	}




	private void createQueryPart(QGridLayout grid) {
		QLabel accountLabel = new QLabel("Account", this);
		grid.addWidget(accountLabel, 0,0);
		accountEdit = new QLineEdit(this);
		accountEdit.returnPressed.connect(this, "query()");
		
		grid.addWidget(accountEdit, 0,1);
		grid.setColumnStretch(1, 1);
		
		QLabel serviceNumberLabel = new QLabel("Service Number", this);
		grid.addWidget(serviceNumberLabel, 1,0);
		serviceNumberEdit = new QLineEdit(this);
		serviceNumberEdit.returnPressed.connect(this, "query()");
		grid.addWidget(serviceNumberEdit, 1,1);
		
		QPushButton queryButton = new QPushButton("Query", this);
		grid.addWidget(queryButton, 1,2);
		
		queryButton.clicked.connect(this, "query()");
	}

	private void createResultPart(QGridLayout grid) {
		resultTab = new QTabWidget();
		resultTab.setTabsClosable(true);
		resultTab.tabCloseRequested.connect(this, "tabCloseRequested(Integer)");
		grid.addWidget(resultTab, 2, 0,1,3);
		
//		result = new QTextEdit(this);
//		result.autoFormatting();
//		grid.addWidget(result, 1,0,1,3);
	}
	
	public void tabCloseRequested(Integer tabNumber){
		resultTab.removeTab(tabNumber);
	}
	
	private void query(){
		List<LdapQueryService.QueryResult> result = null;
		if (!accountEdit.text().isEmpty()){
			result = ldapQueryService.getByAccount(accountEdit.text());			
		} else if (!serviceNumberEdit.text().isEmpty()){
			result = ldapQueryService.getByServiceNumber(serviceNumberEdit.text());
		}
		if (null != result && !result.isEmpty()){
//			this.result.setText(result.get(0));
			for (QueryResult qr : result) {
				QTextEdit qtext = new QTextEdit();
				qtext.setText(qr.getResult());
				resultTab.addTab(qtext, qr.getName());
			}
			
		}
	}
}
