import java.util.Scanner;

public class ScannerTest {




  public static void main(String[] args) {
    String input = "1,fish,2,fish\n\n\n";
    Scanner sc = new Scanner(input);
    sc.useDelimiter(",");
    System.out.print(sc.nextInt());
    System.out.println(sc.next());
    System.out.print(sc.nextInt());
    System.out.println(sc.next());
    
    System.out.println("Done");
    sc.close();
    
  }
  
  public static void main1(String[] args) {
	    System.out.println("Enter your string: ");
	    Scanner sc = new Scanner(System.in);
	    sc.useDelimiter(",");
	    System.out.print(sc.nextInt());
	    System.out.println(sc.next());
	    System.out.print(sc.nextInt());
	    System.out.println(sc.next());
	    
	    System.out.println("Done");
	    sc.close();
	    
	  }
}