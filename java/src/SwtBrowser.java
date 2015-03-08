import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;

import javax.imageio.ImageIO;

import org.eclipse.jetty.server.Server;
import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.browser.*;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.internal.mozilla.nsIDOMWindow;
import org.eclipse.swt.internal.mozilla.nsIWebBrowser;
import org.eclipse.swt.layout.*;
import org.mozilla.interfaces.*;

import sun.misc.BASE64Decoder;

import com.sun.org.apache.xml.internal.security.utils.Base64;



public class SwtBrowser {

	Server server;
	Display display = null;
	Shell shell = null;
	Browser browser;

	public SwtBrowser(final Server server) throws Exception {
		
		this.server = server;
		
		display = new Display();
		shell = new Shell(display);
		shell.setLayout(new FillLayout());
		shell.setText("test");
		shell.setImage(new Image(display, "../images/logo.png"));
		// shell.setSize(500, 500);

		
		shell.addListener(SWT.Close, new Listener() {
		      public void handleEvent(Event event) {
					try {
						server.stop();
					} catch (Exception e) {
						e.printStackTrace();
					}
		      }
		    });
		
		Menu menuBar = new Menu(shell, SWT.BAR);
		MenuItem fileMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
		fileMenuHeader.setText("&File");

		Menu fileMenu = new Menu(shell, SWT.DROP_DOWN);
		fileMenuHeader.setMenu(fileMenu);

		MenuItem fileExitItem = new MenuItem(fileMenu, SWT.PUSH);
		fileExitItem.setText("E&xit");
		fileExitItem.addSelectionListener(new fileExitItemListener());

		MenuItem backwardItem = new MenuItem(fileMenu, SWT.PUSH);
		backwardItem.setText("B&ack");
		backwardItem.addSelectionListener(new backwardListener());

		shell.setMenuBar(menuBar);

		try {
			browser = new Browser(shell, SWT.MOZILLA);
			//browser = new Browser(shell, SWT.NONE);
			
			//browser.setMenu(fileMenu);
		} catch (SWTError e) {
			System.out.println("Could not instantiate Browser: "
					+ e.getMessage());
			display.dispose();
			return;
		}
		shell.open();

		final BrowserFunction function1 = new CustomFunction1 (browser, "theJavaFunction1");
		final BrowserFunction function2 = new CustomFunction2 (browser, "theJavaFunction2");
		
		// browser.setSize(1000, 500);
		browser.setUrl("http://localhost:8181/html/flotcluster.html");
		//browser.setUrl("http://images.elephantjournal.com/wp-content/uploads/2011/10/BabyElephant.jpg");
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	
	
	}

	class fileExitItemListener implements SelectionListener {
		public void widgetSelected(SelectionEvent event) {
			try {
				server.stop();
			} catch (Exception e) {
				e.printStackTrace();
			}
			shell.close();
			display.dispose();
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {

		}
	}

	class backwardListener implements SelectionListener {
		public void widgetSelected(SelectionEvent event) {
			browser.back();
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {

		}
	}

	static class CustomFunction1 extends BrowserFunction {
		CustomFunction1 (Browser browser, String name) {
			super (browser, name);
		}
		@Override
		public Object function (Object[] arguments) {
			Object arg = arguments[0];
			if (arg != null) {
				
				String[] parts = arg.toString().split(",");
				System.out.println(parts[1]);
				BufferedImage image = null;
		        byte[] imageByte;
		        try {
		            BASE64Decoder decoder = new BASE64Decoder();
		            imageByte = decoder.decodeBuffer(parts[1]);
		            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
		            image = ImageIO.read(bis);
		            bis.close();
		            //System.out.println(image.getHeight());
		            File outputfile = new File("circle.jpg");
		            ImageIO.write(image, "png", outputfile);
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
			}
			return null;
		}
	}

	static class CustomFunction2 extends BrowserFunction {
		CustomFunction2 (Browser browser, String name) {
			super (browser, name);
		}
		@Override
		public Object function (Object[] arguments) {
			Object arg = arguments[0];
			if (arg != null) {
				System.out.println("2: " + arg.toString());

			}
			return null;
		}
	}

	public static void main(String[] args) {
		// SwtBrowser swtBrowser = new SwtBrowser();
	}

}