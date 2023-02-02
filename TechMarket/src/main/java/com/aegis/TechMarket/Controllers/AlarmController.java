package com.aegis.TechMarket.Controllers;


import com.aegis.TechMarket.Entities.Alarm;

import com.aegis.TechMarket.Services.AlarmKeywordService;
import com.aegis.TechMarket.Services.AlarmService;
import com.aegis.TechMarket.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("api/alarms")
public class AlarmController {

    private final AlarmService alarmService;

    private final UserService userService;

    private final AlarmKeywordService alarmKeywordService;

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody Alarm alarm){
        return ResponseEntity.ok(alarmService.create(alarm));
    }

    @GetMapping()
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(alarmService.findAll());
    }

    @GetMapping("{userId}")
    public ResponseEntity<?> getAllByUser(@PathVariable long userId){
        var user = userService.findById(userId);
        return ResponseEntity.ok(alarmService.findAllByUser(user));
    }

    @DeleteMapping("{alarmId}")
    public ResponseEntity<?> deleteById(@PathVariable long alarmId){
        alarmService.deleteById(alarmId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<?> delete(@RequestBody Alarm alarm){
        alarmService.delete(alarm);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/keywords")
    public ResponseEntity<?> getKeywordsByAlarm(){
        return ResponseEntity.ok(alarmKeywordService.getKeywordsByAlarm(5));
    }

}
