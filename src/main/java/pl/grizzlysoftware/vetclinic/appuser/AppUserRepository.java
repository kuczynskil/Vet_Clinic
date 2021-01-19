package pl.grizzlysoftware.vetclinic.appuser;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    AppUser findByLoginID(String loginId);
}
