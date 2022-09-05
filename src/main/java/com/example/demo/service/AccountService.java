package com.example.demo.service;

import com.example.demo.data.BankAccount;
import com.example.demo.data.PaymentMethod;
import com.example.demo.data.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class AccountService {

    //Поскольку БД не используется, инициализация пользователя происходит здесь и работать будем с ним же.
    User user = initUserAccount();
    private User initUserAccount () {
        User user = new User();
        user.setClientId("Client1");
        user.setBankAccount(new BankAccount(1L, 5000.0, 0.0));
        return user;
    }

    /**
     * @param method указывает тип покупки (Магазин или Онлайн)
     * @param amount указывает стоимость покупки
     * @return возвращает информацию о счете
     * @throws Exception в случае, если на счете недостаточно средств для покупки
     */
    public BankAccount payment(String method, Double amount) throws Exception {
        //Если сумма покупки превышает сумму на балансе, то выбросить исключение
        if (user.getBankAccount().getMoney() - amount < 0) {
            throw new Exception("Недостаточно средств.");
        }

        if (method.equals(PaymentMethod.SHOP.getMethod())) {
            //Для магазина всегда 10% бонусов
            producePayment(0.1, amount);
        }
        if (method.equals(PaymentMethod.ONLINE.getMethod())) {
            //Если онлайн-покупка более 300 рублей, накинем 30% бонусами
            if (amount > 300.0) {
                producePayment(0.3, amount);
            } else {
                //В остальных случаях всегда 17%
                producePayment(0.17, amount);
                //Если сумма покупки меньше 20 рублей, то снимаем со счета комиссию 10%
                if (amount < 20.0) {
                    user.getBankAccount().setMoney(
                            user.getBankAccount().getMoney() - amount * 0.1
                    );
                }
            }
        }

        return user.getBankAccount();
    }

    /**
     * @return Возвращает текущее количество бонусов
     */
    public Double bankAccountOfEMoney() {
        return user.getBankAccount().getBonuses();
    }

    /**
     * @return Возвращает текущий баланс
     */
    public Double money() {
        return user.getBankAccount().getMoney();
    }

    /**
     * Метод реализующий логику снятия средств/начисления бонусов
     * @param cashback определяет размер бонусов
     * @param amount сумма покупки
     */
    private void producePayment(Double cashback, Double amount) {
        user.getBankAccount().setMoney(
                user.getBankAccount().getMoney() - amount
        );
        user.getBankAccount().setBonuses(
                user.getBankAccount().getBonuses() + amount * cashback
        );
    }


}
