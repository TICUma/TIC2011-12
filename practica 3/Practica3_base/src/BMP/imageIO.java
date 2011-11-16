package BMP;
// imageIO.java
// This class provides routines for image input and output.

import java.util.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.MediaTracker.*;
import java.io.*;


public class imageIO {


// ****************************** loadImage **********************************
// The loadImage method asks the user for the name of a file that contains
// an image.  It reads the contents of this file, returning the
// data as a three dimensional array (row, column, colour).  Colour
// is 0 for red, 1 for green, 2 for blue, 3 for offset.
// The file name may be for a local file (such as "Camel.jpg") or it may
// be a path (such as "/home/turing/alan.bmp").
//
// private static int[][][] loadImage()
// Parameters:
//    none
//
// Precondition:
//    none
//
// Postcondition:
//    Provided that the user gives a legal file name (the file exists and
//    ends in .gif, .jpg, .jpeg or .bmp), a 3D array containing the image
//    data is returned.

    public static int[][][] loadImage() {
        Image img = (Image)null;  // create a null Image

        
        try{
            while (img == null) {
                // Ask for the name of a file that contains an image.
                System.out.print("What image file do you want to open? (gif, jpeg or bmp)  ");
                String openName = null;
                 openName = imageMain.console.readLine();
        
                // Check that the file name has a legal extension
                if (openName.endsWith(".gif") || openName.endsWith(".jpg")
                                          || openName.endsWith(".jpeg")) {
                img = Toolkit.getDefaultToolkit().getImage(openName);
                }
                else if (openName.endsWith(".bmp")) {
                    img = utils.loadbitmap("./", openName);
                }
                else {
                  img=(Image)null;  // we can't read the file
                }


                if (img != null) {
                     // Make sure the entire image is loaded before continuing 
                     Button b = new Button();  // Create a button to use as a paraemter
                                              // to the constructor for MediaTracker.
                    MediaTracker tracker = new MediaTracker(b);
                    tracker.addImage(img,0);
                    tracker.waitForID(0);

                    // Create "observer", an object that allows us to
                      // use getWidth and getHeight.
                    iObserver observer = new iObserver();
                    int width = img.getWidth(observer);
                    int height = img.getHeight(observer);
        
                    if(width==-1 || height==-1){
                        // the image has not loaded.
                        img = (Image)null;
                    }
                }  // if img != null

                // If the image did not load, print an explanatory
                // message to the user and ask him/her to try again.
                if (img == null) {
                    System.out.println("Could not read an image from file "
                        +openName);
                    System.out.println("Make sure that you supply the name of an image file, \nand that you include the bmp, gif, jpg or jpeg extension.");
                }  // if (img==null)
            } // while img==null
        } // end of "try"

        // Catch InterruptedException for tracker.waitfor(), and catch
        // IOException for the console operations.
        catch(InterruptedException e) {
            System.out.println(e);
            System.exit(1);
        }
        catch(IOException e){  
            System.out.println(e);
            System.exit(1);
        }

        // Translate from Image img to a 3D array "imagePixels".
        // Using this 3D array, imagePixels[r][c][w] gives the value
        // of row r, column c, colour w.
        int[][][] imagePixels = getImagePixels(img);
        return imagePixels;
    } // end of method loadImage  





// **************************** getImagePixels ****************************
// The getImagePixels method converts an image object into a 3D array
// representing pixels (rows, columns, pixel value (red, green, blue, offset))
//
// private static int[][][] getImagePixels(Image image)
//
// Parameters:
//    img - the image which is to be converted
//
// Precondition:
//    image img should be fully loaded
//
// Postcondition:
//    a 3D array representing the pixels of image is returned

    private static int[][][] getImagePixels(Image img) {

        // Get the raw pixel data 
        iObserver observer = new iObserver();
        int width1 = img.getWidth(observer);
        int height1 = img.getHeight(observer);
        int[] rawPixels = utils.getPixels(img,width1,height1);

        // Each pixel is represented by 32 bits.  Separate the tH32 bits into
        // four 8-bit values (red, green, blue, offset).
        int[][] rgbPixels = new int[rawPixels.length][4];
        for(int j=0; j<rawPixels.length; j++) {
            rgbPixels[j][0] = ((rawPixels[j]>>16)&0xff);
            rgbPixels[j][1] = ((rawPixels[j]>>8)&0xff);
            rgbPixels[j][2] = (rawPixels[j]&0xff);
            rgbPixels[j][3] =((rawPixels[j]>>24)&0xff);
        }  // for j

        // Arrange the data by rows and columns
        int[][][] imagePixels = new int[height1][width1][4];
        int index=0;
        for(int row=0; row<imagePixels.length; row++) {
            for(int col=0; col<imagePixels[0].length; col++) {
                for(int rgbo=0; rgbo<4; rgbo++) {
                    imagePixels[row][col][rgbo]=rgbPixels[index][rgbo];
                } // for rgbo
                index++;
            } // for col
        }  // for row
        return imagePixels;
    } // end of method getImagePixels





// ******************************* saveImage *******************************
// The saveImage method saves imagePiexls as a windows bitmap (.bmp).
// The user is asked to provide the file name..
//
// private static void saveImage(int[][][] imagePixels)
//
// Parameters:
//    imagePixels - a 3D array of pixel data
//
// Precondition:
//    imagePixels != null
//
// Postcondition:
//    imagePixels has been converted to a windows bitmap, and saved with
//    the filename provided by the user.  (.bmp is appended to the file
//     name in case the user does not type this extension)

    public static void saveImage(int[][][] imagePixels){
        int height = imagePixels.length;
        int width = imagePixels[0].length;
        int[][] flat = new int[width*height][4];
        String saveName = null;

        // Ask the user for the name of the output file.
        System.out.print("What do you want to call your output file? ");
        try{
        saveName = imageMain.console.readLine();
        }
        catch(IOException e){  
            System.out.println(e);
            System.exit(1);
        }

        // If saveName does not already end in .bmp, then add .bmp to saveName.
        saveName=bmpTack(saveName);

        // Flatten the image into a 2D array.
        int index=0;
        for(int row=0; row<height; row++) {
            for(int col=0; col<width; col++) {
                for(int rgbo=0; rgbo<4; rgbo++) {
                    flat[index][rgbo]=imagePixels[row][col][rgbo];
                }
                index++;
            }  // for col
        }  // for row

        // Combine the 8-bit red, green, blue, offset values into 32-bit words.
        int[] outPixels = new int[flat.length];
        for(int j=0; j<flat.length; j++) {
            outPixels[j] = ((flat[j][0]&0xff)<<16) | ((flat[j][1]&0xff)<<8)
                            | (flat[j][2]&0xff) | ((flat[j][3]&0xff)<<24);
        } // for j

        // Write the data out to file with the name given by string saveName.
        BMPFile bmpf = new BMPFile();
        bmpf.saveBitmap(saveName, outPixels, width, height);
            System.out.println("Saved " + saveName);
    }  // end of method saveImage





// ******************************* bmpTack ************************************
// The bmpTack method checks whether the name parameter ends in ".bmp".
// If it doesn't then ".bmp" is appended to the name.
//
// private static String bmpTack(String name)
// Parameters:
//    name - a string
//
// Precondition:
//    name != null
//
// Postcondition:
//    If name ended in .bmp before being passed into bmpTack, name is returned.
//    If name did not end in .bmp before being passed into bmpTack, name+.bmp 
//    is returned
    private static String bmpTack(String name) {
        if (name.endsWith(".bmp"))
            return name;
        else
            return name+".bmp";
    }  // end of method bmpTack
                
} // end of class imageIOm











