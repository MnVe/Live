package com.university.campusguide.service;

import com.university.campusguide.entity.QABot;
import com.university.campusguide.repository.QABotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class QABotService {
    
    private final QABotRepository qaBotRepository;
    
    public List<QABot> searchAnswers(String question) {
        // 简单的关键词匹配，实际项目中可以使用更智能的NLP算法
        String[] keywords = question.toLowerCase().split("\\s+");
        
        List<QABot> results = qaBotRepository.findByKeyword(question);
        
        // 如果没有直接匹配的结果，尝试关键词匹配
        if (results.isEmpty()) {
            for (String keyword : keywords) {
                if (keyword.length() > 1) { // 忽略太短的词
                    List<QABot> keywordResults = qaBotRepository.findByKeyword(keyword);
                    results.addAll(keywordResults);
                }
            }
            
            // 去重并按优先级排序
            results = results.stream()
                    .distinct()
                    .sorted((a, b) -> {
                        int priorityCompare = b.getPriority().compareTo(a.getPriority());
                        if (priorityCompare != 0) return priorityCompare;
                        return b.getHitCount().compareTo(a.getHitCount());
                    })
                    .limit(5) // 最多返回5个结果
                    .collect(Collectors.toList());
        }
        
        // 更新命中次数
        results.forEach(qa -> qaBotRepository.incrementHitCount(qa.getId()));
        
        return results;
    }
    
    public List<QABot> getTopQuestions(int limit) {
        return qaBotRepository.findTopQuestions(limit);
    }
    
    public List<QABot> getQuestionsByCategory(QABot.QACategory category) {
        return qaBotRepository.findByCategory(category);
    }
    
    public QABot createQA(QABot qaBot) {
        return qaBotRepository.save(qaBot);
    }
    
    public QABot updateQA(Long id, QABot qaBot) {
        QABot existing = qaBotRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("问答不存在"));
        
        existing.setQuestion(qaBot.getQuestion());
        existing.setAnswer(qaBot.getAnswer());
        existing.setKeywords(qaBot.getKeywords());
        existing.setCategory(qaBot.getCategory());
        existing.setPriority(qaBot.getPriority());
        existing.setIsActive(qaBot.getIsActive());
        
        return qaBotRepository.save(existing);
    }
    
    public void deleteQA(Long id) {
        QABot qaBot = qaBotRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("问答不存在"));
        qaBot.setDeleted(true);
        qaBotRepository.save(qaBot);
    }
}