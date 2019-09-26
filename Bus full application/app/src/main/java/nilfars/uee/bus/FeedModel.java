package nilfars.uee.bus;

public class FeedModel {

    String Id;
    String cusName;
    String comment;
    String email;

    public FeedModel(){}

    public FeedModel(String Id,String cusName, String email, String comment) {
        this.cusName = cusName;
        this.comment = comment;
        this.email = email;
        this.Id=Id;
    }



    public String getCusName() {
        return cusName;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public String getEmail() {
        return email;
    }
}
