package com.datasynaptic.notemanage;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

import org.apache.commons.io.FileUtils;

import com.datasynaptic.notemanage.utils.Strings;
import com.datasynaptic.notemanage.utils.minifunctions;

public class NoteBrowser {

	// http://support.apple.com/kb/HT4864
	static final String mymailusername = "edoardoc";
	static final String mymailpassword = "ybb32000";
	static final String NOTEFOLDERLBL = "Notes";

	public static void main(String args[]) throws Exception {
		// Get system properties
		Properties props = System.getProperties();

		Session session = Session.getDefaultInstance(props);

		// Get the store
		Store store = session.getStore("imaps");
		//store.connect("imap.gmail.com", mymailusername, mymailpassword);
		store.connect("imap.mail.me.com", mymailusername, mymailpassword);

		String backup_directory = NOTEFOLDERLBL + "_" + System.currentTimeMillis() + "/";

		// saves main folder
		scorre(store, backup_directory, NOTEFOLDERLBL);
		
		// folder..s	
		Folder mainnotefolder = store.getFolder(NOTEFOLDERLBL);
		System.out.println("found " + mainnotefolder.list().length + " note folders");
		Folder[] f = mainnotefolder.list();
		for(Folder fd:f) {
		    String backup_directory_i = backup_directory + fd.getName();
			scorre(store, backup_directory_i, NOTEFOLDERLBL + "/" + fd.getName());
		}

		// Close connection
		store.close();
		System.exit(0);
	}

	private static void scorre(Store store, String wheretobackup, String f) throws MessagingException, IOException {
		
	    System.out.println("opening folder " + f);
	    Folder folder = store.getFolder(f);
	    folder.open(Folder.READ_ONLY);
	    
	    FileUtils.forceMkdir(new File(wheretobackup));
		
		// Get directory
		Message message[] = folder.getMessages();
		for (int i = 0, n = message.length; i < n; i++) {
			// String from = (message[i].getFrom()[0]).toString();
			String subj = (message[i].getSubject()).toString();
			String nota = (message[i].getContent()).toString();
			
			// System.out.println("from: " + from);
			System.out.println("saving: " + subj);

			// BACKUP NOTE
			minifunctions.writeFile(wheretobackup + "/" + Strings.removeStrange(subj).trim() + ".html", nota, message[i].getSentDate());

		}
		folder.close(false);
	}
}
