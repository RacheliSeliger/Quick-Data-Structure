package il.co.lird.FS133.Projects.QuickDataStructure;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

class QuickDataStructureTest {
    QuickPushDataStructure<Integer>  QuickListInt = new QuickPushDataStructure<>();
    QuickPushDataStructure<Student> QuickListStudent = new QuickPushDataStructure<>( new StudentComparator());
    QuickPopDataStructure<Integer> IntQuickList = new QuickPopDataStructure<>();
    QuickPopDataStructure<Student> StudentQuickList = new QuickPopDataStructure<>(new StudentComparator());

    ArrayList<Thread> pushThread = new ArrayList<>();
    ArrayList<Thread> popThread = new ArrayList<>();
    Student student1 = null;
    Student student2 = null;
    Student student3 = null;
    Student student4 = null;

    public class StudentComparator implements Comparator<Student>{
       @Override
       public int compare(Student student1, Student student2) {
           return student1.age - student2.age ;
       }
   }
    public static class Student implements Comparable<Student> {
        int age;
        String name;
        public Student(int age, String name)
        {
            this.age = age;
            this.name = name;
        }

        @Override
        public int compareTo(Student student) {
            if (this.age < student.age) {
                return -1;
            } else if (this.age > student.age) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    @BeforeEach
    void setUp()
    {
        student1 = new Student(25, "racheli");
        student2 = new Student(32, "shani");
        student3 = new Student(29, "lin");
        student4 = new Student(32, "kostiya");

        QuickListStudent.push(student1);
        QuickListStudent.push(student2);
        QuickListStudent.push(student3);

        IntQuickList.push(3);
        IntQuickList.push(6);
        IntQuickList.push(7);
        IntQuickList.push(2);
        IntQuickList.push(4);

        StudentQuickList.push(student1);
        StudentQuickList.push(student2);
        StudentQuickList.push(student3);
    }

    @Test
    void MutliThreadTest() throws InterruptedException {

        class PushThreadSafeRandom implements Runnable {
            @Override
            public void run() {
                Random rand = new Random();
                int pushVal = rand.nextInt(1000);
                QuickListInt.push(pushVal);
                System.out.println("push : " + pushVal);
            }
        }
        class PopThreadSafeRandom implements Runnable {
            @Override
            public void run() {
                int value = QuickListInt.pop();
                System.out.println(" pop : " + value);
            }
        }

        for (int i = 0; i < 10; ++i) {
            Thread thread = new Thread(new PushThreadSafeRandom());
            pushThread.add(thread);
        }


        for (int i = 0; i < 10; ++i) {
            Thread thread = new Thread(new PopThreadSafeRandom());
            popThread.add(thread);
        }

        for (Thread thread : pushThread) {
            thread.start();
        }

        for (Thread thread : popThread) {
            thread.start();
        }

        for (Thread thread : pushThread) {
            thread.join();
        }
        for (Thread thread : popThread) {
            thread.join();
        }
    }
    @Test
    void push() {

        assertEquals(3, QuickListStudent.count());

        assertEquals(5, IntQuickList.count());

        assertEquals(3, StudentQuickList.count());

    }


    @Test
    void pop() {

        QuickListStudent.push(student4);

        assertEquals(QuickListStudent.pop(),student2);
        assertEquals(QuickListStudent.pop(),student4);
        assertEquals(QuickListStudent.pop(),student3);
        assertEquals(QuickListStudent.pop(),student1);

        assertTrue(QuickListStudent.isEmpty());


        assertEquals(IntQuickList.pop(),7 );
        assertEquals(IntQuickList.pop(),6 );
        assertEquals(IntQuickList.pop(),4 );
        assertEquals(IntQuickList.pop(),3 );
        assertEquals(IntQuickList.pop(),2 );


        assertEquals(StudentQuickList.pop(),student2 );
        assertEquals(StudentQuickList.pop(),student3 );
        assertEquals(StudentQuickList.pop(),student1 );

    }

}