
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
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * Represents a list of items to work on
 * @author davidm <a href="mailto:david@hackerdude.com">david@hackerdude.com</a>
 */
public class PathEditList {

  protected Vector pathList;
  protected String varName;
  protected String separator;

  /**
   * Creates a new patheditlist that will use the path separator for the 
   * current OS.
   * 
   * @param pathEnvironment The environment path line.
   * @see #PathEditList(String, String)
   */
  public PathEditList(String pathEnvironment) {
    this(pathEnvironment, File.pathSeparator);
  }

  /**
   * Creates a new patheditlist based on the path 
   * list and the separator. 
   * 
   * <p>The separator is necessary so that you can parse
   * paths from other OS's into this one (say, editing a
   * bash script's path under windows).
   * 
   * @param pathEnvironment The environment path line
   * @param separator The path separator to use.
   */
  public PathEditList(String pathEnvironment, String separator) {
  	this.separator = separator;
    parsePath(pathEnvironment, separator);
  }

  /**
   * Add an item to the pathEdit list
   * @param item
   */
  public void add(PathEditItem item) {
    pathList.add(item);
  }

  /**
   * Removes the specified item from the list.
   * @param index The index of the item to remove.
   */
  public Object remove(int index) {
    return pathList.remove(index);
  }

  /**
   * Parses the specified path environment string.
   * 
   * @param pathEnvironment the path string
   * @param separator The path separator to use. 
   */
  private synchronized void parsePath(String pathEnvironment, String separator) {

    int iEquals = pathEnvironment.indexOf("=");
    String pathItems = pathEnvironment;
    if ( iEquals > 0 ) {
      varName = pathEnvironment.substring(0, iEquals);
      pathItems = pathEnvironment.substring(iEquals+1);
    }

    StringTokenizer tok = new StringTokenizer(pathItems,separator);
    pathList = new Vector(tok.countTokens()*2);
    while ( tok.hasMoreTokens() ) {
      String sItem = tok.nextToken();
      pathList.add(new PathEditItem(sItem));
    }

  }

  /**
   * Returns the items as an array.
   * @return The items as an array.
   */
  public PathEditItem[] getItems() {

    PathEditItem[] result = new PathEditItem[pathList.size()];
    result = (PathEditItem[])pathList.toArray(result);
    return result;
    
  }

  /**
   * Returns the item at the index specified.
   * 
   * @param index The index of the item.
   * @return The pathedit item at the index.
   */
  public PathEditItem get( int index ) {
    return (PathEditItem)pathList.get(index);
  }

  /**
   * Returns the size of this path list (i.e. how many path items).
   * 
   * @return The size of the path list.
   */
  public int size() {
    return pathList.size();
  }

  /**
   * Returns the index at which this pathedititem is located.
   * 
   * @param item The item to search for
   * @return The index where the item is located.
   */
  public int index( PathEditItem item ) {
    return pathList.indexOf(item);
  }

  /**
   * Switches the positions of this path item.
   * 
   * @param itemone The first item
   * @param itemtwo The second item
   * @return True if it was possible to move it, 
   *   false if the move was not done 
   *   (because either the source or destination was not found).
   * 
   */
  public boolean switchPositions(PathEditItem itemone, PathEditItem itemtwo) {

    int origin = index(itemone);
    int destination = index(itemtwo);
    if ( origin < 0  || destination < 0 || origin == destination ) return false;
    PathEditItem oItem = get(origin);
    PathEditItem dItem = get(destination);
    pathList.setElementAt(oItem, destination);
    pathList.setElementAt(dItem, origin);
    return true;

  }

  /**
   * Moves the specified item up in the list.
   * @param item The item to move
   * @return True if it was possible to move, false if it wa s not.
   */
  public boolean moveUp( PathEditItem item ) {
    int iObjtwo = index(item) -1;
    if ( iObjtwo < 0 ) { return false; }
    PathEditItem itemTwo = get(iObjtwo);
    return switchPositions(item, itemTwo);
  }

  /**
   * Replaces the specified item with the new item.
   * 
   * @param oldItem The old item (the one to remove) 
   * @param newItem The new item (the one to insert)
   */
  public void replace( PathEditItem oldItem, PathEditItem newItem ) {
    int i = pathList.indexOf(oldItem);
    if ( i < 0 ) pathList.add(newItem);
    else {
      pathList.add(i, newItem);
      pathList.remove(oldItem);
    }
  }

  /**
   * Moves the specified item down 
   * @param item The item to move
   * @return True if it can be moved down, false otherwise.
   */
  public boolean moveDown( PathEditItem item ) {
    int iObjtwo = index(item) +1;
    if ( iObjtwo >= size() ) { return false; }
    PathEditItem itemTwo = get(iObjtwo);
    return switchPositions(item, itemTwo);
  }


  /**
   * Turns this list line to a string, using the same path \
   * separator this item was built with 
   */
  public String toString() {
    StringBuffer buf = new StringBuffer();
    Iterator iter = pathList.iterator();
    if ( varName != null ) { buf.append(varName).append("="); }
    while ( iter.hasNext() ) {
      PathEditItem theItem = (PathEditItem)iter.next();
      buf.append(theItem.toString());
      if ( iter.hasNext() ) { buf.append(separator); }
    }
    return (buf.toString());
  }

}