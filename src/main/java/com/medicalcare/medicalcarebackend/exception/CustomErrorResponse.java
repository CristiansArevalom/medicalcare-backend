package com.medicalcare.medicalcarebackend.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/***
 * ESta clase permitira personalizar el mensaje de error
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomErrorResponse {

        private LocalDateTime dateTime;
        private String message;
        private String details;
}
