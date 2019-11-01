package com.proquest.interview.phonebook;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;
import com.proquest.interview.util.DatabaseUtil;

public class PhoneBookImplTest extends TestCase {
	
	public PhoneBook pb = null;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		pb = new PhoneBookImpl();
		DatabaseUtil.initDB();		
	};
	
	@Test
	public void testAddPerson() {
		Person p = new Person("abc wey", "(111) 123 - 456", "abc xyz, MI");
		pb.addPerson(p);
		
		List phoneBook = pb.retrivePhoneBook();
		assertEquals(3, phoneBook.size());
	}
	
	@Test
	public void testFindPerson(){
		Person person = pb.findPerson("Chris", "Johnson");
		
		assertEquals("Chris Johnson", person.getName());
		assertEquals("(321) 231-7876", person.getPhoneNumber());
		assertEquals("452 Freeman Drive, Algonac, MI", person.getAddress());
		
		person = pb.findPerson("ABC", "Way");

	
		assertNull(person);
	}
	
	@Test
	public void testAddNewPersonFindPerson() {
		Person person = new Person("charlie king","(333) 125-035","879 somwar peth, Pune");
		pb.addPerson(person);
		
		Person personDB = pb.findPerson("charlie", "king");
		
		assertEquals("charlie king", personDB.getName());
		assertEquals("(333) 125-035", personDB.getPhoneNumber());
		assertEquals("879 somwar peth, Pune", personDB.getAddress());
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		DatabaseUtil.clearTestDB();
	}
}
