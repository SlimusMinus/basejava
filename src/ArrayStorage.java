import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int sizeStorage;

    void clear() {
        Arrays.fill(storage, 0, sizeStorage, null);
        sizeStorage = 0;
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
        sizeStorage = size();
    }

    Resume get(String uuid) {
        for (int i = 0; i < sizeStorage; i++) {
            if (storage[i].uuid.equals(uuid))
                return storage[i];
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = storage[sizeStorage - 1];
                storage[sizeStorage - 1] = null;
                sizeStorage--;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, sizeStorage);
    }

    int size() {
        return (int) Arrays.stream(storage).filter(Objects::nonNull).count();
    }
}
