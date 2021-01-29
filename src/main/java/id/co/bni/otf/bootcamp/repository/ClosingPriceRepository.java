package id.co.bni.otf.bootcamp.repository;

import id.co.bni.otf.bootcamp.entity.ClosingPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ClosingPriceRepository extends JpaRepository<ClosingPrice,Long>, QuerydslPredicateExecutor<ClosingPrice> {

    List<ClosingPrice> findByDateAfterAndDateBefore(LocalDate localDateStrat, LocalDate localDateEnd);
}
