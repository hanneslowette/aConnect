package org.hannes;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.widgets.TrayItem;
import org.hannes.ui.CommandEventListener;

/**
 * Contains application entry point
 * 
 * @author red
 */
public class Main {

	/**
	 * The display
	 */
	private static final Display display = new Display();

	/**
	 * The shell
	 */
	private static final Shell shell = new Shell();
	
	/**
	 * The commands for mounting and unmounting the android device
	 */
	private static final String[][] COMMAND = {
		{
			"mtpfs", "-o", "allow_other", "/media/Nexus 7/"
		},
		{
			"fusermount", "-u", "/media/Nexus 7/"
		}
	};

	/**
	 * Application entry point
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * Get the system tray
		 */
		Tray tray = display.getSystemTray();
		
		/*
		 * Some computers will not have a system tray
		 * TODO: exception
		 */
		if (tray == null) {
			throw new NullPointerException();
		}
		
		/*
		 * Create the tray item
		 */
		TrayItem item = new TrayItem(tray, SWT.NONE);
		item.setImage(new Image(display, "thumbnail.png"));
		
		/*
		 * Create the menu
		 */
		final Menu menu = new Menu(shell, SWT.POP_UP);
		final MenuItem connectMenuItem = new MenuItem(menu, SWT.PUSH);
		connectMenuItem.setText("Connect devices");
		
		final MenuItem disconnectMenuItem = new MenuItem(menu, SWT.PUSH);
		disconnectMenuItem.setText("Disconnect devices");
		
		final MenuItem exitMenuItem = new MenuItem(menu, SWT.PUSH);
		exitMenuItem.setText("Exit");
		item.addListener(SWT.MenuDetect, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				menu.setVisible(true);
			}

		});
		
		connectMenuItem.addListener (SWT.Selection, new CommandEventListener(display, COMMAND[0]));
		disconnectMenuItem.addListener (SWT.Selection, new CommandEventListener(display, COMMAND[1]));
		exitMenuItem.addListener (SWT.Selection, new Listener () {
			
			@Override
			public void handleEvent(Event event) {
				Main.display.close();
			}

		});
		
		/*
		 * eww
		 */
		while (!display.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

}