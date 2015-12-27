Sorting is common functionality required by many applications.  Questions like: <em>How do we sort in Java?</em> or <em>What should we use as sorting algorithm?</em> need to answered before we can perform any kind of sorting.  Definitely we are not the first ones who require this feature and others have already done it before for us.  So we can simple use the Java API provided to perform sorting.


This article explains the concept of sorting and how to use the Java API to solve this problem and closes with some programming tips. 


All code listed below is available at: <a href="https://github.com/javacreed/sorting-objects-using-comparator" target="_blank">https://github.com/javacreed/sorting-objects-using-comparator</a>.  Most of the examples will not contain the whole code and may omit fragments which are not relevant to the example being discussed.  The readers can download or view all code from the above link.


<h2>Concept behind sorting</h2>


In order to be able to sort elements we need to be able to compare them.  What does this mean?  If you have two things and you need to pick one of them, which one would you pick?  Let's make this more realistic.  If you are not rich and you can choose between &euro;1 and &euro;100, which one would you choose?  I do not know what would you do, but I would go for the hundred.  Why? Because 100 is greater than 1!  I was able to make this decision because I was able to compare the choice at hand.  If instead of numbers I said choose between <code>A</code> and <code>B</code>, where <code>A</code> and <code>B</code> can be anything, would you be able to do an informed decision?  No, it would be pure gamble - a matter of luck.  There is no way to compare <code>A</code> and <code>B</code> without further knowledge. 


In order to be able to sort two elements (or more), you need to be able to compare them.  Similar to the problem discussed above, we need to be able to evaluate elements and then sort them based on this value.  For example, if we need to sort the following list: <code>{8, 5, 9}</code>, we know that <code>5</code> is less than <code>8</code> so these two have to be swapped.  Sorting numbers is very simple and straightforward.  We cannot say the same for any other object (Java and tangible).  For example, if we have a list of boxes, how would we sort them?  If we are sorting by size, we first determine the size of each and then using the size value (a number) as sorting criteria.  So in order to sort any kind of object, all we need to do is to convert the object into a number that we can compare with.  So if we have the following list of objects: <code>{a, b, c, d}</code> (the letters in the previous list is used only to define the object name and has no effect on the sorting) with sorting values <code>{4, 3, 7, 5}</code> respectively, we know that object <code>b</code> should come first while object <code>c</code> should be placed last and so on. 


<h2>How do we implement this in Java?</h2>


Java provides a set of classes and interfaces which we can use to sort lists and arrays.  Most of the following examples will use lists but the same concept can be applied for arrays.  A final example will show this.


Let's start by creating a list of Integers and sort these using the <code>Collections.sort()</code> (<a href="http://java.sun.com/javase/7/docs/api/java/util/Collections.html#sort(java.util.List)" target="_blank">Java Doc</a>) method.  The Collections (<a href="http://java.sun.com/javase/7/docs/api/java/util/Collections.html" target="_blank">Java Doc</a>) class (part of the <a href="http://java.sun.com/javase/7/docs/technotes/guides/collections/index.html" target="_blank">Java Collection Framework</a>) provides a list of static methods which we can use when working with collections such as list, set and the like.  So in a nutshell, we can sort a list by simply calling: <code>java.util.Collections.sort(<em>the list</em>)</code> as shown in the following example: 


<pre>
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Example1 {
  public static void main(String[] args) {
    List&lt;Integer&gt; ints = new ArrayList&lt;Integer&gt;();
    ints.add(4);
    ints.add(3);
    ints.add(7);
    ints.add(5);

    Collections.sort(ints);
    System.out.println(ints);
  }
}
</pre>


The above class creates a list of four integers and, using the collection's sort method, sorts this list (in one line of code) without us having to worry about the sorting algorithm.


Java was able to sort this list because it <em>knows</em> how to compare integers as the <a href="http://java.sun.com/javase/7/docs/api/java/lang/Integer.html" target="_blank">Integer</a> class implements the <a href="http://java.sun.com/javase/7/docs/api/java/lang/Comparable.html" target="_blank">Comparable</a> interface.  Note that, as from Java 1.5, the primitive <code>int</code> value is <a href="http://java.sun.com/j2se/1.5.0/docs/guide/language/autoboxing.html" target="_blank">autoboxed</a> into an <code>Integer</code> (the object wrapper for <code>int</code>) before added to the list.  Thus in order to be able to make use of the collection's sort method, all we need to do is implement the comparable interface by our objects.


Let's take this example one step further and create our own class.  Let's create a list of students and sort them by their grade (which is a number).  The student class will have two fields: name and grade.  In order to be able to use the sort method from the collections class, as we did in the above example, we have to implement the comparable interface and its method, as illustrated below:


<pre>
public class Student1 <span class="highlight">implements Comparable&lt;Student1&gt;</span> {

  private int grade;
  private String name;

  public Student1(String name, int grade) {
    setName(name);
    setGrade(grade);
  }

  @Override
  public int compareTo(Student1 o) {
    return Integer.compare(grade, o.grade);
  }

  @Override
  public String toString() {
    return name + " " + grade;
  }

  <span class="comments">// Getters and setters are removed for brevity</span>
}
</pre>


The <code>compareTo()</code> method should return a negative integer, zero, or a positive integer if this student's grade is less than, equal to, or greater than the specified/given student's grade. The simplest way to do it is to subtract the grades of these students.  Why and how would that help sorting?  If this student's grade is larger than the grade of the given student, then a positive number is returned, while if the grades are equal, zero is returned.  This follows the method's contract/specifications.  Note that our job is to provide information to the sorting algorithm and not sorting the objects ourselves. 


The <code>toString()</code> method was overridden so that we can see readable results when we print the list.  Otherwise the output will look something like: <code>[Student1@19821f, Student1@addbf1, Student1@42e816, Student1@9304b1]</code> (instead of: <code>[Joe Vella 47, Paul Galea 52, Albert Attard 65, Mary Borg 93]</code>) which is not readable. 


Let's modify the previous class and sort a list of students.


<pre>
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Example2 {
  public static void main(String[] args) {
    List&lt;Student1&gt; students = new ArrayList&lt;Student1&gt;();
    students.add(new Student1("Albert Attard", 65));
    students.add(new Student1("Mary Borg", 93));
    students.add(new Student1("Joe Vella", 47));
    students.add(new Student1("Paul Galea", 52));

    Collections.sort(students);
    System.out.println(students);
  }
}
</pre>


<h2>Making full use of the API</h2>


How can we sort the student by both name and grade?  In order to achieve this using the comparable interface (above) we have to add more fields to the student class in order to be able to determine which field we're sorting on.  <u>The following approach is not recommended and included here only for demonstration and comparisons purposes</u>.


<pre>
public class Student2 implements Comparable&lt;Student2&gt; {

  private int grade;
  private String name;
  <span class="highlight">private int sortBy;</span>

  public Student2(String name, int grade, <span class="highlight">int sortBy</span>) {
    setName(name);
    setGrade(grade);
    <span class="highlight">setSortBy(sortBy);</span>
  }

  <span class="highlight">@Override
  public int compareTo(Student2 o) {
    switch (sortBy) {
    case 1: // Sort by name
      return name.compareTo(o.name);
    default: // Sort by grade by default
      return grade - o.grade;
    }
  }</span>

  // Getters, setters and toString methods are removed for brevity
}
</pre>


We had to add a new field in the student class, called <code>sortBy</code>, which we have to set to 1 in order to sort the students by their name.  Any other value will sort the students by their grade as illustrated in the following example.  Ideally we use enums instead of <code>int</code> as the data type of the <code>sortBy</code> field which will prevent illegal values.


<pre>
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Example3 {
  public static void main(String[] args) {
    List&lt;Student2&gt; students = new ArrayList&lt;Student2&gt;();
    students.add(new Student2("Albert Attard", 65<span class="highlight">, 1</span>));
    students.add(new Student2("Mary Borg", 93<span class="highlight">, 1</span>));
    students.add(new Student2("Joe Vella", 47<span class="highlight">, 1</span>));
    students.add(new Student2("Paul Galea", 52<span class="highlight">, 1</span>));
    
    <span class="comments">// Sort by name</span>
    Collections.sort(students);
    System.out.println(students);
    
    <span class="highlight">// Change these to sort them by grade
    for(Student2 student:students){
      student.setSortBy(0);
    }
    Collections.sort(students);
    System.out.println(students);</span>
  }
}
</pre>


This approach has two main pitfalls.  First, the student class has to include fields and methods that are not related to the student object.  Sorting properties are not student properties.  The other issue is that for any new fields or sorting order we have to change the student class and emend the <code>compareTo()</code> method accordingly, adding complexity to a method making it harder to maintain.  Also, the sorting method is bound with the student class and cannot be used alone (apart from the object).  The sorting state is saved with the object's state which is not what we want.  For every student we have an instance of the <code>sortby</code> field, which we cannot make static.  Why? If we have two lists, one to be sorted by name and the other by grade, then the static value will be shared by all instance of student and may affect the sorting outcome. 


Java provides another way to compare objects.  Instead of implementing the comparable interface, we can implement the <code>Comparator</code> (<a href="http://java.sun.com/javase/7/docs/api/java/util/Comparator.html" target="_blank">Java Doc</a>) interface.  What's the difference?  The main difference between these two interfaces is that the comparable interface defines one method <code>compareTo()</code>, which takes <strong>one</strong> parameter.  This parameter is compared with <em>this</em> object (the instance of student).  In other words, the student object has a method which makes it able to compare to another student.  This is also referred to the natural ordering of this class.  On the other hand, the comparator interface defines one method (in reality two, but we are not interested from the <a href="http://java.sun.com/javase/7/docs/api/java/util/Comparator.html#equals(java.lang.Object)" target="_blank">second one</a>) that takes two parameters (of the same type) and returns the comparison of these two objects (an <code>int</code> exactly the same as the method <code>compareTo()</code> from the comparable interface).  As such, the comparator allows us to remove the unnecessary fields and methods from the student class and move these elsewhere as illustrated below.


With reference to the <code>Student1</code> class defined above.


<pre>
import java.util.Comparator;

public class StudentGradeComparator1 implements Comparator&lt;Student1&gt; {

  @Override
  public int compare(Student1 a, Student1 b) {
    return Integer.compare(a.getGrade(), b.getGrade());
  }
}
</pre>


How can we use this?  The collections class has an overloaded version of the sort method (<a href=" http://java.sun.com/javase/7/docs/api/java/util/Collections.html#sort(java.util.List, java.util.Comparator)" target="_blank">http://java.sun.com/javase/6/docs/api/java/util/Collections.html#sort(java.util.List, java.util.Comparator)</a>).  The overloaded version accepts two parameters: the list to be sorted and an instance of comparator.  The comparator outcome will be used by the sorting algorithm to determine the elements' magnitude and relation.


<pre>
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Example4 {
  public static void main(String[] args) {
    List&lt;Student1&gt; students = new ArrayList&lt;Student1&gt;();
    students.add(new Student1("Albert Attard", 65));
    students.add(new Student1("Mary Borg", 93));
    students.add(new Student1("Joe Vella", 47));
    students.add(new Student1("Paul Galea", 52));

    Collections.sort(students, <span class="highlight">new StudentGradeComparator1()</span>);
    System.out.println(students);
  }
}
</pre>


How can we sort by name?  We can create another class that implements comparator and compare the student class as required.  All we need to do is replace the compare method as shown below.


<pre>
  @Override
  public int compare(Student1 a, Student1 b) {
    return <span class="highlight">a.getName().compareTo(b.getName())</span>;
  }
</pre>


<h2>Making a difference</h2>


Let's analyse the <code>StudentGradeComparator1</code> class created above.  This class has no fields, thus we can say that this class is stateless.  Do we need to create more than one instance of this class?  No.  We do not need to have more than one instance of this class as the fields of this class (that are none) never change.  So we can have one instance and always use it.  This is referred to the singleton pattern.  In simple terms, this pattern prevents the user to create more than one instance of this object.  Why should we have it?  Every object created consumes memory.  Since our class (<code>StudentGradeComparator1</code>) is stateless, there is no need to enable/allow the user to create hundreds or thousands instance of this class as these will be identical.


<pre>
import java.util.Comparator;

public class StudentGradeComparator2 implements Comparator&lt;Student1&gt; {

  <span class="highlight">private static final StudentGradeComparator2 instance = 
      new StudentGradeComparator2();</span>

  <span class="highlight">public static StudentGradeComparator2 getInstance() {
    return instance;
  }</span>

  <span class="highlight">private StudentGradeComparator2() {
  }</span>

  @Override
  public int compare(Student1 a, Student1 b) {
    return a.getGrade() - b.getGrade();
  }
}
</pre>


This got more complex, but in reality it got simpler.  The student class only contain code related to the student, while the <em>sorting</em> classes contain the code required by the comparator. This means that each class is doing only thing. 


How can we use it?  Instead of creating an instance of the comparator, we call the get instance method: <code>Collections.sort(students, StudentGradeComparator2.getInstance())</code> as shown below.


<pre>
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Example5 {
  public static void main(String[] args) {
    List&lt;Student1&gt; students = new ArrayList&lt;Student1&gt;();
    students.add(new Student1("Albert Attard", 65));
    students.add(new Student1("Mary Borg", 93));
    students.add(new Student1("Joe Vella", 47));
    students.add(new Student1("Paul Galea", 52));

    Collections.sort(students, 
        <span class="highlight">StudentGradeComparator2.getInstance()</span>);
    System.out.println(students);
  }
}
</pre>


How can we sort the students by their grade but in descending order?  Taking this another level, we can have two instances of the comparator class (not singleton anymore): one to sort ascending and the other descending.  Here we are extending the singleton concept to prevent the user from freely creating instances.  We can remove the get instance method and allow access to the static fields as shown below.  Since, the class is radically changed, no changes are highlighted.


<pre>
import java.util.Comparator;

public class StudentGradeComparator3 implements Comparator&lt;Student1&gt; {

  public static final StudentGradeComparator3 ASC = 
      new StudentGradeComparator3(1);

  public static final StudentGradeComparator3 DESC = 
      new StudentGradeComparator3(-1);

  private final int order;

  private StudentGradeComparator3(int order) {
    this.order = order;
  }

  @Override
  public int compare(Student1 a, Student1 b) {
    return order * (Integer.compare(a.getGrade(), b.getGrade()));
  }
}
</pre>


Here we removed the get instance method and included two instances <code>ASC</code> and <code>DESC</code>.  We added an integer (named <code>order</code>) with values 1 or -1 which is used to switch the sorting polarity.  Note that when a number is multiplied by a negative number, its sign (negative or positive) is changed.  Changing the sign will change the sorting order.  This field is constant (not static) and is set through the solo private constructor by the two static fields.


<pre>
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Example6 {
  public static void main(String[] args) {
    List&lt;Student1&gt; students = new ArrayList&lt;Student1&gt;();
    students.add(new Student1("Albert Attard", 65));
    students.add(new Student1("Mary Borg", 93));
    students.add(new Student1("Joe Vella", 47));
    students.add(new Student1("Paul Galea", 52));

    Collections.sort(students, <span class="highlight">StudentGradeComparator3.ASC</span>);
    System.out.println(students);

    Collections.sort(students, <span class="highlight">StudentGradeComparator3.DESC</span>);
    System.out.println(students);
  }
}
</pre>


<h2>Arrays</h2>


We can easily adopt the above example for arrays.  Instead of using collection's sort method we use the array's version (<a href="http://java.sun.com/javase/7/docs/api/java/util/Arrays.html#sort(T[], java.util.Comparator)" target="_blank">http://java.sun.com/javase/7/docs/api/java/util/Arrays.html#sort(T[], java.util.Comparator)</a>).


<pre>
import java.util.Arrays;

public class Example7 {
  public static void main(String[] args) {
    Student1[] students = { new Student1("Albert Attard", 65),
        new Student1("Mary Borg", 93), 
        new Student1("Joe Vella", 47),
        new Student1("Paul Galea", 52) };

    Arrays.sort(students, StudentGradeComparator3.ASC);
    System.out.println(students);
  }
}
</pre>


The same comparator is used to sort the array of students. 


<h2>Conclusion</h2>


This article discusses how to perform sorting without having to worry about the sorting algorithm.  From the collections Java doc:


<blockquote>...the sorting algorithm is a modified merge sort (in which the merge is omitted if the highest element in the low sublist is less than the lowest element in the high sublist).  This algorithm offers guaranteed n log(n) performance.  The specified list must be modifiable, but need not be resizable.  This implementation dumps the specified list into an array, sorts the array, and iterates over the list resetting each element from the corresponding position in the array.  This avoids the n<sup>2</sup> log(n) performance that would result from attempting to sort a linked list in place...
</blockquote>


One thing to take away from this article is do not reinvent the wheel.  Before implementing something, have a look around as most probably others did it before you.
