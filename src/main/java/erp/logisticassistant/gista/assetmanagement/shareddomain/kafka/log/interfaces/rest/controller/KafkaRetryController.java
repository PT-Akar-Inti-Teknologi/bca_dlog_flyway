package erp.logisticassistant.gista.assetmanagement.shareddomain.kafka.log.interfaces.rest.controller;

import erp.logisticassistant.gista.assetmanagement.shareddomain.constant.GlobalConstant;
import erp.logisticassistant.gista.assetmanagement.shareddomain.kafka.log.application.internal.command.ImplInternalCommandKafkaLogService;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.dto.response.GlobalResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/kafka-retry")
@Tag(name = GlobalConstant.DOCUMENTATION_GROUP_KAFKALOG)
@SecurityRequirement(name = GlobalConstant.TAG_AUTHENTICATION_HEADER)
public class KafkaRetryController{

    private final ImplInternalCommandKafkaLogService commandKafkaLogService;
    
    @Operation(summary = "execute")
    @PutMapping(value = "/{kafka_log_id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity processRetry(@PathVariable(required = true, value = "kafka_log_id") Long logId){
        GlobalResponse response = commandKafkaLogService.doRetry(logId);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "produce all messages")
    @PutMapping(value = "/produce-all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity processProduceAll(){
        GlobalResponse response = commandKafkaLogService.processProduceAll();
        return ResponseEntity.ok(response);
    }
}
