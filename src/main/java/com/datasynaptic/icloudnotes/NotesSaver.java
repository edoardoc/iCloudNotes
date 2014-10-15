/**
 *    Copyright 2014 Edoardo Ceccarelli
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


package com.datasynaptic.icloudnotes;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

import org.apache.commons.io.FileUtils;

import com.datasynaptic.icloudnotes.utils.minifunctions;

public class NotesSaver {

	// http://support.apple.com/kb/HT4864
	static final String NOTEFOLDERLBL = "Notes";

	public static void main(String args[]) throws Exception {
		// Get system properties
		Properties props = System.getProperties();

		Session session = Session.getDefaultInstance(props);

		// Get the store
		Store store = session.getStore("imaps");
		//store.connect("imap.gmail.com", mymailusername, mymailpassword);
		store.connect("imap.mail.me.com", args[0], args[1]);	// username without @icloud.com

		String backup_directory = NOTEFOLDERLBL + "_" + System.currentTimeMillis() + "/";

		// saves main folder
		save(store, backup_directory, NOTEFOLDERLBL);
		
		// folder..s	
		Folder mainnotefolder = store.getFolder(NOTEFOLDERLBL);
		System.out.println("found " + mainnotefolder.list().length + " note folders");
		Folder[] f = mainnotefolder.list();
		for(Folder fd:f) {
		    String backup_directory_i = backup_directory + fd.getName();
			save(store, backup_directory_i, NOTEFOLDERLBL + "/" + fd.getName());
		}

		// Close connection
		store.close();
		System.exit(0);
	}

	private static void save(Store store, String wheretobackup, String f) throws MessagingException, IOException {
		
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
			minifunctions.writeFile(wheretobackup + "/" + minifunctions.makeFilename(subj).trim() + ".html", nota, message[i].getSentDate());

		}
		folder.close(false);
	}
}
