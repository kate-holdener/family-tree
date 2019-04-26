package layered.src.application;
import layered.src.data.*;
import layered.src.database.FamilyMember;

import java.util.NoSuchElementException;
import java.util.Iterator;

public class FamilyMemberIterator implements Iterator<NameInfo>
{
   private FamilyMember[] familyMembers;
   private int nextIndex = 0;

   public FamilyMemberIterator()
   {
      familyMembers = null;
   }

   public FamilyMemberIterator(FamilyMember[] familyMembers)
   {
      initialize(familyMembers);
   }
   protected void initialize(FamilyMember[] familyMembers)
   {
      this.familyMembers = familyMembers;
   }

   public boolean hasNext()
   {
      if (familyMembers.length > nextIndex)
      {
         return true;
      }
      return false;
   }

   public NameInfo next()
   {
      FamilyMember f = null;
      if (hasNext())
      {
         f = familyMembers[nextIndex];
         nextIndex++;
      }
      else
      {
         throw new NoSuchElementException();
      }
      return f.getName();
      
   }
}
