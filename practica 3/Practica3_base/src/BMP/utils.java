package BMP;


import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import java.util.Vector.*;


public class utils{

    /*loadbitmap originally written by Jeff West, and published at
     *http://www.javaworld.com/javaworld/javatips/jw-javatip43.html
     *modified slightly by Jeb Thorley.
     */

    public static  Image loadbitmap (String sdir, String sfile)
    {
        Image image;
        System.out.println("loading:"+sdir+sfile);
        try
            {
                FileInputStream fs;
                if(sdir.equals("./")){//a bit of a hack
                    fs=new FileInputStream(sfile);
                }
                else{
		    fs=new FileInputStream(sdir+sfile);
                }

                int bflen=14; // 14 byte BITMAPFILEHEADER
                byte bf[]=new byte[bflen];
                fs.read(bf,0,bflen);
                int bilen=40; // 40-byte BITMAPINFOHEADER
                byte bi[]=new byte[bilen];
                fs.read(bi,0,bilen);

                // Interperet data.
                int nsize = (((int)bf[5]&0xff)<<24)
                    | (((int)bf[4]&0xff)<<16)
                    | (((int)bf[3]&0xff)<<8)
                    | (int)bf[2]&0xff;
                //System.out.println("File type is :"+(char)bf[0]+(char)bf[1]);
                //System.out.println("Size of file is :"+nsize);

                int nbisize = (((int)bi[3]&0xff)<<24)
                    | (((int)bi[2]&0xff)<<16)
                    | (((int)bi[1]&0xff)<<8)
                    | (int)bi[0]&0xff;
                //System.out.println("Size of bitmapinfoheader is :"+nbisize);

                int nwidth = (((int)bi[7]&0xff)<<24)
                    | (((int)bi[6]&0xff)<<16)
                    | (((int)bi[5]&0xff)<<8)
                    | (int)bi[4]&0xff;
                //System.out.println("Width is :"+nwidth);

                int nheight = (((int)bi[11]&0xff)<<24)
                    | (((int)bi[10]&0xff)<<16)
                    | (((int)bi[9]&0xff)<<8)
                    | (int)bi[8]&0xff;
                //System.out.println("Height is :"+nheight);


                int nplanes = (((int)bi[13]&0xff)<<8) | (int)bi[12]&0xff;
                //System.out.println("Planes is :"+nplanes);

                int nbitcount = (((int)bi[15]&0xff)<<8) | (int)bi[14]&0xff;
                //System.out.println("BitCount is :"+nbitcount);

                // Look for non-zero values to indicate compression
                int ncompression = (((int)bi[19])<<24)
                    | (((int)bi[18])<<16)
                    | (((int)bi[17])<<8)
                    | (int)bi[16];
                //System.out.println("Compression is :"+ncompression);

                int nsizeimage = (((int)bi[23]&0xff)<<24)
                    | (((int)bi[22]&0xff)<<16)
                    | (((int)bi[21]&0xff)<<8)
                    | (int)bi[20]&0xff;
                //System.out.println("SizeImage is :"+nsizeimage);

                int nxpm = (((int)bi[27]&0xff)<<24)
                    | (((int)bi[26]&0xff)<<16)
                    | (((int)bi[25]&0xff)<<8)
                    | (int)bi[24]&0xff;
                //System.out.println("X-Pixels per meter is :"+nxpm);

                int nypm = (((int)bi[31]&0xff)<<24)
                    | (((int)bi[30]&0xff)<<16)
                    | (((int)bi[29]&0xff)<<8)
                    | (int)bi[28]&0xff;
                //System.out.println("Y-Pixels per meter is :"+nypm);

                int nclrused = (((int)bi[35]&0xff)<<24)
                    | (((int)bi[34]&0xff)<<16)
                    | (((int)bi[33]&0xff)<<8)
                    | (int)bi[32]&0xff;
                //System.out.println("Colors used are :"+nclrused);

                int nclrimp = (((int)bi[39]&0xff)<<24)
                    | (((int)bi[38]&0xff)<<16)
                    | (((int)bi[37]&0xff)<<8)
                    | (int)bi[36]&0xff;
                //System.out.println("Colors important are :"+nclrimp);

                if (nbitcount==24)
                    {
                        // No Palatte data for 24-bit format but scan lines are
                        // padded out to even 4-byte boundaries.
                        int npad = (nsizeimage / nheight) - nwidth * 3;
                        //added for Bug correction
                        if(npad == 4){
                            npad=0;
                        }
                        int ndata[] = new int [nheight * nwidth];
                        byte brgb[] = new byte [( nwidth + npad) * 3 * nheight];

                        fs.read (brgb, 0, (nwidth + npad) * 3 * nheight);
                        int nindex = 0;
                        for (int j = 0; j < nheight; j++)
                            {
                                for (int i = 0; i < nwidth; i++)
                                    {
                                        ndata [nwidth * (nheight - j - 1) + i] =

                                            (255&0xff)<<24
                                            | (((int)brgb[nindex+2]&0xff)<<16)
                                            | (((int)brgb[nindex+1]&0xff)<<8)
                                            | (int)brgb[nindex]&0xff;
                                        nindex += 3;
                                    }
                                nindex += npad;
                            }

                        image = Toolkit.getDefaultToolkit().createImage( new MemoryImageSource (nwidth, nheight,ndata, 0, nwidth));
                    }
                else if (nbitcount == 8)
                    {
                        // Have to determine the number of colors, the clrsused
                        // parameter is dominant if it is greater than zero. If
                        // zero, calculate colors based on bitsperpixel.
                        int nNumColors = 0;
                        if (nclrused > 0)
                            {
                                nNumColors = nclrused;
                            }
                        else
                            {
                                nNumColors = (1&0xff)<<nbitcount;
                            }
                        //System.out.println("The number of Colors is"+nNumColors);

                        // Some bitmaps do not have the sizeimage field calculated
                        // Ferret out these cases and fix 'em.
                        if (nsizeimage == 0)
                            {
                                nsizeimage = ((((nwidth*nbitcount)+31) & ~31 ) >> 3);
                                nsizeimage *= nheight;
                                //System.out.println("nsizeimage (backup) is"+nsizeimage);
                            }

                        // Read the palatte colors.
                        int npalette[] = new int [nNumColors];
                        byte bpalette[] = new byte [nNumColors*4];
                        fs.read (bpalette, 0, nNumColors*4);
                        int nindex8 = 0;
                        for (int n = 0; n < nNumColors; n++)
                            {
                                npalette[n] = (255&0xff)<<24
                                    | (((int)bpalette[nindex8+2]&0xff)<<16)
                                    | (((int)bpalette[nindex8+1]&0xff)<<8)
                                    | (int)bpalette[nindex8]&0xff;
                                nindex8 += 4;
                            }
                        // Read the image data (actually indices into the palette)
                        // Scan lines are still padded out to even 4-byte
                        // boundaries.
                        int npad8 = (nsizeimage / nheight) - nwidth;
                        //System.out.println("nPad is:"+npad8);

                        int ndata8[] = new int [nwidth*nheight];
                        byte bdata[] = new byte [(nwidth+npad8)*nheight];
                        fs.read (bdata, 0, (nwidth+npad8)*nheight);
                        nindex8 = 0;
                        for (int j8 = 0; j8 < nheight; j8++)
                            {
                                for (int i8 = 0; i8 < nwidth; i8++)
                                    {
                                        ndata8 [nwidth*(nheight-j8-1)+i8] =
                                            npalette [((int)bdata[nindex8]&0xff)];
                                        nindex8++;
                                    }
                                nindex8 += npad8;
                            }

                        image = Toolkit.getDefaultToolkit().createImage( new MemoryImageSource (nwidth, nheight,ndata8, 0, nwidth));
                    }
                else if (nbitcount == 1) {

		    int npad1 = (nsizeimage / nheight) - nwidth/8;
		    byte bdata[] = new byte [(nwidth+npad1)*nheight];
		    fs.read (bdata, 0, 8);
		    fs.read (bdata, 0, (nwidth+npad1)*nheight);
		    int ndata1[] = new int [nwidth*nheight];
		    int nindex1 = 0 ;

		    int max = 0 ;
		    //System.out.println (" npad1 = " + npad1);

		    for (int j1 = 0 ; j1 < nheight ; j1++) {
			int iindex ;
			iindex = nindex1 ;
			for (int i1 = 0 ; i1 <= nwidth/8 ; i1++) {
			    int ib1 = 0 ;
			    if (i1*8 < nwidth) {
                                for (int b1 = 128 ; b1 > 0 ; b1 = b1 / 2) {
				    ndata1 [nwidth*(nheight-j1-1)+i1*8+ib1] = ((b1 & bdata[iindex]) > 0) ? 255+(255+255*256)*256 : 0 ;
				    ib1++ ;
				    if (i1*8+ib1 >= nwidth) {
					b1 = 0 ;
				    }
                                }
			    }
			    max = i1 * 8 + ib1 ;
			    iindex++ ;
			}
			nindex1 += (nsizeimage / nheight) ;
		    }
		    //System.out.println ("max = " + max);

		    image = Toolkit.getDefaultToolkit().createImage( new MemoryImageSource (nwidth, nheight,ndata1, 0, nwidth));
                }
                else
                    {
                        System.out.println ("Not a 24-bit or 8-bit or 1-bit Windows Bitmap, aborting...");
                        image = (Image)null;
                    }

                fs.close();
                return image;

            }
        catch (Exception e)
            {
                System.out.println("Caught exception in loadbitmap!");
            }
        return (Image)null;
    }


    public static  int[] getPixels(Image parImage, int parWidth, int parHeight)
    {
        int[] bitmap = new int [parWidth * parHeight];
        PixelGrabber pg = new PixelGrabber (parImage, 0, 0, parWidth, parHeight,
                                            bitmap, 0, parWidth);
        try {
            pg.grabPixels ();
        }
        catch (InterruptedException e) {
            e.printStackTrace ();
        }
        return bitmap;
    }
}
