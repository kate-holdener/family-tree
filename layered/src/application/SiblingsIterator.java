package layered.src.application;
import layered.src.database.FamilyMember;
import java.util.ArrayList;

public class SiblingsIterator extends FamilyMemberIterator
{

   public SiblingsIterator(FamilyMember[] relatives, FamilyMember person)
   {
 
      ArrayList<FamilyMember> siblings = new ArrayList<FamilyMember>();

      // if the person exists, iterate over the list and find that person's siblings
      if (person != null)
      {
         FamilyMember mother = person.getMother();
         FamilyMember father = person.getFather();
         if (mother != null && father != null)
         {
            for (int i = 0; i < relatives.length; i++)
            {
               FamilyMember nextPerson = relatives[i];
               if (nextPerson != person && 
                   nextPerson.getMother() == mother && 
                   nextPerson.getFather() == father)
               {
                  siblings.add(nextPerson);
               } 
            }
         }
      }
      FamilyMember []sib = {};
      initialize(siblings.toArray(sib));
   }
}

