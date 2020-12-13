package edu.netcracker.menugenerator.services;

import edu.netcracker.menugenerator.entity.Meal;
import edu.netcracker.menugenerator.entity.Menu;
import javassist.NotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface MenuService {
    Menu getMenuById(long id) throws NotFoundException;

    Slice<Menu> getAll(long id, String name, Pageable pageable);

    Menu getCurrent(long id);

    Menu setCurrent(long id, long userId);

    Menu updateMenu(long id, String name, String description);

    Menu generateMenu(long userId);

    void deleteById(long id);
}
