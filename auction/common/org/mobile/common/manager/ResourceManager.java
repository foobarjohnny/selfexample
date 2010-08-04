package org.mobile.common.manager;

import java.util.ResourceBundle;

/**
 * 资源管理器，获得资源文件内容
 * 
 * @author 孙树林
 * 
 */
public class ResourceManager {

	public static ResourceManager resource;

	public static ResourceManager getInstance() {
		return resource;
	}

	private static ResourceBundle bundle;

	private ResourceManager(String path) {
		bundle = ResourceBundle.getBundle(path);
	}

	public String getString(String key) {
		return bundle.getString(key);
	}

	public String getString(String key, String defaultValue) {
		String value = bundle.getString(key);
		if (value != null && !value.trim().equals("")) {
			return value;
		}
		return defaultValue;
	}

	public String getString(String key, String[] params) {
		String result = "";
		String source = bundle.getString(key);
		String[] sources = new String[params.length + 1];
		int ind = 0;
		for (int i = 1; i < (params.length + 1); i++) {
			int index = source.indexOf("%" + i);
			if (index > 0) {
				sources[i - 1] = source.substring(ind, index);
				if (i < 10) {
					sources[i] = source.substring(index + 2);
					ind = index + 2;
				} else {
					sources[i] = source.substring(index + 3);
					ind = index + 3;
				}
			}
		}
		for (int i = 0; i < sources.length; i++) {
			if (i < (sources.length - 1)) {
				result += sources[i] + params[i];
			} else {
				result += sources[i];
			}
		}
		return result;
	}

	public int getInt(String key) {
		return Integer.parseInt(bundle.getString(key));
	}

	public int getInt(String key, int defaultValue) {
		String value = bundle.getString(key);
		if (value != null && !value.trim().equals("") && isDigit(value)) {
			return Integer.parseInt(value);
		}
		return defaultValue;
	}

	public long getLong(String key) {
		return Long.parseLong(bundle.getString(key));
	}

	public long getLong(String key, long defaultValue) {
		String value = bundle.getString(key);
		if (value != null && !value.trim().equals("") && isDigit(value)) {
			return Long.parseLong(value);
		}
		return defaultValue;
	}

	private static boolean isDigit(String value) {
		for (int i = 0, j = value.length(); i < j; i++) {
			if (!Character.isDigit(value.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static void init(String string) {
		resource = new ResourceManager(string);
	}

	public static void main(String[] args) {
		ResourceManager.init("resources");
		ResourceManager rm = ResourceManager.getInstance();
		System.out.println(rm.getString("date.format"));
	}
}
