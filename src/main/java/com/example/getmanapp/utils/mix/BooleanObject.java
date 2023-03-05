package com.example.getmanapp.utils.mix;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
/**
 * Объект-обёртка, нужен для возврата ответа на фронт в формате объекта.
 */
public class BooleanObject {
    private Boolean result;
    private String exceptionMessage;

    public BooleanObject(Boolean result) {
        this.result = result;
        this.exceptionMessage = null;
    }
}
