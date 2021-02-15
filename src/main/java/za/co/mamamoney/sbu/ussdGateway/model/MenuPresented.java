package za.co.mamamoney.sbu.ussdGateway.model;

import za.co.mamamoney.sbu.ussdGateway.persistence.MenuPresentedId;

import javax.persistence.*;

@Entity
@Table(name="MenuPresented")
@IdClass(MenuPresentedId.class)
public class MenuPresented {
    @Id
    private String sessionId;
    @Id
    private Integer menuId;
    @Column
    private String message;
    @Column
    private String selection;
    @Column
    private Integer attempts;

    public MenuPresented(){
        this.attempts = 0;
    }

    public MenuPresented(String sessionId) {
        this();
        this.sessionId = sessionId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MenuPresented(String sessionId, Integer menuId) {
        this(sessionId);
        this.menuId = menuId;
    }

    public MenuPresented(String sessionId, Integer menuId, String message) {
        this(sessionId);
        this.menuId = menuId;
        this.message = message;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    public Integer getAttempts() {
        return attempts;
    }

    public void setAttempts(Integer attempts) {
        this.attempts = attempts;
    }

}
