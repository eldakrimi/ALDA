package alda.heap;
/*
 * Ellen Dahlgren, elda7225
 * Samarbetat med Adam Jacobsson, adja6724
 */


public class DHeap<AnyType extends Comparable<? super AnyType>>
{

    private int dimension;

    public DHeap( )
    {
        this(2 );
    }

    public DHeap( int dimensions )
    {

        if(dimensions<2){
            throw new IllegalArgumentException();
        }

        currentSize = 0;

        this.dimension = dimensions;
        array = (AnyType[]) new Comparable[ dimensions + 1 ];
    }

    public DHeap( AnyType [ ] items )
    {
        currentSize = items.length;
        array = (AnyType[]) new Comparable[ ( currentSize + 2 ) * 11 / 10 ];

        int i = 1;
        for( AnyType item : items )
            array[ i++ ] = item;
        buildHeap( );
    }

    public int parentIndex(int i){

        if(i<2){
            throw new IllegalArgumentException();
        }
        return  (i-2+dimension)/dimension;
    }

    public int firstChildIndex(int i){
        if(i<1){
            throw new IllegalArgumentException();
        }
        return (dimension*i)+2-dimension;
    }

    public int size(){
        return currentSize;
    }

    public AnyType get(int index){

        return array[index];
    }


    public void insert( AnyType x )
    {
        if( currentSize == array.length - 1 )
            enlargeArray( array.length * 2 + 1 );

        int hole = ++currentSize;
        for( array[ 0 ] = x; hole >1&&x.compareTo( array[ parentIndex(hole) ] ) < 0; hole = parentIndex(hole) )

            array[ hole ] = array[ parentIndex(hole)];

        array[ hole ] = x;
    }

    private void enlargeArray( int newSize )
    {
        AnyType [] old = array;
        array = (AnyType []) new Comparable[ newSize ];
        for( int i = 0; i < old.length; i++ )
            array[ i ] = old[ i ];
    }


    public AnyType findMin( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return array[ 1 ];
    }


    public AnyType deleteMin( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );

        AnyType minItem = findMin( );
        array[ 1 ] = array[ currentSize-- ];
        percolateDown( 1 );

        return minItem;
    }


    private void buildHeap( )
    {
        for( int i = currentSize / 2; i > 0; i-- )
            percolateDown( i );
    }


    public boolean isEmpty( )
    {
        return currentSize == 0;
    }


    public void makeEmpty( )
    {
        currentSize = 0;
    }


    private int currentSize;
    private AnyType [ ] array;

    private void percolateDown( int hole )
    {

        AnyType tmp = array[ hole ];
        for(; indexOfSmallestChild(hole) <= currentSize && tmp.compareTo(smallestChild(hole)) >= 0; hole = indexOfSmallestChild(hole))
            array[hole] = smallestChild(hole);
        array[ hole ] = tmp;
    }

    private int indexOfSmallestChild(int parent){

        int firstChildIndex = firstChildIndex(parent);

        if(firstChildIndex > currentSize){
            return currentSize+1;
        }

        int smallestChild = firstChildIndex;

        for(int i =1;i<dimension;i++){
            if(firstChildIndex+i <= currentSize){
                if(array[firstChildIndex+i] !=null  && array[smallestChild].compareTo(array[firstChildIndex+i]) > 0){
                    smallestChild = firstChildIndex+i;
                }
            }
        }
        return smallestChild;
    }
    private AnyType smallestChild(int parent){
        return get(indexOfSmallestChild(parent));
    }


    public static void main( String [ ] args )
    {
        int numItems = 10000;
        DHeap<Integer> h = new DHeap<>( );
        int i = 37;

        for( i = 37; i != 0; i = ( i + 37 ) % numItems )
            h.insert( i );
        for( i = 1; i < numItems; i++ )
            if( h.deleteMin( ) != i )
                System.out.println( "Oops! " + i );
    }
}