package layered.src.user_interface;
import layered.src.application.FamilyCatalog;
import layered.src.data.NameInfo;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.awt.Dimension;
import java.util.Iterator;
import java.util.Vector;

public class FamilyCatalogGui extends JPanel
{
   private JList<NameInfo> peopleList;
   private FamilyCatalog familyCatalog;

   public void loadPeopleList()
   {
      Iterator<NameInfo> iter = familyCatalog.list();
      Vector<NameInfo> names = new Vector<NameInfo>();
      while (iter.hasNext())
      {
         names.add(iter.next());
      }
      peopleList.setListData(names);
   }

   public void setFamilyCatalog(FamilyCatalog c)
   {
      familyCatalog = c;
   }

   public FamilyCatalogGui()
   {
      peopleList = new JList<NameInfo>();
      this.setLayout(new BorderLayout());

      peopleList.setPreferredSize(new Dimension(200, 200));
      this.add(peopleList, BorderLayout.WEST);

      JTextArea personalInfo = new JTextArea("Select a person");
      personalInfo.setPreferredSize(new Dimension(200, 200));
      personalInfo.setEditable(false);

      this.add(personalInfo, BorderLayout.EAST);

      peopleList.addListSelectionListener(new ListSelectionListener(){
         public void valueChanged(ListSelectionEvent e)
         {
            NameInfo selection = peopleList.getSelectedValue();
            String fName = selection.firstName;
            String lName = selection.lastName;
            personalInfo.setText(selection.toString() + "\n");
            personalInfo.append("Mother: " + familyCatalog.getMother(fName, lName) + "\n");
            personalInfo.append("Father: " + familyCatalog.getFather(fName, lName) + "\n");
            Iterator<NameInfo> siblings = familyCatalog.getSiblings(fName, lName);
            while(siblings.hasNext())
            {
               personalInfo.append("Sibling: " + siblings.next() + "\n");
            }
            Iterator<NameInfo> children = familyCatalog.getChildren(fName, lName);
            while(children.hasNext())
            {
               personalInfo.append("Child: " + children.next() + "\n");
            } 
         }
      });


   }
}
