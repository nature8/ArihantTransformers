package arihanttransformers;

import java.util.Scanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spr.dao.Operation;
import spr.dto.Accountant;
import spr.dto.MarketingUser;
import spr.dto.Quotation;
public class ArihantTransformers {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("/SpringXMLConfig.xml");
        Operation dao = (Operation)context.getBean("technosoft");
        Scanner sc = new Scanner(System.in);
        int CH=0;
        do{
            System.out.println("---------INDEX---------");
            System.out.println("1.Login as Marketing User");
            System.out.println("2.New Marketing user entry");
            System.out.println("3.Login as Accountant");
            System.out.println("4.New Accountant entry");
            System.out.println("5.Exit");
            byte choice = sc.nextByte();
            switch(choice){
                case 1:
                    System.out.println("Enter Marketing user id");
                    String mId = sc.next();
                    System.out.println("Enter Marketing user's password");
                    String mPwd = sc.next();
                    if(dao.checkLoginMU(mId, mPwd)){
                        int X3 = 0;
                        do{
                        System.out.println("---------INDEX---------");
                        System.out.println("1.Add Quotation");
                        System.out.println("2.Send request to edit quotation");
                        System.out.println("3.View salary and incentive");
                        System.out.println("4.Send request to change password");
                        System.out.println("5.Exit");
                        byte x2 = sc.nextByte();
                        switch(x2){
                            case 1:
                                System.out.println("Enter employee id");
                                String empId=sc.next();
                                System.out.println("Enter employee name");
                                String empName = sc.next();
                                System.out.println("Enter date of quotation");
                                String date = sc.next();
                                System.out.println("Enter number of clients on that day");
                                int noClient = sc.nextInt();
                                System.out.println("Enter salary per day expected");
                                double sal = sc.nextDouble();
                                System.out.println("Enter expected incentive");
                                double inc = sc.nextDouble();
                                double totSal = sal+inc;
                                Quotation qt = new Quotation(empId, empName, date, noClient, sal, inc, totSal);
                                dao.addQuotation(qt);
                                System.out.println("Quotation added");
                                break;
                            case 2:
                                System.out.println("Enter employee id to update the record");
                                String empId1 = sc.next();
                                System.out.println("Specify the reason:");
                                String reason = sc.next();
                                dao.updateQuotation(empId1); 
                                break;
                            case 3:
                                System.out.println("Enter employee id to view salary and incentive");
                                String emId = sc.next();
                                dao.searchSalIncen(emId);
                                break;
                            case 4:
                                System.out.println("Enter marketing user id to change password");
                                String muId = sc.next();
                                dao.updateMUPassword(muId);
                                break;
                            case 5:
                                System.exit(0);
                                
                        }
                            System.out.println("Press 1 to load the menu of marketing user....");
                            X3=sc.nextInt();
                        }while(X3==1);
                    }
                    else{
                        System.out.println("Invalid marketing user's id or password");
                    }
                    break;
                case 2:
                    System.out.println("Enter new marketing user's id");
                    String mId1 = sc.next();
                    System.out.println("Enter new marketing user's password");
                    String mPass1 = sc.next();
                    MarketingUser obj = new MarketingUser(mId1,mPass1); 
                    dao.insertMU(obj);
                    System.out.println("Record inserted");
                    break;
                case 3:
                    System.out.println("Enter accountant id for login");
                    String aId = sc.next();
                    System.out.println("Enter accountant password for login");
                    String aPwd = sc.next();
                    if(dao.checkLoginAccount(aId, aPwd)){
                        int X=0;
                        do{
                        System.out.println("---------INDEX---------");
                        System.out.println("1.View marketing user's quotation information");
                        System.out.println("2.View marketing user's quotation edit request");
                        System.out.println("3.View marketing user's password change request");
                        System.out.println("4.Update salary and incentives");
                        byte choice1 = sc.nextByte();
                        switch(choice1){
                            case 1:
                                dao.showAllQuotations();
                                break;
                            case 2:
                                System.out.println("Enter employee id to update the record");
                                String empId = sc.next();
                                dao.updateQuotation(empId); 
                                break;
                            case 3:
                                System.out.println("Enter marketing user's id whose password is updated");
                                String mUId = sc.next();
                                dao.muPassView(mUId);
                                break;
                            case 4:
                                System.out.println("Enter employee id to update the record");
                                String empId1 = sc.next();
                                dao.updateQuotation(empId1); 
                                break;
                        }
                            System.out.println("Press 1 to continue....");
                            X=sc.nextInt();
                        }while(X==1);
                    }
                    else{
                        System.out.println("Invalid accountant id/password!!!");
                    }
                case 4:
                    System.out.println("Enter new Accountant's id");
                    String aId1 = sc.next();
                    System.out.println("Enter new accountant's password");
                    String aPass1 = sc.next();
                    Accountant obj1 = new Accountant(aId1,aPass1); 
                    dao.insertAccountant(obj1);
                    System.out.println("Record inserted");
                    break;
            }
            System.out.println("Press 1 to continue.......");
            CH = sc.nextInt();
        }while(CH==1);
    }   
}
