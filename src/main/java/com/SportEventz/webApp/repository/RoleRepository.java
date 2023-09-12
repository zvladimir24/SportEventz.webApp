
package com.SportEventz.webApp.repository;

import com.SportEventz.webApp.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}


