package com.example.FirstStub.service;

import com.example.FirstStub.model.request.PaymentRequest;
import com.example.FirstStub.model.response.CheckAccountResponse;
import com.example.FirstStub.model.response.Contact;
import com.example.FirstStub.model.response.Debt;
import com.example.FirstStub.model.response.PaymentResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProcessingService {

    @Value("${stub.payment-delay-seconds:1}")
    private int paymentDelaySeconds;

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");


    // Проверка задолженностей по счету.

    public CheckAccountResponse checkAccount(String acc, int days) {

        // Если последняя цифра acc нечетная,
        // клиент считается VIP
        boolean vipClient = false;

        if (acc != null && !acc.isEmpty()) {

            char lastCharacter = acc.charAt(acc.length() - 1);

            if (Character.isDigit(lastCharacter)) {
                int lastDigit = Character.getNumericValue(lastCharacter);

                vipClient = lastDigit % 2 != 0;
            }
        }

        // INN = acc + "111"
        String inn = acc + "111";

        // Количество задолженностей = days
        List<Debt> debts = new ArrayList<>();

        for (int i = 0; i < days; i++) {

            if (i % 2 == 0) {
                debts.add(new Debt(1000, "parking"));
            } else {
                debts.add(new Debt(3000, "gkh"));
            }
        }

        return new CheckAccountResponse(
                acc,
                vipClient,
                false,
                inn,
                debts
        );
    }

     //Подтверждение платежа.
    public PaymentResponse payment(
            PaymentRequest request,
            String bankCode) {

        int digitsSum = bankCode
                .chars()
                .filter(Character::isDigit)
                .map(Character::getNumericValue)
                .sum();

        // Количество telecom должно быть равно сумме цифр BankCode
        List<String> telecom = new ArrayList<>();

        for (int i = 0; i < digitsSum; i++) {
            telecom.add("telecom" + (i + 1));
        }

        Contact contact = new Contact(
                "HL pay company",
                telecom
        );

        return new PaymentResponse(
                request.getTransactionId(),
                "2345678997",
                "accepted",
                List.of(contact)
        );
    }

   // Получение времени ответа.
    public String getProcessingTime() {

        return LocalDateTime.now()
                .format(DATE_FORMATTER);
    }


    /*
     * Удаление транзакции.
     * Задержка зависит от активного профиля:
     * default = 1 секунда
     * test1   = 2 секунды
     * test2   = 4 секунды
     */
    public String clearTransaction() throws InterruptedException {

        Thread.sleep(paymentDelaySeconds * 1000L);

        return "deleted success";
    }
}