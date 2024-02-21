package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.infrastructure;

import erp.logisticassistant.gista.assetmanagement.shareddomain.cache.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository
        extends JpaRepository<User, String>,
                JpaSpecificationExecutor<User> {

    List<User> findByUsernameIn(List<String> usernameList);

    Optional<User> findByUsername(String username);

    Optional<User> findByUserIdAndEnabledTrue(String id);

    void deleteByUserId(String id);

    Optional<User> findByUserIdAndEnabledFalse(String id);

    Optional<User> findByUserIdAndInternalTrue(String id);

    Optional<User> findByUserIdAndExternalTrue(String id);
}
