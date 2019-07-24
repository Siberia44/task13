package task8.part2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

public class ByteFinder implements Runnable {

    Exchanger<String> exchanger;
    private byte[] bytes;
    private List<Byte> sequence = new ArrayList<>();
    private ByteMatrix byteMatrix;

    public ByteFinder(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public synchronized void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            byteMatrix = new ByteMatrix();
            byteMatrix.getBiggestSequence();
            endProgram();
        }
    }

    public void endProgram() {
        try {
            exchanger.exchange("FINISH");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String convertToString(List<Byte> sequence) {
        byte[] bytes = new byte[sequence.size()];
        for (int i = 0; i < sequence.size(); i++) {
            bytes[i] = sequence.get(i);
        }
        return new String(bytes);
    }


    synchronized void search() {
        notify();
    }

    void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    String getSequence() {
        return convertToString(sequence);
    }

    int getSecondOccurrenceIndex() {
        return byteMatrix.getSecondOccurrenceIndex();
    }

    int getFirstOccurrenceIndex() {
        return byteMatrix.getFirstOccurrenceIndex();
    }

    void refreshSequence() {
        sequence = new ArrayList<>();
    }

    private class ByteMatrix {

        private char[][] matrix = new char[bytes.length][bytes.length];
        private List<Byte> additionalSequence = new ArrayList<>();
        private int firstOccurrenceIndex;
        private int secondOccurrenceIndex;

        ByteMatrix() {
            initMatrix();
        }

        private synchronized void initMatrix() {
            for (int i = 0; i < bytes.length; i++) {
                for (int j = 0; j < bytes.length; j++) {
                    initCell(i, j);
                }
            }
        }

        private void initCell(int column, int line) {
            if (bytes[column] == (bytes[line])) {
                matrix[column][line] = 1;
            } else {
                matrix[column][line] = 0;
            }
        }

        synchronized void getBiggestSequence() {
            for (int i = 0; i < matrix.length - 1; i++) {
                for (int j = i + 1; j < matrix[i].length - 1; j++) {
                    findSequence(i, j);
                }
            }
        }

        private void findSequence(int column, int line) {
            if (matrix[column][line] == 1) {
                int last = getLastIndex(column, line);
                if (additionalSequence.size() > sequence.size()) {
                    changeSequence(column, last);
                }
                additionalSequence.clear();
            }
        }

        private int getLastIndex(int column, int line) {
            int firstColumnIndex = column;
            int firstLineIndex = line;
            while (matrix[firstColumnIndex][firstLineIndex] == 1) {
                additionalSequence.add(bytes[firstColumnIndex]);
                if (matrix.length - 1 == firstColumnIndex || matrix.length - 1 == firstLineIndex) {
                    break;
                }
                firstColumnIndex++;
                firstLineIndex++;
            }
            return firstColumnIndex;
        }

        private void changeSequence(int first, int last) {
            transferCurrentStatusInformation(first, last);
            sequence.clear();
            sequence = getByteSequenceByIndexes(first, last);
            firstOccurrenceIndex = first;
            secondOccurrenceIndex = last;
            getSecondIndexOfSequence();
        }

        private void transferCurrentStatusInformation(int first, int last) {
            try {
                exchanger.exchange("first " + first);
                exchanger.exchange("last " + last);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void getSecondIndexOfSequence() {
            String sequenceWithoutDuplicates = removeDuplicates(getSequence());
            String sequence = getSequence();
            secondOccurrenceIndex = firstOccurrenceIndex + sequence.indexOf(sequenceWithoutDuplicates, sequence.indexOf(sequenceWithoutDuplicates) + 1);
        }

        private String removeDuplicates(String input) {
            String result = "";
            for (int i = 0; i < input.length(); i++) {
                if (!result.contains(String.valueOf(input.charAt(i)))) {
                    result += String.valueOf(input.charAt(i));
                }
            }
            return result;
        }

        private List<Byte> getByteSequenceByIndexes(int first, int last) {
            List<Byte> biggestSequence = new ArrayList<>();
            for (int i = first; i <= last; i++) {
                biggestSequence.add(bytes[i]);
            }
            return biggestSequence;
        }

        int getFirstOccurrenceIndex() {
            return firstOccurrenceIndex;
        }

        int getSecondOccurrenceIndex() {
            return secondOccurrenceIndex;
        }
    }
}
