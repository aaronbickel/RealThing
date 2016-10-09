package imageViewer;

import java.io.File;

import javax.swing.tree.DefaultMutableTreeNode;

public class CreateChildNodes implements Runnable {

    private DefaultMutableTreeNode dirrootnode;

    private File fileRoot; //grab the root location from above

    public CreateChildNodes(File fileRoot,  //runs the actual method here
            DefaultMutableTreeNode dirrootnode) {
        this.fileRoot = fileRoot;
        this.dirrootnode = dirrootnode;
    }
    @Override
    public void run() {
        createChildren(fileRoot, dirrootnode);
}

    private void createChildren(File fileRoot, 
            DefaultMutableTreeNode dirrootnode){
    	File[] dirfileslist = fileRoot.listFiles(); 
    	//creates an array called dirfileslist that lists all the files at dirrootnode
        if (dirfileslist == null) return;
        for (File file : dirfileslist) {
        	if (file.isDirectory()){
        		DefaultMutableTreeNode childNode = 
                        new DefaultMutableTreeNode(new FileNode(file));
                dirrootnode.add(childNode);
                createChildren(file, childNode);
        		
        	}
        }
        
        
  
    	
    }
		
}

