package erp.logisticassistant.gista.assetmanagement.configuration.timezone;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.time.ZoneId;
import java.util.TimeZone;

@Configuration
public class LocaleConfig {
    @PostConstruct
    public void initTimezone() {
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.of("Asia/Jakarta")));
    }
}