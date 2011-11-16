package BMP;


// imageMain.java
// This main() method illustrates how to perform image operations:
//    - read an image from an input file.  The user is asked to type a
//      file name.  The file can be in gif, jpeg or bmp format.
//    - write an image to a bmp output file.  The user is asked to type the
//      file name.
//    - modify the image
//             apply a green filter to the top half,
//             apply a fading green filter across the whole image, 
//             add vertical black bars to the image,
//             convert to a grayscale image
//      The user selects which operations to apply.
//
// Within the Java code, a colour image is represented using a 
// three-dimensional array.
//    The first index selects one row of the image.
//    The second index selects on column of the image.
//    The third index selects a colour (RED, GREEN, or BLUE).
// For example, "testImage[3][5][RED]" is an integer value in the range
// 0 to 255.  A 0 indicates that there is no red colour at row 3, column 5
// in testImage.  A 255 indicates that there is maximal red colour at
// this location.
// If RED, GREEN, and BLUE are all 0, the image appears black.  If they
// are all 255, the image appears white.
//
import java.util.*;
import java.io.*;



public class imageMain {

    // Give names to the constants used for the red, green and blue 
    // values in an image.
    // The use of final variables is discussed on page 10-11 of the
    // Main textbook used in CISC124.
    // Red, green and blue are 8 bit quantities, each with a value 
    // between 0 and 255.  In a file, each pixel occupies a 32 bit word.
    // Since red, green and blue need a total of 24 bits, this leaves
    // 8 extra bits.  These extra bits are called "offset".  You never need
    // to change the value of the offset bits.
    final static int RED    = 0;
    final static int GREEN  = 1;
    final static int BLUE   = 2;
    final static int OFFSET = 3;  // ignore offset; use only red, green, blue


    // The console is used to receive input from the user.  In this program,
    // the user is asked to type file names.
    static public BufferedReader console = new BufferedReader(
                                    new InputStreamReader(System.in));




// ****************************** main **********************************
// The main method reads an input image file, and allows the user to 
// select from a set of modifications that can be applied to the image.
//

    public static void main(String[] args) {
        System.out.println("Welcome to the Image Program.");
        System.out.println("");

        // This program is supposed to be run without any arguments.
        // If the user supplies arguments, issue an explanatory message.
        if (args.length!=0) {
           System.out.println("Usage: java imageProgram");
           System.exit(1);  // exit.  The exit code 1 indicates failure
        }


        // Ask the user for a file name and read the contents of this 
        // file into "testImage".
        // After method "loadImage" completes, the size of the image 
        // is available as follows:
        //    - the number of rows is testImage.length
        //    - the number of columns is testImage[0].length
        int[][][] testImage = imageIO.loadImage();
        final int MAXROWS = testImage.length;
        final int MAXCOLS = testImage[0].length;



        // Repeatedly offer the user a list of operations to choose from
        // Sample operations are provided, to illustrate how an image
        // can be updated.
        //
        while (true) {   
            System.out.println("");
            System.out.println("What operation do you want to perform? ");
            System.out.println("   1 Green filter in top half   2 Fading green filter");
            System.out.println("   3 Add vertical black bars    4 Convert to gray scale");
            System.out.println("   5 Save as bmp file           6 quit");
            String reply = null;
            try{
            reply = console.readLine();
            }
            catch(IOException e) {
                System.out.println(e);
                System.exit(1);
            }


            // Make the top half of the image green, while still allowing
            // red and blue to show through.  This is done by visiting
            // rows 0 to MAXROWS/2.  At each visited pixel, the GREEN value 
            // is set to its maximum possible value, which is 255.
            if (reply.startsWith("1")) {
                for (int row=0; row<MAXROWS/2; row++) {
                    for (int col=0; col<MAXCOLS; col++) {
                        testImage[row][col][GREEN] = 255;
                    }  // for col
                }  // for row
                System.out.println("The top half of the image is green.");
            } // if reply is 1

            // Apply a fading green filter to the image.  Green is weak
            // on the left and strong on the right.
            else if (reply.startsWith("2")) {
                double scaleFactor = 255.0/MAXCOLS;
                for (int row=0; row<MAXROWS; row++) {
                    for (int col=0; col<MAXCOLS; col++) {
                        testImage[row][col][GREEN] = (int)(col*scaleFactor);
                    }  // for col
                }  // for row
                System.out.println("A fading green filter has been applied.");
            } // if reply is 2

            // Put vertical bars across the image.  
            // Use modulus (written as %) to decide which columns to make black
            else if (reply.startsWith("3")) {
                for (int row=0; row<MAXROWS; row++) {
                    for (int col=0; col<MAXCOLS; col++) {
                        if (col % 30 > 22) { 
                            testImage[row][col][RED]   = 0;
                            testImage[row][col][GREEN] = 0;
                            testImage[row][col][BLUE]  = 0;
                      }  // if col
                    }  // for col
                }  // for row
                System.out.println("Vertical bars have been added.");
            } // if reply is 3

            // Convert the image from colour to grayscale
            else if (reply.startsWith("4")) {
                for (int row=0; row<MAXROWS; row++) {
                    for (int col=0; col<MAXCOLS; col++) {
                        int avg = (testImage[row][col][RED]
                                 + testImage[row][col][BLUE]
                                 + testImage[row][col][GREEN]) / 3;
                        testImage[row][col][RED]   = avg;
                        testImage[row][col][GREEN] = avg;
                        testImage[row][col][BLUE]  = avg;
                    }  // for col
                }  // for row
                System.out.println("The image is now grayscale.");
            }  // if reply is 4

            // Save the image to a bmp file
            else if (reply.startsWith("5")) {
                imageIO.saveImage(testImage);
            }  // if reply is 5

            // Quit: Close the console and exit the program 
            else if (reply.startsWith("6")) {
                try{
                    console.close();
                }
                catch(IOException e) {
                    System.out.println(e);
                    System.exit(1);
                }
                System.exit(0);
            }  // if reply is 6

            // The user's reply was not recognized as a command.
            else System.out.println("Unrecognized command.  Please try again.");
        } // end of "while true"
    } // end of main()


} // end of class imageMain











