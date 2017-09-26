import static org.junit.Assert.*;

import org.junit.*;
import org.mockito.*;
import java.util.*;

public class RentACatTest
{
  RentACat _rac;


  /*PLEASE NOTE: These tests do NOT use mocks - I found it appropriate to test them in a way i knew first and left these tests in.
    These include testing empty lists or nonexistent cats. 
   Tests with mocks are below these at line 103.*/

  @Before
  public void setup()
  {
    _rac = new RentACat();
    _rac._cats.add(new Cat(1, "Gennifer"));
    _rac._cats.add(new Cat(2, "Gary"));
    _rac._cats.add(new Cat(3, "Grandson"));
  }



  //Test to see if returning an available cat returns false.
  @Test
  public void returnAvailableCat()
  {
    Cat testCat = new Cat(4, "Graham");
    _rac._cats.add(testCat);
    assertFalse(_rac.returnCat(testCat));
  }

  //Test to see if renting an available cat returns true.
  @Test
  public void rentAvailablecat()
  {
    Cat testCat = new Cat(5, "Garrett");
    _rac._cats.add(testCat);
    assertTrue(_rac.rentCat(testCat));
  }

  //Test to see if returning a rented cat returns true.
  @Test
  public void returnRentedCat()
  {
    Cat testCat = new Cat(6, "Gavin");
    _rac._cats.add(testCat);
    _rac.rentCat(testCat);
    assertTrue(_rac.returnCat(testCat));
  }

  //Test to see if renting an already rented cat returns false.
  @Test
  public void rentARentedCat()
  {
    Cat testCat = new Cat(7, "Gilius");
    _rac._cats.add(testCat);
    _rac.rentCat(testCat);
    assertFalse(_rac.rentCat(testCat));
  }

  //Test to see if stringified version of an empty cat list returns the empty string.
  @Test
  public void testEmptyCatList()
  {
    String catList = "";
    assertSame(_rac.listCats(new ArrayList<Cat>()), "");
  }

  //Test to see if strigified version of a non-empty cat list returns the correctly formatted string.
  @Test
  public void testCatList()
  {
    String catList = "ID 1. Gennifer\nID 2. Gary\nID 3. Grandson\n";
    assertEquals(catList, _rac.listCats(_rac._cats));
  }

  //Test to see if a cat in a list exists.
  @Test
  public void testIfCatExists()
  {
    assertTrue(_rac.catExists(1, _rac._cats));
  }


  //Test to determine whether the method recognizes that a cat does not exist in a list.
  @Test
  public void testIfNonexistentCatIsThere()
  {
    assertFalse(_rac.catExists(4, _rac._cats));
  }

  //Test to determine whether the method recognizes that a cat cannot be found in an empty list.
  @Test
  public void findCatInEmptyList()
  {
    assertFalse(_rac.catExists(1, new ArrayList<Cat>()));
  }


  /*BEGIN TESTS WITH MOCKS AND STUBS*/

  //Tests if a mock cat exists within a list using a stub method for getId().
  @Test
  public void mockCatExists()
  {
    RentACat test = new RentACat();
    Cat c = Mockito.mock(Cat.class);
    Mockito.when(c.getId()).thenReturn(4);
    test._cats.add(c);
    assertTrue(_rac.catExists(4, test._cats));
  }

  //Tests if you can rent a mock cat. Cat is available by default.
  @Test
  public void rentMockCat()
  {
    RentACat test = new RentACat();
    Cat c = Mockito.mock(Cat.class);
    test._cats.add(c);
    assertTrue(test.rentCat(c));
  }

  //Tests if you can return an available mock cat. Test should return false because you cannot return a cat that is still available.
  @Test
  public void returnAvailableMockCat()
  {
    RentACat test = new RentACat();
    Cat c = Mockito.mock(Cat.class);
    test._cats.add(c);
    assertFalse(test.returnCat(c));
  }

  //Tests if you can return a list of mock cats. Uses a stub method for toString() to control what string is returned.
  @Test
  public void returnListOfMockCats()
  {
    RentACat test = new RentACat();
    Cat c = Mockito.mock(Cat.class);
    Mockito.when(c.toString()).thenReturn("1. Gerald");
    Cat c2 = Mockito.mock(Cat.class);
    Mockito.when(c2.toString()).thenReturn("2. Geraldine");
    test._cats.add(c);
    test._cats.add(c2);
    assertEquals("1. Gerald\n2. Geraldine\n", test.listCats(test._cats));
  }

  //Tests if you can return a mock cat that has been rented.
  @Test
  public void returnMockCatSuccessfully()
  {
    RentACat test = new RentACat();

    Cat c = new Cat(1, "Geraldine");
    Cat cat = Mockito.spy(c);
    test._cats.add(cat);
    test.rentCat(cat);
    assertTrue(test.returnCat(cat));
  }


}
