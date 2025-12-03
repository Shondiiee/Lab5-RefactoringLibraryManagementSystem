package lab5;


public class LibraryApp {

	// Create Controller, It will create an empty Library 
	private static LibrarianController librarian =  new LibrarianController();
	
	// Some functions for the Assignment
	private static void addMember(String name) {
		librarian.addMember(name);
	}	
	private static void addBook(String title) {
		librarian.addBook(title);
	}
	private static void borrowBook(String title, String name) {
		librarian.borrowBookByMember(title, name);
	}
	private static void returnBook(String title, String name) {
		librarian.returnBookByMember(title, name);
	}
		
	public static void main(String[] args) {
						 	
		System.out.println(" *** Library management system demo *** ");
		
		// Add this test code in main() method
		System.out.println("\n *** Testing borrowing limit:");
		librarian.addMember("Charlie");
		librarian.addBook("Book1");
		librarian.addBook("Book2");
		librarian.addBook("Book3");
		librarian.addBook("Book4");
		
		System.out.println("\n *** Testing Factory Method:");
	    librarian.addBook(new PaperBookFactory(), "Dune");
	    librarian.addBook(new EBookFactory(), "Digital Fortress");
	    librarian.addBook(new AudioBookFactory(), "The Hobbit");
	    
	    System.out.println("\n *** All books in library:");
	    librarian.showBooks();

		librarian.borrowBookByMember("Book1", "Charlie");
		librarian.borrowBookByMember("Book2", "Charlie");
		librarian.borrowBookByMember("Book3", "Charlie");
		librarian.borrowBookByMember("Book4", "Charlie"); // Should fail - limit exceeded
		
	    // Adding one book, see the Sequence diagram in the lab document.
		addBook("Dune");
		
		// DONE: Create three sequence diagrams for each of these method calls 
		addMember("Alice"); 			// 1. Add a member
		borrowBook("Dune", "Alice");  	// 2. Borrow a book
		returnBook("Dune", "Alice"); 	// 3. Return book		
		 
	    // Adding some books to the catalog
		System.out.println("\n *** Adding books to the library:");
		librarian.addBook("Dune");
		librarian.addBook("1984");
		librarian.addBook("Moby Dick");
		
		// Adding members to the library
		System.out.println("\n *** Adding members to the library:");
		librarian.addMember("Alice");
		librarian.addMember("Bob");
		
		librarian.borrowBookByMember("Dune", "Alice");
		librarian.borrowBookByMember("1984", "Alice");
		librarian.borrowBookByMember("Moby Dick", "Bob");
		librarian.borrowBookByMember("Dune", "Bob"); // will be rejected
		
		System.out.println("\n *** Books borrowed by Alice:");
		librarian.showMemberBooks("Alice");
		
		librarian.showMemberBooks("Bob");
		
		System.out.println("\n *** Book returned by Alice: Dune");
		librarian.returnBookByMember("Dune", "Alice");
		librarian.borrowBookByMember("Dune", "Bob"); // will be successful
		System.out.println("\n *** Books borrowed by Bob:");
		librarian.showMemberBooks("Bob");
	}
	
}
