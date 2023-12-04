package com.anasdidi.bank.domain.customer;

import org.hibernate.annotations.GenericGenerator;

import com.anasdidi.bank.common.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "t_cust")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SuperBuilder
public class Customer extends BaseEntity {

  @Id
  @GenericGenerator(name = "generator", strategy = "guid")
  @GeneratedValue(generator = "generator")
  @Column(name = "id", nullable = false)
  private String id;

  @Column(name = "cust_no", length = 16, nullable = false)
  private String customerNo;

  @Column(name = "name", length = 200, nullable = false)
  private String name;
}
