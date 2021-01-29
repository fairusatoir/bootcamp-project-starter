package id.co.bni.otf.bootcamp.service;

import id.co.bni.otf.bootcamp.entity.ClosingPrice;
import id.co.bni.otf.bootcamp.repository.ClosingPriceRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class ClosingPriceService {

    private final ClosingPriceRepository closingPriceRepository;

    public ClosingPriceService(ClosingPriceRepository closingPriceRepository) {
        this.closingPriceRepository = closingPriceRepository;
    }

    @Transactional(readOnly = true)
    public BigDecimal findMaxProfit(LocalDate start, LocalDate end) {
        BigDecimal maxProfit = BigDecimal.ZERO;
        List<ClosingPrice> closingPrices = closingPriceRepository.findByDateAfterAndDateBefore(start, end);

        for (int iterStart = 1; iterStart < closingPrices.size(); iterStart++) {
            if (closingPrices.get(iterStart+1).getPrice().compareTo(closingPrices.get(iterStart+1).getPrice()) == 1){
                maxProfit = closingPrices.get(iterStart+1).getPrice();
            }
        }
        return maxProfit;
    }
}
