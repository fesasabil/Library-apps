package com.sinaukoding.perpustakaan.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinaukoding.perpustakaan.common.RestResult;
import com.sinaukoding.perpustakaan.common.StatusCode;
import com.sinaukoding.perpustakaan.entity.Loan;
import com.sinaukoding.perpustakaan.service.LoanService;
import com.sinaukoding.perpustakaan.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("loans")
public class LoanController extends BaseController {

    @Autowired
    private LoanService loanService;

//    @PreAuthorize("permitAll()")
    @GetMapping
    public RestResult find(@RequestParam(value = "param", required = false) String param,
                           @RequestParam(value = "offset") int offset,
                           @RequestParam(value = "limit") int limit) throws JsonProcessingException {

        Loan loan = param != null ? new ObjectMapper().readValue(param, Loan.class) : new Loan();

        Long rows = loanService.count(loan);

        return new RestResult(rows > 0 ? loanService.find(loan, offset, limit) : new ArrayList<>(), rows);
    }

    @GetMapping(value = "by-date")
    public RestResult findByDate(@RequestParam(value = "param", required = false) String param,
                                 @RequestParam(value = "start-date") String startDate,
                                 @RequestParam(value = "end-date") String endDate) throws JsonProcessingException {

        RestResult result = new RestResult(StatusCode.OPERATION_FAILED);

        Loan loan = param != null ? new ObjectMapper().readValue(param, Loan.class) : new Loan();

        result.setData(loanService.findByDate(loan, DateUtils.fromString(startDate), DateUtils.fromString(endDate)));
        result.setRows((long) loanService.findByDate(loan, DateUtils.fromString(startDate), DateUtils.fromString(endDate)).size());

        return result;
    }

    @PostMapping
    public RestResult save(@RequestBody Loan entity) {
        RestResult result = new RestResult(StatusCode.OPERATION_FAILED);

        if (entity != null) {
            result.setData(loanService.save(entity));
            result.setStatus(StatusCode.SAVE_SUCCESS);
        }

        return result;
    }

    @PutMapping
    public RestResult update(@RequestBody Loan entity) {
        RestResult result = new RestResult(StatusCode.OPERATION_FAILED);

        if (entity != null) {
            result.setData(loanService.update(entity));
            result.setStatus(loanService.update(entity) != null ? StatusCode.UPDATE_SUCCESS : StatusCode.UPDATE_FAILED);
        }

        return result;
    }

    @DeleteMapping(value = "{id}")
    public RestResult delete(@PathVariable Long id) {
        return new RestResult(loanService.delete(id) ? StatusCode.DELETE_SUCCESS : StatusCode.DELETE_FAILED);
    }
}
