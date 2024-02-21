package erp.logisticassistant.gista.assetmanagement.configuration.datasource.flyway;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
@DependsOn("flywayPopulate")
@RequiredArgsConstructor
public class FlywayListener {
    @PostConstruct
    public void syncFlywayRecordUpdated() {
        /**populate data menu*/
    }
}
