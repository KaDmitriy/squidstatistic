package ru.ntc.csir.squid.squidstatistic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ntc.csir.squid.squidstatistic.model.Node;

public interface NodeRepository extends JpaRepository<Node, Integer> {
}