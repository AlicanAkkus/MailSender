package com.wora.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

public class PropertiesUtils {

	/**
	 * fileName ismi ile verilen properties dosyasini props nesnesine yukler
	 */
	public static boolean loadProperties(String fileName, Properties props) {
		File f = new File(fileName);
		if (!f.exists()) {
			f = new File(System.getProperty("user.dir") + File.separator + fileName);
		}

		if (f.exists() && !f.isDirectory()) {
			try {
				FileInputStream fis = new FileInputStream(f);
				props.load(fis);

				fis.close();
			} catch (IOException e) {
				return false;
			}
		} else {
			return false;
		}
		return true;
	}

}