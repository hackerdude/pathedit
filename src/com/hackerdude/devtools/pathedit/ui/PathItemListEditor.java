/**
 * Title:        PathEdit - A Classpath Editor written in Java<p>
 * Description:  This is a tiny program that tries to make it easy for the developer
 * to take a path specification and reorganize it.<p>
 * Copyright:    Copyright (c) David Martinez<p>
 * Company:      <p>
 * @author David Martinez
 * @version 1.0
 */
package com.hackerdude.devtools.pathedit.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import com.hackerdude.devtools.pathedit.PathEditItem;
import java.util.*;

public class PathItemListEditor extends JPanel {
  BorderLayout borderLayout1 = new BorderLayout();
  EditPathItem editor = new EditPathItem();

  public PathListTableModel model = new PathListTableModel();
  JScrollPane jScrollPane2 = new JScrollPane();
  JTable jTable1 = new JTable();

  public PathItemListEditor() {
    try {
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  void jbInit() throws Exception {
    this.setLayout(borderLayout1);
    borderLayout1.setHgap(3);
    borderLayout1.setVgap(3);
 //   TableColumn pathItemColumn  = jTable1.getColumn("Item");
    this.setEnabled(true);
    this.setBorder(BorderFactory.createEtchedBorder());
    jTable1.setBackground(SystemColor.controlLtHighlight);
    jTable1.setDoubleBuffered(true);
    jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    jTable1.setModel(model);
    this.add(jScrollPane2, BorderLayout.CENTER);
    jScrollPane2.getViewport().add(jTable1, null);
    jTable1.sizeColumnsToFit(-1);
    jTable1.getColumnModel().getColumn(1).setMaxWidth(40);
  }

  private TableColumn getLastColumn() {
    return jTable1.getColumnModel().getColumn(jTable1.getColumnCount()-1);
  }

  public void addPath() {
    editor.setPathItem(new PathEditItem(System.getProperty("user.dir")));
    editor.setTitle("Add a new Path");
    editor.show();
    if ( editor.getFinalSelection() == JOptionPane.OK_OPTION ) {
      model.addPathItem(editor.getPathItem());
      model.fireTableChanged(new TableModelEvent(model, TableModelEvent.INSERT));
    }

  }

  public boolean isPathSelected() {
    return jTable1.getSelectedRow() > -1;
  }

  public void removeCurrentPath() {
    if ( ! isPathSelected() ) return;
    int row = jTable1.getSelectedRow();
    model.remove(jTable1.getSelectedRow());
    if ( row >= jTable1.getRowCount() ) row = jTable1.getRowCount()-1;
    if ( row > -1 ) jTable1.setRowSelectionInterval(row, row);
  }

  public void editCurrentPath() {
    //
    int row = jTable1.getSelectedRow();
    if ( row > -1 ) {

      editor.setPathItem(model.getItem(row));
      editor.show();


    }
  }

  public void moveUp(){
    int row = jTable1.getSelectedRow();
    if ( row > -1 ) {
      model.moveUp(row);
      jTable1.clearSelection();
      jTable1.getSelectionModel().setSelectionInterval(row-1, row-1);
    }
  }

  public void moveDown() {
    int row = jTable1.getSelectedRow();
    if ( row > -1 ) {
      model.moveDown(row);
      jTable1.clearSelection();
      jTable1.getSelectionModel().setSelectionInterval(row+1, row+1);
    }
  }

  public boolean canMoveDown() {
    int row = jTable1.getSelectedRow();
    return ( row > -1 && row < model.getRowCount()-1 );
  }

  public boolean canMoveUp() {
    int row = jTable1.getSelectedRow();
    return ( row > 0  );
  }

  public void addSelectionListener(ListSelectionListener x) {
      jTable1.getSelectionModel().addListSelectionListener(x);
  }

  public void removeListSelectionListener( ListSelectionListener x ) {
      jTable1.getSelectionModel().removeListSelectionListener(x);
  }

}