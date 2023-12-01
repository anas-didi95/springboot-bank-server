package com.anasdidi.bank.domain.customer;

import java.time.LocalDateTime;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "t_cust")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Customer {

  @Id
  @GenericGenerator(name = "generator", strategy = "guid")
  @GeneratedValue(generator = "generator")
  @Column(name = "id", nullable = false)
  private String id;

  @Column(name = "cust_no", length = 10, nullable = false)
  private String customerNo;

  @Column(name = "name", length = 200, nullable = false)
  private String name;

  @CreatedBy
  @Column(name = "created_by", length = 20, nullable = false)
  private String createdBy;

  @CreatedDate
  @Column(name = "created_dt", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private LocalDateTime createdDate;

  @LastModifiedBy
  @Column(name = "updated_by", length = 20, nullable = false)
  private String updatedBy;

  @LastModifiedDate
  @Column(name = "updated_dt", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private LocalDateTime updatedDate;

  @Version
  @Column(name = "version", nullable = false)
  private Integer version;
}
