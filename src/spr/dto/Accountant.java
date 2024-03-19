
package spr.dto;
public class Accountant {
    private String accountId;
    private String accountPass;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountPass() {
        return accountPass;
    }

    public void setAccountPass(String accountPass) {
        this.accountPass = accountPass;
    }

    public Accountant() {
    }

    public Accountant(String accountId, String accountPass) {
        this.accountId = accountId;
        this.accountPass = accountPass;
    }
    
}
