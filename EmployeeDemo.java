import java.io.*;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

class Employee implements Serializable {
    int empno;
    String ename;
    int salary;

    public Employee(int empno, String ename, int salary) {
        this.empno=empno;
        this.ename=ename;
        this.salary=salary;

    }
    public String toString(){
        return empno+" "+ename+" "+salary;
    }

}

class EmployeeDemo implements Serializable{
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        int choice=-1;
        Scanner sc = new Scanner(System.in);
        Scanner sc1 = new Scanner(System.in);
        File file = new File("employee.txt");
        ArrayList<Employee> al =new ArrayList<Employee>();
        ObjectOutputStream oos =null;
        ObjectInputStream ois = null;
        ListIterator li =null;

        if (file.isFile()){
            ois = new ObjectInputStream(new FileInputStream(file));
            al= (ArrayList<Employee>) ois.readObject();
            ois.close();
        }

        do { 
            System.out.println("1.Insert");
            System.out.println("2.Display");
            System.out.println("3.Search");
            System.out.println("4.Delete");
            System.out.println("0.EXIT");
            System.out.println("Enter Your choice: ");
            choice=sc.nextInt();
            switch (choice) {
                case 1: 
                        System.out.println("How many employee you want: ");
                        int n = sc.nextInt();
                        for(int i =0; i<n; i++){
                        System.out.println("Enter Employee No: ");
                        int empno=sc.nextInt();

                        System.out.println("Enter Employee Name: ");
                        String ename=sc1.nextLine();
                    
                        System.out.println("Enter Salary: ");
                        int salary=sc.nextInt();

                        al.add(new Employee(empno,ename,salary));
                            }
                        oos=new ObjectOutputStream(new FileOutputStream(file));
                        oos.writeObject(al);
                        oos.close();

                        break;

                case 2: 
                if (file.isFile()){
                    ois = new ObjectInputStream(new FileInputStream(file));
                    al= (ArrayList<Employee>) ois.readObject();
                    ois.close();

                        System.out.println("-----------------------------------");
                        li = al.listIterator();
                        while(li.hasNext())
                            System.out.println(li.next());
                            
                        System.out.println("-----------------------------------");
                    } else {
                        System.out.println("File not Exist.....");
                    }
                        break;
                
                case 3: 
                        if (file.isFile()){
                            ois = new ObjectInputStream(new FileInputStream(file));
                            al= (ArrayList<Employee>) ois.readObject();
                            ois.close();

                            boolean found= false;
                            System.out.println("Enter empno to search: ");
                            int empn = sc.nextInt();
                            System.out.println("-----------------------------------");
                            
                            li = al.listIterator();
                            while(li.hasNext()){
                                Employee e =(Employee) li.next();
                                if(e.empno==empn){
                                    
                                    System.out.println(e.ename);
                                    found = true;
                                }
                            }
                            if (!found){
                                System.out.println("Record not found");
                            }
                            
                            System.out.println("-----------------------------------");
                        } else {
                            System.out.println("File not exist...");
                        }
                        break;

                
                        case 4: 
                        if (file.isFile()){
                            ois = new ObjectInputStream(new FileInputStream(file));
                            al= (ArrayList<Employee>) ois.readObject();
                            ois.close();

                            boolean found= false;
                            System.out.println("Enter empno to delete: ");
                            int empn = sc.nextInt();
                            System.out.println("-----------------------------------");
                            
                            li = al.listIterator();
                            while(li.hasNext()){
                                Employee e =(Employee) li.next();
                                if(e.empno==empn){
                                    
                                    li.remove();
                                    found = true;
                                }
                            }
                            if (!found){
                                System.out.println("Record not found");
                            }else{
                                oos = new ObjectOutputStream(new FileOutputStream(file));
                                oos.writeObject(al);
                                oos.close();
                                System.out.println("deleted successfully..");
                            }
                            
                            System.out.println("-----------------------------------");
                        } else {
                            System.out.println("File not exist...");
                        }
                        break;
                
                // default:
                //     throw new AssertionError();
            }
        } while (choice!=0);
    }
}