package Grave;

import javax.swing.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.filechooser.FileFilter;
import java.io.*;

public class FileMenuControl implements ActionListener {

    JFileChooser fileChooser = new JFileChooser();
    JFrame parent;
    
    public FileMenuControl (JFrame p) {
	parent=p;
	fileChooser.setCurrentDirectory(new File("."));
	fileChooser.setFileFilter(new FileFilter() {
		public boolean accept (File f) {
		    return f.getName().toLowerCase().endsWith(".gif") 
			||f.getName().toLowerCase().endsWith(".bmp")
			||f.getName().toLowerCase().endsWith(".jpg")
			||f.getName().toLowerCase().endsWith(".jpeg")
			||f.isDirectory();}
		public String getDescription() {
		    return "gif, jpg, jpeg and bmp files";
		}
	    });
    }

    public void actionPerformed (ActionEvent e) {

	    String command = e.getActionCommand(); 
	    if (command.equals("Open...")){
	      fileChooser.rescanCurrentDirectory(); 
		int result = fileChooser.showOpenDialog(parent);
		if(result == JFileChooser.APPROVE_OPTION){
		    File fp = fileChooser.getCurrentDirectory();
		    String path = fp.getPath()+fp.separator;
		    String name = fileChooser.getSelectedFile().getName();
		    ImageIcon ic = Grave.openFile(path,name);
		    Grave.showNew(ic);
		}
	    }
	    else if(command.equals("Exit")){
		parent.dispose();
		System.exit(0);
	    }
    }
}












