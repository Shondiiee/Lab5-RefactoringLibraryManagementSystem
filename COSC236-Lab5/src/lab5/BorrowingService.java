package lab5;

public class BorrowingService implements BorrowingServiceAPI {
    
    private static BorrowingService instance; // Singleton instance
    private int borrowingLimit;
    
    // Private constructor
    private BorrowingService() {
        borrowingLimit = 3;
    }
    
    // Public method to get the single instance
    public static synchronized BorrowingService getInstance() {
        if (instance == null) {
            instance = new BorrowingService();
        }
        return instance;
    }
    
    @Override
    public BorrowingBookResult borrowBook(Member member, Book book) {
        if (book == null || member == null) {
            return new BorrowingBookResult(false, "Invalid book or member");
        }
        
        // Check if member already has the book
        if (member.getBorrowedBooks().contains(book)) {
            return new BorrowingBookResult(false, 
                "Member has already borrowed this book");
        }
        
        // Check if book is available
        if (!book.getIsAvailable()) {
            return new BorrowingBookResult(false, 
                "Book is currently borrowed by another member");
        }
        
        // Check borrowing limit
        if (member.borrowedBooksCount() >= borrowingLimit) {
            return new BorrowingBookResult(false, 
                "Member has exceeded borrowing limit of " + borrowingLimit);
        }
        
        // Borrow the book
        member.getBorrowedBooks().add(book);
        book.setIsAvailable(false);
        return new BorrowingBookResult(true, 
            "Book '" + book.getTitle() + "' borrowed successfully");
    }
    
    @Override
    public BorrowingBookResult returnBook(Member member, Book book) {
        if (book == null || member == null) {
            return new BorrowingBookResult(false, "Invalid book or member");
        }
        
        // Check if member has the book
        if (!member.getBorrowedBooks().contains(book)) {
            return new BorrowingBookResult(false, 
                "Member has not borrowed this book");
        }
        
        // Check if book is already returned
        if (book.getIsAvailable()) {
            return new BorrowingBookResult(false, 
                "Book has already been returned");
        }
        
        // Return the book
        member.getBorrowedBooks().remove(book);
        book.setIsAvailable(true);
        return new BorrowingBookResult(true, 
            "Book '" + book.getTitle() + "' returned successfully");
    }
}