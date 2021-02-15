package za.co.mamamoney.sbu.ussdGateway.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Menu")
public class Menu {
    @Id
    private Integer menuId;
    @Column
    private String message;
    @Column
    private String userOptions;
    @Column
    private String metaDescription;

    public Menu(){}

    public Menu(Integer menuId, String message, String userOptions, String metaDescription) {
        this.menuId = menuId;
        this.message = message;
        this.userOptions = userOptions;
        this.metaDescription = metaDescription;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserOptions() {
        return userOptions;
    }

    public void setUserOptions(String userOptions) {
        this.userOptions = userOptions;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }
}
