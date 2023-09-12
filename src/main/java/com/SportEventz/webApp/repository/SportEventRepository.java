package com.SportEventz.webApp.repository;

import com.SportEventz.webApp.models.SportEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SportEventRepository extends JpaRepository<SportEvent, Long> {
}
