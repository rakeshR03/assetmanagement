package com.example.assetmanagement.dtos;

import com.example.assetmanagement.models.Assignment_status;
import com.example.assetmanagement.models.Category;

import javax.persistence.ManyToOne;
import java.util.Date;


public class AssetDTO {
    private String name;
    private Date purchaseDate;
    private String conditionNote;
    private String assignment_status;

    @ManyToOne
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getConditionNote() {
        return conditionNote;
    }

    public void setConditionNote(String conditionNote) {
        this.conditionNote = conditionNote;
    }

    public String getAssignment_status() {
        return assignment_status;
    }

    public void setAssignment_status(String assignment_status) {
        this.assignment_status = assignment_status;
    }
}
