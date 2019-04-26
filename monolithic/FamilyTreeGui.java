import javax.swing.*;
import javax.swing.event.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.awt.Dimension;
import java.io.File;

public class FamilyTreeGui
{
   private JList<FamilyMember> peopleList;
   private FamilyTree family;

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

   public void loadPeopleList(FamilyTree family)
   {
      FamilyMember []fam = family.list();
      peopleList.setListData(fam);
   }

   public JButton getSelectFileButton(FamilyTree family)
   {
      JButton selectFileButton = new JButton("Select a file");
      selectFileButton.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e)
         {
            String csvFile = selectFile();
            family.fromCSV(csvFile);
	    loadPeopleList(family);
         }
      });
      return selectFileButton;
   }

   public FamilyTreeGui()
   {
      family = new FamilyTree();
      peopleList = new JList<FamilyMember>();
      JButton select = getSelectFileButton(family);

      JFrame frame = new JFrame("Family Tree");
      // operation to do when the window is closed
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      JPanel mainPanel = new JPanel();
      mainPanel.setLayout(new BorderLayout());

      peopleList.setPreferredSize(new Dimension(200, 200));
      mainPanel.add(peopleList, BorderLayout.WEST);

      JTextArea personalInfo = new JTextArea("Select a person");
      personalInfo.setPreferredSize(new Dimension(200, 200));
      personalInfo.setEditable(false);

      mainPanel.add(personalInfo, BorderLayout.EAST);
      mainPanel.add(select, BorderLayout.NORTH);

      peopleList.addListSelectionListener(new ListSelectionListener(){
         public void valueChanged(ListSelectionEvent e)
         {
            FamilyMember selection = peopleList.getSelectedValue();
            String fName = selection.getFirstName();
            String lName = selection.getLastName();
            personalInfo.setText(selection.toString() + "\n");
            personalInfo.append("Mother: " + family.getMother(fName, lName) + "\n");
            personalInfo.append("Father: " + family.getFather(fName, lName) + "\n");
            FamilyMember []siblings = family.getSiblings(fName, lName);
            for (FamilyMember f: siblings)
            {
               personalInfo.append("Sibling: " + f + "\n");
            }
            FamilyMember []children = family.getChildren(fName, lName);
            for (FamilyMember f: children)
            {
               personalInfo.append("Child: " + f + "\n");
            } 
         }
      });


      frame.add(mainPanel);
      frame.pack();
      frame.setVisible(true);
   }

   public static void main(String []args)
   {
      FamilyTreeGui gui = new FamilyTreeGui();
   }
}
