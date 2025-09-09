package ru.ntc.csir.squid.squidstatistic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ntc.csir.squid.squidstatistic.model.Access;

public interface AccessRepository extends JpaRepository<Access, Integer> {
}