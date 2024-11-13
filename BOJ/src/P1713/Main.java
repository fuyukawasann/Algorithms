package P1713;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int N;
    static int K;
    static int[] inputs;
    static Person[] people;

    public static void main(String[] args) throws Exception{

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();

        inputs = new int[K];
        people = new Person[101];

        List<Person> list = new ArrayList<>();
        for(int k = 0; k < K; k++){
            int num = sc.nextInt();
            if (people[num] == null) {
                people[num] = new Person(num, 0, 0);
            }
            // 이 방식이 중요하다.
            if(people[num].count != 0) { // 사진틀에 존재
                people[num].count++;
            }
            else {
                // 사진틀에 없음
                // 자리가 없을 때
                if(list.size() == N) {
                    Collections.sort(list);
                    Person p = list.remove(N - 1);
                    p.count = 0;
                    p.timestamp = 0;
                }
                people[num].count = 1;
                people[num].timeStamp = k;
                list.add(people[num]);
            }
        }
    }
}

class Person {
    int num;
    int count;
    int timestamp;

    public Person(int num, int count, int timestamp) {
        this.num = num;
        this.count = count;
        this.timestamp = timestamp;
    }

    @Override
    public int compareTo(Person o2) {
        int comp1 = o2.count - count;
        if(comp1 == 0) {
            return o2.timestamp - timestamp;
        }
        return comp1;
    }

    @Override
    public String toString() {
        return "[num=" + num + ", count=" + count + ", timestamp=" + timestamp + "]";
    }
}
