package com.datasynaptic.notemanage.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class minifunctions {
	static public boolean booleanParm(String parametro) {
		return (parametro == null) ? false : true;
	}

	static public String readFile(String nome) {
		String ret = "";
		try {
			FileInputStream inStream = new FileInputStream(nome);
			int inBytes = inStream.available();
			byte inBuf[] = new byte[inBytes];
			inStream.read(inBuf);
			ret = new String(inBuf);
			inStream.close();
		} catch (Exception e) {
			System.err.println("Errore apertura del file " + nome
					+ " Messaggio: " + e.getMessage());
		}
		return ret;
	}

	static public boolean writeFile(String nome, String Contenuto, Date date) {
		boolean ret;
		try {
			FileOutputStream outStream = new FileOutputStream(nome);
			outStream.write(Contenuto.getBytes());
			outStream.close();
			ret = true;
			
			File nn = new File(nome);
			nn.setLastModified(date.getTime());
		} catch (Exception e) {
			System.err.println("Errore scrittura del file " + nome
					+ " Messaggio: " + e.getMessage());
			ret = false;
		}
		return ret;
	}

	static public String dateFormatter(java.util.Date now, String dbformat) {
		if (now != null) {
			SimpleDateFormat format = new SimpleDateFormat(dbformat);
			Calendar cal = Calendar.getInstance();
			cal.setTime(now);
			String datareg = format.format(cal.getTime());
			return datareg;
		} else
			return "";
	}
}