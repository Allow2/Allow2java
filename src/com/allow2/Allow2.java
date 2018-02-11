package com.allow2;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Allow2 {
	
	// static variable single_instance of type Allow2
    private static Allow2 _shared = null;
    
    private volatile int x = 0;
    public Allow2EnvType env = Allow2EnvType.SANDBOX;		// default to sandbox

    @SuppressWarnings("unused")
	private final String getApiUrl() {
        switch (env) {
            case SANDBOX:	return "https://api.allow2.com:8443"; // "https://sandbox-api.allow2.com";
            case STAGING:	return "https://staging-api.allow2.com";
            default:		return "https://api.allow2.com";
        }
    }
    
	@SuppressWarnings("unused")
	private final String getServiceUrl () {
        switch (env) {
            case SANDBOX:	return "https://api.allow2.com:9443"; // "https://sandbox-service.allow2.com";
            case STAGING:	return "https://staging-service.allow2.com";
            default:		return "https://service.allow2.com";
        }
    }
    
    /**
     * No-argument constructor.
     * @return 
     */
    private Allow2()
    {
    }
 
    // static method to create instance of Singleton class
    public static Allow2 getShared()
    {
        if (_shared == null)
        	_shared = new Allow2();
 
        return _shared;
    }

    
    /**
     * Method to get the current integer value.
     * @return the value (int)
     */
    public final void pair( 
    		String user,
    		String password,
    		String deviceName) {
		try {
			URL url = new URL( getApiUrl() + "/api/pairDevice" );
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
	    	conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
	    	conn.setDoOutput(true);
            conn.setDoInput(true);
	    	conn.setRequestMethod("POST");
            
	    	int status = conn.getResponseCode();
	    	System.out.println(status);
	    	
	    	BufferedInputStream in = new BufferedInputStream(conn.getInputStream());
	    	String result = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
            JSONObject jsonObject = new JSONObject(result);
			
			conn.disconnect();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


    /**
     * Test this lightly.
     * @param args (none are ever processed)
     */
    public static void main( String[] args )
    {
        Allow2 allow2 = Allow2.getShared();
        allow2.env = Allow2EnvType.SANDBOX;
        allow2.pair("user@gmail.com", "password", "Java Device Name");
    }
}
