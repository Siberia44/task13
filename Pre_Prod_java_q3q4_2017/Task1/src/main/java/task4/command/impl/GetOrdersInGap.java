package task4.command.impl;

import task4.command.Command;
import task4.service.OrderService;
import task4.util.InputUtil;

import java.time.DateTimeException;
import java.util.Scanner;

public class GetOrdersInGap implements Command {
    private OrderService orderService;

    public GetOrdersInGap(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void execute() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input first date");
        String firstDate = sc.nextLine();
        System.out.println("Input last date");
        String lastDate = sc.nextLine();
        try {
            System.out.println(orderService.getOrdersInGap(InputUtil.fromStringToDate(firstDate),
                    InputUtil.fromStringToDate(lastDate)));
        } catch (DateTimeException e) {
            System.out.println("Date is not correct");
        }

    }
}
