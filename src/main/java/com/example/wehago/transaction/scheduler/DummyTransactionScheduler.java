package com.example.wehago.transaction.scheduler;

import com.example.wehago.transaction.dto.TransactionEntity;
import com.example.wehago.transaction.mapper.TransactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class DummyTransactionScheduler {

    private final TransactionMapper transactionMapper;

    @Scheduled(cron = "0 30 23 * * *") // 매일 23:30 실행
//    @Scheduled(cron = "0 * * * * *")
    public void insertDummyTransactions() {
        Random random = new Random();
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


        for (int i = 0; i < 10; i++) {
            int randomClientId = random.nextInt(23) + 1;

            //  회수 지연일 설정: 70%는 LOW (0~6), 30%는 HIGH (7~15)
            int delay;
            if (random.nextDouble() < 0.7) {
                delay = random.nextInt(7);           // LOW
            } else {
                delay = 7 + random.nextInt(9);       // HIGH
            }

            int paymentDelay = random.nextInt(10) + 1; // 1~10일 전의 예상 회수일

            LocalDate expectedPaymentDate = today.minusDays(paymentDelay);
            LocalDate recoveredDate = expectedPaymentDate.plusDays(delay);
            LocalDate transactionDate = expectedPaymentDate.minusDays(5);
            long amount = (random.nextInt(90) + 10) * 10_000L; // 100,000 ~ 1,000,000

            TransactionEntity entity = TransactionEntity.builder()
                    .clientId(String.valueOf(randomClientId))
                    .transactionDate(transactionDate.format(formatter))
                    .transactionAmount(amount)
                    .expectedPaymentDate(expectedPaymentDate.format(formatter))
                    .recoveredDate(recoveredDate.format(formatter))
                    .recoveredAmount(amount)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            transactionMapper.insertTransaction(entity);
        }

        System.out.println(" Dummy HIGH/LOW 거래 10건 자동 생성 완료 (자정 실행)");
    }
}
