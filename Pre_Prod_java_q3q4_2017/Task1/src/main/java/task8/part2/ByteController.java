package task8.part2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.Exchanger;

public class ByteController {
    private static byte[] fileBytes;
    private Scanner scanner = new Scanner(System.in);
    private String filename;
    private Exchanger<String> exchanger = new Exchanger<>();
    private ByteFinder thread = new ByteFinder(exchanger);

    public static void main(String[] args) {
        ByteController byteController = new ByteController();
        byteController.startProcessing();

    }

    public void startProcessing() {
        new Thread(thread).start();
        while (isFileExistByFilename()) {
            startThread(filename);
            showIntermediateState();
            showFinalInfo();
            thread.refreshSequence();
        }
    }

    private void showFinalInfo() {
        System.out.println("sequence " + getSequence());
        System.out.println("first " + getFirstOccurrenceIndex());
        System.out.println("last " + getSecondOccurrenceIndex());
    }

    private void showIntermediateState() {
        String info = " ";
        while (!info.equals("FINISH")) {
            try {
                info = exchanger.exchange(null);
                System.out.println(info);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isFileExistByFilename() {
        System.out.println("Enter filename");
        filename = scanner.nextLine();
        if (!new File(filename).exists()) {
            System.out.println("Invalid filename, please, try again");
            return false;
        }
        return true;
    }

    private void startThread(String filename) {
        getFileBytes(filename);
        thread.setBytes(fileBytes);
        thread.search();
    }

    private void getFileBytes(String filename) {
        fileBytes = getFileLines(filename);
    }

    private byte[] getFileLines(String filename) {
        try {
            return Files.readAllBytes(Paths.get(filename));
        } catch (IOException e) {
            return new byte[0];
        }
    }

    public String getSequence() {
        return thread.getSequence();
    }

    public Integer getFirstOccurrenceIndex() {
        return thread.getFirstOccurrenceIndex();
    }

    public Integer getSecondOccurrenceIndex() {
        return thread.getSecondOccurrenceIndex();
    }
}
