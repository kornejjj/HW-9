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

    public void add(E value) {
        if (array.length == pointer) {
            resize();
        }
        array[pointer] = value;
        pointer++;
    }

    public E remove(int index) {
        if (index >= pointer || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (index == pointer - 1) {
            E tmp = array[index];
            array[index] = null;

            pointer--;
            return tmp;
        }

        if (pointer - 1 - index >= 0) {
            System.arraycopy(array, index + 1, array, index, pointer - 1 - index);
            pointer--;
            return (E) this;
        }

        return (E) this;
    }

    public void clear() {
        this.array = (E[]) Array.newInstance(clazz, 10);
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
        E[] newArray = (E[]) Array.newInstance(clazz, newSize);
        if (pointer >= 0)
            System.arraycopy(array, 0, newArray, 0, pointer);
        array = newArray;
    }
}
