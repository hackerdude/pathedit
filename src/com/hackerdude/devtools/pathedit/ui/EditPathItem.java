
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
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.hackerdude.devtools.pathedit.PathEditItem;

/**
 * Shows an edit box for a single path item.
 */
public class EditPathItem extends JDialog {

	String currentMode = "absolute";
	FileAddressingModel model;
	ButtonGroup relativeGroup = new ButtonGroup();
	JPanel jPanel1 = new JPanel();
	JPanel jPanel2 = new JPanel();
	GridBagLayout gridBagLayout1 = new GridBagLayout();
	JPanel jPanel3 = new JPanel();
	JLabel lblFileName = new JLabel();
	BorderLayout borderLayout1 = new BorderLayout();
	JButton btnBrowse = new JButton();
	JPanel jPanel4 = new JPanel();
	JRadioButton rbAbsolute = new JRadioButton();
	JRadioButton rbUserDir = new JRadioButton();
	JRadioButton rbCurrentDir = new JRadioButton();
	JTextField txtFileName = new JTextField();

   ActionListener relativeSelection = new ActionListener() {
		public void actionPerformed(ActionEvent ev) {
			model.setValue(txtFileName.getText());
			currentMode = ev.getActionCommand();
			lblWillInsert.setText(model.getView());
		}
  };

  protected int finalSelection;
    JPanel jPanel5 = new JPanel();
    JLabel lblWillInsert = new JLabel();
    JLabel jLabel2 = new JLabel();
    JLabel lblWillInsertCaption = new JLabel();
    BorderLayout borderLayout2 = new BorderLayout();
    JPanel jPanel6 = new JPanel();
    JButton btnOk = new JButton();
    JButton btnCancel = new JButton();
    FlowLayout flowLayout1 = new FlowLayout();
    BorderLayout borderLayout3 = new BorderLayout();
    BorderLayout borderLayout4 = new BorderLayout();

  public EditPathItem( PathEditItem item ) {
    setPathItem(item);
    try {
      jbInit();
      centerWindow();
      appUpdateUI();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }


  public EditPathItem() {
    this(new PathEditItem("."));
  }

  public int getFinalSelection() { return finalSelection; }

  private void jbInit() throws Exception {
    this.setModal(true);
    this.setTitle("Edit Path");
    this.setSize(new Dimension(550, 150));
        this.getContentPane().setLayout(borderLayout4);
    jPanel2.setLayout(gridBagLayout1);
    lblFileName.setDisplayedMnemonic('F');
    lblFileName.setLabelFor(txtFileName);
    lblFileName.setText("Archive File or Directory: ");
    jPanel3.setLayout(borderLayout1);
    btnBrowse.setMaximumSize(new Dimension(100, 23));
    btnBrowse.setMinimumSize(new Dimension(100, 23));
    btnBrowse.setNextFocusableComponent(rbAbsolute);
    btnBrowse.setPreferredSize(new Dimension(113, 23));
    btnBrowse.setRequestFocusEnabled(false);
    btnBrowse.setActionCommand("browse");
    btnBrowse.setIcon(new ImageIcon(EditPathItem.class.getResource("Open16.gif")));
    btnBrowse.setMnemonic('B');
    btnBrowse.setText("Browse...");
    btnBrowse.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnBrowse_actionPerformed(e);
      }
    });
    rbAbsolute.setSelected(true);
    rbAbsolute.setText("Absolute");
    rbAbsolute.setMnemonic('A');
	rbAbsolute.setActionCommand("absolute");
	rbAbsolute.addActionListener(relativeSelection);
    rbUserDir.setText("Home");
    rbUserDir.setMnemonic('H');
        rbUserDir.setActionCommand("userdir");
	rbUserDir.addActionListener(relativeSelection);
    rbCurrentDir.setText("Current Dir");
    rbCurrentDir.setMnemonic('C');
        rbCurrentDir.setActionCommand("currentdir");
	rbCurrentDir.addActionListener(relativeSelection);
    txtFileName.setNextFocusableComponent(btnBrowse);
        lblWillInsert.setText(".");
        jLabel2.setText("jLabel2");
        lblWillInsertCaption.setText("Will Insert: ");
        jPanel5.setLayout(borderLayout2);
        btnOk.setNextFocusableComponent(btnCancel);
        btnOk.setActionCommand("ok");
        btnOk.setMnemonic('O');
        btnOk.setSelected(true);
        btnOk.setText("Ok");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnOk_actionPerformed(e);
      }
    });
        btnCancel.setMnemonic('C');
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnCancel_actionPerformed(e);
      }
    });
        jPanel6.setLayout(flowLayout1);
        jPanel1.setLayout(borderLayout3);
        jPanel2.setMinimumSize(new Dimension(510, 92));
        this.getContentPane().add(jPanel1, BorderLayout.SOUTH);
        jPanel1.add(jPanel6, BorderLayout.CENTER);
        jPanel6.add(btnOk, null);
        jPanel6.add(btnCancel, null);
    this.getContentPane().add(jPanel2, BorderLayout.CENTER);
    jPanel2.add(jPanel3, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 300, 0));
    jPanel3.add(lblFileName, BorderLayout.NORTH);
    jPanel3.add(txtFileName, BorderLayout.CENTER);
    jPanel3.add(btnBrowse, BorderLayout.EAST);
    jPanel2.add(jPanel4, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    jPanel4.add(rbAbsolute, null);
    jPanel4.add(rbUserDir, null);
    jPanel4.add(rbCurrentDir, null);
        jPanel2.add(jPanel5, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        jPanel5.add(lblWillInsertCaption, BorderLayout.WEST);
        jPanel5.add(lblWillInsert, BorderLayout.CENTER);
    readFromData();
    relativeGroup.add(rbAbsolute);
    relativeGroup.add(rbUserDir);
    relativeGroup.add(rbCurrentDir);
  }

  public void readFromData() {
	txtFileName.setText(model.absolute);
	lblWillInsert.setText(model.getView());
  }

  public void setPathItem( PathEditItem item ) {

    model = new FileAddressingModel(item);
    readFromData();
    appUpdateUI();
  }

  public PathEditItem getPathItem( ) {
    return model.getItem();
  }

  public void centerWindow() {
    //Center the window
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = this.getSize();
    if (frameSize.height > screenSize.height) {
      frameSize.height = screenSize.height;
    }
    if (frameSize.width > screenSize.width) {
      frameSize.width = screenSize.width;
    }
    this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
  }

  void btnBrowse_actionPerformed(ActionEvent e) {
     // Browse for an archive file.
     JFileChooser fileChooser = new JFileChooser();
     try {
       File curFile = new File(txtFileName.getText());
       File selFile = null;
       if ( ! curFile.isDirectory() ) {
         selFile = curFile;
         curFile = curFile.getParentFile();
       }
       fileChooser.setCurrentDirectory(curFile);
       if ( selFile != null ) fileChooser.setSelectedFile(selFile);
     } catch (Exception exc ) {
       System.err.println("Problem setting curdir as "+txtFileName.getText());
     }
     // TODO: Make sure it allows directories as well as files.
     if ( fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION ) {
       String fileName = fileChooser.getSelectedFile().getAbsolutePath();
       txtFileName.setText(fileName);
     }

  }

  void appUpdateUI() {
    lblWillInsert.setText(this.model.getView());
  }

  void btnCancel_actionPerformed(ActionEvent e) {
     this.hide();
     this.finalSelection = JOptionPane.CANCEL_OPTION;
  }

  void btnOk_actionPerformed(ActionEvent e) {
     // TODO: Write code to add the item.
     setPathItem(new PathEditItem(txtFileName.getText()));
     this.hide();
     this.finalSelection = JOptionPane.OK_OPTION;

  }


	/**
	* A little model class that contains the path item and knows
	* to keep around the absolute directory, as well as how to calculate
	* the others.
	*/
	class FileAddressingModel {
		String typedIn; // Assume absolute
		String absolute;
		PathEditItem itemEditing;

	public FileAddressingModel(PathEditItem item ) {
		itemEditing = item;
		setValue(item.toString());
	}

	public void setValue(String newValue) {
		typedIn = newValue;
		File fTypedIn = new File(typedIn);
		if ( fTypedIn.exists() ) {
			absolute = fTypedIn.getAbsolutePath();
		} else absolute = typedIn;
	}

	/**
	* From the absolute path of the item, determine the rest.
	*/
	public String getView() {
		String path;
		if ( currentMode.equals("currentdir") ) {
			String currentDir = System.getProperty("user.dir");
			path = PathEditItem.calculateRelativePath(currentDir, typedIn);
		} else if ( currentMode.equals("userdir") ) {
			String userDir = System.getProperty("user.home");
			path = PathEditItem.calculateRelativePath(userDir, typedIn);
		} else {
			// default is absolute
			path = txtFileName.getText();
		}
		itemEditing.setPathItem(path);
		return path;
	}

	public PathEditItem getItem() {
		getView();
		return itemEditing;
	}

	}

}