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

import java.io.File;

public class PathEditItem {

/*  public static final int PATHITEM_DIRECTORY = 0;
  public static final int PATHITEM_JAR         = 1;
  public static final int PATHITEM_UNKNOWN;    = 2;
*/

  protected String fileName;
  protected File theFile;

  /**
   * Creates a new PathEdit item. This
   */
  public PathEditItem(String pathItem) {
	setPathItem(pathItem);
  }

  public void setPathItem(String pathItem) {

    fileName = pathItem;

    String pathSep = System.getProperty("path.separator");
    /*
     * Note: I'm assuming that a path separator means a unix-like
     * system. I am also assuming that ~ means the home directory
     * under all shells, which I realize is incorrect.
     */
    String theRealFile = fileName;
    if ( pathSep.equals(":") ) {
	    // If we are in unix makes sure the ~ is the home directory.
       if ( fileName.startsWith("~") ) {
         theRealFile = System.getProperty("user.home")+
                       System.getProperty("file.separator")+
                       pathItem.substring(2, pathItem.length());
       }
    }
    theFile = new File(theRealFile);
  }

  /**
   * Returns true if this path item exists.
   */
  public boolean exists() {
     return theFile.exists();
  }

  public String toString() {
    return( fileName );
  }

  /**
   * This function calculates the relative path from two absolute paths.
   *
   * @param relativeTo The directory that the path will be relative to.
   * @param file The file or directory that we want to reach from the relativeTo location.
   * @returns A string with the relative path using double-dot notation.
   * @warning Does not work with multiple drives under Windows yet.
   */
  public static String calculateRelativePath(String relativeTo, String file) {
	// First check if they're the same thing. If they are, just return a dot.
	if ( relativeTo.equals(file) ) return ".";
	if ( ! relativeTo.endsWith(File.separator) ) relativeTo = relativeTo+File.separator;
	StringBuffer commonality = new StringBuffer(relativeTo.length());
	for ( int i=0; i<relativeTo.length(); i++ ) {
		char relChar = relativeTo.charAt(i);
		if ( i < file.length() ) {
			char fileChar = file.charAt(i);
			if ( relChar == fileChar ) {
				commonality.append(relativeTo.charAt(i));
			} else {
				break;
			}
		}
	}
	// Different Drives on Windows machine; return the absolute path.
	if ( commonality.length() == 0 && System.getProperty("os.name").startsWith("Win") ) {
		return file;
	}
	String newRelativeTo = relativeTo.substring(commonality.length());
	int stepsBack = 0;
	while ( newRelativeTo.indexOf(File.separator) > -1 ) {
		newRelativeTo = newRelativeTo.substring(newRelativeTo.indexOf(File.separator)+1);
		stepsBack++;
	}
	if ( newRelativeTo.length() > 1 ) stepsBack++;
	StringBuffer newPath = new StringBuffer();
	for ( int i=0; i<stepsBack; i++) {
		newPath.append("..").append(File.separatorChar);
	}
	newPath.append(file.substring(commonality.length()));

	if ( newPath.length() == 0 ) newPath.append(".");
	return newPath.toString();
  }

  public static void main(String[] args ) {
    String relativeTo = "C:\\Projects\\Personal\\src";
	String fileName   = "C:\\Documents and Settings\\dmartinez";
	String calculatedResult = calculateRelativePath(relativeTo, fileName);

	String correctResult = "..\\..\\..\\Documents and Settings\\dmartinez";


  }

}