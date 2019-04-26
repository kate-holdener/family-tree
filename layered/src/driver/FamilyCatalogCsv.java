package layered.src.driver;

import layered.src.user_interface.*;
import layered.src.application.*;
import layered.src.database.*;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.awt.Dimension;
import java.io.File;

public class FamilyCatalogCsv
{
   private FamilyCatalogGui mainUserInterface;
   private FamilyCatalog familyCatalog;
   public JButton getSelectFileButton()
   {
      JButton selectFileButton = new JButton("Select a file");
      selectFileButton.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e)
         {
            String csvFile = selectFile();
            CsvDatabase database = new CsvDatabase(csvFile);
            familyCatalog = new FamilyCatalog();
            familyCatalog.connectToDatabase(database);
            mainUserInterface.setFamilyCatalog(familyCatalog);
            mainUserInterface.loadPeopleList();
         }
      });
      return selectFileButton;
   }


   public static String selectFile()
   {
      String fileName = null;
      JFileChooser fc = new JFileChooser();
      int retVal = fc.showOpenDialog(null);

      if (retVal == JFileChooser.APPROVE_OPTION)
      {
         File file = fc.getSelectedFile();
         fileName = file.getAbsolutePath();
      }
      return fileName;
   }


   public FamilyCatalogCsv()
   {
      mainUserInterface = new FamilyCatalogGui();
      JButton select = getSelectFileButton();
      JFrame frame = new JFrame("Family Tree");
      // operation to do when the window is closed
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      JPanel containerPanel = new JPanel();
      containerPanel.setLayout(new BorderLayout());
      
      containerPanel.add(select, BorderLayout.NORTH);
      containerPanel.add(mainUserInterface, BorderLayout.SOUTH);
      frame.add(containerPanel);
      frame.pack();
      frame.setVisible(true);
   }
   
   public static void main(String []args)
   {
      FamilyCatalogCsv catalog = new FamilyCatalogCsv();
   }
}
