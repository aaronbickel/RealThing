package imageViewer;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

public class ImageGrabber {
	

	
	public static void main(String[] args) {
		
	}
	
public ImageGrabber(String selecteddir){
	
}
		

	public static String[] Grab(String dirpath2){
		
		
		//String[] picfilenames2;
		
		Collection<String> picfilenames  = new ArrayList<String>();
		
		File directoryin = new File(dirpath2);
		
		File[] allfilenames = directoryin.listFiles();
		
		for(File file:allfilenames){
			if(file.isFile()){
				picfilenames.add(file.getAbsolutePath());
			}
		}
		//System.out.println(picfilenames.toString());
		return picfilenames.toArray(new String[]{});
		
		
		
		
	}


}
