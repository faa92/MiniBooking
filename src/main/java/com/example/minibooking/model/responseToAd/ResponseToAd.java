package com.example.minibooking.model.responseToAd;

import com.example.minibooking.model.BaseEntity;
import com.example.minibooking.model.rentalAd.RentalAd;
import com.example.minibooking.model.tenant.Tenant;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "responsetoad")
@Getter
@Setter
@Accessors(chain = true)
public class ResponseToAd extends BaseEntity {

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "rentalad_id", nullable = false)
    private RentalAd rentalAd;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "date_from", columnDefinition = "DATE", nullable = false)
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//    @JsonDeserialize(using = LocalDateDeserializer.class)
//    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateFrom;


    @Column(name = "date_to", columnDefinition = "DATE", nullable = false)
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//    @JsonDeserialize(using = LocalDateDeserializer.class)
//    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateTo;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

}