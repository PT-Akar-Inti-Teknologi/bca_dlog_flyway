package erp.logisticassistant.gista.assetmanagement.configuration.datasource;

import erp.logisticassistant.gista.assetmanagement.shareddomain.commons.ApplicationProperties;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.base.AuditRevisionEntity;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.base.AuditUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.envers.RevisionListener;

@Slf4j
@RequiredArgsConstructor
public class AuditRevisionListener implements RevisionListener {

    private final AuditUserService auditUserService;
    private final ApplicationProperties applicationProperties;

    @Override
    public void newRevision(Object revisionEntity) {
        AuditRevisionEntity auditRevisionEntity = (AuditRevisionEntity) revisionEntity;
        auditRevisionEntity.setUsername(auditUserService.getUsernameSigned(applicationProperties));
    }

}