package alda.linear;
/*
 * Ellen Dahlgren, elda7225
 * Samarbetat med Adam Jacobsson, adja6724
 */

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyQueue<E> implements ALDAQueue<E> {

    private Node<E> first;
    private Node<E>last;
    private int totalCapacity;
    private int currentSize;

    private static class Node<T>{
        T data;
        Node<T>next;

        public Node(T data) {
            this.data= data;
        }
    }


    public MyQueue( int totalCapacity) {

        if (totalCapacity > 0)
            this.totalCapacity = totalCapacity;
        else
            throw new IllegalArgumentException();
    }

    @Override
    public void add(E element) {
        if(element ==null){
            throw new NullPointerException();
        }
        else if(isFull()){
            throw new IllegalStateException();

        }else {
            if (first == null) {
                first = new Node<E>(element);
                last = first;
            } else {
                last.next = new Node<E>(element);
                last =  last.next;
            }
            currentSize++;
        }
    }

    @Override
    public void addAll(Collection<? extends E> c) {

        for(E e:c){
            add(e);
            currentSize++;
        }
    }

    @Override
    public E remove() {
        if(first == null){
            throw new NoSuchElementException();
        }else {
            E temp = first.data;
            first = first.next;
            currentSize--;
            return temp;

        }
    }

    @Override
    public E peek() {
        if(first == null){
            return null;
        }
        return first.data;
    }

    @Override
    public void clear() {
        first = null;
        last = null;
        currentSize =0;
    }


    @Override
    public int size() {
//        int currentSize =0;
//
//        //dumt att inte ha en current size.
//        for(Node<E> tmp = first; tmp !=null; tmp = tmp.next){
//            currentSize++;
//        }
        return currentSize;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public boolean isFull() {

        return totalCapacity == currentSize;
    }

    @Override
    public int totalCapacity() {
        return totalCapacity;
    }

    @Override
    public int currentCapacity() {

        return totalCapacity -currentSize;
    }

    @Override
    //Ska man tänka såhär istället
    public int discriminate(E o) {

        if(o == null){
            throw new NullPointerException();

        }else{

            int counter  =0;
            Node prev = new Node(o);
            int noOfElements =countNoOfElements(o);
            int foundElements =0;

            for(Node<E> tmp = first; tmp !=null; tmp = tmp.next) {

                if((counter ==currentSize-1)&& tmp.data.equals(o)){
                    foundElements++;
                    return noOfElements;
                }

                if(o.equals(tmp.data)){
                    foundElements++;
                    Node lastTmp = new Node<E>(o);
                    if(tmp ==first){
                        first = first.next;
                    }else {
                        prev.next = tmp.next;
                    }
                    last.next = lastTmp;
                    last = lastTmp;

                }else{
                    prev =tmp;
                }
                counter++;
                if(noOfElements== foundElements ){
                    return noOfElements;
                }
            }
            return noOfElements;
        }

    }


    private int countNoOfElements(E o){
        int noOfElements = 0;
        if(!isEmpty()){
            for(Node<E> tmp = first; tmp !=null; tmp = tmp.next) {
                if(tmp.data.equals(o)) {
                    noOfElements++;
                }
            }
        }
        return noOfElements;
    }


    /*
     För insipration och hjälp har jag använt
     Youtube: "Implementing a Custom Iterator in Java"
     https://youtu.be/arkoC146TfQ
     */

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<E>{
        Node<E> current;

        @Override
        public boolean hasNext() {
            return current != last;
        }

        @Override
        public E next() {

            if(current  == null&&first != null){
                current = first;
                return first.data;
            }
            else if(current != null && current.next != null){

                current = current.next;
                return current.data;
            }
            throw new NoSuchElementException();

        }

    }

    @Override
    public String toString() throws NullPointerException {
        if (currentSize == 0) {
            return "[]";
        } else {
            StringBuilder builder = new StringBuilder("");
            builder.append("[");
            Node<E> temp = first;
            while (temp.next != null) {
                builder.append(temp.data+", ");
                temp = temp.next;
            }
            builder.append("" + temp.data + "]");
            return builder.toString();
        }
    }
}


