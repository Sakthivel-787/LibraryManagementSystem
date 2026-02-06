import java.util.*;

public class LibrarySystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        // Sample Books
        library.addBook(new Book("978-3-16-148410-0","Java Programming Guide","John Smith","Programming"));
        library.addBook(new Book("978-0-262-03384-8","Introduction to Algorithms","Thomas Cormen","Computer Science"));
        library.addBook(new Book("978-0-13-468599-1","Effective Java","Joshua Bloch","Programming"));

        // Sample Members
        library.addMember(new Member("M001","Alice Johnson","1234567890"));
        library.addMember(new Member("M002","Bob Smith","9876543210"));

        int choice;
        do {
            System.out.println("\n=== LIBRARY MANAGEMENT SYSTEM ===");
            System.out.println("1. Add New Book\n2. Register New Member\n3. Display All Books");
            System.out.println("4. Display Available Books\n5. Search Books\n6. Borrow Book\n7. Return Book\n8. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch(choice) {
                case 1:
                    System.out.print("Enter ISBN: "); String isbn = sc.nextLine();
                    System.out.print("Enter Title: "); String title = sc.nextLine();
                    System.out.print("Enter Author: "); String author = sc.nextLine();
                    System.out.print("Enter Genre: "); String genre = sc.nextLine();
                    library.addBook(new Book(isbn,title,author,genre));
                    System.out.println("✅ Book added successfully!");
                    break;

                case 2:
                    System.out.print("Enter Member ID: "); String mid = sc.nextLine();
                    System.out.print("Enter Name: "); String name = sc.nextLine();
                    System.out.print("Enter Contact: "); String contact = sc.nextLine();
                    library.addMember(new Member(mid,name,contact));
                    System.out.println("✅ Member registered successfully!");
                    break;

                case 3: library.displayAllBooks(); break;
                case 4: library.displayAvailableBooks(); break;

                case 5:
                    System.out.print("Enter search keyword: "); String keyword = sc.nextLine();
                    ArrayList<Book> results = library.searchBooks(keyword);
                    if(results.isEmpty()) System.out.println("No books found!");
                    else {
                        System.out.println("Search Results:");
                        for(Book b : results) b.displayInfo();
                    }
                    break;

                case 6:
                    System.out.print("Enter Member ID: "); String borrowMid = sc.nextLine();
                    System.out.print("Enter Book ISBN: "); String borrowIsbn = sc.nextLine();
                    Member member = library.findMemberById(borrowMid);
                    Book book = library.findBookByIsbn(borrowIsbn);
                    if(member != null && book != null && member.borrowBook(book))
                        System.out.println("✅ Book borrowed successfully!");
                    else
                        System.out.println("❌ Cannot borrow book!");
                    break;

                case 7:
                    System.out.print("Enter Member ID: "); String returnMid = sc.nextLine();
                    System.out.print("Enter Book ISBN: "); String returnIsbn = sc.nextLine();
                    Member mem = library.findMemberById(returnMid);
                    Book bk = library.findBookByIsbn(returnIsbn);
                    if(mem != null && bk != null && mem.returnBook(bk))
                        System.out.println("✅ Book returned successfully!");
                    else
                        System.out.println("❌ Cannot return book!");
                    break;

                case 8: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice!");
            }

        } while(choice != 8);

        sc.close();
    }
}
