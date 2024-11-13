package sort;

import java.util.Arrays;
import java.util.Comparator;

public class sortTest {

    public static void main(String[] args) {
        Integer[] nums = {10, 50, 5, 6, 1};

        System.out.println(Arrays.toString(nums));

        // 자바 특성상 Comparator 안에는 기본 자료형을 넣을 수 없다.
        Arrays.sort(nums, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                // 오름차순 정렬
                // return o1-o2;
                // 내림차순 정렬
                return o2 - o1;
            }
        });

        // 위 구조가 어려우면 좀 더 쉬운 구조로 하자
        // 내림차순 정렬
        Arrays.sort(nums, Comparator.reverseOrder());
        // 람다식 사용
        Arrays.sort(nums, (o1, o2) -> o2 - o1);
        // 오름차순 정렬
        Arrays.sort(nums, Comparator.naturalOrder());
        // 람다식 사용
        Arrays.sort(nums, (o1, o2) -> o1 - o2);
        System.out.println(Arrays.toString(nums));

        // 객체를 정렬해보자
        Item[] items = new Item[5];
        // items안에 있는 데이터는 아래에서 만든 Item의 주소가 들어가 있는 것이다. (Call by reference)
        // Call by reference에서는 바뀌면 안돼는 값이 수정될 수도 있기 때문에 조심해야 한다.
        System.out.println(Arrays.toString(items));
        items[0] = new Item(10, 100, 1);
        items[1] = new Item(20, 200, 2);
        items[2] = new Item(50, 100, 3);
        items[3] = new Item(1, 50, 4);
        items[4] = new Item(3, 1, 5);
        System.out.println(Arrays.toString(items));
        // Arrays.sort(items); // 이렇게 하면 Exception을 날린다.(기준이 없어서 정렬하지 못함)
        Arrays.sort(items); // Comparable 사용
        // 다만, Comparator를 사용하면 Comparable은 무시가 된다. -> Comparator는 Comparable과 다른 정렬을 할 때 사용
        // Comparable이 기본, Comparator는 뒤집어 씌울 때 사용
        // 자바에서는 객체를 정렬하는 게 두 가지 -> Comparator, Comparable
        // 이 방식은 보기 불편하다.
//        Arrays.sort(items, new Comparator<Item>() {
//
//            @Override
//            public int compare(Item o1, Item o2) {
//                // value 기준
//                // return o1.value - o2.value;
//                // weight 기준
//                //return o1.weight - o2.weight;
//                // 겹치는 값이 있을 경우,
//                int resultWeight = o1.weight - o2.weight;
//                if (resultWeight == 0) {
//                    return o2.value - o1.value;
//                } else {
//                    return resultWeight;
//                }
//            }
//        });

        // Comparator와 Comparable을 같이 쓰면 욕먹음;; -> Getter Setter를 사용
        // 말하는 것처럼 코드를 작성할 수 있다.(정렬 가능)
        // 내림차순을 하려면 reversed()를 하면되지만, 이렇면 코드를 복잡해진다.
        Arrays.sort(items, Comparator.comparing(Item::getValue).reversed().thenComparing(Item::getWeight));

        System.out.println(Arrays.toString(items));
    }
}


// 머리가 아파자는 것은 객체를 정렬할 때이다.
// Comparable은 아이템을 꾸미는 용도로 사용하고 comparator는 그렇지 않다.
class Item implements Comparable<Item> {
    int value;
    int weight;
    int order;
    public Item(int value, int weight, int order) {
        this.value = value;
        this.weight = weight;
        this.order = order;
    }

    @Override
    public String toString() {
        return "[v=" + value + ", w=" + weight + ", o=" + order + "]";
    }

    @Override
    public int compareTo(Item o2) {
        int resultWeight = weight - o2.weight;
        if (resultWeight == 0) {
            return o2.value - value;
        } else {
            return resultWeight;
        }
    }

    // Comparator와 Comparable을 같이 쓰면 욕먹음;; -> Getter Setter를 사용

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}