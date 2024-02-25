import com.urize.webapp.model.Resume;
import com.urize.webapp.storage.SortedArrayStorage;
import com.urize.webapp.storage.Storage;

/**
 * Test for your com.urize.webapp.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage{
    private static final Storage ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        final Resume r1 = new Resume();
        r1.setUuid("uuid333");
        final Resume r2 = new Resume();
        r2.setUuid("uuid112");
        final Resume r3 = new Resume();
        r3.setUuid("uuid404");
        final Resume r4 = new Resume();
        r4.setUuid("uuid125");
        final Resume r5 = new Resume();
        r5.setUuid("uuid307");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);
        ARRAY_STORAGE.save(r4);
        ARRAY_STORAGE.save(r5);
        ARRAY_STORAGE.save(r1);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());
        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        printAll();
        ARRAY_STORAGE.delete(r3.getUuid());

        ARRAY_STORAGE.update(r2);
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());

    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
