package View;

import Interface.*;
import Model.Order;
import Model.OrderItem;
import Service.BillingService;
import Service.OrderService;
import Service.UserSession;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 *  Name of file: BillingMenu.java
 *  Author:  Nadish Maredia
 *  Purpose & Description: This class implement the FeatureMenu and is responsible for showing
 *                          different menus related to BL.Billing module
 * */
public class BillingMenu implements FeatureMenu {

    List<Order> orderlist = new ArrayList<Order>();

    Scanner scanner = new Scanner(System.in);

    IDashboard dashboard;

    public BillingMenu(IDashboard dashboard) {
        this.dashboard = dashboard;
    }

    @Override
    public void menu() {
//        System.out.println("Enter user id: ");
//        String userId = scanner.nextLine();

        OrderService orderService = new OrderService();

        if(orderService.isUserFound(UserSession.userId)) {
            BillingOrderDAO billingOrderDAO = new BillingService();
            Billing billing = new Billing(billingOrderDAO);
            orderlist = billing.getUserOrder(UserSession.userId);

            System.out.println("=========Your Latest Orders=========");
            System.out.println(String.format(Constant.STRING_FORMAT, "Order Id"));
            for  (int i = 0;i<orderlist.size();i++)  {
                String order_id = String.format(Constant.STRING_FORMAT, orderlist.get(i).getOrder_id());
                System.out.println(order_id);
            }

            if(orderlist.size() > 0) {
                System.out.println("Press v to view order details or e to exit");
                String userInput = scanner.nextLine();

                if  (userInput.equals(Constant.SMALL_v) || userInput.equals(Constant.CAPITAL_V))  {
                    viewOrderDetails();
                }  else  {
                    NotFound();
                    menu();
                }
            } else {
                System.out.println(Colors.C_RED +" You don't have order anything yet.");
            }
        } else {
            System.out.println("User not found with this Id");
            menu();
        }
    }


    public void viewOrderDetails()  {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter order id to view: ");
        String orderIdFromUser = scanner.nextLine();

        Boolean isFound = false;
        Integer index = 0;

        for  (int i = 0;i<orderlist.size();i++)  {
            if  (orderlist.get(i).getOrder_id().toString().equals(orderIdFromUser))  {
                isFound = true;
                index = i;
                break;
            }
        }

        if  (isFound)  {
            List<OrderItem> orderItemsList = new ArrayList<OrderItem>();
            // use billing to reduce coupling
            BillingOrderItemDAO billingOrderItemDAO = new BillingService();
            Billing billing = new Billing(billingOrderItemDAO);
            orderItemsList = billing.getUserOrderItems(orderlist.get(index).getOrder_id());

            showOrderItems(orderItemsList);
        }  else  {
            System.out.println(Colors.C_RED +" Order not found with that Id" + Colors.C_RESET);
            viewOrderDetails();
        }

    }

    public void showOrderItems(List<OrderItem> items)  {

        String data = "";
        String heading = "";
        String orderId = "";
        Double tt_bill = 0.0;
        Double ff_bill = 0.0;

        String order_heading = String.format(Constant.STRING_FORMAT, "Order Id") +" ";
        String name_heading = String.format(Constant.STRING_FORMAT, "Name") +" ";
        String qty_heading = String.format(Constant.STRING_FORMAT, "Qty") +" ";
        String price_heading = String.format(Constant.STRING_FORMAT, "Price") +" ";
        String t_price_heading = String.format(Constant.STRING_FORMAT, "Total Price") +" ";
        String final_bill_heading = String.format(Constant.STRING_FORMAT, "Final Bill");
        heading = order_heading + name_heading +qty_heading+price_heading+t_price_heading+final_bill_heading;

        data += heading +"\n";
        for  (int j = 0;j<items.size();j++)  {
            orderId = items.get(j).getOrder_id().toString();
            String order_id = String.format(Constant.STRING_FORMAT, items.get(j).getOrder_id());
            String name = String.format(Constant.STRING_FORMAT, items.get(j).getName());
            String price = String.format(Constant.DOUBLE_FORMAT, items.get(j).getPrice());
            String qty = String.format(Constant.INTERGER_FORMAT, items.get(j).getQty());
            String total_price = String.format(Constant.DOUBLE_FORMAT, items.get(j).getTotal_bill());
            String final_bill = String.format(Constant.DOUBLE_FORMAT, items.get(j).getFinal_bill());
            data += order_id +" " +name +" " +qty +" " +price +" " +total_price +" " +final_bill +"\n";

            tt_bill += items.get(j).getTotal_bill();
            ff_bill = items.get(j).getFinal_bill();
        }
        System.out.println(data);
        System.out.println("Total Bill: " +String.format(Constant.DOUBLE_WITHOUT_SPACE_FORMAT, tt_bill));
        System.out.println("Final Bill: " +String.format(Constant.DOUBLE_WITHOUT_SPACE_FORMAT, ff_bill));
        System.out.println("Discount: " +String.format(Constant.DOUBLE_WITHOUT_SPACE_FORMAT, (tt_bill - ff_bill)));

        Scanner scanner = new Scanner(System.in);
        System.out.println("Press s to save invoice or e to exit");
        String userInput = scanner.nextLine();
        if  (userInput.equals(Constant.SMALL_s) || userInput.equals(Constant.CAPITAL_S))  {
            createPDF(data, orderId, tt_bill, ff_bill, (tt_bill - ff_bill));
        }  else  {
            dashboard.HomeMenu();
        }

        System.out.println();
    }

    public void createPDF(String data, String orderId, Double totalbill,
                          Double finalbill, Double discount) {

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        String FinalData = "Hospital System                                                     " +
                "                    created At: " +formattedDate +"\n\n";
        FinalData += "Order Id: " +orderId +"\n\n\n";
        FinalData += data;
        FinalData += "\n\nTotal Bill: " +String.format(Constant.DOUBLE_WITHOUT_SPACE_FORMAT, totalbill) +" \n";
        FinalData += "Discount: " +String.format(Constant.DOUBLE_WITHOUT_SPACE_FORMAT, discount) +" \n";
        FinalData += "Final Bill: " +String.format(Constant.DOUBLE_WITHOUT_SPACE_FORMAT, finalbill) +" \n";
        FinalData += "\n System generated bill";

        CustomPDF pdf = new CustomPDF();
        pdf.generateBill(FinalData);

        dashboard.HomeMenu();

    }

    public void NotFound()  {

        System.out.println(Colors.C_RED  +  "Please select the correct option"  +  Colors.C_RESET);
    }


}
