package lab5.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lab5.*;

class TestBookTypes {
	
	private Library library;
	private BorrowingService service;
	
	@BeforeEach
	void setUp() throws Exception {
		this.library = new Library();
		this.service = BorrowingService.getInstance();
	}
	
	@Test
	void testAddEBook() {
		Book ebook = new EBook("Digital Fortress");
		library.addBook(ebook);
		assertEquals(library.booksCount(), 1, "Should have 1 book");
		assertTrue(ebook instanceof EBook, "Should be an EBook");
		assertEquals("Digital Fortress", ebook.getTitle());
	}
	
	@Test
	void testAddAudioBook() {
		Book audiobook = new AudioBook("The Hobbit");
		library.addBook(audiobook);
		assertEquals(library.booksCount(), 1, "Should have 1 book");
		assertTrue(audiobook instanceof AudioBook, "Should be an AudioBook");
		assertEquals("The Hobbit", audiobook.getTitle());
	}
	
	@Test
	void testMixedBookTypes() {
		Book paperBook = new PaperBook("Dune");
		Book ebook = new EBook("Digital Fortress");
		Book audiobook = new AudioBook("The Hobbit");
		
		library.addBook(paperBook);
		library.addBook(ebook);
		library.addBook(audiobook);
		
		assertEquals(library.booksCount(), 3, "Should have 3 books");
	}
	
	@Test
	void testBorrowDifferentBookTypes() {
		Member member = new Member("Alice", service);
		library.addMember(member);
		
		Book paperBook = new PaperBook("Dune");
		Book ebook = new EBook("Digital Fortress");
		Book audiobook = new AudioBook("The Hobbit");
		
		library.addBook(paperBook);
		library.addBook(ebook);
		library.addBook(audiobook);
		
		member.borrowBook(paperBook);
		member.borrowBook(ebook);
		member.borrowBook(audiobook);
		
		assertEquals(member.borrowedBooksCount(), 3, "Member should have 3 books");
		assertFalse(paperBook.getIsAvailable());
		assertFalse(ebook.getIsAvailable());
		assertFalse(audiobook.getIsAvailable());
	}
}