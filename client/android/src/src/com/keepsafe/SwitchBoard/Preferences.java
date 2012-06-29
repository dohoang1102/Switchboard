/**
 * 
 */
package com.keepsafe.SwitchBoard;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;



/**
 * @author philipp
 *
 */
public class Preferences {
	
	
	private static final String switchBoardSettings = "com.keepsafe.switchboard.settings";
	
	//dynamic config
	private static final String kDynamicConfigServerUrl = "dynamic-config-server-url";
	private static final String kDynamicConfigServerUpdateUrl = "dynamic-config-server-update-url";
	private static final String kDynamicConfig = "dynamic-config";
	
	

	//dynamic config
	/**
	 * Returns a JSON string array with <br />
	 * position 0 = updateserverUrl <br />
	 * Fields a null if not existent.
	 * @param c
	 * @return
	 */
	public static String getDynamicUpdateServerUrl(Context c) {
		SharedPreferences settings = (SharedPreferences) Preferences.getPreferenceObject(c, false);
		return settings.getString(kDynamicConfigServerUpdateUrl, null);
	}
	
	/**
	 * Returns a JSON string array with <br />
	 * postiion 1 = configServerUrl <br />
	 * Fields a null if not existent.
	 * @param c
	 * @return
	 */
	public static String getDynamicConfigServerUrl(Context c) {
		SharedPreferences settings = (SharedPreferences) Preferences.getPreferenceObject(c, false);
		return settings.getString(kDynamicConfigServerUrl, null);
	}

	/**
	 * Stores the config servers URL. 
	 * @param c
	 * @param updateServerUrl Url end point to get the current config server location
	 * @param configServerUrl UR: end point to get the current endpoint for the apps config file
	 * @return true if saved successful
	 */
	public static boolean setDynamicConfigServerUrl(Context c, String updateServerUrl, String configServerUrl) {
	
		SharedPreferences.Editor settings = (Editor) Preferences.getPreferenceObject(c, true);
		settings.putString(kDynamicConfigServerUpdateUrl, updateServerUrl);
		settings.putString(kDynamicConfigServerUrl, configServerUrl);
		return settings.commit();
	}
	
	/**
	 * 
	 * @param c
	 * @return
	 */
	public static String getDynamicConfigJson(Context c) {
		SharedPreferences settings = (SharedPreferences) Preferences.getPreferenceObject(c, false);
		return settings.getString(kDynamicConfig, null);
	}

	public static boolean setDynamicConfigJson(Context c, String configJson) {
		SharedPreferences.Editor settings = (Editor) Preferences.getPreferenceObject(c, true);
		settings.putString(kDynamicConfig, configJson);
		return settings.commit();
	}

	static private Object getPreferenceObject(Context ctx, boolean writeable) {
		
		Object returnValue = null;
		
		Context sharedDelegate = ctx.getApplicationContext();
		
		if(!writeable) {
			returnValue = sharedDelegate.getSharedPreferences(switchBoardSettings, Context.MODE_PRIVATE);
		} else {
			returnValue = sharedDelegate.getSharedPreferences(switchBoardSettings, Context.MODE_PRIVATE).edit();
		}
		
		return returnValue;
	}
}