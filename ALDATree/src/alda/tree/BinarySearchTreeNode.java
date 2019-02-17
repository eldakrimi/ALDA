
package alda.tree;
/*
Ellen Dahlgren, elda7225
 */

/**
 *
 * Detta är den enda av de tre klasserna ni ska göra några ändringar i. (Om ni
 * inte vill lägga till fler testfall.) Det är också den enda av klasserna ni
 * ska lämna in. Glöm inte att namn och användarnamn ska stå i en kommentar
 * högst upp, och att paketdeklarationen måste plockas bort vid inlämningen för
 * att koden ska gå igenom de automatiska testerna.
 *
 * De ändringar som är tillåtna är begränsade av följande:
 * <ul>
 * <li>Ni får INTE byta namn på klassen.
 * <li>Ni får INTE lägga till några fler instansvariabler.
 * <li>Ni får INTE lägga till några statiska variabler.
 * <li>Ni får INTE använda några loopar någonstans. Detta gäller också alterntiv
 * till loopar, så som strömmar.
 * <li>Ni FÅR lägga till fler metoder, dessa ska då vara privata.
 * <li>Ni får INTE låta NÅGON metod ta en parameter av typen
 * BinarySearchTreeNode. Enbart den generiska typen (T eller vad ni väljer att
 * kalla den), String, StringBuilder, StringBuffer, samt primitiva typer är
 * tillåtna.
 * </ul>
 *
 * @author henrikbe
 *
 * @param <T>
 */
@SuppressWarnings("unused") // Denna rad ska plockas bort. Den finns här
// tillfälligt för att vi inte ska tro att det är
// fel i koden. Varningar ska normalt inte döljas på
// detta sätt, de är (oftast) fel som ska fixas.
public class BinarySearchTreeNode<T extends Comparable<T>> {

    private T data;
    private BinarySearchTreeNode<T> left;
    private BinarySearchTreeNode<T> right;

    public BinarySearchTreeNode(T data) {
        this.data = data;

    }

    public boolean add(T data) {
        int compareResault = this.data.compareTo(data);

        if(compareResault<0){
            if(right !=null){
                return right.add(data);
            }else{
                right  = new BinarySearchTreeNode<>(data);
                return true;
            }
        }
        else if(compareResault>0){
            if(left != null){
                return left.add(data);
            }else{

                left  = new BinarySearchTreeNode<>(data);
                return true;
            }
        }
        return false;
    }

    private T findMin() {
        if (left != null) {
            return left.findMin();
        } else {
            return data;
        }
    }



    public BinarySearchTreeNode<T> remove(T data) {

        int compareValue=this.data.compareTo(data);


        if(compareValue>0){
            if(left != null){
                left = left.remove(data);
            }
        }
        else if(compareValue<0){
            if(right != null){
                right = right.remove(data);
            }
        }
        else if(this.right != null && this.left !=null){
            this.data = right.findMin();
            right = right.remove(this.data);
        }

        else {
            if(right!=null){
                return right;
            }else{
                return left;
            }
        }
        return this;
    }

    public boolean contains(T data) {

        int compareValue=this.data.compareTo(data);
        if(compareValue==0){
            return true;
        }

        else if(compareValue>0&& left !=null){
            return left.contains(data);

        }
        else if(compareValue>0&& left==null){
            return false;
        }
        else if(compareValue<0&& right !=null){
            return right.contains(data);
        }
        else if(compareValue<0&& right ==null){
            return false;
        }

        else

            return false;
    }

    public int size() {

        int count = 1;
        if(left!=null){
            count+=left.size();
        }
        if(right!= null){
            count+=right.size();
        }
        return count;

    }


    public int depth() {

        int leftDepth =0;
        int rightDepth=0;

        if(left== null&& right==null){
            return 0;
        }

        if(left != null) {
            leftDepth = left.depth();
        }

        if(right != null) {
            rightDepth = right.depth();
        }

        if(leftDepth>rightDepth){
            return leftDepth+1;
        }else{
            return rightDepth+1;
        }


    }

    public String toString() {

        StringBuilder str = new StringBuilder("");

        if (data == null) {
            return null;
        }
        if (left != null) {
            str.append(left.toString() + ", ");
        }

        str.append(data.toString());

        if (right != null) {
            str.append(", " + right.toString());
        }
        return str.toString();

    }

}
