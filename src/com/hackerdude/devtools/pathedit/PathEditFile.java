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

import java.io.*;
import java.util.*;

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

  public PathEditFile(String fileName) throws IOException {
    if ( fileName != null ) {
       loadFromFile(fileName);
    }

  }

  protected void loadFromFile(String fileName) throws FileNotFoundException, IOException {
    BufferedReader br = new BufferedReader(new FileReader( fileName ));
    String item = br.readLine();
    theLines = new Vector();
    theVariables = new Vector();
    while ( item != null ) {
      PathEditLine aLine = new PathEditLine(item);
      theLines.add(aLine);
      if ( aLine.isPath() ) { theVariables.add(aLine); }
      item = br.readLine();
    }
  }

  public int size() {
    return theVariables.size();
  }

  public PathEditLine get( int item ) {
    return( (PathEditLine)theVariables.get(item) );
  }

  public void saveToFile(String fileName) throws FileNotFoundException, IOException {
    PrintWriter bw = new PrintWriter( new BufferedWriter(new FileWriter(fileName)));
    for ( int i=0; i<theLines.size(); i++ ) {
      PathEditLine theLine = (PathEditLine)theLines.get(i);
      bw.println(theLine.toString());
    }
    bw.flush();
  }

}