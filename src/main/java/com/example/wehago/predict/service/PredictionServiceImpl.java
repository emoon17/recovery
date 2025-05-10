package com.example.wehago.predict.service;

import com.example.wehago.client.service.ClientService;
import com.example.wehago.predict.dto.*;
import com.example.wehago.predict.flaskclient.FlaskPredictionClient;
import com.example.wehago.predict.mapper.PredictionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import org.springframework.mail.SimpleMailMessage;
import java.util.List;

@Slf4j
@Service
public class PredictionServiceImpl implements PredictionService {

    private final PredictionMapper predictionMapper;
    private final FlaskPredictionClient flaskClient;
    private final JavaMailSender mailSender;
    private final ClientService clientService;

    public PredictionServiceImpl(PredictionMapper predictionMapper, FlaskPredictionClient flaskClient, JavaMailSender mailSender, ClientService clientService) {
        this.predictionMapper = predictionMapper;
        this.flaskClient = flaskClient;
        this.mailSender = mailSender;
        this.clientService = clientService;
    }

    @Override
    public void predictAndSaveDelays() {
        List<PredictionTargetDto> targets = predictionMapper.selectPredictionTargets();

        for (PredictionTargetDto dto : targets) {
            try {
                // 예측 모델 응답값
                PredictionResponseDto response = flaskClient.predict(dto);

                PredictionRiskEntity risk = PredictionRiskEntity.builder()
                        .clientId(dto.getClientId())
                        .txId(dto.getTxId())
                        .predictedDelay(response.getPredictedDelay())
                        .riskLevel(response.getRiskLevel())
                        .comment(response.getComment())
                        .build();
                predictionMapper.insertRiskPrediction(risk);

            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
    }



    @Override
    public void predictTrain() {
        try {
            flaskClient.train();
            log.info(" 모델 학습 요청 성공 (/ml/train)");
        } catch (Exception e) {
            log.error(" 모델 학습 요청 실패: " + e.getMessage(), e);
        }
    }

    @Override
    public List<PredictRiskTableResponse> getAllPredicts() {
        LocalDate yesterDate = LocalDate.now().minusDays(1);
        String yesterday = yesterDate.toString();
        return predictionMapper.getAllPredicts(yesterday);
    }

    @Override
    public void sendTestMail(List<PredictionRequestDto> requestDtos) {

        String email = "";

        for (PredictionRequestDto requestDto : requestDtos) {
            email = clientService.selectEmailByClientId(requestDto.getClientId());
            String subject = "[회수 요청 안내] " + requestDto.getTransactionDate() + " 거래 건";
            String body = buildMailBody(requestDto.getName(), requestDto.getTransactionDate());
            sendMail(email, subject, body);
        }
    }

    private String buildMailBody (String name, String transactionDate) {
        return String.format("""
                안녕하세요 %s 담당자님, Recovery 담당자 정은혜입니다.

                귀사와의 %s 일 거래 건에 대한 회수 기한이 임박하여 안내드립니다.
                기한 내 원활한 정산을 위해 빠른 회수 처리 부탁드립니다.

                궁금하신 점이나 조치가 필요한 사항이 있다면 언제든지 회신 주시기 바랍니다.

                감사합니다.

                - 담당자 정은혜 드림
                """, name, transactionDate);
    }

    private void sendMail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom("eunhea62@gmail.com"); // 발신자 주소 (구글 메일)
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
}
