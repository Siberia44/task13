package task6;

import task4.entity.Beer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.zip.GZIPOutputStream;

public class SerialisationTest {
    private static final String SERIALIZE_PRODUCT_FILE_NAME_PATH = "test1File";
    private static final String GZIP_SERIALIZE_PRODUCT_FILE_NAME_PATH = "test2File";
    private static final int NUMBER_OF_ELEMENT_TO_ADD_INTO_MAP = 6;

    private Map<Integer, Beer> map;

    public static void main(String[] args) throws IOException {

        SerialisationTest test = new SerialisationTest();
        test.setMap(test.fill());

        test.serializeProduct();
        test.gZIPSerializeProduct();

    }

    public Map<Integer, Beer> getMap() {
        return map;
    }

    public void setMap(Map<Integer, Beer> map) {
        this.map = map;
    }

    private void serializeProduct() {
        System.out.println("Length Serialize start = " + new File(SERIALIZE_PRODUCT_FILE_NAME_PATH).length());
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SERIALIZE_PRODUCT_FILE_NAME_PATH))) {
            for (int index = 0; index < 10; index++) {
                oos.writeObject(map);
            }
        } catch (IOException e) {
            System.err.println("Err Serialize");
        }
        System.out.println("Length Serialize end = " + new File(SERIALIZE_PRODUCT_FILE_NAME_PATH).length());
        new File(SERIALIZE_PRODUCT_FILE_NAME_PATH).delete();
    }

    private void gZIPSerializeProduct() {
        System.out.println("Length GZIPSerialize start = " + new File(GZIP_SERIALIZE_PRODUCT_FILE_NAME_PATH).length());
        try (ObjectOutputStream oos = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(GZIP_SERIALIZE_PRODUCT_FILE_NAME_PATH)))) {
            for (int index = 0; index < 10; index++) {
                oos.writeObject(map);
            }
        } catch (IOException e) {
            System.err.println("Err GZIPSerialize");
        }
        System.out.println("GZIPLength Serialize end = " + new File(GZIP_SERIALIZE_PRODUCT_FILE_NAME_PATH).length());
        new File(GZIP_SERIALIZE_PRODUCT_FILE_NAME_PATH).delete();
    }

    private Map<Integer, Beer> fill() {
        Map<Integer, Beer> map = new HashMap<>();
        for (int index = 1; index < NUMBER_OF_ELEMENT_TO_ADD_INTO_MAP; index++) {
            Beer beer = new Beer();
            beer.setId(index);
            beer.setCost(index * 100);
            beer.setCountry(String.valueOf(new Random().nextInt(100)) + index);
            map.put(beer.getId(), beer);
        }
        return map;
    }
}
