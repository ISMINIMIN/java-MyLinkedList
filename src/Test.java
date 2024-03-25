import mylist.MyLinkedList;
import mylist.MyList;

import java.util.Iterator;

public class Test {
    public static void main(String[] args) {
        MyList<Integer> list = new MyLinkedList<>();

        list.add(100);
        list.add(200);
        list.add(300);
        System.out.println("[삽입] " + list);

        list.add(0, 50);
        list.add(2, 150);
        System.out.println("[삽입(인덱스)] " + list);

        list.delete(list.size()-1);
        System.out.println("[삭제] " + list);

        System.out.println("[조회] " + list.get(0));

        Iterator<Integer> iterator = list.iterator();
        System.out.println("[순회]");
        while(iterator.hasNext()) {
            System.out.println(" - value : " + iterator.next());
        }
    }
}
