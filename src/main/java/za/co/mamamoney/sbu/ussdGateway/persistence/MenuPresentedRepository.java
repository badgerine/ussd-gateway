package za.co.mamamoney.sbu.ussdGateway.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import za.co.mamamoney.sbu.ussdGateway.model.MenuPresented;

public interface MenuPresentedRepository extends JpaRepository<MenuPresented,MenuPresentedId> {
    @Query("SELECT MP FROM MenuPresented MP " +
            "WHERE MP.sessionId = ?1 " +
            "and MP.menuId = (Select MAX(menuId) FROM MenuPresented i WHERE i.sessionId =?1)")
    MenuPresented findLatestBySessionId(String sessionId);

    @Transactional
    @Modifying
    @Query("update MenuPresented m set m.selection = ?3, m.attempts = ?4 " +
            "where m.sessionId = ?1 and m.menuId = ?2")
    void setMenuPresentedById(String sessionId, Integer menuId, String selection, Integer attempts);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO MENU_PRESENTED (SESSION_ID, MENU_ID) VALUES" +
            "(:sessionId,:menuId)", nativeQuery = true)
    void setMenuPresentedById(String sessionId, Integer menuId);

//    @Modifying
//    @Query(value = "INSERT INTO MenuPresented (sessionId, menuId) VALUES" +
//            "(:sessionId,:menuId)", nativeQuery = true)
//    void setMenuPresentedById(@Param("menuId") String sessionId, @Param("menuId") Integer menuId);
}
