package com.pbattles.platformTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Nazar_Sheremeta on 5/14/14.
 */
public class Sandbox {

    interface Validator<T>{
        public boolean isValid(T elem);
    }

    class ValidatingIterator<T> implements Iterator<T> {
      private Iterator<T> elementIterator;
      private Validator<T> elementValidator;
      private T currentValidElem;


        ValidatingIterator(Iterator<T> elementIterator, Validator<T> elementValidator) {
            this.elementIterator = elementIterator;
            this.elementValidator = elementValidator;
            currentValidElem = getNextValidElement();
        }

        @Override
        public boolean hasNext() {
            return currentValidElem != null;
        }

        @Override
        public T next() {
            T result = currentValidElem;
            currentValidElem = getNextValidElement();
            return result;
        }

        private T getNextValidElement() {
            while(elementIterator.hasNext()){
                T current = elementIterator.next();
                if(elementValidator.isValid(current)) return current;
            }
            return null;
        }

        @Override
        public void remove() {
            elementIterator.remove();
        }

    }

    class AboveZeroValidator implements Validator<Integer>{

        @Override
        public boolean isValid(Integer elem) {
          return elem > 0;
        }
    }

    public  void run() {
        List list = new ArrayList();
        list.addAll(Arrays.asList(1,2,3,4,-6,-3,-13,4,0,15));
        Iterator iterator = list.iterator();
        Validator validator = new AboveZeroValidator();
        ValidatingIterator megaIterator = new ValidatingIterator<Integer>(iterator,validator);
            while(megaIterator.hasNext()){
                System.out.println(megaIterator.next());
            }
    }


    public static void main(String[] args) {
        Sandbox main = new Sandbox();
        main.run();
    }
}
