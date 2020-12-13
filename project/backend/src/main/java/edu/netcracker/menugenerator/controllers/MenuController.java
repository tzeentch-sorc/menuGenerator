package edu.netcracker.menugenerator.controllers;

import edu.netcracker.menugenerator.dto.MenuDto;
import edu.netcracker.menugenerator.dto.MenuDtoShort;
import edu.netcracker.menugenerator.dto.response.MessageResponse;
import edu.netcracker.menugenerator.entity.Menu;
import edu.netcracker.menugenerator.services.MenuService;
import edu.netcracker.menugenerator.util.CalculationUtils;
import edu.netcracker.menugenerator.util.PaginationUtil;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600, exposedHeaders = "X-Has-Next-Page")
@RestController
@RequestMapping("/api/menu")
@Slf4j
public class MenuController {

    private final MenuService menuService;
    private final Mapper mapper;

    @Autowired
    public MenuController(MenuService menuService, Mapper mapper){
        this.menuService = menuService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<?> getMenuList(@RequestParam long id, @RequestParam(required = false) String name,Pageable pageable) {
        Slice<MenuDtoShort> menus = menuService.getAll(id, name, pageable).map(
                e ->{
                    MenuDtoShort menu = mapper.map(e, MenuDtoShort.class);
                    menu.setMealsLength(e.getMealsInMenu().size());
                    return menu;
                } );
        return ResponseEntity.ok().headers(PaginationUtil.generateSliceHttpHeaders(menus)).body(menus.getContent());
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> getMenuById(@PathVariable long id){
        try {
            Menu menu = menuService.getMenuById(id);
//            if(menu.isCurrent())
//                return ResponseEntity.ok(mapper.map(menu, MenuDtoAdditional.class));
//            else
                return ResponseEntity.ok(mapper.map(menu, MenuDto.class));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();//TODO fix this
        }
    }

    @PostMapping("/current/set")
    public ResponseEntity<?> setCurrentById(@RequestParam long id, @RequestParam long userId){
        Menu menu = menuService.setCurrent(id, userId);
        if(menu == null)
            return ResponseEntity.ok(new MessageResponse("Deleted unsaved menu" ,true));
        MenuDtoShort menuDto = mapper.map(menu, MenuDtoShort.class);
        menuDto.setMealsLength(menu.getMealsInMenu().size());
        return ResponseEntity.ok(menuDto);
    }

    @PostMapping("/current/get")
    public ResponseEntity<?> getCurrent(@RequestParam long userId){
        return ResponseEntity.ok((mapper.map(menuService.getCurrent(userId), MenuDto.class)));
    }

    @PostMapping("/update")
    public ResponseEntity<?> saveMenu(@RequestParam String name, @RequestParam long id,
                                      @RequestParam String description){
        return ResponseEntity.ok(mapper.map(menuService.updateMenu(id, name, description), MenuDtoShort.class));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> removeById(@PathVariable long id){
        menuService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/generate")
    public ResponseEntity<?> generate(@RequestParam long userId){
        Menu menu = menuService.generateMenu(userId);
        log.info("" + CalculationUtils.calcTotalCalories(menu.getMealsInMenu()));
        return ResponseEntity.ok(mapper.map(menu, MenuDto.class));
    }
}
