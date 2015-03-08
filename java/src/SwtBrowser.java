import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.browser.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.*;

public class SwtBrowser {

	public static void main(String [] args) {
		SwtBrowser swtBrowser = new SwtBrowser();
	}

	public SwtBrowser() {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		shell.setText("test");
		shell.setImage(new Image(display, "C:\\Users\\johan\\Desktop\\xul\\logo.png"));
		//shell.setSize(500, 500);
		
		
	    Menu menuBar = new Menu(shell, SWT.BAR);
	    MenuItem fileMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
	    fileMenuHeader.setText("&File");

	    Menu fileMenu = new Menu(shell, SWT.DROP_DOWN);
	    fileMenuHeader.setMenu(fileMenu);

	    MenuItem fileSaveItem = new MenuItem(fileMenu, SWT.PUSH);
	    fileSaveItem.setText("&Save");
		
	    shell.setMenuBar(menuBar);
		
		final Browser browser;
		try {
			browser = new Browser(shell, SWT.MOZILLA);
		} catch (SWTError e) {
			System.out.println("Could not instantiate Browser: " + e.getMessage());
			display.dispose();
			return;
		}
		shell.open();
		
		//browser.setSize(1000, 500);
		browser.setUrl("http://localhost:8181");
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) display.sleep();
		}
		display.dispose();
	}
}