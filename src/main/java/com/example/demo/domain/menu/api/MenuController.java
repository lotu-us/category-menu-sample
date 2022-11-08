package com.example.demo.domain.menu.api;

import com.example.demo.domain.menu.application.MenuService;
import com.example.demo.domain.menu.dto.MenuDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequestMapping("/menu")
@RestController
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    /**
     * create
     */
    @PostMapping("/{categoryCode}")
    public ResponseEntity create(@PathVariable String categoryCode, @Validated @RequestBody MenuDto.CreateReq createReq){
        return ResponseEntity.ok(menuService.create(categoryCode, createReq));
    }

    /**
     * read
     */
    @GetMapping("/{categoryCode}/{menuCode}")
    public ResponseEntity read(@PathVariable String categoryCode, @PathVariable Long menuCode){
        return ResponseEntity.ok(menuService.read(categoryCode, menuCode));
    }

    /**
     * read list
     */
    @GetMapping("/{categoryCode}/list")
    public ResponseEntity readListByCategory(@PathVariable String categoryCode, @PageableDefault(size = 10) Pageable pageable){
        return ResponseEntity.ok(menuService.readListByCategory(categoryCode, pageable));
    }

    /**
     * read list
     */
    @GetMapping("/list")
    public ResponseEntity readList(@PageableDefault(size = 10) Pageable pageable){
        return ResponseEntity.ok(menuService.readList(pageable));
    }


    /**
     * update
     */
    @PutMapping("/{categoryCode}/{menuCode}")
    public ResponseEntity update(@PathVariable String categoryCode, @PathVariable Long menuCode, @Validated @RequestBody MenuDto.UpdateReq updateReq){
        return ResponseEntity.ok(menuService.update(categoryCode, menuCode, updateReq));
    }

    /**
     * delete
     */
    @DeleteMapping("/{categoryCode}/{menuCode}")
    public ResponseEntity delete(@PathVariable String categoryCode, @PathVariable Long menuCode){
        return ResponseEntity.ok(menuService.delete(categoryCode, menuCode));
    }
}
