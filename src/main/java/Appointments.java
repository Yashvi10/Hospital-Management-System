import java.util.Scanner;

public class Appointments {

    public int book_appointment(){
        System.out.println("Book");
        return 1;
    }

    public int cancel_appointment(){
        System.out.println("Cancel");
        return 1;
    }

    public int reschedule_appointment(){
        System.out.println("Reschedule");
        return 1;
    }

    public static void main(String[] args) {

        Appointments appointments = new Appointments();

        int choice;
        System.out.println("Please let us know if you want to book a new appointment, cancel an existing appointment or " +
                "reschedule an existing appointment");
        System.out.println("""
                Please select:\s
                1: Book an appointment\s
                2: Cancel an appointment\s
                3: Reschedule an appointment\s
                """);
        Scanner input = new Scanner(System.in);
        choice = input.nextInt();

        switch (choice) {
            case 1 -> appointments.book_appointment();
            case 2 -> appointments.cancel_appointment();
            case 3 -> appointments.reschedule_appointment();
            default -> System.out.println("Please choose a valid option! Invalid selection");
        }

    }

}
