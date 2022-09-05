package com.example.demo.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Класс данных хранящий в себе информацию о счете клиента
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {

    /**
     * Номер счета
     */
    private Long accountId;

    /**
     * Средства
     */
    private Double money;

    /**
     * Бонусы
     */
    private Double bonuses;

}
