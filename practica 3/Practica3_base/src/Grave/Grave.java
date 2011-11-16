package Grave;

import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;

/*******************************************************
 ********GRAVE(GRAphics Viewer for Everywhere)**********
 *******************************************************
 *Public Class Grave written by Jeb Thorley Aug. 2000
 *Grave is a simple platform independent viewer
 *capable of displaying gif, jpeg and bitmap
 *files.  Viewer requires FileMenuControl.class to control
 *its file menu, and utils.class to open bitmaps.  The  
 *loadbitmap method in utils.class was originally written 
 *by Jeff West and published at 
 *http://www.javaworld.com/javaworld/javatips/jw-javatip43.html
 ******************************************************/

public class Grave extends JFrame{
    private static JLabel label1;
    private static Box layoutBox = Box.createVerticalBox();
    private final static JFrame resultFrame = new JFrame("GRAVE (GRAphics Viewer for Everywhere)");
    private static Container contentPane = resultFrame.getContentPane();
    private static JScrollPane imageScroller;
    public static void main(String[] args){
	//Check to make sure the correct number of arguments were supplied to Viewer.
	//Exit and give the user feedback if they were not.
	if(args.length!=0&&args.length!=1 && args.length!=2){
	    System.out.println("Usage: Viewer directory filename OR Viewer filename");
	    System.exit(1);
	}
	

	//initialize label1, and load first image if one is provided.
	ImageIcon ic = new ImageIcon();
	if(args.length==0){
	    label1 = new JLabel();
	}
	else if(args.length ==1){
	    ic = openFile(args[0]);
	    label1 = new JLabel(ic);
	}
	else if(args.length == 2){
	    ic = openFile(args[0],args[1]);
	    label1 = new JLabel(ic);
	}
	
	//Set resultFrame's size
	resultFrame.setSize(600,400);

	//Make Viewer let go of its resources and relinquish control if 
	//its window is closed.
	resultFrame.addWindowListener(new WindowAdapter(){
		public void windowClosing(WindowEvent e){
		    resultFrame.dispose();
		    System.exit(0);
		}
	    });

	/***************Create Menu*********************/
	JMenuBar menuBar = new JMenuBar();
	resultFrame.setJMenuBar(menuBar);
	FileMenuControl fmc = new FileMenuControl(resultFrame);

	//Add File Heading to menu
	JMenu fileMenu = new JMenu("File");
	
	//Add options under File
	JMenuItem Open = new JMenuItem("Open...");
	Open.addActionListener(fmc);
	fileMenu.add(Open);
	fileMenu.addSeparator();

	JMenuItem quit = new JMenuItem("Exit");
	quit.addActionListener(fmc);
	fileMenu.add(quit);
	menuBar.add(fileMenu);
	/**********************************************/
	
	/**Create a Scrollable area in which to display the image**/
	imageScroller = new JScrollPane(label1);
	imageScroller.setPreferredSize(new Dimension(580,380));
	imageScroller.setMinimumSize(new Dimension(580,380));
	/******************************************************/

	//Add the scrollable are to the layout box 
	layoutBox.add(imageScroller);

	//display the layout box
	contentPane.add(layoutBox);

	//show the JFrame
	resultFrame.show();
    }
    /*****************************************************
     *openFile returns an ImageIcon representation of a graphics 
     *file.  It has been overloaded, so it may take one, or two 
     *input parameters.  If supplied with a single string, the string
     *should be the name of the file to opened.  If supplied with two
     *arguments, they should be the path to the file, and the file 
     *name, respectively.
     ****************************************************/
    public static ImageIcon openFile(String dir, String filename){
	//File f is used only to get the file separator string.  This 
	//is done to ensure platform independence.
	File f = new File(filename);
	String s = f.separator;
	if(dir.endsWith(s)!=true){
	    dir = dir+s;
	}
	Image i;
	ImageIcon oic = new ImageIcon();
	if(filename.endsWith(".gif")||filename.endsWith(".jpg")||filename.endsWith(".jpeg")){
	    i=Toolkit.getDefaultToolkit().getImage(dir+filename);
	    oic = new ImageIcon(i);
	}
	else if(filename.endsWith(".bmp")){
	    i = utils.loadbitmap(dir,filename);
	    oic = new ImageIcon(i);
	}
	else{
	    System.out.println("Unable to open " + filename+".  File must end in bmp, gif, jpg or jpeg.");
	    System.exit(1);
	}
	return oic;
    }

    public static ImageIcon openFile(String filename){
	Image i;
	ImageIcon ic = new ImageIcon();
	if(filename.endsWith(".gif")||filename.endsWith(".jpg")||filename.endsWith(".jpeg")){
	    i=Toolkit.getDefaultToolkit().getImage(filename);
	    ic = new ImageIcon(i);
	}
	else if(filename.endsWith(".bmp")){
	    i = utils.loadbitmap("./",filename);
	    ic = new ImageIcon(i);
	}
	else{
	    System.out.println("Unable to open " + filename+".  File must end in bmp, gif, jpg or jpeg.");
	    System.exit(1);
	}
	return ic;
    }
 
    /**************************************************
     *showNew is a public method to update the image displayed
     *in Viewer.  It takes the the ImageIcon that is to replace
     *the current image as a parameter.
     **************************************************/
    public static void showNew(ImageIcon newIcon){
	//Remove everything from each level of container.
	//This seems to be required to have changes show.
	contentPane.removeAll();
	layoutBox.removeAll();
	imageScroller.removeAll();

	//Change the image and put everything back together
	label1 = new JLabel(newIcon);
	imageScroller = new JScrollPane(label1);
	layoutBox.add(imageScroller);
	contentPane.add(layoutBox);

	//Show your changes
	resultFrame.repaint();
	resultFrame.show();
    }
}





























  
