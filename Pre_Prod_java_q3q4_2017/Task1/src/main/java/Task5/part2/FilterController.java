package Task5.part2;

import Task5.part2.filter.*;
import Task5.part2.util.Validation;

import java.util.Objects;
import java.util.Scanner;

public class FilterController {

    /**
     * Returns a class object if required by the user.
     * Searches by date
     * Otherwise, it returns the old filter object.
     *
     * @param scanner
     * @param filter  old class instance that is
     *                returned if class is not needed
     * @return
     */
    public Filter date(Scanner scanner, Filter filter) {
        System.out.println("Input date 0/1");
        if (Validation.isNumberValid(scanner)) {
            SearchByDate searchByDate = new SearchByDate(filter);
            System.out.println("Enter min date >");
            searchByDate.setMinTime(Validation.validDateInput(scanner));
            System.out.println("Enter max date >");
            searchByDate.setMaxTime(Validation.validDateInput(scanner));
            return searchByDate;
        }
        return filter;
    }

    /**
     * Returns a class object if required by the user.
     * Searches by extension
     * Otherwise, it returns the old filter object.
     *
     * @param scanner
     * @param filter  old class instance that is
     *                returned if class is not needed
     * @return
     */
    public Filter extension(Scanner scanner, Filter filter) {
        System.out.println("Input extension 0/1");
        if (Validation.isNumberValid(scanner)) {
            SearchByExtension searchByExtension = new SearchByExtension(filter);
            System.out.println("Enter file extension");
            searchByExtension.setExtension(Validation.validExtension(scanner));
            return searchByExtension;
        }
        return filter;
    }

    /**
     * Returns a class object if required by the user.
     * Searches by size
     * Otherwise, it returns the old filter object.
     *
     * @param scanner
     * @param filter  old class instance that is
     *                returned if class is not needed
     * @return
     */
    public Filter size(Scanner scanner, Filter filter) {
        System.out.println("Input size 0/1");
        if (Validation.isNumberValid(scanner)) {
            SearchByLength searchByLength = new SearchByLength(filter);
            System.out.println("Enter min size >");
            searchByLength.setSizeMin(Validation.validLongInput(scanner));
            System.out.println("Enter max size >");
            searchByLength.setSizeMax(Validation.validLongInput(scanner));
            return searchByLength;
        }
        return filter;
    }

    /**
     * Returns a class object if required by the user.
     * Searches by name
     * Otherwise, it returns the old filter object.
     *
     * @param scanner
     * @param filter  old class instance that is
     *                returned if class is not needed
     * @return
     */
    public Filter name(Scanner scanner, Filter filter) {
        System.out.println("Input name 0/1");
        if (Validation.isNumberValid(scanner)) {
            SearchByName searchByName = new SearchByName(filter);
            System.out.println("Enter file name");
            searchByName.setFileName(scanner.next());
            return searchByName;
        }
        return filter;
    }

    /**
     * Initializes the default implementation of
     * the filter if the chain does not exist.
     *
     * @param filter
     * @return
     */
    public Filter allFilesAreTrue(Filter filter) {
        return Objects.isNull(filter) ? new DefaultRealisation(null) : filter;
    }
}
