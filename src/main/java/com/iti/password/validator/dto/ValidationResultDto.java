package com.iti.password.validator.dto;

import javax.validation.constraints.NotNull;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ValidationResultDto {
    @NotNull
    private boolean isValid;
}
