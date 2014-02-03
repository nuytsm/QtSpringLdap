package be.nuytsm.QtSpringLdap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Starter {
	
	private static final Logger logger = LoggerFactory.getLogger(Starter.class);
	
	public static void main(String[] args){
		logger.debug("Loading context");
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		
		logger.info("querying..");
		
		LdapQueryService ldapQueryService = (LdapQueryService) context.getBean("LdapQueryService");
		
		
		logger.info("" +ldapQueryService.getByServiceNumber("0401368"));
		
		LdapQueryService ldapQueryService2 = ApplicationContextProvider.getApplicationContext().getBean("LdapQueryService", LdapQueryService.class);
		logger.info("" +ldapQueryService2.getByAccount("nuyts.m"));
	}

}
