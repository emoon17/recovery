package com.example.wehago.transaction.scheduler;

import com.example.wehago.client.dto.ClientEntity;
import com.example.wehago.client.dto.ClientResponseDto;
import com.example.wehago.client.mapper.ClientMapper;
import com.example.wehago.transaction.dto.TransactionEntity;
import com.example.wehago.transaction.mapper.TransactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class DummyTransactionScheduler {

    private final TransactionMapper transactionMapper;
    private final ClientMapper clientMapper;

    @Scheduled(cron = "0 30 23 * * *") // 매일 23:30 실행
//    @Scheduled(cron = "0 * * * * *")
    public void insertDummyTransactions() {
        Random random = new Random();
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (int i = 0; i < 20; i++) {
            int randomClientId = 24 + random.nextInt(21);

            // 지연일  (0~50일)
            int delayDays;
            double r = random.nextDouble();
            if (r < 0.5) delayDays = random.nextInt(5);         // LOW
            else if (r < 0.8) delayDays = 5 + random.nextInt(10); // MEDIUM
            else delayDays = 15 + random.nextInt(35);           // HIGH

            // 회수율 (0%, 30%, 50%, 70%, 100%)
            int[] recoveryRates = {0, 30, 50, 70, 100};
            int recoveryRate = recoveryRates[random.nextInt(recoveryRates.length)];

            // 날짜 계산
//            LocalDate expectedPaymentDate = today;
            LocalDate transactionDate = today.minusDays(random.nextInt(30) + 1);  // 최근 30일 이내 거래일
            ClientEntity clientEntity  = clientMapper.findById(randomClientId);
//            LocalDate expectedPaymentDate = transactionDate.plusDays(delayDays); // 지연일 기준 예정일
            LocalDate recoveredDate = null;
//            // 회수 예정일 = 거래일 + 회수일수
            LocalDate expectedPaymentDate = transactionDate.plusDays(clientEntity.getExpectedRecoveryDays());
            if (recoveryRate != 0) {
                LocalDate calculatedDate = expectedPaymentDate.plusDays(delayDays);
                recoveredDate = calculatedDate.isAfter(today)
                        ? today.minusDays(random.nextInt(3)) // 오늘보다 미래면 최대 2일 전으로 조정
                        : calculatedDate;
            }

            // 금액 (1,000,000 ~ 60,000,000원 사이)
            long amount = 1_000_000L + random.nextInt(59_000_001); // 1백만 + 0 ~ 59백만
            long recoveredAmount = (long) (amount * recoveryRate / 100.0);

            TransactionEntity entity = TransactionEntity.builder()
                    .clientId(String.valueOf(randomClientId))
                    .transactionDate(transactionDate.format(formatter))
                    .transactionAmount(amount)
                    .expectedPaymentDate(String.valueOf(expectedPaymentDate))
                    .recoveredDate(recoveredDate != null ? recoveredDate.format(formatter) : null)
                    .recoveredAmount(recoveredAmount)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            transactionMapper.insertTransaction(entity);
        }

        System.out.println(" Dummy HIGH/LOW 거래 20건 자동 생성 완료 (자정 실행)");
    }
}
