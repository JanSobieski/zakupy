package pl.wd.zakupy.web;

import java.util.Hashtable;

public class DataContainer {
	private static DataContainer instance = null;
	
	private Hashtable<String,String> _client2List = new Hashtable<>();
 
	protected DataContainer() {
		// Exists only to defeat instantiation.
	}

	public static DataContainer getInstance() {
		if (instance == null) {
			instance = new DataContainer();
		}
		return instance;
	}
	
	public Hashtable<String, String> getClient2List() {
		return _client2List;
	}

	
}
