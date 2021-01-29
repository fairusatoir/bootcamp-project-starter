package id.co.bni.otf.bootcamp.web.controller.stocks;

import id.co.bni.otf.bootcamp.service.ClosingPriceService;
import id.co.bni.otf.bootcamp.service.StocksService;
import id.co.bni.otf.bootcamp.service.dto.StocksDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@RestController
@Validated
@RequestMapping(value = "/stocks", produces = MediaType.APPLICATION_JSON_VALUE)
public class StocksController {

    private final StocksService stocksService;
    private final ClosingPriceService closingPriceService;

    public StocksController(StocksService stocksService, ClosingPriceService closingPriceService) {
        this.stocksService = stocksService;
        this.closingPriceService = closingPriceService;
    }

    @GetMapping("/")
    public ResponseEntity<Object> findAllPageable(
            Pageable pageable,
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Boolean active
    ) {
        return ResponseEntity.ok().body(stocksService.findAll(pageable, code, name, active));
    }

    @GetMapping("/lists")
    public ResponseEntity<Object> findAllList(
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Boolean active
    ) {
        return ResponseEntity.ok().body(stocksService.findAll(code, name, active));
    }

    @GetMapping("/{id}")
    public ResponseEntity <Object> findOne(@PathVariable Long id) {
        return ResponseEntity.ok().body(stocksService.findOne(id));
    }

    @PostMapping("/")
    public ResponseEntity <Object> create(@Valid @RequestBody StocksDTO stocksDTO) {
        return ResponseEntity.ok().body(stocksService.create(stocksDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity <Object> update(@PathVariable Long id, @Valid @RequestBody StocksDTO stocksDTO) {
        stocksDTO.setId(id);
        return ResponseEntity.ok().body(stocksService.update(stocksDTO));
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        stocksService.delete(id);
        return HttpStatus.NO_CONTENT;
    }

    @GetMapping("/profit/max")
    public ResponseEntity <Object> getProfitMaxByDate(
            @RequestParam(required = false) LocalDate start,
            @RequestParam(required = false) LocalDate end
    ) {
        try {
            return ResponseEntity.ok().body(closingPriceService.findMaxProfit(start,end));
        }catch (Exception e){
            return ResponseEntity.ok().body("Ada yang error nich! = "+e.toString());
        }
    }
}
