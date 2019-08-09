package com.example.spm.demo.controller;

import com.example.spm.demo.util.FileReadUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/file/")
public class FileReadController {


    @GetMapping("read")
    public ResponseEntity<?> readFile(@RequestParam("filepath") String filepath) throws IOException {
        FileReadUtil fileReadUtil = new FileReadUtil();

        List<String> list = fileReadUtil.showResourceData(filepath);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
