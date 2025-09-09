package ru.ntc.csir.squid.squidstatistic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ntc.csir.squid.squidstatistic.model.HierarchyCode;

public interface HierarchyCodeRepository extends JpaRepository<HierarchyCode, Integer> {
}