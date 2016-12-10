package pl.wd.zakupy.config;

public class ConfigurationManager {
	private static ConfigurationManager _instance = null;
	
	private int _max_cv_upload_length = 0;
	
	public ConfigurationManager() {
		_instance = this;
	}
	
	public static ConfigurationManager get_instance() {
		return _instance;
	}

	public int getMax_cv_upload_length() {
		return _max_cv_upload_length;
	}

	public void setMax_cv_upload_length( int max_cv_upload_length ) {
		_max_cv_upload_length = max_cv_upload_length;
	}

}
