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
    	String imgName="img.jpg";
    	
    	
    	File input = new File(imgName);
    	BufferedImage image = ImageIO.read(input),
    			image2=new BufferedImage(image.getWidth(),image.getHeight(),image.getType());
    	int w=image.getWidth(),h=image.getHeight();
    	int [][] a=new int[][]{
    			{-1,-1,-1},
    			{-1,8,-1},
    			{-1,-1,-1}
    	};
    	
    	int r=Color.RED.getRed();
    	System.out.println(r);
    	for(int x=0;x<w;x++){
    		for(int y=0;y<h;y++){Convolution(image,image2,a,x,y);
    		}
    	}
    	
    	FileOutputStream fos=new FileOutputStream("output_red_"+imgName);
    	ImageIO.write(image2,"jpg", fos);
    	fos.close();
    	
    }
    
    
    public static void process1(BufferedImage image,BufferedImage image2,int x,int y){
		Color c=new Color(image.getRGB(x,y));
		int r=c.getRed(),g=c.getGreen(),b=c.getBlue(),t=r;
		image2.setRGB(x,y,(int)(t*256*256+t*256+t));
    }
    
    
    public static void Convolution(BufferedImage img,BufferedImage img2,int[][] a,int x,int y){
    	
    	
    	
    	
    	float r=0;
    	r+=(a[0][0]*new Color(getRGB(img,x-1, y-1)).getRed());
    	r+=(a[0][1]*new Color(getRGB(img,x-1, y)).getRed());
    	r+=(a[0][2]*new Color(getRGB(img,x-1, y+1)).getRed());
    	r+=(a[1][0]*new Color(getRGB(img,x, y-1)).getRed());
    	r+=(a[1][1]*new Color(getRGB(img,x, y)).getRed());
    	r+=(a[1][2]*new Color(getRGB(img,x, y+1)).getRed());
    	r+=(a[2][0]*new Color(getRGB(img,x+1, y-1)).getRed());
    	r+=(a[2][1]*new Color(getRGB(img,x+1, y)).getRed());
    	r+=(a[2][2]*new Color(getRGB(img,x+1, y+1)).getRed());
    	

    	float g=0;
    	g+=(a[0][0]*new Color(getRGB(img,x-1, y-1)).getGreen());
    	g+=(a[0][1]*new Color(getRGB(img,x-1, y)).getGreen());
    	g+=(a[0][2]*new Color(getRGB(img,x-1, y+1)).getGreen());
    	g+=(a[1][0]*new Color(getRGB(img,x, y-1)).getGreen());
    	g+=(a[1][1]*new Color(getRGB(img,x, y)).getGreen());
    	g+=(a[1][2]*new Color(getRGB(img,x, y+1)).getGreen());
    	g+=(a[2][0]*new Color(getRGB(img,x+1, y-1)).getGreen());
    	g+=(a[2][1]*new Color(getRGB(img,x+1, y)).getGreen());
    	g+=(a[2][2]*new Color(getRGB(img,x+1, y+1)).getGreen());
    	
    	
    	float b=0;
    	b+=(a[0][0]*new Color(getRGB(img,x-1, y-1)).getBlue());
    	b+=(a[0][1]*new Color(getRGB(img,x-1, y)).getBlue());
    	b+=(a[0][2]*new Color(getRGB(img,x-1, y+1)).getBlue());
    	b+=(a[1][0]*new Color(getRGB(img,x, y-1)).getBlue());
    	b+=(a[1][1]*new Color(getRGB(img,x, y)).getBlue());
    	b+=(a[1][2]*new Color(getRGB(img,x, y+1)).getBlue());
    	b+=(a[2][0]*new Color(getRGB(img,x+1, y-1)).getBlue());
    	b+=(a[2][1]*new Color(getRGB(img,x+1, y)).getBlue());
    	b+=(a[2][2]*new Color(getRGB(img,x+1, y+1)).getBlue());
    	
    	float A=1;
    	//r=(r+2040f)/4080/A; g=(g+2040f)/4080/A; b=(b+2040f)/4080/A;
    	r=Math.abs(r)/8/255;g=Math.abs(g)/8/255;b=Math.abs(b)/8/255;
    	//float t=0.14f; r=b=g=((r+g+b)>t)?1:0;
    	float t=0.14f; r=((r+g+b)>t)?1:0;g=b=0;
    	
    	//System.out.println("r="+r+",g="+g+",b="+b+",grey="+(r+g+b));
    	img2.setRGB(x, y,new Color(r,g,b).getRGB());
    }
    
    
    public static int getRGB(BufferedImage image,int x,int y){
    	try{
    		return image.getRGB(x, y);
    	}catch(IndexOutOfBoundsException iobe){
    		return 0;
    	}
    	
    	
    }
    
    
}
