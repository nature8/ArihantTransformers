
package spr.dto;

public class MarketingUser {
    private String markUserId;
    private String markUserPass;

    public String getMarkUserId() {
        return markUserId;
    }

    public void setMarkUserId(String markUserId) {
        this.markUserId = markUserId;
    }

    public String getMarkUserPass() {
        return markUserPass;
    }

    public void setMarkUserPass(String markUserPass) {
        this.markUserPass = markUserPass;
    }

    public MarketingUser() {
    }

    public MarketingUser(String markUserId, String markUserPass) {
        this.markUserId = markUserId;
        this.markUserPass = markUserPass;
    }
        
}
