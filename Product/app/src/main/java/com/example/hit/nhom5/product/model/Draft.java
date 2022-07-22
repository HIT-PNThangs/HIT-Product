package com.example.hit.nhom5.product.model;

public class Draft {
    private int resoureId;
    private String typeDraft;
    private String nameDraft;
    private String addressDraft;
    private String priceDraft;

    public Draft(int resoureId, String typeDraft, String nameDraft, String addressDraft, String priceDraft) {
        this.resoureId = resoureId;
        this.typeDraft = typeDraft;
        this.nameDraft = nameDraft;
        this.addressDraft = addressDraft;
        this.priceDraft = priceDraft;
    }

    public int getResoureId() {
        return resoureId;
    }

    public void setResoureId(int resoureId) {
        this.resoureId = resoureId;
    }

    public String getTypeDraft() {
        return typeDraft;
    }

    public void setTypeDraft(String typeDraft) {
        this.typeDraft = typeDraft;
    }

    public String getNameDraft() {
        return nameDraft;
    }

    public void setNameDraft(String nameDraft) {
        this.nameDraft = nameDraft;
    }

    public String getAddressDraft() {
        return addressDraft;
    }

    public void setAddressDraft(String addressDraft) {
        this.addressDraft = addressDraft;
    }

    public String getPriceDraft() {
        return priceDraft;
    }

    public void setPriceDraft(String priceDraft) {
        this.priceDraft = priceDraft;
    }
}
