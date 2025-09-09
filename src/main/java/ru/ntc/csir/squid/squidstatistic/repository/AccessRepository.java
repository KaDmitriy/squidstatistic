package ru.ntc.csir.squid.squidstatistic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.query.Param;
import ru.ntc.csir.squid.squidstatistic.model.Access;

public interface AccessRepository extends JpaRepository<Access, Integer> {

    @NativeQuery(value = "select * from \"access\" a where id = (select max(id) from \"access\" where node_id = :nodeid)")
    Access getLastInNode(@Param("nodeid") Short nodeid);

}