package com.example.createApi.customException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ControllerException extends RuntimeException {

    private static final long serialVersionUID=1L;
    private String errorCode;
    private String errorMessage;
}
