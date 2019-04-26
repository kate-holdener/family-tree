package layered.src.application;
import layered.src.database.FamilyMember;
import java.util.ArrayList;

public class ChildrenIterator extends FamilyMemberIterator
{
   public ChildrenIterator(FamilyMember [] relatives, FamilyMember person)
   {
      ArrayList<FamilyMember>children = new ArrayList<FamilyMember>();
      if (person != null)
      {
         for (int i = 0; i < relatives.length; i++)
         {
            FamilyMember nextPerson = relatives[i];
            if (nextPerson != person && 
                nextPerson.getMother() == person || 
                nextPerson.getFather() == person)
            {
               children.add(nextPerson);
            } 
         }
      }
      FamilyMember []kids = {};
      initialize(children.toArray(kids));

   }
}
