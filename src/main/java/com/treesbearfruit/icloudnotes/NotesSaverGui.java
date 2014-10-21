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


package com.treesbearfruit.icloudnotes;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

import com.treesbearfruit.icloudnotes.utils.generals;

public class NotesSaverGui extends JFrame {

	public NotesSaverGui() throws HeadlessException {
        initUI();
	}

	
    private void initUI() {
		Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);

        JButton quitButton = new JButton("Quit");

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

        gl.setAutoCreateContainerGaps(true);
        
        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addComponent(quitButton)
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(quitButton)
        );
        
        pack();

		setTitle("iCloudNotes Backup");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
	
	public static void main(final String args[]) throws Exception {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            	// tries to launch the class with parameters:
            	try {
            		NotesSaver ns = new NotesSaver(args[0], args[1], args[2], args[3]);
            	} catch (Exception e) {
            		e.printStackTrace();
            		// something didn't work out, let's show the GUI
                	try {
						NotesSaverGui ex = new NotesSaverGui();
						ex.setVisible(true);
					} catch (HeadlessException e1) {
						// if we are headless we should show the parameters

						System.out.println(" ****** " + generals.APPNAME + " ****** ");
						System.out.println(generals.APPNAME + " username password apple_or_google where_to_save_the_backup");
						
						
					}
            	}
            }
        });
	}
}
