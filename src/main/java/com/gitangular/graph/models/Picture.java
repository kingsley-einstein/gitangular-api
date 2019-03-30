package com.gitangular.graph.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Table(name = "pictures")
@Entity
public class Picture implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "format", nullable = false)
    private String mimeType;

    @Column(name = "content", columnDefinition = "mediumBlob", nullable = false)
    private String binaryContent;

    @Column(name = "identifier", nullable = true)
    private String name;

    public Picture(String mimeType, String binaryContent, String name) {
        this.mimeType = mimeType;
        this.binaryContent = binaryContent;
        this.name = name;
    }
}