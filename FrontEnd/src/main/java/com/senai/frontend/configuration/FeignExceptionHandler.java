package com.senai.frontend.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senai.frontend.dto.ResponseDto;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@ControllerAdvice
@RequiredArgsConstructor
public class FeignExceptionHandler {
    private final ObjectMapper objectMapper;

    @ExceptionHandler(FeignException.class)
    public RedirectView handleFeignStatusException(FeignException e, RedirectAttributes redirectAttributes) {
        String defaultMessage = "Erro ao comunicar com o serviço.";
        String message = defaultMessage;

        try {
            String responseBody = e.contentUTF8();
            ResponseDto responseDto = objectMapper.readValue(responseBody, ResponseDto.class);

            if (responseDto.getResult() instanceof String) {
                message = (String) responseDto.getResult();
            } else {
                message = objectMapper.writeValueAsString(responseDto.getResult()); // exibe JSON legível
            }

        } catch (Exception parseError) {
            message = defaultMessage + " (" + e.status() + ")";
        }

        redirectAttributes.addFlashAttribute("errorMessage", message);
        return new RedirectView("/");
    }
}
