import Model.Order;
import Model.OrderItem;
import Services.BillingService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BillingPage {

    List<Order> orderlist = new ArrayList<Order>();


    public void showUserOrder(){
        BillingService billingService = new BillingService();

        orderlist = billingService.getUserOrder(123);

        System.out.println("=========Your Latest Orders=========");
        System.out.println(String.format("|%10s|", "Order Id"));
        for(int i = 0;i<orderlist.size();i++) {
            String order_id = String.format("|%10s|", orderlist.get(i).getOrder_id());
            System.out.println(order_id);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Press v to view order details or e to exit");
        String userInput = scanner.nextLine();

        if(userInput.equals("v") || userInput.equals("V")) {
            viewOrderDetails();
        } else {
            return;
        }
    }

    public void viewOrderDetails(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter order id to view: ");
        String orderIdFromUser = scanner.nextLine();

        Boolean isFound = false;
        Integer index = 0;

        for(int i = 0;i<orderlist.size();i++) {
            if(orderlist.get(i).getOrder_id().toString().equals(orderIdFromUser)) {
                isFound = true;
                index = i;
                break;
            }
        }

        if(isFound) {
            BillingService billingService = new BillingService();
            List<OrderItem> orderItemsList = new ArrayList<OrderItem>();
            orderItemsList = billingService.getUserOrderItems(orderlist.get(index).getOrder_id());
            showOrderItems(orderItemsList);
        }

    }

    public void showOrderItems(List<OrderItem> items){
        String data = "";
        String heading = "";
        String orderId = "";

        // asasa
        Double tt_bill = 0.0;
        Double ff_bill = 0.0;

        String order_heading = String.format("|%10s|", "Order Id") +" ";
        String name_heading = String.format("|%10s|", "Name") +" ";
        String qty_heading = String.format("|%10s|", "Qty") +" ";
        String price_heading = String.format("|%10s|", "Price") +" ";
        String t_price_heading = String.format("|%10s|", "Total Price") +" ";
        String final_bill_heading = String.format("|%10s|", "Final Bill");
        heading = order_heading + name_heading +qty_heading+price_heading+t_price_heading+final_bill_heading;

        data += heading +"\n";
        for(int j = 0;j<items.size();j++) {
            orderId = items.get(j).getOrder_id().toString();
            String order_id = String.format("|%10s|", items.get(j).getOrder_id());
            String name = String.format("|%10s|", items.get(j).getName());
            String price = String.format("|%10.2f|", items.get(j).getPrice());
            String qty = String.format("|%10d|", items.get(j).getQty());
            String total_price = String.format("|%10.2f|", items.get(j).getTotal_bill());
            String final_bill = String.format("|%10.2f|", items.get(j).getFinal_bill());
            data += order_id +" " +name +" " +qty +" " +price +" " +total_price +" " +final_bill +"\n";

            tt_bill += items.get(j).getTotal_bill();
            ff_bill = items.get(j).getFinal_bill();
        }
        System.out.println(data);
        System.out.println("Total Bill: " +String.format("%.2f", tt_bill));
        System.out.println("Final Bill: " +String.format("%.2f", ff_bill));
        System.out.println("Discount: " +String.format("%.2f", (tt_bill - ff_bill)));

        Scanner scanner = new Scanner(System.in);
        System.out.println("Press s to save invoice or e to exit");
        String userInput = scanner.nextLine();
        if(userInput.equals("s") || userInput.equals("S")) {
            createPDF(data, orderId, tt_bill, ff_bill, (tt_bill - ff_bill));
        } else {
            return;
        }

        System.out.println();
    }

    public void createPDF(String data, String orderId, Double totalbill, Double finalbill, Double discount){

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        String FinalData = "Hospital System                                                     " +
                "                    created At: " +formattedDate +"\n\n";
        FinalData += "Order Id: " +orderId +"\n\n\n";
        FinalData += data;
        FinalData += "\n\nTotal Bill: " +String.format("%.2f", totalbill) +" \n";
        FinalData += "Discount: " +String.format("%.2f", discount) +" \n";
        FinalData += "Final Bill: " +String.format("%.2f", finalbill) +" \n";
        FinalData += "\n System generated bill";

        CustomPDF pdf = new CustomPDF();
        pdf.generateBill(FinalData);

    }


}
