package com.example.restfulwebservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse { // AOP = 컨트롤러 bean 에서 에러가 발생히면 여기로 전달됨
    private Date timeStamp;
    private String message;
    private String details;
}
