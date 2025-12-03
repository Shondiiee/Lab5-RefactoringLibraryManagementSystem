package lab5.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import lab5.*;

class TestMembersBorrowingService {
	
	BorrowingService service = BorrowingService.getInstance();
	
	@Test
	void testMemberServices() {
		Member member1 = new Member("Member 1", service);
		Member member2 = new Member("Member 2", service);
		assertEquals(member1.getBorrowingService(), 
		            member2.getBorrowingService(),
		            "Members have two different borrowing services");
		assertSame(member1.getBorrowingService(), 
		          member2.getBorrowingService(),
		          "Should be the exact same instance");
	}
}