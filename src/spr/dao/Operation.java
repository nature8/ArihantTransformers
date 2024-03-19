
package spr.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import spr.dto.Accountant;
import spr.dto.MarketingUser;
import spr.dto.Quotation;

public class Operation {
    HibernateTemplate template;

    public HibernateTemplate getTemplate() {
        return template;
    }

    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }

    public Operation() {
    }

    public Operation(HibernateTemplate template) {
        this.template = template;
    }
    Scanner sc = new Scanner(System.in);
    public void insertRecord(Quotation obj){
        template.save(obj);
    } 
    public void insertMU(MarketingUser obj){
        template.save(obj);
    } 
    public void insertAccountant(Accountant obj){
        template.save(obj);
    } 
    public void deleteRecord(String empId){
        
        template.execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(org.hibernate.Session session) throws HibernateException, SQLException {
                Quotation ref=(Quotation)session.get(Quotation.class, empId);
                if(ref==null){
                    System.out.println("No record found to delete");
                }
                else{
                    session.delete(ref);
                    System.out.println("record deleted!!");
                }
                return null;
            }
        });
    }
    public void showAllQuotations(){
        template.execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(org.hibernate.Session session) throws HibernateException, SQLException {

                Criteria crit = session.createCriteria(Quotation.class); 
                List<Quotation> list=crit.list();
                for(Quotation ref:list){
                    System.out.println(ref.getEmpId()+" "+ref.getEmpName()+" "+ref.getDate()+" "+ref.getNoOfClient()+" "+ref.getSalaryPerDay()+" "+ref.getIncentive()+" "+ref.getTotSal());
                }
                return null;
            }
        });
        
    }
    public void searchSalIncen(String empId)
    {
        template.execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(org.hibernate.Session session) throws HibernateException, SQLException 
            {
                Quotation ref = (Quotation)session.get(Quotation.class, empId);
                if(ref==null){
                    System.out.println("No record found to load");
                }
                else{
                    System.out.println(ref.getIncentive()+" "+ref.getSalaryPerDay()+" "+ref.getTotSal());
                }
                return null;
            }        
        });
        
    }
    public void addQuotation(Quotation obj){
        template.save(obj);
    } 
    public void muPassView(String name)
    {
        template.execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(org.hibernate.Session session) throws HibernateException, SQLException 
            {
                MarketingUser ref = (MarketingUser)session.get(MarketingUser.class, name);
                if(ref==null){
                    System.out.println("No record found to load");
                }
                else{
                    System.out.println(ref.getMarkUserId()+" "+ref.getMarkUserPass());
                }
                return null;
            }        
        });  
    }
    public void updateMUPassword(String name)
    {
                template.execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(org.hibernate.Session session) throws HibernateException, SQLException 
            {
        MarketingUser ref = (MarketingUser)session.get(MarketingUser.class, name);
                if(ref==null){
                System.out.println("No record found to update");
                }
                else{
                    System.out.println("Enter old password");
                    String oldPass = sc.next();
                    Criteria crit1 = session.createCriteria(MarketingUser.class);
                    crit1.add(Restrictions.eq("markUserPass",oldPass));
                    List <MarketingUser> data = crit1.list();
                    if(data.isEmpty()){
                        System.out.println("Invalid old password");
                    }
                    else{
                        System.out.println("Enter new password");
                        String newPass = sc.next();
                        ref.setMarkUserPass(newPass);
                        session.update(ref);
                        System.out.println("Password change successfully");
                    }
                }
                return null;
            }
        });
    }
    public void updateQuotation(String empId)
    {
                template.execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(org.hibernate.Session session) throws HibernateException, SQLException 
            {
            Quotation ref = (Quotation)session.get(Quotation.class, empId);
                if(ref.equals(null)){
                    System.out.println("No record found to update");
                }
                else{
                    System.out.println("-------INDEX-------");
                    System.out.println("1.Employee name");
                    System.out.println("2.Update in number of client");
                    System.out.println("3.Update in salary per day expected");
                    System.out.println("4.Incentive and total salary");
                    System.out.println("5.Exit");
                    Scanner sc = new Scanner(System.in);
                    byte choice = sc.nextByte();
                    switch(choice){
                        case 1:
                            System.out.println("Enter updated employee name");
                            String empNam = sc.next();
                            ref.setEmpName(empNam);
                            session.update(ref);
                            System.out.println("Record updated");
                            break;
                        case 2:
                            System.out.println("Enter updated number of client in quotation");
                            int clientNo = sc.nextInt();
                            ref.setNoOfClient(clientNo);
                            session.update(ref);
                            System.out.println("Record updated");
                            break;
                        case 3:
                            System.out.println("Enter expacted/estimated updated salary");
                            double sal = sc.nextDouble();
                            ref.setSalaryPerDay(sal);
                            session.update(ref);
                            System.out.println("Record updated");
                            break;
                        case 4:
                            double SALARY=0, INCENTIVE=0, TOTSALARY=0;
                            Criteria crit1 = session.createCriteria( Quotation.class, empId);
                            crit1.add(Restrictions.eq("empId", empId));
                            List<Quotation> data = crit1.list();
                            for(Quotation records: data){
                                if(records.getNoOfClient() < 10){
                                    INCENTIVE=0;
                                    SALARY = records.getSalaryPerDay()*0.5;
                                    TOTSALARY = INCENTIVE + SALARY;
                                    ref.setSalaryPerDay(SALARY);
                                    ref.setIncentive(INCENTIVE);
                                    ref.setTotSal(TOTSALARY);
                                    session.update(ref);
                                    System.out.println("Record updated!!");
                                }
                                else if(records.getNoOfClient()>10){
                                    INCENTIVE = records.getIncentive();
                                    SALARY = records.getSalaryPerDay()+200;
                                    TOTSALARY = INCENTIVE + SALARY;
                                    ref.setIncentive(INCENTIVE);
                                    ref.setSalaryPerDay(SALARY);
                                    ref.setTotSal(TOTSALARY);
                                    session.update(ref);
                                    System.out.println("Record updated");
                                }
                                else{
                                    double SALARY1 = records.getSalaryPerDay();
                                    double INCENTIVE1 = records.getIncentive();
                                    double TOTSALARY1 = records.getIncentive() + records.getSalaryPerDay();
                                    ref.setIncentive(INCENTIVE1);
                                    ref.setSalaryPerDay(SALARY1);
                                    ref.setTotSal(TOTSALARY1);
                                    session.update(ref);
                               }
                            }
                            break;
                        case 5:
                            System.exit(0);
                    }
                 }
                return null;
            }
        });
    }
    public boolean checkLoginMU(String id, String pwd){
        List<MarketingUser> data = template.find("from MarketingUser where markUserId=? and markUserPass=?",id,pwd);
        if(data.isEmpty()){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean checkLoginAccount(String id, String pwd){
        Scanner sc = new Scanner(System.in);
        List<Accountant> data = template.find("from Accountant where accountId=? and accountPass=?",id,pwd);
        if(data.isEmpty()){
            return false;
        }
        else{
            return true;
        }
    }
}
