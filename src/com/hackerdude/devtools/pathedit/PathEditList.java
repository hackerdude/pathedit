
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

import java.util.*;
import java.io.File;

public class PathEditList {

  protected Vector pathList;
  protected String varName;

  public PathEditList(String pathEnvironment) {
    this(pathEnvironment, File.pathSeparator);
  }

  public PathEditList(String pathEnvironment, String separator) {
    parsePath(pathEnvironment, separator);
  }

  public synchronized void add(PathEditItem item) {
    pathList.add(item);
  }

  public synchronized void remove(int item) {
    pathList.remove(item);
  }

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

  public PathEditItem[] getItems() {

    PathEditItem[] result = new PathEditItem[pathList.size()];
    Iterator iter = pathList.iterator();
    int i = 0;
    while ( iter.hasNext() ) {
      PathEditItem nextItem = (PathEditItem)iter.next();
      result[i++] = nextItem;
    }
    return (result);

  }

  public PathEditItem get( int i ) {
    return (PathEditItem)pathList.get(i);
  }

  public int size() {
    return pathList.size();
  }

  public int index( PathEditItem item ) {
    return pathList.indexOf(item);
  }

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

  public boolean moveUp( PathEditItem item ) {
    int iObjtwo = index(item) -1;
    if ( iObjtwo < 0 ) { return false; }
    PathEditItem itemTwo = get(iObjtwo);
    return switchPositions(item, itemTwo);
  }

  public void replace( PathEditItem oldItem, PathEditItem newItem ) {
    int i = pathList.indexOf(oldItem);
    if ( i < 0 ) pathList.add(newItem);
    else {
      pathList.add(i, newItem);
      pathList.remove(oldItem);
    }
  }

  public boolean moveDown( PathEditItem item ) {
    int iObjtwo = index(item) +1;
    if ( iObjtwo >= size() ) { return false; }
    PathEditItem itemTwo = get(iObjtwo);
    return switchPositions(item, itemTwo);
  }

  private static void main( String[] args ) {
    PathEditList classPath = new PathEditList("CLASSPATH="+System.getProperty("java.class.path"));
    System.out.println(System.getProperty("java.class.path"));
    PathEditItem[] items = classPath.getItems();
    System.out.println(classPath.varName);
    for ( int i = 0; i<items.length; i++ ) {
      System.out.println(items[i].fileName);
      System.out.println(items[i].exists());
    }
  }

  public String toString() {
    StringBuffer buf = new StringBuffer();
    Iterator iter = pathList.iterator();
    if ( varName != null ) { buf.append(varName).append("="); }
    while ( iter.hasNext() ) {
      PathEditItem theItem = (PathEditItem)iter.next();
      buf.append(theItem.toString());
      if ( iter.hasNext() ) { buf.append(File.pathSeparator); }
    }
    return (buf.toString());
  }

}