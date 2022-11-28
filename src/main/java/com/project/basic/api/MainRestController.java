package com.project.basic.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.basic.model.api.common.response.DefDataResponse;
import com.project.basic.model.api.common.response.DefResponse;
import com.project.basic.model.base.Base;
import com.project.basic.model.base.BaseSrch;
import com.project.basic.model.common.SearchList;
import com.project.basic.service.MainService;
import com.project.basic.utils.ValidErrUtil;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class MainRestController {
    
    MainService mainService;

    // Create
    @PostMapping
    public ResponseEntity<?> insert(@RequestBody @Valid Base base, BindingResult result){
       
        if(result.hasErrors()){
            return ValidErrUtil.getValidateError(result.getFieldErrors());
        }

        if(mainService.insert(base) > 0){
            return ResponseEntity.ok(new DefResponse(HttpStatus.OK));
        }
        
        return ResponseEntity.ok(new DefResponse(HttpStatus.BAD_REQUEST));
    }

    // Read
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable int id){
        Base base = mainService.get(id);
        return ResponseEntity.ok(new DefDataResponse(HttpStatus.OK, base));
    }
    

    @GetMapping
    public ResponseEntity<?> getList(@ModelAttribute BaseSrch option){
        System.out.println(option.toString());
        SearchList<Base> baseList = mainService.getList(option);
        return ResponseEntity.ok(new DefDataResponse(HttpStatus.OK, baseList));
    }

}
