/**
 *    Copyright (C) 2014 Edoardo Ceccarelli
 *    
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *    
 *    	http://www.apache.org/licenses/LICENSE-2.0
 *    
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *    
 */


package com.treesbearfruit.icloudnotes.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class minifunctions {
    
	public static String makeFilename(String str) {
        StringBuffer buf = new StringBuffer();
        char cs[] = str.toCharArray();
        char c = ' ';
        for (int i = 0; i < cs.length; i++) {
            c =cs[i];
            if (!(((c == '_')
                    || (c == '+')
                    || (c == '\'')
                    || (c == '*')
                    || (c == '-')
                    || (c == '^')
                    || (c == ')')
                    || (c == '(')
                    || (c == '&')
                    || (c == '$')
                    || (c == '.')
                    || (c == '_')
                    || (c == '@')
                    || (c == '!')
                    || (c == ' ') // spazio
                    )
                    || ((c >= 'A') && (c <= 'Z'))
                    || ((c >= 'a') && (c <= 'z'))
                    || ((c >= '0') && (c <= '9')))) {
                c = ' ';
            }   
            buf.append(c);
        }
        return new String(buf);
    }
	
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