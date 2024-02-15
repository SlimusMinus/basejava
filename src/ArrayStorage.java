import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10];

    void clear() {
        Arrays.fill(storage, null);
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if(storage[i] == null){
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        try {
            for (var item : storage) {
                if (item.uuid.equals(uuid))
                    return item;
            }
        }
        catch (NullPointerException nullPointerException){
            System.out.println("Database haven't this id");
        }
        return null;
    }

    void delete(String uuid) {
        int index = 0;
        for (int i = 0; i < storage.length; i++) {
            if(storage[i].uuid.equals(uuid)){
                index = i;
                break;
            }

        }

        for (int i = index; i < storage.length-1; i++)
            storage[i] = storage[i+1];

        storage = Arrays.copyOf(storage, storage.length - 1);
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return storage;
    }

    int size() {
        return storage.length;
    }
}
