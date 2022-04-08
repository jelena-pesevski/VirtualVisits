package org.unibl.etf.virtualvisits.configuration;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class Configuration {
	
	public static String virtualVisitsDir;

	static {
		ResourceBundle bundle=PropertyResourceBundle.getBundle("org.unibl.etf.virtualvisits.configuration.Configuration");
		virtualVisitsDir=bundle.getString("virtualVisitsDir");
	}
	

}
