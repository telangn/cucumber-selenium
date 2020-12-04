package utilities;

import java.util.Optional;

public enum Settings {
	
	BROWSER(Optional.ofNullable(System.getProperty("browser")).orElse("chrome")),
	HEADLESS(Optional.ofNullable(System.getProperty("headless")).orElse("off")),
	MOBILE(Optional.ofNullable(System.getProperty("mobile")).orElse("off"));
	
	private String value;

    Settings(String value) {
        this.value = value;
    }

    public String getValue(){
    	return value;
    }
}
