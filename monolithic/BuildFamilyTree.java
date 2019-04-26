import java.util.Scanner;

public class BuildFamilyTree
{
   /*
    * Prints menu of options to the terminal
    */
   public static void mainMenu()
   {
      System.out.println("1. Load from file");
      System.out.println("2. Find siblings");
      System.out.println("3. Find children");
      System.out.println("4. Find parents");
      System.out.println("5. List family members");
      System.out.println("6. Exit");
   }

   /*
    * Accepts user selection from the terminal
    */
   public static int getUserSelection()
   {
      Scanner scanner = new Scanner(System.in);
      int selection = scanner.nextInt();
      return selection;
   }

   /*
    * Reads a name from the terminal
    */
   public static String[] readNameFromUser()
   {
      System.out.println("Enter name: LAST, FIRST");
      Scanner in = new Scanner(System.in);
      String fullName = in.nextLine();
      String [] firstLastNames = fullName.split(",");
      for (int i = 0; i < firstLastNames.length; i++)
      {
         firstLastNames[i] = firstLastNames[i].trim();
      }
      return firstLastNames;
   }

   /*
    * Loads family members from CSV file
    */
   public static void loadFromCSV(FamilyTree family)
   {
      System.out.println("Enter CSV file name: ");
      Scanner in = new Scanner(System.in);
      String fileName = in.nextLine();
      family.fromCSV(fileName);
      
   }

   /*
    * Prints an array of family members to the terminal
    */
   public static void printFamilyMembers(FamilyMember []relatives)
   {
      for (int i = 0; i < relatives.length; i++)
      {
         System.out.println(relatives[i]);
      }
   }

   /*
    * Initiates search for siblings in the family tree
    */
   public static void findSiblings(FamilyTree family)
   {
      String [] firstLastNames = readNameFromUser();
      FamilyMember []siblings = family.getSiblings(firstLastNames[1], firstLastNames[0]);
      System.out.println("Siblings:");
      printFamilyMembers(siblings);
   }

   /*
    * Initiates search for children in the family tree
    */
   public static void findChildren(FamilyTree family)
   {
      String [] firstLastNames = readNameFromUser();
      FamilyMember []siblings = family.getChildren(firstLastNames[1], firstLastNames[0]);
      System.out.println("Children:");
      printFamilyMembers(siblings);
   }

   /*
    * Initiates search for parents in the family tree
    */
   public static void findParents(FamilyTree family)
   {
      String [] firstLastNames = readNameFromUser();
      FamilyMember []parents = {family.getFather(firstLastNames[1], firstLastNames[0]),
                                family.getMother(firstLastNames[1], firstLastNames[0])};
      System.out.println("Parents:");
      printFamilyMembers(parents);
   }

   
   public static void main(String []args)
   {
      boolean quit = false;
      FamilyTree family = new FamilyTree();
      while (!quit)
      {
         mainMenu();
         int selection = getUserSelection();
         switch (selection)
         {
            case 1:
              loadFromCSV(family);
              break;
            case 2:
              findSiblings(family);
              break;
            case 3:
              findChildren(family);
              break;
            case 4:
              findParents(family);
              break;
            case 5:
              printFamilyMembers(family.list());
              break;
            case 6:
              quit = true;
              break;
         }//switch
      }// while
   }
}
