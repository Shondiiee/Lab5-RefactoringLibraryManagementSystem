package lab5.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lab5.Book;
import lab5.PaperBook;
import lab5.Library;
import lab5.Member;
import lab5.BorrowingService;

class TestFindClassString {

	private Library library;
	private BorrowingService service;
	
	@BeforeEach
	void setUp() throws Exception {
		this.library = new Library();
		this.service = BorrowingService.getInstance();
	}
	
	@Test
	void FindMember() {
		Member member1 = new Member("Dude", service);
		Member member2 = new Member("Gal", service);
		
		library.addMember(member1);
		library.addMember(member2);
		Member member = library.findMemberByName(member1.getName());
		assertEquals(member, member1, "Found member doesn't match");
		assertEquals(library.membersCount(), 2, "There should be two members");
		library.removeMember(member2);
		assertEquals(library.membersCount(), 1, "There should be only one member remain");
		member = library.findMemberByName(member2.getName());
		assertNull(member, "The member should not have been found after removal from the library");
		library.removeMember(member2);
		assertEquals(library.membersCount(), 1, "Removal of a non-existent member should not affect library");
		library.removeMember(member1);
		assertEquals(library.membersCount(), 0, "All members should have been removed");
	}

	@Test
	void FindBook() {
		Book book1 = new PaperBook("Dune");
		Book book2 = new PaperBook("1984");
		
		library.addBook(book1);
		library.addBook(book2);
		Book book = library.findBookByTitle(book1.getTitle());
		assertEquals(book, book1);
		assertEquals(library.booksCount(), 2);
		library.removeBook(book2);
		assertEquals(library.booksCount(), 1);
		book = library.findBookByTitle(book2.getTitle());
		assertNull(book);
		library.removeBook(book2);
		assertEquals(library.booksCount(), 1);
		library.removeBook(book1);
		assertEquals(library.booksCount(), 0);
	}
}