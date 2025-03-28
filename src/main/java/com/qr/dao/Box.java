package com.qr.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Entity
@Table(name = "box")
public class Box {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "name", nullable = false)
    String name;
    @Column(name = "image", nullable = false, length = 100000)
    byte[] image;
}
