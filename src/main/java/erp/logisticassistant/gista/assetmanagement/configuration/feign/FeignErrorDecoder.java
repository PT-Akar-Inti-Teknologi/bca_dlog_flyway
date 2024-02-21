package erp.logisticassistant.gista.assetmanagement.configuration.feign;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import erp.logisticassistant.gista.assetmanagement.shareddomain.commons.ApplicationProperties;
import erp.logisticassistant.gista.assetmanagement.shareddomain.constant.GlobalConstant;
import erp.logisticassistant.gista.assetmanagement.shareddomain.constant.GlobalMessage;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.dto.response.GlobalResponse;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.dto.response.exception.FeignClientException;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.dto.response.exception.GlobalException;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.enums.StatusCode;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Configuration
@Slf4j
public class FeignErrorDecoder extends FeignLogger implements ErrorDecoder {

    private final ApplicationProperties applicationProperties;
    private final ObjectMapper mapper;

    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            String requestUrl = response.request().url();

            throw new FeignClientException(requestUrl,
                                            response.status(),
                                            mapper.readValue(response.body().toString(), GlobalResponse.class));

        } catch (JsonProcessingException e) {
            log.error("failed to decode : " + e);
            throw new GlobalException(StatusCode.INTERNAL_SERVER_ERROR,
                    GlobalMessage.Core.ERROR_INTERNAL_SERVER.replaceAll(GlobalConstant.FORMAT_EMAIL_REPLACE, applicationProperties.getGista().getMail().getHelp()));
        }
    }
}