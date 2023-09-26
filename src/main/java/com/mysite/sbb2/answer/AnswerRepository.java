package com.mysite.sbb2.answer;

import com.mysite.sbb2.answer.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer,Integer>{
    
}
