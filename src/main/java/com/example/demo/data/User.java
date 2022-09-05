package com.example.demo.data;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Класс пользователя
 */
@Data
@Getter
@Setter
@NoArgsConstructor
public class User {

    /**
     * Идентификатор пользователя
     */
    private String clientId;

    /**
     * Банковский счет
     */
    private BankAccount bankAccount;

}
