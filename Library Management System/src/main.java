import java.util.Scanner;

public class main {

    static BookServiceInterface service = new BookServiceImpl();

    public static void main(String[] args) {

        readSavedInfoBooks();
        Scanner sc = new Scanner(System.in);




        menuLoop:
        while(true){
            displayMenu();
            System.out.println("Enter Your Choice: " );
            int choice = sc.nextInt();

            switch (choice){
                case 1:
                    service.addBook();
                    break;
                case 2:
                    service.showAllBooks();
                    break;
                case 3:
                    service.showAllAvailableBooks();
                    break;
                case 4:
                    service.borrowBook();
                    break;
                case 5:
                    service.returnBook();
                    break;
                case 6:
                    service.saveBookToFile();
                    break;
                case 7:
                    System.out.println("Have A Nice Day !!");
                    break menuLoop;
                default:
                    System.out.println("Please Enter Valid Choice....");
                    break;
            }

        }

    }

    public static void displayMenu(){
        System.out.println(">>>>>>>>>>>>>>>>>>>Welcome to Book Management Application System<<<<<<<<<<<<<<<<<<<< ");
        System.out.println("====================================================================================");
        System.out.println("" +
                "1 - Add Book\n" +
                "2 - Show All Books\n" +
                "3 - Show Available Books\n" +
                "4 - Borrow Book\n" +
                "5 - Return Book\n" +
                "6 - Save Book List To File\n" +
                "7 - Exit\n");
        System.out.println("====================================================================================");

    }

    private static void readSavedInfoBooks(){
        service.readSavedInfoBooks();
    }
}
