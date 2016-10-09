package imageViewer;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;


public class ExternalLayout extends JPanel /*implements TreeSelectionListener*/ implements ActionListener{

	//main panel
	JPanel panelmain;
	
	//image panel - center
	JPanel panelimage; 
	String imagepath = "C:/Users/aaron/Pictures/stuff/aaroncar/tempadr/W-X-Y-Z/ZongshenStorm.jpg";
	String [] imagepatharray; //maybe this should be an array list?
	
	//directory logic
	File fileRoot = new File("C:/Users/aaron/Pictures/stuff/aaroncar/tempadr");
	
	//tree panel - west
	JTree dirtree;
	
	
	
	//here is where we do the create child node
	/*public void run(){
		CreateChildNodes ccnodes = new CreateChildNodes(fileRoot,dirrootnode);
		 new Thread(ccnodes).start();
	}*/
	

		
	
	
	public ExternalLayout() throws IOException{
		super();
		//main
		panelmain = new JPanel();
		panelmain.setLayout(new BorderLayout());
		
		//center - images
		panelimage = new JPanel();
		panelimage.setLayout(new GridLayout(0,6));
		JScrollPane panelimageScr = new JScrollPane(panelimage,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		ImageIcon ii = new ImageIcon();
		BufferedImage img = ImageIO.read(new File (imagepath));
		
		JLabel lbl = new JLabel();
		ii.setImage(img.getScaledInstance(200, -1, Image.SCALE_FAST));
		lbl.setIcon((Icon) ii);
		
		JLabel lbl2 = new JLabel();
		ii.setImage(img.getScaledInstance(200, -1, Image.SCALE_FAST));
		lbl2.setIcon((Icon) ii);
		
		//west - directory	
		DefaultMutableTreeNode dirrootnode = new DefaultMutableTreeNode(new FileNode(fileRoot));
		CreateChildNodes ccnodes = new CreateChildNodes(fileRoot,dirrootnode);
		 new Thread(ccnodes).start();
		DefaultTreeModel treemodel = new DefaultTreeModel(dirrootnode);
		dirtree = new JTree(treemodel);
		dirtree.setShowsRootHandles(true);
		dirtree.addTreeSelectionListener(new TreeSelectionListener(){
			public void valueChanged(TreeSelectionEvent e) {
				
				TreePath path = e.getNewLeadSelectionPath();
			    if (path != null) {
			        Object f =  path.getLastPathComponent();
			        String selecteddir = f.toString();
			        imagepatharray = ImageGrabber.Grab(selecteddir);
			        displayImages(imagepatharray);
			        
			        //this code determines what was last clicked and sends it to image grabber as an input
			        //output of image grabber is array of all file paths in folder
			    }
 
				
			}
		});
		
		dirtree.getSelectionModel().setSelectionMode
        (TreeSelectionModel.SINGLE_TREE_SELECTION);
		JScrollPane dirScr = new JScrollPane(dirtree, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//only one thing can be selected at a time
		
		//System.out.println(Arrays.toString(imagepatharray));
		
		
		
		
		
		//add everything
		
		panelmain.add(panelimageScr, BorderLayout.CENTER);
		panelmain.add(dirScr, BorderLayout.WEST);
		panelimage.add(lbl);
		panelimage.add(lbl2);
		add(panelmain);

	}
	
	private void displayImages(String[] imagearray){
		panelimage.removeAll();
		for(String item : imagearray){
			//System.out.println(" "+item+"\n");
			try {
				BufferedImage img = ImageIO.read(new File (item));
				JButton imgbutton = new JButton(new ImageIcon(img.getScaledInstance(200, -1, Image.SCALE_FAST)));
				imgbutton.addActionListener(this);
				imgbutton.setName(item);
				panelimage.add(imgbutton);
			} catch (IOException e) {
				e.printStackTrace();
			}
			panelimage.revalidate();
		}
		
	}
	
	private static void createGUI() throws IOException{
		JFrame frame = new JFrame();
		frame.add(new ExternalLayout());
		frame.pack();
		frame.setSize(1600,800);
		frame.setResizable(true);
		frame.setVisible(true);
	}
	
	
	//main is here - run it w invoke later for constant updating
	public static void main(String[] args) throws IOException {
		SwingUtilities.invokeLater(new Runnable(){

			public void run() {
				try {
					createGUI();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("action!");
		JButton buttonPressed = (JButton) (e.getSource());
		System.out.println(buttonPressed.getName());
	}	
}
