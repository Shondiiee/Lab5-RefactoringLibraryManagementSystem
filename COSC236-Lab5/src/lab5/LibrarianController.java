package lab5;

public class LibrarianController {
	
	Library library;
	BorrowingService borrowingService;
	BookFactory paperBookFactory;
	BookFactory eBookFactory;
	BookFactory audioBookFactory;
	
	public LibrarianController() {
		this.library = new Library();
		this.borrowingService = BorrowingService.getInstance();
		this.paperBookFactory = new PaperBookFactory();
		this.eBookFactory = new EBookFactory();
		this.audioBookFactory = new AudioBookFactory();
	}
	
	public Library getLibrary() {
		return this.library;
	}
	
	public void showBooks() {
		library.showBooks();
	}
	
	public void showMembers() {
		library.showMembers();
	}
	
	// Generic method using factory
	public void addBook(BookFactory factory, String title) {
		library.addBook(factory.createBook(title));
	}
	
	// Generic method that defaults to PaperBook
	public void addBook(String title) {
		library.addBook(paperBookFactory.createBook(title));
	}
	
	// Specific methods for each book type using factories
	public void addPaperBook(String title) {
		library.addBook(paperBookFactory.createBook(title));
	}
	
	public void addEBook(String title) {
		library.addBook(eBookFactory.createBook(title));
	}
	
	public void addAudioBook(String title) {
		library.addBook(audioBookFactory.createBook(title));
	}
	
	public void addMember(String name) {
		library.addMember(new Member(name, borrowingService));
	}
	
	public void removeBook(String title) {
		library.removeBook(title);
	}
	
	public void removeMember(String name) {
		library.removeMember(name);
	}
	
	public void showMember(String name) {
		Member member = library.findMemberByName(name);
		if (member != null)
			System.out.println(member);
		else 
			System.out.println("Member " + name + " not found.");
	}
	
	public void showBook(String title) {
		Book book = library.findBookByTitle(title);
		if (book != null)
			System.out.println(book);
		else 
			System.out.println("Book " + title + " not found.");
	}
	
	public void showMemberBooks(String name) {
		Member member = library.findMemberByName(name);
		if (member != null)
			member.listBorrowedBooks();
		else 
			System.out.println("Member " + name + " not found.");
	}
	
	public void borrowBookByMember(String title, String name) {
		Member member = library.findMemberByName(name);
		Book book = library.findBookByTitle(title);
		if (book != null && member != null)
			member.borrowBook(book);
		else 	
			System.out.println("Either book " + title + " or member " + name + " not found.");
	}
	
	public void returnBookByMember(String title, String name) {
		Member member = library.findMemberByName(name);
		Book book = library.findBookByTitle(title);
		if (book != null && member != null)
			member.returnBook(book);
		else  	
			System.out.println("Either book " + title + " or member " + name + " not found.");
	}
}