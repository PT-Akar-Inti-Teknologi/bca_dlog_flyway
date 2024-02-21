package erp.logisticassistant.gista.assetmanagement.configuration.feign;

import feign.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
@Slf4j
public class FeignLogger extends Logger {

        @Override
        protected void log(String configKey, String format, Object... objects) {
            log.info(String.format(format, objects));
        }
}
