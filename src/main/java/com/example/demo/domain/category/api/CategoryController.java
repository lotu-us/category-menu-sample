package com.example.demo.domain.category.api;


import com.example.demo.domain.category.application.CategoryService;
import com.example.demo.domain.category.dto.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RequestMapping("/category")
@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;


    /**
     * create
     */
    @PostMapping
    public ResponseEntity create(@Validated @RequestBody CategoryDto.CreateReq createReq){
        return ResponseEntity.ok(categoryService.create(createReq));
    }

    /**
     * read
     */
    @GetMapping("/{categoryCode}")
    public ResponseEntity read(@PathVariable String categoryCode, @Validated CategoryDto.ReadReq readReq){
        return ResponseEntity.ok(categoryService.read(categoryCode, readReq));
    }

    /**
     * readList
     */
    @GetMapping("/list")
    public ResponseEntity readList(@NotNull @RequestParam Boolean getChild, @PageableDefault(size = 10)Pageable pageable){
        return ResponseEntity.ok(categoryService.readList(getChild, pageable));
    }

    /**
     * update
     */
    @PutMapping("/{categoryCode}")
    public ResponseEntity update(@PathVariable String categoryCode, @Validated @RequestBody CategoryDto.UpdateReq updateReq){
        return ResponseEntity.ok(categoryService.update(categoryCode, updateReq));
    }

    /**
     * delete
     */
    @DeleteMapping("/{categoryCode}")
    public ResponseEntity delete(@PathVariable String categoryCode){
        return ResponseEntity.ok(categoryService.delete(categoryCode));
    }
}
