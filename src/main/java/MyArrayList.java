import java.lang.reflect.Array;
import java.util.Arrays;

public class MyArrayList<E> {
    private E[] array;
    private int pointer;

    public MyArrayList() {
        this.array = (E[]) new Object[10];
        this.pointer = 0;
    }

    public void add(E value) {
        if (array.length == pointer) {
            resize();
        }
        array[pointer] = value;
        pointer++;
    }

    public E remove(int index) {
        E tmp = null;
        if (index >= pointer || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (index == pointer - 1) {
            tmp = array[index];
            array[index] = null;

            pointer--;
            return tmp;
        }

        if (pointer - 1 - index >= 0) {
            System.arraycopy(array, index + 1, array, index, pointer - 1 - index);
            pointer--;
        }

        return tmp;
    }

    public void clear() {
        this.array = (E[]) new Object[10];
        this.pointer = 0;
    }

    public int size() {
        return pointer;
    }

    public E get(int index) {
        E result;
        try {
            result = array[index];
        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException("Index out of range of MyArrayList", e);
        }
        return result;
    }

    private void resize() {
        int newSize = (int) (pointer * 1.5);
        E[] newArray = Arrays.copyOf(array, newSize);
        if (pointer >= 0)
            System.arraycopy(array, 0, newArray, 0, pointer);
        array = newArray;
    }
}
