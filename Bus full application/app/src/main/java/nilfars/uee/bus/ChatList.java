package nilfars.uee.bus;


public class ChatList {

    private int chatid;
    private String fullname, email, contact, language, message;

    public ChatList(int chatid, String fullname, String email, String contact, String language, String message) {
        this.chatid = chatid;
        this.fullname = fullname;
        this.email = email;
        this.contact = contact;
        this.language = language;
        this.message = message;
    }

    public int getChatid() {
        return chatid;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getContact() {
        return contact;
    }

    public String getLanguage() {
        return language;
    }

    public String getMessage() {
        return message;
    }
}