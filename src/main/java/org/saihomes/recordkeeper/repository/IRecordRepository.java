package org.saihomes.recordkeeper.repository;

import org.saihomes.recordkeeper.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRecordRepository extends JpaRepository<Record, Long> {
}
