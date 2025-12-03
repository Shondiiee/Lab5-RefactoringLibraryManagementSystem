package lab5.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import lab5.*;

class TestFactoryMethod {
	
	@Test
	void testPaperBookFactory() {
		BookFactory factory = new PaperBookFactory();
		Book book = factory.createBook("Test Book");
		assertTrue(book instanceof PaperBook, "Should be a PaperBook");
		assertEquals("Test Book", book.getTitle());
		assertTrue(book.getIsAvailable());
	}
	
	@Test
	void testEBookFactory() {
		BookFactory factory = new EBookFactory();
		Book book = factory.createBook("Test EBook");
		assertTrue(book instanceof EBook, "Should be an EBook");
		assertEquals("Test EBook", book.getTitle());
		assertTrue(book.getIsAvailable());
	}
	
	@Test
	void testAudioBookFactory() {
		BookFactory factory = new AudioBookFactory();
		Book book = factory.createBook("Test AudioBook");
		assertTrue(book instanceof AudioBook, "Should be an AudioBook");
		assertEquals("Test AudioBook", book.getTitle());
		assertTrue(book.getIsAvailable());
	}
	
	@Test
	void testLibrarianControllerWithFactory() {
		LibrarianController librarian = new LibrarianController();
		Library library = librarian.getLibrary();
		
		// Test the generic addBook method with different factories
		librarian.addBook(new PaperBookFactory(), "Dune");
		librarian.addBook(new EBookFactory(), "Digital Fortress");
		librarian.addBook(new AudioBookFactory(), "The Hobbit");
		
		assertEquals(3, library.booksCount(), "Should have 3 books");
		
		// Verify books were created with correct types
		Book book1 = library.findBookByTitle("Dune");
		Book book2 = library.findBookByTitle("Digital Fortress");
		Book book3 = library.findBookByTitle("The Hobbit");
		
		assertTrue(book1 instanceof PaperBook);
		assertTrue(book2 instanceof EBook);
		assertTrue(book3 instanceof AudioBook);
	}
	
	@Test
	void testFactoryPolymorphism() {
		// Test that we can treat all factories uniformly
		BookFactory[] factories = {
			new PaperBookFactory(),
			new EBookFactory(),
			new AudioBookFactory()
		};
		
		String[] titles = {"Book1", "Book2", "Book3"};
		
		for (int i = 0; i < factories.length; i++) {
			Book book = factories[i].createBook(titles[i]);
			assertNotNull(book);
			assertEquals(titles[i], book.getTitle());
			assertTrue(book.getIsAvailable());
		}
	}
}