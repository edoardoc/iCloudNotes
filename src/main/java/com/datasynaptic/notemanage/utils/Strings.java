package com.datasynaptic.notemanage.utils;
// StringUtils.java
//    Utility methods for strings.
//
//    muilu@ebi.ac.uk
//    April, 2000
//




/**

 * StringUtils.java
 * 
 * An abstract class with several utilities dealing with strings.
 * <P>
 * @author <A HREF="mailto:muilu@ebi.ac.uk">Juha Muilu</A>
 * @version $Id: StringUtils.java,v 1.25 2000/08/01 11:00:04 senger Exp $
 **/

public abstract class Strings {

	/**
		 * remove occurrences of character CHAR from  string STR.
		 * <p>
		 * @param CHAR The character to be removed. 
		 * @param str  The string from which to remove them
		 * @return     string with CHARs removed
		 **/
	public static String stripChar(char CHAR, String str) {
		char s[] = str.toCharArray();
		int padds = 0;
		for (int i = 0; i < s.length; i++) {
			if (s[i] == CHAR)
				padds++;
		}
		char n[] = new char[s.length - padds];
		int j = 0;
		for (int i = 0; i < s.length; i++) {
			if (s[i] != CHAR)
				n[j++] = s[i];
		}
		return new String(n);
	} // stripChar()

	/**
		 * number of occurrences of character CHAR before location loc in string
		 * str. 
		 * <p>
		 * @param CHAR character whose occurrences should be counted
		 * @param str the string in which to do this
		 * @param loc the offset in str at which to start.
		 * @return number of occurrences.
		 *
		 **/
	public static int numBeforeLocation(char CHAR, String str, int loc) {
		char c[] = str.toCharArray();
		int ix = 0;
		for (int i = 0; i < Math.min(loc, c.length); i++) { // M.S. corrected
			if (c[i] == CHAR)
				ix++;
		}
		return ix;
	}

	public static boolean isLettersornumbers(String name) {
		for (int i = 0; i < name.getBytes().length; i++) {
			char c = (char) name.toLowerCase().getBytes()[i];
			if (!(((c >= 'a') && (c <= 'z')) || ((c >= '0') && (c <= '9')))) {
				return false;
			}
		}
		return true;
	}
	public static boolean areNumbers(String name) {
		for (int i = 0; i < name.getBytes().length; i++) {
			char c = (char) name.toLowerCase().getBytes()[i];
			if (!(((c >= '0') && (c <= '9')))) {
				return false;
			}
		}
		return true;
	}

	public static boolean isLettersornumbersandsigns(String name) {
		for (int i = 0; i < name.getBytes().length; i++) {
			char c = (char) name.toLowerCase().getBytes()[i];
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
				|| (c == '�')
				|| (c == '!'))
				|| ((c >= 'a') && (c <= 'z'))
				|| ((c >= '0') && (c <= '9')))) {
				return false;
			}
		}
		return true;
	}

	/**
		 * return list of positions of character CHAR inside string str.
		 * <p>
		 * @param CHAR the character whose positions are sought
		 * @param str the string in which to find them
		 * @return a (possibly empty) list of integers that lists the
							positions at which CHAR was found.
		 * 
		 **/
	public static int[] positions(char CHAR, String str) {
		char c[] = str.toCharArray();
		int ix = 0;
		for (int i = 0; i < c.length; i++) {
			if (c[i] == CHAR)
				ix++;
		}
		int res[] = new int[ix];
		ix = 0;
		for (int i = 0; i < c.length; i++) {
			if (c[i] == CHAR) {
				res[ix++] = i;
			}
		}
		return res;
	}

	/**
		 * removes all whitespace and 'unprintables characters' from String str,
		 * and returns new one. 
		 * <p>
		 * @param  str The string on which  to operate
		 * @return a string that doesn't have the whitespace nor unprintables
		 */
	public static String removeWhites(String str) {
		StringBuffer buf = new StringBuffer(); // M.S. changed to StringBuffer
		char c[] = str.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] > ' ')
				buf.append(c[i]);
		}
		return new String(buf);
	}

    /*
     * La seguente rimuove completamente i caratteri inutili
     * 
     * una nuova funzione dedicata all'import, in quanto per l'inserimento/ ricerca 
     * è meglio lasciare la funzione cosi' come è organizzata ora 
     * (problemi di interfaccia con exporter)
     * 
     */
    public static String eraseStrange(String str) {
        StringBuffer buf = new StringBuffer();
        char cs[] = str.toCharArray();
        char c = ' ';
        for (int i = 0; i < cs.length; i++) {
            c = cs[i];
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
                    || (c == '£')   // simbolo della Lira
                    
                    // i seguenti sono stati aggiunti
                    // rispetto alla vecchia removeStrange
                    
                    || (c == '.')
                    || (c == 'à') // la a con accento a sinistra
                    
                    // la seguente è il separatore maledetto!
                    /* || (c == 'á') // la a con accento a destra */
                    
                    
                    || (c == ' ') // spazio
                    || (c == 'è')
                    || (c == 'é')
                    || (c == 'ì')
                    || (c == 'ù')
                    || (c == '°')
                    || (c == '/')
                    || (c == '\\')
                    || (c == ',')
                    || (c == '.')
                    || (c == ';')
                    || (c == 'ò')
                    || (c == '%')
                    || (c == '\"')
                    || (c == '±')   // simboletto piccolo +-
                    || (c == '=')
                    || (c == ':')
                    
                    
                    || (c == '_')
                    || (c == '@')
                    || (c == '!'))
                    || ((c >= 'A') && (c <= 'Z'))
                    || ((c >= 'a') && (c <= 'z'))
                    || ((c >= '0') && (c <= '9')))) {
                
                // debug
                /* 
                if (Character.getType(c) != Character.SPACE_SEPARATOR) {
                    Syslog.write("Strange ch: " + c + " " + analyze(c) + " at (" + i + ") " + str);
                }
                */
                c = ' ';
            }
            buf.append(c);
        }
        return new String(buf);
    }
    
    /*
     * Versione semplificata di remove strange che toglie solo
     * i caratteri accentati
     */
    public static String removeAccenti(String str) {
        StringBuffer buf = new StringBuffer();
        char cs[] = str.toCharArray();
        char c = ' ';
        for (int i = 0; i < cs.length; i++) {
            c =cs[i];
            if (
                    (c == 'à') // la a con accento a sinistra
                    || (c == 'á') // la a con accento a destra
                    || (c == 'è')
                    || (c == 'é')
                    || (c == 'ì')
                    || (c == 'ù')
                    || (c == 'ò')) {
                    
                c = ' ';
            }   
            buf.append(c);
        }
        return new String(buf);
    }    
    public static String removeStrange(String str) {
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
                    || (c == '£')
                    || (c == '.')
                    || (c == '_')
                    || (c == '@')
                    || (c == '!')
                    
                    || (c == 'à') // la a con accento a sinistra
                    || (c == 'á') // la a con accento a destra
                    || (c == ' ') // spazio
                    || (c == 'è')
                    || (c == 'é')
                    || (c == 'ì')
                    || (c == 'ù')
                    || (c == 'ò')
                    
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

    /*
     * Viene usata solo dal vocabolario, il suo scopo è evidenziare i tokens
     * sostituendo con spazio tutti i caratteri che possono funzionare da 
     * congiunzione
     * . , : ; - _ ( ) / \ | " + = & $ £ ! ? # < > ^ '
     * 
     */
    public static String evidenziaTokens(String str) {
        StringBuffer buf = new StringBuffer();
        char cs[] = str.toCharArray();
        char c = ' ';
        for (int i = 0; i < cs.length; i++) {
            c =cs[i];
            if ((      (c == '.')
                    || (c == ',')
                    || (c == ':')
                    || (c == ';')
                    || (c == '-')
                    || (c == '_')
                    || (c == ')')
                    || (c == '(')
                    || (c == '/')
                    || (c == '\\')
                    || (c == '|')
                    || (c == '\"')
                    || (c == '+')
                    || (c == '=')
                    || (c == '&')
                    || (c == '$')
                    || (c == '£')
                    || (c == '!')
                    || (c == '?')
                    || (c == '#')
                    || (c == '<')
                    || (c == '>')
                    || (c == '^')
                    || (c == '@'))) {
                c = ' ';
            }
            buf.append(c);
            /*
            if (c == '\'') {
                // inserisco uno spazio se si tratta di un ', in modo da spezzare la parola ed 
                // aggregare l'apostrofo con la parola precedente
                buf.append(' ');
            }
            */
        }
        return new String(buf);
    }
    
	public static String analyze(char ch) {
        String message="";
        switch (Character.getType(ch)) {
           case Character.COMBINING_SPACING_MARK:
             message = "Combining Spacing Mark";
             break;
           case Character.CONNECTOR_PUNCTUATION:
             message = "Connector Punctuation";
             break;
           case Character.CONTROL:
             message = "Control";
             break;
           case Character.CURRENCY_SYMBOL:
             message = "Currency Symbol";
             break;
           case Character.DASH_PUNCTUATION:
             message = "Dash Punctuation";
             break;
           case Character.DECIMAL_DIGIT_NUMBER:
             message = "Decimal Digit Number";
             break;
           case Character.ENCLOSING_MARK:
             message = "Enclosing Mark";
             break;
           case Character.END_PUNCTUATION:
             message = "End Punctuation";
             break;
           case Character.FINAL_QUOTE_PUNCTUATION:
             message = "Final Quote Punctuation";
             break;
           case Character.FORMAT:
             message = "Format";
             break;
           case Character.INITIAL_QUOTE_PUNCTUATION:
             message = "Initial Quote Punctuation";
             break;
           case Character.LETTER_NUMBER:
             message = "Letter Number";
             break;
           case Character.LINE_SEPARATOR:
             message = "Line Separator";
             break;
           case Character.LOWERCASE_LETTER:
             message = "Lowercase Letter";
             break;
           case Character.MATH_SYMBOL:
             message = "Math Symbol";
             break;
           case Character.MODIFIER_LETTER:
             message = "Modifier Letter";
             break;
           case Character.MODIFIER_SYMBOL:
             message = "Modifier Symbol";
             break;
           case Character.NON_SPACING_MARK:
             message = "Non Spacing Mark";
             break;
           case Character.OTHER_LETTER:
             message = "Other Letter";
             break;
           case Character.OTHER_NUMBER:
             message = "Other Number";
             break;
           case Character.OTHER_PUNCTUATION:
             message = "Other Punctuation";
             break;
           case Character.OTHER_SYMBOL:
             message = "Other Symbol";
             break;
           case Character.PARAGRAPH_SEPARATOR:
             message = "Paragraph Separator";
             break;
           case Character.PRIVATE_USE:
             message = "Private Use";
             break;
           case Character.SPACE_SEPARATOR:
             message = "Space Separator";
             break;
           case Character.START_PUNCTUATION:
             message = "Start Punctuation";
             break;
           case Character.SURROGATE:
             message = "Surrogate";
             break;
           case Character.TITLECASE_LETTER:
             message = "Titlecase Letter";
             break;
           case Character.UNASSIGNED:
             message = "Unassigned";
             break;
           case Character.UPPERCASE_LETTER:
             message = "Uppercase Letter";
             break;
           default:
             message = "Unknown";
         }
        return message;
    }
    
	/** Opposite of split; concatenates STRINGLIST using DELIMITER as the
		 *  separator. The separator is only added between strings, so there will
		 *  be no separator at the beginning or end. 
		 * <p>
		 * @param  stringList The list of strings that will to be put together
		 * @param  delimiter  The string to put between the strings of stringList
		 * @return            string that has DELIMITER put between each of the
							  elements of stringList
		 */
	public static String join(String[] stringList, String delimiter) {
		StringBuffer buf = new StringBuffer(stringList.length * 20);
		synchronized (stringList) {
			int len = stringList.length;
			for (int i = 0; i < len - 1; i++) {
				buf.append(stringList[i]);
				buf.append(delimiter);
			}
			if (len > 0)
				buf.append(stringList[len - 1]);
		}
		return buf.toString();
	}

	/** Make one string out of stringList by putting spaces between each
			element. Same as join(stringList, " "); 
		 * <p>
		 * @param  stringList the list of strings that will be put together
		 * @return            a string that has ' ' put between each of the
							  elements of stringList. 
		 * @see               #join(String[],String) join(String[],String)
	
		 */
	public static String join(String[] stringList) {
		return join(stringList, " ");
	}

	/** In string S, replace all occurrence of FROM by TO and return this
		 * string. If string FROM is empty, this will be considered to match no
		 * single occurrence of it in the target string, hence no replacements
		 * will be made.
	
		 * <p>
		 * @param string The string in which the replacements will be made
		 * @param from String that, when it occurs in string, will be replaced
		 * @param to The replacement of from in string
		 * @return A new string
		 */
	public static String replace(String string, String from, String to) {
		if (from.equals(""))
			return string;
		StringBuffer buf = new StringBuffer(2 * string.length());

		int previndex = 0;
		int index = 0;
		int flen = from.length();
		while (true) {
			index = string.indexOf(from, previndex);
			if (index == -1) {
				buf.append(string.substring(previndex));
				break;
			}
			buf.append(string.substring(previndex, index) + to);
			previndex = index + flen;
		}
		return buf.toString();
	} // replace

	/** Delete all occurrences of substrings from a string. Same as
			replace(string, from, "") 
		 * <p>
		 * @param  string The string from which substrings will be deleted.
		 * @param  delete The substrings that are to be deleted.
		 * @return        new string.
		 * @see    #replace(String, String, String) replace(String, String, String)
		 */
	public static String delete(String string, String delete) {
		return replace(string, delete, "");
	}

	/** Opposit string. 
		 * @param str string
		 * @param pos positions of chars to be removed (must be ordered from lowest to highest and position can occur only once)
		 * @return new string from which the chars are removed
		 **/
	public static String removePositions(String str, int pos[]) {
		char[] n = new char[str.length() - pos.length];
		char[] s = str.toCharArray();
		int shift = 0;
		for (int i = 0; i < s.length; i++) {
			if (shift < pos.length && i == pos[shift]) {
				shift++;
			} else {
				n[i - shift] = s[i];
			}

		}
		return new String(n);
	}

	public static String purify(String s) {
		StringBuffer buf = new StringBuffer(2 * s.length());
		char ch;
		for (int n = 0; n < s.length(); n++) {
			ch = s.charAt(n);
			switch (ch) {
				case '\'' :
					buf.append("\\'");
					break;
				case '\"' :
					buf.append("\\\"");
					break;
				case '\\' :
					buf.append("\\\\");
					break;
				default :
					buf.append(ch);
					break;
			}

		}
		return buf.toString();
	}
	static String[] admittedtags = { "li", "ul", "font", "br", "strong", "p" };
	static String[][] tags = { { "<br>", "\r\n" }, {
			"<br>", "\n" }, {
			"&lt;", "<" }, {
			"&gt;", ">" }
	};

	public static String chars(String ss, int num) {
		StringBuffer returnVal = new StringBuffer("");
		for (int i = 0; i < num; i++) {
			returnVal.append(ss);
		}
		return returnVal.toString();
	}
	public static String chars(char ch, int num) {
		StringBuffer returnVal = new StringBuffer("");
		for (int i = 0; i < num; i++) {
			returnVal.append(ch);
		}
		return returnVal.toString();
	}

	public static String html2String(String s, int from) {
		StringBuffer buf = new StringBuffer(2 * s.length());
		if (from <= 1) {
			int to = (from == 0 ? 1 : 0);
			for (int n = 0; n < s.length(); n++) {

				// confronta i vari tags
				for (int i = 0;(i < tags.length) && (!s.substring(n).equals("")); i++) {
					if ((s.substring(n).startsWith(tags[i][from]))
						|| (s.substring(n).startsWith(tags[i][from].toUpperCase()))) {
						buf.append(tags[i][to]);
						n = n + tags[i][from].length();
						i = -1; // restart
					} else {

					}
				}
				// perch� le stringhe vanno da 0 a n
				if (n < s.length())
					buf.append(s.charAt(n));
			}
		}
		return buf.toString();

	}

	public static String htmlPurifier(String s) {
		StringBuffer buf = new StringBuffer(2 * s.length());
		StringBuffer currenttag = new StringBuffer();

		boolean insidetag = false;
		char ch;
		for (int i = 0; i <= s.length() - 1; i++) {
			ch = s.charAt(i);
			if (insidetag) {
				if (ch == ' ') {
					
					System.err.println("TROVATO TAG: <" + currenttag + ">");
					currenttag.setLength(0);
					continue;
				} else if (ch == '>') {
					insidetag = false;
					System.err.println("TROVATO FINE TAG: <" + currenttag + ">");
					currenttag.setLength(0);
					continue;
				} else {
					currenttag.append(ch);
				}
			} else {
				if (ch == '<') {
					insidetag = true;
					continue;
				} else {
					buf.append(ch);
				}
			}

		}
		return buf.toString();
	}

	/*
	
	StringTokenizer tags = new StringTokenizer(s, "<");
	String tag="";
	while (tags.hasMoreTokens()) {
	tag=tags.nextToken();
	Syslog.write("TAG TROVATO: " + tag);
	// confronta con i vari tags permessi
	for (int i = 0;i < admittedtags.length; i++) {
		
		if ((s.substring(n).startsWith(tags[i][from])) || (s.substring(n).startsWith(tags[i][from].toUpperCase()))) {
			buf.append(tags[i][to]);
			n = n + tags[i][from].length();
			i = -1; // restart
		} else {
	
		}
	}
	} else {
	// LO AGGIUNGE, NON E' UN TAG
	
	}
	
	buf.append("<" + tag); // ricostruisce
	*/
}
