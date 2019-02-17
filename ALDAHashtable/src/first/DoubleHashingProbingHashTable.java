package first;

/*
 * Ellen Dahlgren, elda7225
 * Samarbetat med Adam Jacobsson, adja6724
 */

public class DoubleHashingProbingHashTable<T> extends ProbingHashTable<T> {

    /*
     * Denna metod ska skrivas klart. Den ska använda bokens förslag på andra
     * hashalgoritm.
     *
     * TODO: algoritmen måste finnas med här för dem som inte har boken.
     */
    @Override
    protected int findPos(T x) {

        int currentPos = myhash(x);

        while (continueProbing(currentPos, x)) {
            currentPos+=myHash2(x);
            if (currentPos >= capacity())
                currentPos -= capacity();
        }

        return currentPos;
    }

    private int myHash2(T x){
        int hashVal = (smallerPrimeThanCapacity()-(x.hashCode()%smallerPrimeThanCapacity()));
        if(hashVal<0){
            hashVal+= smallerPrimeThanCapacity();
        }
        return hashVal;
    }


    /*
     * Denna metod ger ett primtal mindre än tabellens storlek. Detta primtal ska
     * användas i metoden ovan.
     */
    protected int smallerPrimeThanCapacity() {
        int n = capacity() - 2;
        while (!isPrime(n)) {
            n -= 2;
        }
        return n;
    }

}

