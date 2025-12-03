package lab5.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lab5.*;

class TestBorrowingService {
	
	BorrowingService service;
	Member member;
	Book book1;
	Book book2;
	Book book3;
	Book book4;
	
	@BeforeEach
	void setUp() throws Exception {
		service = BorrowingService.getInstance();
		member = new Member("Alice", service);
		book1 = new PaperBook("Dune");
		book2 = new PaperBook("1984");
		book3 = new PaperBook("Moby Dick");
		book4 = new PaperBook("Foundation");
	}
	
	@Test
	void testBorrowBookSuccess() {
		BorrowingBookResult result = service.borrowBook(member, book1);
		assertTrue(result.isSuccess(), "Should successfully borrow book");
		assertEquals(1, member.borrowedBooksCount());
		assertFalse(book1.getIsAvailable());
		assertTrue(result.getBorrowingMessage().contains("successfully"));
	}
	
	@Test
	void testBorrowAlreadyBorrowedBook() {
		service.borrowBook(member, book1);
		BorrowingBookResult result = service.borrowBook(member, book1);
		assertFalse(result.isSuccess(), "Should fail to borrow same book twice");
		assertEquals(1, member.borrowedBooksCount());
		assertTrue(result.getBorrowingMessage().contains("already borrowed"));
	}
	
	@Test
	void testBorrowUnavailableBook() {
		book1.setIsAvailable(false);
		BorrowingBookResult result = service.borrowBook(member, book1);
		assertFalse(result.isSuccess(), "Should fail to borrow unavailable book");
		assertEquals(0, member.borrowedBooksCount());
		assertTrue(result.getBorrowingMessage().contains("currently borrowed"));
	}
	
	@Test
	void testBorrowingLimit() {
		service.borrowBook(member, book1);
		service.borrowBook(member, book2);
		service.borrowBook(member, book3);
		assertEquals(3, member.borrowedBooksCount());
		
		BorrowingBookResult result = service.borrowBook(member, book4);
		assertFalse(result.isSuccess(), "Should fail due to borrowing limit");
		assertEquals(3, member.borrowedBooksCount());
		assertTrue(result.getBorrowingMessage().contains("limit"));
	}
	
	@Test
	void testReturnBookSuccess() {
		service.borrowBook(member, book1);
		BorrowingBookResult result = service.returnBook(member, book1);
		assertTrue(result.isSuccess(), "Should successfully return book");
		assertEquals(0, member.borrowedBooksCount());
		assertTrue(book1.getIsAvailable());
		assertTrue(result.getBorrowingMessage().contains("successfully"));
	}
	
	@Test
	void testReturnBookNotBorrowed() {
		BorrowingBookResult result = service.returnBook(member, book1);
		assertFalse(result.isSuccess(), "Should fail to return book not borrowed");
		assertTrue(result.getBorrowingMessage().contains("not borrowed"));
	}
	
	@Test
	void testReturnAlreadyReturnedBook() {
		service.borrowBook(member, book1);
		service.returnBook(member, book1);
		book1.setIsAvailable(true);
		member.getBorrowedBooks().add(book1);
		
		BorrowingBookResult result = service.returnBook(member, book1);
		assertFalse(result.isSuccess(), "Should fail to return already returned book");
		assertTrue(result.getBorrowingMessage().contains("already been returned"));
	}
	
	@Test
	void testBorrowNullBook() {
		BorrowingBookResult result = service.borrowBook(member, null);
		assertFalse(result.isSuccess(), "Should fail with null book");
		assertTrue(result.getBorrowingMessage().contains("Invalid"));
	}
	
	@Test
	void testBorrowNullMember() {
		BorrowingBookResult result = service.borrowBook(null, book1);
		assertFalse(result.isSuccess(), "Should fail with null member");
		assertTrue(result.getBorrowingMessage().contains("Invalid"));
	}
}