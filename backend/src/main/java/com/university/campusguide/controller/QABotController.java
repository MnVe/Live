package com.university.campusguide.controller;

import com.university.campusguide.dto.ApiResponse;
import com.university.campusguide.entity.QABot;
import com.university.campusguide.service.QABotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/qa")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class QABotController {
    
    private final QABotService qaBotService;
    
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<QABot>>> searchAnswers(@RequestParam String question) {
        List<QABot> answers = qaBotService.searchAnswers(question);
        return ResponseEntity.ok(ApiResponse.success(answers));
    }
    
    @GetMapping("/top")
    public ResponseEntity<ApiResponse<List<QABot>>> getTopQuestions(
            @RequestParam(defaultValue = "10") int limit) {
        List<QABot> questions = qaBotService.getTopQuestions(limit);
        return ResponseEntity.ok(ApiResponse.success(questions));
    }
    
    @GetMapping("/category/{category}")
    public ResponseEntity<ApiResponse<List<QABot>>> getQuestionsByCategory(
            @PathVariable QABot.QACategory category) {
        List<QABot> questions = qaBotService.getQuestionsByCategory(category);
        return ResponseEntity.ok(ApiResponse.success(questions));
    }
    
    @PostMapping
    public ResponseEntity<ApiResponse<QABot>> createQA(@RequestBody QABot qaBot) {
        QABot createdQA = qaBotService.createQA(qaBot);
        return ResponseEntity.ok(ApiResponse.success("问答创建成功", createdQA));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<QABot>> updateQA(@PathVariable Long id, @RequestBody QABot qaBot) {
        QABot updatedQA = qaBotService.updateQA(id, qaBot);
        return ResponseEntity.ok(ApiResponse.success("问答更新成功", updatedQA));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteQA(@PathVariable Long id) {
        qaBotService.deleteQA(id);
        return ResponseEntity.ok(ApiResponse.success("问答删除成功", null));
    }
}