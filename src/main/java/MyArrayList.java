import java.lang.reflect.Array;

public class MyArrayList<E> {
    private E[] array;
    private final Class<E> clazz;
    private int pointer;

    public MyArrayList(Class<E> clazz) {
        this.array = (E[]) Array.newInstance(clazz, 10);
        this.pointer = 0;
        this.clazz = clazz;
    }

    public MyArrayList(E[] array) {
        this.array = array.clone();
        this.pointer = array.length;
        this.clazz = (Class<E>) array[0].getClass();
    }

    public MyArrayList add(E value) {
        if (array.length == pointer) {
            resize();
        }
        array[pointer] = value;
        pointer++;
        return this;
    }

    public MyArrayList remove(int index) {
        //if index is bigger than count of element in array or index < 0
        if (index >= pointer || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        //if index is the last element in array of objects
        if (index == pointer - 1) {
            array[index] = null;
            pointer--;
            return this;
        }

        if (pointer - 1 - index >= 0) {
            System.arraycopy(array, index + 1, array, index, pointer - 1 - index);
            pointer--;
            return this;
        }

        return this;
    }

    public MyArrayList clear() {
        this.array = (E[]) Array.newInstance(clazz, 10);
        this.pointer = 0;
        return this;
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
        E[] newArray = (E[]) Array.newInstance(clazz, newSize);
        if (pointer >= 0)
            System.arraycopy(array, 0, newArray, 0, pointer);
        array = newArray;
    }
}
