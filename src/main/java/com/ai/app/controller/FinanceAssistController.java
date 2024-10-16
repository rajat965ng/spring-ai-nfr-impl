package com.ai.app.controller;

import com.ai.app.service.AssistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/finance/assist")
public class FinanceAssistController {

    private AssistService assistService;

    public FinanceAssistController(AssistService assistService) {
        this.assistService = assistService;
    }

    @PostMapping("/save")
    public ResponseEntity ingest(@RequestBody List<String> urls){
        try{
        assistService.ingest(urls);
        return ResponseEntity.ok().build();}
        catch (Exception e){
            return ResponseEntity.notFound().header("error",e.getMessage()).build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity getAnswer(@RequestParam("question") String question) {
        try {
            return ResponseEntity.ok(assistService.getAnswer(question));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(e.getMessage());
        }
    }
}
