package task4;

public class MemoryManager <T> implements Manager{

    public T[] extendArray(T[] array) {
        T[] newArray = (T[]) new Object[array.length + 10];

        System.arraycopy(array, 0, newArray, 0, array.length);

        return newArray;
    }

    public boolean isFull(T[] array){

        for(T x : array){

            if(x == null){
                return false;
            }
        }

        return true;
    }
}
