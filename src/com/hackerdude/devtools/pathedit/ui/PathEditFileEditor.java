
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

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PathEditFileEditor extends JPanel {
  PathItemListEditor listEditor = new PathItemListEditor();
  VariableListPanel variables = new VariableListPanel();
  JSplitPane jSplitPane1 = new JSplitPane();
  BorderLayout borderLayout1 = new BorderLayout();
  String fileName;

  public PathEditFileEditor() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  public void jbInit() {
    this.setLayout(borderLayout1);
    this.add(jSplitPane1, BorderLayout.CENTER);
    jSplitPane1.add(listEditor, JSplitPane.BOTTOM);
    jSplitPane1.add(variables, JSplitPane.TOP);
    variables.addSelectionListener(new VariableUIListener());
    jSplitPane1.setDividerLocation(150);

  }


 public class VariableUIListener implements ListSelectionListener {
   public void valueChanged( ListSelectionEvent ev ) {
      listEditor.model.setData(variables.getCurrent());
//      this.appUpdateUI();  // TODO NOTIFY
   }
 }


}