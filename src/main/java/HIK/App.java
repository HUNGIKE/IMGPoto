package HIK;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) throws IOException {
    	File input = new File("img.jpg");
    	BufferedImage image = ImageIO.read(input)
    			,image2=new BufferedImage(image.getWidth(),image.getHeight(),image.getType());
    	int w=image.getWidth(),h=image.getHeight();
    	for(int i=0;i<w;i++){
    		for(int j=0;j<h;j++){
    			
    			//process1(image,image2,i,j);
    			
    			try{
    			int u=image.getRGB(i-1,j),
    				d=image.getRGB(i+1,j),
    				r=image.getRGB(i,j-1),
    				l=image.getRGB(i,j+1),
    				u_l=image.getRGB(i-1,j+1),
    		    	d_l=image.getRGB(i+1,j+1),
    		    	u_r=image.getRGB(i-1,j-1),
    		    	d_r=image.getRGB(i+1,j-1),
    				t=image.getRGB(i,j);
    			int A=90;
    			if(    Math.abs(t-r)>256*256*A ||
    			       Math.abs(t-l)>256*256*A ||
    			       Math.abs(t-u)>256*256*A ||
    			       Math.abs(t-d)>256*256*A ||
       			       Math.abs(t-u_l)>256*256*A ||
    			       Math.abs(t-u_r)>256*256*A ||
    			       Math.abs(t-d_l)>256*256*A ||
    			       Math.abs(t-d_r)>256*256*A || 
    				Math.abs(image.getRGB(i,j)-Color.red.getRGB()) < 256*256*A 
    					){
    				// image2.setRGB(i,j,Color.black.getRGB());
    				image2.setRGB(i,j,image.getRGB(i,j));
    				//process1(image,image2,i,j);;
    			}else{

    				//image2.setRGB(i,j,Color.white.getRGB());
    				image2.setRGB(i,j,image.getRGB(i,j));
    				//process1(image,image2,i,j);;
    			
    			}
    			}catch(Throwable t){}
    			
    			
    			
    		}
    	}
    	
    	FileOutputStream fos=new FileOutputStream("img2.jpg");
    	ImageIO.write(image2,"jpg", fos);
    	fos.close();
    	
    }
    
    
    public static void process1(BufferedImage image,BufferedImage image2,int x,int y){
		Color c=new Color(image.getRGB(x,y));
		int r=c.getRed(),g=c.getGreen(),b=c.getBlue(),t=r;
		image2.setRGB(x,y,(int)(t*256*256+t*256+t));
    }
    
    
    public static int  getRGB(BufferedImage image,int x,int y){
    	try{
    		return image.getRGB(x, y);
    	}catch(IndexOutOfBoundsException iobe){
    		return 0;
    	}
    	
    	
    }
    
    
}
