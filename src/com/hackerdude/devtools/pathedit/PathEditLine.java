
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

/**
 * Represents a single line on a PathEdit file.
 *
 * <P>This class parses the line given to it via the constructor and it determines
 * if it looks like a PATH or not. It also creates PathEditItems for every path
 * component.
 */
public class PathEditLine {

  String line;
  PathEditList thePath;

  /**
   * Creates a new PathEditLine with the incoming line as constructor.
   */
  public PathEditLine(String line) {
    this.line = line;
    parseLine();
  }

  private void parseLine() {
    if ( isPath() ) { thePath = new PathEditList(line); }
  }

  /**
   * Returns the Variable name this pathedit line represents.
   */
  public String getVarName() {
	if ( thePath == null ) return null;
    return thePath.varName;
  }

  public void setVarName(String path) {
    thePath.varName = path;
  }


  /**
   * Returns a PathEditList that contains the path elements.
   */
  public PathEditList getPath() {
    return thePath;
  }

  /**
   * Returns true if this line represents a path. A line represents a path when
   * it contains both an equals sign and a path separator for the current OS.
   */
  public boolean isPath() {
    boolean result = line.indexOf("=") > -1 && line.indexOf(File.pathSeparator) > -1;
    return result;
  }

  public String toString() {
    if ( thePath != null ) {
      return thePath.toString();
    } else {
      return line;
    }
  }

}