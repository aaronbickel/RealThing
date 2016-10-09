package imageViewer;

import java.io.File;

public class FileNode {

    private File file;

    public FileNode(File file) {
        this.file = file;
    }

    @Override
    public String toString() {
        /*String name = file.getName();
        if (name.equals("")) {
            return file.getAbsolutePath();
        } else {
            return name;*/
    	//the commented out selection returns just folder name
    	return file.getAbsolutePath();
        
    }

}
