package com.hackerdude.devtools.pathedit.ui;

import javax.swing.UIManager;
import java.awt.*;
import java.io.File;
import java.util.*;

public class PathEdit {

	boolean packFrame = false;
	ProgramOptions options = new ProgramOptions();

  //Construct the application
  public PathEdit() {
    PathEditFrame frame = new PathEditFrame();
    //Validate frames that have preset sizes
    //Pack frames that have useful preferred size info, e.g. from their layout
    if (packFrame) {
      frame.pack();
    }
    else {
      frame.validate();
    }
    //Center the window
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = frame.getSize();
    if (frameSize.height > screenSize.height) {
      frameSize.height = screenSize.height;
    }
    if (frameSize.width > screenSize.width) {
      frameSize.width = screenSize.width;
    }
    frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    frame.setVisible(true);
  }

  //Main method
  public static void main(String[] args) {


    PathEdit pe = new PathEdit();
	pe.parseArgs(args);
	if (pe.options.showHelp ) {
		showHelp();
		System.exit(2);
	}
	if ( ! pe.options.useMetalLF ) {
		try {
		  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e) {
		  e.printStackTrace();
		}
	}

  }

	public void parseArgs(String[] args) {

		for ( int i=0; i<args.length; i++) {
			if ( args[i].startsWith("-") || args[i].startsWith("/") ) {
				if ( args[i].endsWith("metal") ) {
				    options.useMetalLF = true;
				}
				if ( args[i].endsWith("h") || args[i].endsWith("?") || args[i].endsWith("help") ) { options.showHelp = true; }
			}
			else if ( ! new File(args[i]).exists() ) {
				System.out.println("Error: File "+args[i]+" does not exist");
				System.exit(1);
			} else {
				options.fileNames.add(args[i]);
			}
		}
		//options =
	}

	public static void showHelp() {
		System.out.println("PathEdit [help]");
	}

  public class ProgramOptions {
	boolean useMetalLF = false;
	boolean showHelp = false;
	ArrayList fileNames = new ArrayList();
  }



}