package lab5;

public interface BorrowingServiceAPI {
    BorrowingBookResult borrowBook(Member member, Book book);
    BorrowingBookResult returnBook(Member member, Book book);
}