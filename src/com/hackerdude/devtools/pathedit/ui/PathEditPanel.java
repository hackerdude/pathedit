package com.hackerdude.devtools.pathedit.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Title:        PathEdit
 * Description:
 * Copyright:    Copyright (c) 2000
 * Company:
 * @author David Martinez
 * @version 1.0
 */

public class PathEditPanel extends JPanel {
  JToolBar toolBar = new JToolBar();
  JButton btnOpen = new JButton();
  JButton btnClose = new JButton();
  JButton btnHelp = new JButton();
  ImageIcon image1;
  ImageIcon image2;
  ImageIcon image3;
  ImageIcon image4;
  ImageIcon image5;
  ImageIcon imgEdit;
  ImageIcon imgAdd;
  ImageIcon imgRemove;
  JLabel statusBar = new JLabel();
  BorderLayout borderLayout1 = new BorderLayout();
  JButton btnUp = new JButton();
  JButton btnDown = new JButton();
  JTabbedPane jTabbedPane1 = new JTabbedPane();
  JFileChooser fd;
  JButton btnSave = new JButton();
  Vector files = new Vector();
  PathEditFileEditor currentFile = new PathEditFileEditor();
  JButton btnAdd = new JButton();
  JButton btnRemove = new JButton();
    JButton btnEdit = new JButton();

    //Construct the frame
  public PathEditPanel() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  //Component initialization
  private void jbInit() throws Exception  {
    image1  = new ImageIcon(PathEditPanel.class.getResource("Open16.gif"));
    image2  = new ImageIcon(PathEditPanel.class.getResource("Open16.gif"));
    image3  = new ImageIcon(PathEditPanel.class.getResource("Help16.gif"));
    image4  = new ImageIcon(PathEditPanel.class.getResource("Down16.gif"));
    image5  = new ImageIcon(PathEditPanel.class.getResource("Up16.gif"));
    imgEdit = new ImageIcon(PathEditPanel.class.getResource("Edit16.gif"));
    imgAdd  = new ImageIcon(PathEditPanel.class.getResource("JarAdd16.gif"));
    imgRemove = new ImageIcon(PathEditPanel.class.getResource("Delete16.gif"));
    setLayout(borderLayout1);
    jTabbedPane1.addChangeListener(new ChangeListener() {

      public void stateChanged(ChangeEvent e ) {
        setCurrentFile();
      }

    });
    btnClose.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        btnClose_actionPerformed(e);
      }
    });
    btnUp.setMnemonic('K');
    btnDown.setMnemonic('J');
    btnAdd.setToolTipText("Add");
    btnAdd.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        btnAdd_actionPerformed(e);
      }
    });
    btnRemove.setIcon(imgRemove);
    btnRemove.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnRemove_actionPerformed(e);
      }
    });
    btnRemove.setToolTipText("Remove");
    btnEdit.setActionCommand("edit");
        btnEdit.setIcon(imgEdit);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnEdit_actionPerformed(e);
            }
        });
        jTabbedPane1.add(currentFile, "Current Environment");
    currentFile.listEditor.addSelectionListener(new PathUIListener());
    statusBar.setText(" ");
    btnOpen.setIcon(image1);
    btnOpen.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        btnOpen_actionPerformed(e);
      }
    });
    btnOpen.setToolTipText("Open File");
    btnClose.setIcon(image2);
    btnClose.setToolTipText("Close File");
    btnAdd.setIcon(new ImageIcon(PathEditPanel.class.getResource("JarAdd16.gif")));

    btnHelp.setIcon(new ImageIcon(PathEditPanel.class.getResource("About16.gif")));
    btnHelp.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        btnHelp_actionPerformed(e);
      }
    });
    btnHelp.setToolTipText("Help");
    btnUp.setToolTipText("Move Path Item Up");
    btnUp.setIcon(new ImageIcon(PathEditPanel.class.getResource("Up16.gif")));
    btnUp.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        btnUp_actionPerformed(e);
      }
    });
    btnDown.setIcon(image4);
    btnDown.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        btnDown_actionPerformed(e);
      }
    });
    btnDown.setToolTipText("Move Path Item Down");
    btnSave.setToolTipText("Save File");
    btnSave.setIcon(new ImageIcon(PathEditPanel.class.getResource("Save16.gif")));
    btnSave.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        btnSave_actionPerformed(e);
      }
    });
    toolBar.add(btnOpen);
    toolBar.add(btnSave, null);
    toolBar.add(btnClose);
    toolBar.add(btnUp, null);
    toolBar.add(btnDown, null);
        toolBar.add(btnEdit, null);
    toolBar.add(btnAdd, null);
    toolBar.add(btnRemove, null);
    toolBar.add(btnHelp);
    this.add(toolBar, BorderLayout.NORTH);
    this.add(statusBar, BorderLayout.SOUTH);
    this.add(jTabbedPane1, BorderLayout.CENTER);
    appUpdateUI();
  }

  //File | Exit action performed
  public void fileExit_actionPerformed(ActionEvent e) {
    System.exit(0);
  }

  //Help | About action performed
  public void helpAbout_actionPerformed(ActionEvent e) {
    PathEditFrame_AboutBox dlg = new PathEditFrame_AboutBox(null);
    Dimension dlgSize = dlg.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
    dlg.setModal(true);
    dlg.show();
  }

  public static void main( String[] argv ) {
    PathEditFrame frame = new PathEditFrame();
    frame.show();
  }

  void btnHelp_actionPerformed(ActionEvent e) {
    helpAbout_actionPerformed(e);
  }

  void btnUp_actionPerformed(ActionEvent e) {
     currentFile.listEditor.moveUp();
  }

  void btnDown_actionPerformed(ActionEvent e) {
     currentFile.listEditor.moveDown();
  }

  public void appUpdateUI() {
  if ( btnUp != null ) {
     btnUp.setEnabled(currentFile.listEditor.canMoveUp());
     btnDown.setEnabled(currentFile.listEditor.canMoveDown());
     btnClose.setEnabled(jTabbedPane1.getComponentCount()>1);
     btnRemove.setEnabled(currentFile.listEditor.isPathSelected());
  }

 }

 public class PathUIListener implements ListSelectionListener {
    public void valueChanged( ListSelectionEvent ev ) {
      appUpdateUI();
    }
 }

  void makeFileChooser() {
    if ( fd == null ) {
      fd = new JFileChooser();
    }
  }

  void btnOpen_actionPerformed(ActionEvent e) {
      makeFileChooser();
      fd.setVisible(true);
      if ( fd.showOpenDialog(this) == fd.APPROVE_OPTION ) {
    try {
      PathEditFileEditor newFile = new PathEditFileEditor();
      newFile.fileName = fd.getSelectedFile().getAbsoluteFile().getAbsolutePath();
      newFile.variables.model.setFile(newFile.fileName);
      newFile.listEditor.addSelectionListener(new PathUIListener());
     jTabbedPane1.add(newFile, fd.getSelectedFile().getPath());
    } catch ( IOException exc ) {
      exc.printStackTrace();
    }
    }
    appUpdateUI();

  }

  void btnSave_actionPerformed(ActionEvent e) {
     makeFileChooser();
     try {
       if ( currentFile.fileName == null ) {
         if ( fd.showSaveDialog(this) == fd.APPROVE_OPTION )
           currentFile.fileName = fd.getSelectedFile().getAbsoluteFile().getAbsolutePath();
       }
       if ( currentFile.fileName != null ) {
         currentFile.variables.model.scriptFile.saveToFile(currentFile.fileName);
       }

     } catch ( IOException exc ) {
      exc.printStackTrace();
     }
  }

  void setCurrentFile() {
    currentFile = (PathEditFileEditor)jTabbedPane1.getComponent(jTabbedPane1.getSelectedIndex());
    appUpdateUI();
  }


  void btnClose_actionPerformed(ActionEvent e) {
    if ( jTabbedPane1.getComponentCount() > 1 ) {
      jTabbedPane1.remove(currentFile);
    }
    appUpdateUI();
  }

  void btnAdd_actionPerformed(ActionEvent e) {
    currentFile.listEditor.addPath();
  }
  void btnRemove_actionPerformed(ActionEvent e) {
    currentFile.listEditor.removeCurrentPath();
  }

    void btnEdit_actionPerformed(ActionEvent e) {
		currentFile.listEditor.editCurrentPath();
    }


  }