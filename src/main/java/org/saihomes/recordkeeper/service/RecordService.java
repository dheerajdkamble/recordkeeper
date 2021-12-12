package org.saihomes.recordkeeper.service;

import org.saihomes.recordkeeper.exception.RecordNotFoundException;
import org.saihomes.recordkeeper.model.Record;
import org.saihomes.recordkeeper.repository.IRecordRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecordService implements IRecordService {

    @Autowired
    private IRecordRepository recordRepository;

    @Override
    public List<Record> getRecords() {
        List<Record> list = new ArrayList<>();
        recordRepository.findAll().forEach(e -> list.add(e));
        return list;
    }

    @Override
    public Record getRecord(long id) throws RecordNotFoundException {
        Optional<Record> record = recordRepository.findById(id);
        if(record.isPresent()) {
            return record.get();
        } else {
            throw new RecordNotFoundException(String.format("No record exist for %s", id));
        }
    }

    @Override
    public void addRecord(Record record) throws RecordNotFoundException
    {
        Optional<Record> recordEntity = recordRepository.findById(record.getId());
        if(recordEntity.isPresent())
            BeanUtils.copyProperties(record, recordEntity.get());
        else
            recordRepository.save(record);
    }

    public void deleteRecordById(Long id) throws RecordNotFoundException {
        Optional<Record> employee = recordRepository.findById(id);
        if(employee.isPresent())
            recordRepository.deleteById(id);
        else
            throw new RecordNotFoundException(String.format("No Record exist for given id : %s", id));
    }
}
