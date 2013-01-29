package org.hannes.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 * @author red
 *
 */
public class ProgressDialog {

	/**
	 * ok
	 */
	private final ProgressBar progressBar;
	
	/**
	 * the shell
	 */
	private final Shell shell;

	/**
	 * 
	 * @param display
	 * @param shell
	 */
	public ProgressDialog(Display display, Shell shell) {
		/*
		 * Init shell
		 */
		this.shell = shell;
		this.shell.setSize(330, 70);
		this.shell.setText("Searching for devices ...");

		/*
		 * Set progressbar
		 */
		this.progressBar = new ProgressBar(this.shell, SWT.HORIZONTAL | SWT.INDETERMINATE);
		this.progressBar.setMinimum(0);
		this.progressBar.setMaximum(100);
		this.progressBar.setBounds(10, 10, 300, 20);

		/*
		 * Calculate center of screen
		 */
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shell.getBounds();

		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;

		/*
		 * Set shell location
		 */
		this.shell.setLocation(x, y);
	}

	/**
	 * Opens the shell
	 */
	public void open() {
	    this.shell.open();
	}

	/**
	 * Closes the shell
	 */
	public void close() {
	    this.shell.dispose();
	}

}
