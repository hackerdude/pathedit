/**
 * Title:        PathEdit - A Classpath Editor written in Java<p>
 * Description:  This is a tiny program that tries to make it easy for the developer
 * to take a path specification and reorganize it.<p>
 * Copyright:    Copyright (c) David Martinez<p>
 * Company:      <p>
 * @author David Martinez
 * @version 1.0
 */
package com.hackerdude.devtools.pathedit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

/**
 * A model representation of a file. It tries to respect the lines it doesn't know
 * about.
 * 
 * @author davidm <a href="mailto:david@hackerdude.com">david@hackerdude.com</a>
 */
public class PathEditFile {

  Vector theLines;
  Vector theVariables;

  /**
   * Creates a new PathEditFile.
   */
  public PathEditFile()  {
    theLines = new Vector();
    theVariables = new Vector();
    String classpath = "CLASSPATH="+System.getProperty("java.class.path");
    PathEditLine aLine = new PathEditLine(classpath);
    theLines.add(aLine);
    if ( aLine.isPath() ) { theVariables.add(aLine); }
  }

  /**
   * Creates a new PathEditFile read on the specified filename.
   * 
   * @param fileName The filename to read from.
   * @throws IOException If there is problem reading the file.
   */
  public PathEditFile(String fileName) throws IOException {
    if ( fileName != null ) {
       loadFromFile(fileName);
    }

  }

  /**
   * Loads the local storage from a file. 
   * @param fileName The filename to use
   * @throws FileNotFoundException If the file cannot be found
   * @throws IOException If there is a problem reading the file.
   */
  protected void loadFromFile(String fileName) throws FileNotFoundException, IOException {
    FileReader fileReader = new FileReader( fileName );
    try {
			BufferedReader br = new BufferedReader(fileReader);
	    String item = br.readLine();
	    theLines = new Vector();
	    theVariables = new Vector();
	    while ( item != null ) {
	      PathEditLine aLine = new PathEditLine(item);
	      theLines.add(aLine);
	      if ( aLine.isPath() ) { theVariables.add(aLine); }
	      item = br.readLine();
	    }
    } finally {
    	fileReader.close();
    }
  }

  /**
   * Returns how many variables the current file contains.
   * 
   * @return The amount of variables that were identified.
   */
  public int size() {
    return theVariables.size();
  }

  /**
   * Returns the line definition at the index specified.
   * 
   * @param index The index of the item
   * @return THe line definition.
   */
  public PathEditLine get( int index ) {
    return( (PathEditLine)theVariables.get(index) );
  }

  /**
   * Saves the document to the specified file.
   * 
   * @param fileName The filename to use
   * @throws FileNotFoundException If the file to save on does not exist (i.e. wrong directory)
   * @throws IOException If there is a problem writing this file.
   */
  public void saveToFile(String fileName) throws FileNotFoundException, IOException {
    FileWriter fileWriter = new FileWriter(fileName);
    try {
			PrintWriter bw = new PrintWriter( new BufferedWriter(fileWriter));
	    for ( int i=0; i<theLines.size(); i++ ) {
	      PathEditLine theLine = (PathEditLine)theLines.get(i);
	      bw.println(theLine.toString());
	    }
	    bw.flush();
    } finally {
    	fileWriter.close();
    }
  }

}