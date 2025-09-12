package ru.ntc.csir.squid.squidstatistic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ntc.csir.squid.squidstatistic.model.Domain;

public interface DomainRepository extends JpaRepository<Domain, Integer> {
}