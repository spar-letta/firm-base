package auth.entity;

import auth.entity.dataType.CounterType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(schema = "core", name = "counters")
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
