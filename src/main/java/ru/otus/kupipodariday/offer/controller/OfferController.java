package ru.otus.kupipodariday.offer.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.kupipodariday.offer.dto.CreateOfferDto;
import ru.otus.kupipodariday.offer.dto.ManyOfferDto;
import ru.otus.kupipodariday.offer.dto.OfferShortDto;
import ru.otus.kupipodariday.offer.dto.OneOfferDto;
import ru.otus.kupipodariday.offer.service.OfferService;

import java.util.List;

@RestController
@RequestMapping("/offers")
@RequiredArgsConstructor
@Slf4j
public class OfferController {
    private final OfferService offerService;

    @GetMapping
    public List<ManyOfferDto> findAllOffers() {
        return offerService.findAll();
    }

    @GetMapping("/{id}")
    public OneOfferDto findOffer(@PathVariable long id) {
        return offerService.findById(id);
    }

    @PostMapping
    public OfferShortDto createOffer(@Valid @RequestBody CreateOfferDto createOfferDto) {
        return offerService.create(createOfferDto);
    }
}
