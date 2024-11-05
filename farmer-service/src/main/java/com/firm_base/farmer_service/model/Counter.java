package com.firm_base.farmer_service.model;

import com.firm_base.farmer_service.model.dataType.CounterType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(schema = "farmer_v1", name = "voucher_counters")
public class Counter implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "counter_id")
    private Long id;

    @Version
    @Column(name = "counter_version")
    private Long version;

    @Enumerated(EnumType.STRING)
    @Column(name = "counter_type")
    private CounterType counterType;

    @Column(name = "counter_current")
    private Integer current;

    public Counter(CounterType counterType) {
        this.counterType = counterType;
        this.current = 10000;
    }

}
