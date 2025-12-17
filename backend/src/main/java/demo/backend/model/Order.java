package demo.backend.model;

import java.sql.Timestamp;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "orders", schema = "demo")
public class Order {

     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     @Column(name = "id")
     private UUID id;

     @JdbcTypeCode(SqlTypes.ARRAY)
     @Column(name = "order_item", columnDefinition = "text[]")
     private String[] orderItem; 

     @Column(name = "order_status")
     private String orderStatus;

     @Column(name = "payment_total")
     private Long paymentTotal;

     @Column(name = "order_detail")
     private String orderDetail;

     @CreationTimestamp
     @Column(name = "created_at")
     private Timestamp createdAt;

     @ManyToOne(cascade = {CascadeType.ALL})
     @JoinColumn(name = "customer_id")
     private User customerId;

}
