package shop.hooking.hooking.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Entity
@Table(name="card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String text;

    @Column(name = "scrap_cnt", columnDefinition = "int default 0")
    private Integer scrapCnt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="brand_id")
    private Brand brand;

    @Builder
    public Card(String text, Integer scrapCnt, Brand brand) {
        this.text = text;
        this.scrapCnt = scrapCnt;
        this.brand = brand;
    }

}
