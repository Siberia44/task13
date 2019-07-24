package task4.command.impl;

import task4.command.Command;
import task4.service.OrderService;
import task4.util.InputUtil;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

public class GetNearestDate implements Command {
    private OrderService orderService;

    public GetNearestDate(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void execute() {
        System.out.println("Input nearest date");
        Scanner scanner = new Scanner(System.in);
        String date = scanner.nextLine();
        try {
            LocalDate localDate = InputUtil.fromStringToDate(date);
            System.out.println(orderService.getNearestOrder(localDate));
        } catch (DateTimeException e) {
            System.out.println("Date is not correct");
        }
    }
}
