package za.co.mamamoney.sbu.ussdGateway.persistence;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

public class MenuPresentedId implements Serializable {
    private String sessionId;
    private Integer menuId;

    public MenuPresentedId() {
    }

    public MenuPresentedId(String sessionId, Integer menuId) {
        this.sessionId = sessionId;
        this.menuId = menuId;
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

    @Override
    public boolean equals(Object object){
        return object == this ||
            (object instanceof MenuPresentedId)
            && ((MenuPresentedId) object).getMenuId().equals(this.menuId)
            && ((MenuPresentedId) object).getSessionId().equals(this.sessionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuId, sessionId);
    }
}
