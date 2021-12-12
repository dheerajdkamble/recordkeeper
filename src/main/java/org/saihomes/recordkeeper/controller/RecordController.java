package org.saihomes.recordkeeper.controller;

import org.saihomes.recordkeeper.exception.RecordNotFoundException;
import org.saihomes.recordkeeper.model.Record;
import org.saihomes.recordkeeper.service.IRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("saihomes")
public class RecordController {

    @Autowired
    IRecordService recordService;

    @GetMapping(value="/records")
    public ResponseEntity<List<Record>> getAllRecords() {
        List<Record> records = new ArrayList<>();
        return new ResponseEntity<List<Record>>(recordService.getRecords(), HttpStatus.OK);
    }

    @PostMapping(value="/record")
    public ResponseEntity<Void> addRecord(@RequestBody @Validated Record record, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();
        try {
            recordService.addRecord(record);
            headers.setLocation(builder.path("/record/{id}").buildAndExpand(record.getId()).toUri());
            return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
        } catch(RecordNotFoundException e) {
            return new ResponseEntity<Void>(headers, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value="/record")
    public ResponseEntity<Record> getRecord(@PathVariable @Validated Long id) {
        HttpHeaders headers = new HttpHeaders();
        try {
            return new ResponseEntity<Record>(recordService.getRecord(id), HttpStatus.FOUND);
        } catch(RecordNotFoundException e) {
            return new ResponseEntity<Record>(HttpStatus.NOT_FOUND);
        }
    }
}
