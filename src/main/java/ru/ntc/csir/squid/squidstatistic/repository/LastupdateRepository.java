package ru.ntc.csir.squid.squidstatistic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ntc.csir.squid.squidstatistic.model.Lastupdate;

public interface LastupdateRepository extends JpaRepository<Lastupdate, Integer> {
}