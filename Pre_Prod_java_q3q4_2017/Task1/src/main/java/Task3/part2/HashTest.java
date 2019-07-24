package Task3.part2;

import Task1.part1.Beer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class HashTest {
    public static void main(String[] args) {
        HashMap<LengthHashCode, Beer> hashMap1 = new HashMap<>();
        HashMap<CharsHashCode, Beer> hashMap2 = new HashMap<>();
        LinkedHashMap<LengthHashCode, Beer> linkedHashMap1 = new LinkedHashMap<>();
        LinkedHashMap<CharsHashCode, Beer> linkedHashMap2 = new LinkedHashMap<>();

        hashMap1.put(new LengthHashCode("absd"), new Beer("Hoegarden", 4, "Germany"));
        hashMap1.put(new LengthHashCode("zxcv"), new Beer("Radler", 5, "Ukraine"));
        hashMap1.put(new LengthHashCode("fsfs"), new Beer("Leffe", 6, "Poland"));
        hashMap1.put(new LengthHashCode("hasd"), new Beer("Faxe", 7, "Denmark"));

        hashMap2.put(new CharsHashCode("absd"), new Beer("Faxe", 7, "Denmark"));
        hashMap2.put(new CharsHashCode("zxcv"), new Beer("Radler", 5, "Ukraine"));
        hashMap2.put(new CharsHashCode("zsfs"), new Beer("Leffe", 6, "Poland"));
        hashMap2.put(new CharsHashCode("hasd"), new Beer("Faxe", 7, "Denmark"));

        linkedHashMap1.put(new LengthHashCode("absd"), new Beer("Faxe", 7, "Denmark"));
        linkedHashMap1.put(new LengthHashCode("zxcv"), new Beer("Radler", 5, "Ukraine"));
        linkedHashMap1.put(new LengthHashCode("fsfs"), new Beer("Leffe", 6, "Poland"));
        linkedHashMap1.put(new LengthHashCode("hasd"), new Beer("Faxe", 7, "Denmark"));

        linkedHashMap2.put(new CharsHashCode("absd"), new Beer("Faxe", 7, "Denmark"));
        linkedHashMap2.put(new CharsHashCode("zxcv"), new Beer("Radler", 5, "Ukraine"));
        linkedHashMap2.put(new CharsHashCode("zsfs"), new Beer("Leffe", 6, "Poland"));
        linkedHashMap2.put(new CharsHashCode("hasd"), new Beer("Faxe", 7, "Denmark"));


        // sout hashMap1
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~HashMap1");
        Iterator iterator1 = hashMap1.entrySet().iterator();
        while (iterator1.hasNext()) {
            Map.Entry pair = (Map.Entry) iterator1.next();
            System.out.println(pair.getKey() + "   " + pair.getKey().hashCode() + " = " + pair.getValue());
        }
        // sout hashMap2
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~HashMap2");
        Iterator iterator2 = hashMap2.entrySet().iterator();
        while (iterator2.hasNext()) {
            Map.Entry pair = (Map.Entry) iterator2.next();
            System.out.println(pair.getKey() + "   " + pair.getKey().hashCode() + " = " + pair.getValue());
        }

        // sout LinkedHashMap1
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~LinkedHashMap1");
        Iterator iterator3 = linkedHashMap1.entrySet().iterator();
        while (iterator3.hasNext()) {
            Map.Entry pair = (Map.Entry) iterator3.next();
            System.out.println(pair.getKey() + "   " + pair.getKey().hashCode() + " = " + pair.getValue());
        }
        // sout LinkedHashMap2
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~LinkedHashMap2");
        Iterator iterator4 = linkedHashMap2.entrySet().iterator();
        while (iterator4.hasNext()) {
            Map.Entry pair = (Map.Entry) iterator4.next();
            System.out.println(pair.getKey() + "   " + pair.getKey().hashCode() + " = " + pair.getValue());
        }
    }
}
