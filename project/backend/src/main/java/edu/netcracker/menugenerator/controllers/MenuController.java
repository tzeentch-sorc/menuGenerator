package edu.netcracker.menugenerator.controllers;

import edu.netcracker.menugenerator.dto.MenuDto;
import edu.netcracker.menugenerator.dto.MenuDtoShort;
import edu.netcracker.menugenerator.dto.response.MessageResponse;
import edu.netcracker.menugenerator.entity.Menu;
import edu.netcracker.menugenerator.services.MenuService;
import edu.netcracker.menugenerator.util.CalculationUtils;
import edu.netcracker.menugenerator.util.PaginationUtil;
import edu.netcracker.menugenerator.util.exceptions.MenuNotFoundException;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"${menuGenerator.cors.allowedOrigin}"}, maxAge = 3600, exposedHeaders = "X-Has-Next-Page")
@RestController
@RequestMapping("/menu")
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
                return ResponseEntity.ok(mapper.map(menu, MenuDto.class));
        } catch (NotFoundException e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());//TODO fix this
        }
    }

    @PostMapping("/current/set")
    public ResponseEntity<?> setCurrentById(@RequestParam long id, @RequestParam long userId){
        try {
            Menu menu = menuService.setCurrent(id, userId);
            MenuDtoShort menuDto = mapper.map(menu, MenuDtoShort.class);
            menuDto.setMealsLength(menu.getMealsInMenu().size());
            return ResponseEntity.ok(menuDto);
        } catch (MenuNotFoundException e){
            return ResponseEntity.ok(new MessageResponse("Deleted unsaved menu" ,true));
        }
    }

    @PostMapping("/current/get")
    public ResponseEntity<?> getCurrent(@RequestParam long userId){
        return ResponseEntity.ok((mapper.map(menuService.getCurrent(userId), MenuDto.class)));
    }

    @PostMapping("/update")
    public ResponseEntity<?> saveMenu(@RequestParam String name, @RequestParam long id,
                                      @RequestParam String description){
        try {
            return ResponseEntity.ok(mapper.map(menuService.updateMenu(id, name, description), MenuDtoShort.class));
        } catch (MenuNotFoundException e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage(), false));
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> removeById(@PathVariable long id){
        menuService.deleteById(id);
        return ResponseEntity.ok("Deleted successfully");
    }

    @PostMapping("/generate")
    public ResponseEntity<?> generate(@RequestParam long userId){
        Menu menu = menuService.generateMenu(userId);
        log.info("" + CalculationUtils.calcTotalCalories(menu.getMealsInMenu()));
        return ResponseEntity.ok(mapper.map(menu, MenuDto.class));
    }
}
