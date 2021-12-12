package org.saihomes.recordkeeper.service;

import org.saihomes.recordkeeper.exception.RecordNotFoundException;
import org.saihomes.recordkeeper.model.Record;

import java.util.List;
import java.util.Optional;

public interface IRecordService {

    List<Record> getRecords();
    Record getRecord(long id) throws RecordNotFoundException;
    void addRecord(Record record) throws RecordNotFoundException;

}
