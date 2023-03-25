import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookServiceImpl implements BookServiceInterface{

    public static final String RED="\u001B[31m";
    public static final String RESET="\u001B[0m";
    public static final String BLUE="\u001B[34m";
    public static final String GREEN="\u001B[32m";
    public static final String CYAN="\u001B[36m";
    public static final String BLACK="\u001B[30m";
    public static final String PURPLE_BACKGROUND = "\033[45m"; // PURPLE

    Scanner sc = new Scanner(System.in);
    Validator validator = new Validator();
    List<Book> books = new ArrayList<>();



    @Override
    public void addBook() {
        System.out.println("-----------------------------------------------------------------------");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Add a Book To Library<<<<<<<<<<<<<<<<<<<");
        System.out.println();
        String bookid = validator.validateId();
        String author = validator.validateAuthorTitle("Author");
        String Title = validator.validateAuthorTitle("Title");
        String year = validator.validatePublishYear();

        Book book = new Book(bookid,author,Title,year,"Available");
        books.add(book);
        System.out.println(GREEN+"Book Added Successfully......" + RESET);

    }

    @Override
    public void showAllBooks() {
        boolean flag=false;
        System.out.println("\n----------------------------------------------------------------------------------------------");
        System.out.format(CYAN+"%s%15s%15s%15s%15s","ID","TITLE","AUTHOR","PUBLISH YEAR","STATUS"+RESET);
        System.out.println("\n----------------------------------------------------------------------------------------------");

        for (Book book:books){
            System.out.format("%s%15s%15s%15s%15s",book.getId(),book.getTitle(),book.getAuthor(),book.getPublishYear(),book.getStatus());
            System.out.println();
            flag=true;
        }
        System.out.println("\n----------------------------------------------------------------------------------------------");
        if(flag==false)
            System.out.println(RED+"There are no Books in Library"+RESET);


    }

    @Override
    public void showAllAvailableBooks() {
        System.out.println("-----------------------------------------------------------------------");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Available Books<<<<<<<<<<<<<<<<<<<");
        System.out.println();

        boolean flag=false;
        System.out.println("\n----------------------------------------------------------------------------------------------");
        System.out.format(CYAN+"%s%15s%15s%15s%15s","ID","TITLE","AUTHOR","PUBLISH YEAR","STATUS"+RESET);
        System.out.println("\n----------------------------------------------------------------------------------------------");

        if(books.size()>0){
            for (Book book:books){
                if(book.getStatus()=="Available"){
                    System.out.format("%s%15s%15s%15s%15s",book.getId(),book.getTitle(),book.getAuthor(),book.getPublishYear(),book.getStatus());
                    System.out.println();
                    flag=true;
                }
            }
        }
        else{
            System.out.println(RED+"No Books Available in the library"+RESET);
        }
        System.out.println("\n----------------------------------------------------------------------------------------------");
        if(flag==false)
            System.out.println(RED+"There are no books with status Available"+RESET);

    }

    @Override
    public void borrowBook() {

        System.out.println("-----------------------------------------------------------------------");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Can Burrow a Book Or Not<<<<<<<<<<<<<<<<<<<");
        System.out.println();

        String bookid= validator.validateId();
        boolean flag=false;
        for(Book book:books){
            if(book.getId().equals(bookid) && book.getStatus().equals("Available")){
                flag=true;
                System.out.println(GREEN+"Book Borrowed Successfully !!!"+RESET);
                book.setStatus("Not Available");
                System.out.println("Borrowed Book Details: "+book);
            }
        }
        if(flag==false)
            System.out.println(RED+"This book is not available to borrow"+RESET);

    }

    @Override
    public void returnBook() {

        System.out.println("-----------------------------------------------------------------------");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Return the Book To the Library<<<<<<<<<<<<<<<<<<<");
        System.out.println();

        boolean flag=false;
        String bookid= validator.validateId();
        for(Book book:books){
            if(book.getId().equals(bookid) && book.getStatus().equals("Not Available")){
                flag=true;
                System.out.println(GREEN+"Book Returned Successfully !!!"+RESET);
                book.setStatus("Available");
                System.out.println("Returned Book Details: "+book);
            }

        }
        if(flag==false)
            System.out.println(RED+"We can not return this book"+RESET);



    }

    @Override
    public void saveBookToFile() {

        System.out.println("-----------------------------------------------------------------------");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Save Books To the File<<<<<<<<<<<<<<<<<<<");
        System.out.println();

        int bookCount = books.size();

        try {
            FileWriter saveBook = new FileWriter("saveBookFile.txt");
            saveBook.write(String.valueOf(bookCount));
            saveBook.write("\n");

            for (int i=0;i<books.size();i++){
                saveBook.write(books.get(i).getId());
                saveBook.write("\n");

                saveBook.write(books.get(i).getTitle());
                saveBook.write("\n");

                saveBook.write(books.get(i).getAuthor());
                saveBook.write("\n");

                saveBook.write(books.get(i).getPublishYear());
                saveBook.write("\n");

                saveBook.write(books.get(i).getStatus());
                saveBook.write("\n");

                saveBook.close();
                System.out.println();
                System.out.println("Book List Saved Successfully !!!");
                System.out.println();

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("File Doesn't Exist");
        }

    }

    public void readSavedInfoBooks(){
        File file = new File("saveBookFile.txt");
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            int bookCount = Integer.parseInt(reader.readLine());

            for (int i=0; bookCount>i;i++){
                String bookid = (reader.readLine());
                String bookTitle = (reader.readLine());
                String bookAuthor = (reader.readLine());
                String bookPublishYear = (reader.readLine());
                String bookStatus = (reader.readLine());

                Book book1 = new Book(bookid,bookTitle,bookAuthor,bookPublishYear,bookStatus);
                books.add(book1);

            }
        }

        catch (FileNotFoundException e) {
            throw new RuntimeException(e);

        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
